package com.puxiansheng.www.common.player

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.VideoView

class MyVideoView(context: Context?, attrs: AttributeSet?) : VideoView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


        val widthX = View.getDefaultSize(0, widthMeasureSpec) //得到默认的大小（0，宽度测量规范）
        val height = View.getDefaultSize(0, heightMeasureSpec) //得到默认的大小（0，高度度测量规范）


        println("videoview宽度--->${width}--->${widthX}--->${minimumWidth}--->${measuredWidth}")
        setMeasuredDimension(width, height) //设置测量尺寸,将高和宽放进去
    }
}