package com.puxiansheng.www.common

import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewSwitcher
import com.puxiansheng.logic.bean.MarqueeInfo
import com.puxiansheng.www.R
import java.util.*

class TextSwitchView : TextSwitcher, ViewSwitcher.ViewFactory,View.OnClickListener {
    private var index = -1
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                1 -> {
                    index = next() //取得下标值
                    updateText() //更新TextSwitcherd显示内容;
                }
            }
        }
    }
    private var infos: List<MarqueeInfo>? = null
    private var itemClickListener: OnItemClickListener? = null
    private var timer: Timer? = null

    constructor(context: Context) : super(context, null) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }


    private fun init() {
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
//
//    fun onImageClick(onImageClick: (info: MarqueeInfo) -> Unit) {
//        this.setOnClickListener(onImageClick( ))
//    }

    fun setResources(infos: List<MarqueeInfo>?) {
        this.infos = infos
    }

    fun setTextStillTime(time: Long) {
        if (timer == null) {
            timer = Timer()
        } else {
            timer!!.scheduleAtFixedRate(MyTask(), 1, time) //每3秒更新
        }
    }


    private inner class MyTask : TimerTask() {
        override fun run() {
            mHandler.sendEmptyMessage(1)
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
        setText(infos!![index].title)
    }

    override fun makeView(): View {
        return TextView(context)
    }

    /**
     * 设置点击事件监听
     *
     * @param
     */
    public fun setOnItemClickListener(OnItemClickListener: (item: MarqueeInfo) -> Unit) {}


    /**
     * 轮播文本点击监听器
     */
    public interface OnItemClickListener {
        /**
         * 点击回调
         *
         * @param position 当前点击ID
         */
        fun onItemClick(position :Int)
    }
}