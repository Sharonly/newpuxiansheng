package com.puxiansheng.www.common

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.puxiansheng.logic.bean.MarqueeInfo
import com.puxiansheng.www.R
import java.util.*
import kotlin.collections.ArrayList

class TextSwitchView : TextSwitcher, ViewSwitcher.ViewFactory,View.OnClickListener ,LifecycleEventObserver{
    private var index = -1
    private var infos = ArrayList<MarqueeInfo>()
    var itemClickListener: OnItemClickListener? = null
    private var timer: Timer? = null
    private var druation:Long=3*1000L

    constructor(context: Context) : super(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    private fun init() {
        //TODO 2020/6/8
        setOnClickListener(this)

        if (timer == null) timer = Timer()
        setFactory(this)
        this.inAnimation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_in
        )
        this.outAnimation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_out
        )
    }

    override fun onClick(v: View) {
        if (itemClickListener != null && infos?.size!! > 0 && index != -1) itemClickListener!!.onItemClick(index)
    }


    fun setResources(infos: ArrayList<MarqueeInfo>) {
        if(infos.isNotEmpty()) {
            this.infos = infos
            startTimer()
        }
    }

    fun clearResources(){
        if(infos.isNotEmpty()) {
            this.infos?.clear()
            stopTimer()
        }
    }


    fun startTimer(){
       if (timer == null) {
            timer = Timer()
        }
        timer?.schedule(MyTask(),0,druation)
       // timer!!.scheduleAtFixedRate(MyTask(), 1, duration) //每3秒更新
    }

    fun stopTimer(){
        timer?.cancel()
        timer=null
    }


    private inner class MyTask : TimerTask() {
        override fun run() {
            postDelayed(object :Runnable{
                override fun run() {
                  infos?.let {
                      index = next() //取得下标值
//                      println("跑马灯run-->${index}")
                      updateText() //更新TextSwitcherd显示内容;
                  }
                }
            },0)
        }
    }

    private operator fun next(): Int {

        var flag = index + 1
        if (flag > infos!!.size - 1) {
            flag -= infos!!.size
        }
        return flag
    }

    private fun updateText() {
        if(infos?.isNotEmpty()) {
            setText(infos!![index].title)
        }
    }

    override fun makeView(): View {
        return TextView(context)
    }


    /**
     * 设置点击事件监听
     *
     * @param
     */
//    public fun setOnItemClickListener(OnItemClickListener: (item: MarqueeInfo) -> Unit) {}


    /**
     * 轮播文本点击监听器
     */
    interface OnItemClickListener {
        /**
         * 点击回调
         *
         * @param position 当前点击ID
         */
        fun onItemClick(position :Int)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
         when(event){
             Lifecycle.Event.ON_PAUSE->{
//                 println("跑马灯-->onpause")
                 stopTimer()
             }

             Lifecycle.Event.ON_RESUME->{
//                 println("跑马灯-->onResume")
                 startTimer()
             }
         }
    }
}