package com.puxiansheng.www.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import java.util.*

class ScrollTextView:AppCompatTextView{
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private var mText = ""
    private var mOffsetX = 0
    private val mRect: Rect
    private var mTimer: Timer?
    private var mTimerTask: TimerTask?

    /**
     * 速度，负数左移，正数右移。
     */
    private var mSpeed = -10

    private inner class MyTimerTask : TimerTask() {
        override fun run() {
            //如果View能容下所有文字，直接返回
            if (mRect.right < width) {
                return
            }
            if (mOffsetX < -mRect.right - paddingEnd) {
                //左移时的情况
                mOffsetX = paddingStart
            } else if (mOffsetX > paddingStart) {
                //右移时的情况
                mOffsetX = -mRect.right
            }
            mOffsetX += mSpeed
            postInvalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        //此处去掉了super.onDraw(Canvas canvas);
        mText = text.toString()
        val textPaint = paint
        textPaint.color = currentTextColor
        //获取文本区域大小，保存在mRect中。
        textPaint.getTextBounds(mText, 0, mText.length, mRect)
        val mTextCenterVerticalToBaseLine =
            (-textPaint.ascent() + textPaint.descent()) / 2 - textPaint.descent()
        if (mRect.right < width) {
            canvas.drawText(mText, 0f, height / 2 + mTextCenterVerticalToBaseLine, textPaint)
        } else {
            canvas.drawText(
                mText,
                mOffsetX.toFloat(),
                height / 2 + mTextCenterVerticalToBaseLine,
                textPaint
            )
        }
    }

    /**
     * 视图移除时销毁任务和定时器
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.e(TAG, "killTimer")
        if (mTimerTask != null) {
            mTimerTask!!.cancel()
            mTimerTask = null
        }
        if (mTimer != null) {
            mTimer!!.cancel()
            mTimer = null
        }
    }

    fun setSpeed(speed: Int) {
        mSpeed = speed
    }

    companion object {
        private const val TAG = "ScrollTextView"
        private const val PFS = 24
    }

    init {
        mRect = Rect()
        mTimer = Timer()
        mTimerTask = MyTimerTask()
        //更新帧率24
        mTimer!!.schedule(mTimerTask, 0, 1000 / PFS.toLong())
    }
}