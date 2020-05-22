package com.puxiansheng.www.common

import android.content.Context
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class MarqueeText @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    override fun isFocused(): Boolean {
        return true
    }

    override fun onFocusChanged(
        focused: Boolean,
        direction: Int,
        previouslyFocusedRect: Rect?
    ) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect)
        }
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        if (hasWindowFocus) super.onWindowFocusChanged(hasWindowFocus)
    }

    init {
        //设置单行
        setSingleLine()
        //设置Ellipsize
        ellipsize = TextUtils.TruncateAt.MARQUEE
        //获取焦点
        isFocusable = true
        //走马灯的重复次数，-1代表无限重复
        marqueeRepeatLimit = 1
        //强制获得焦点
        isFocusableInTouchMode = true
    }
}