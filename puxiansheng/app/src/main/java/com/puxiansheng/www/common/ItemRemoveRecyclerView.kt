package com.puxiansheng.www.common

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.Scroller
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.www.R

class ItemRemoveRecyclerView @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(mContext, attrs, defStyle) {
    private var mLastX = 0
    private var mLastY = 0

    //当前触摸的item的位置
    //item对应的布局
    private var mItemLayout: LinearLayout? = null

    //删除按钮
    private var mDelete: TextView? = null
    private val mReaded: TextView? = null

    //消息提示
    private val mTextView: TextView? = null
    private var curSelectPosition = 0
    private val isFirst = true

    //最大滑动距离(即删除按钮的宽度)
    private var mMaxLength = 0

    //是否在垂直滑动列表
    private var isDragging = false

    //item是在否跟随手指移动
    private var isItemMoving = false

    //item是否开始自动滑动
    private var isStartScroll = false

    //删除按钮状态   0：关闭 1：将要关闭 2：将要打开 3：打开
    private var mDeleteBtnState = 0

    //检测手指在滑动过程中的速度
    private val mVelocityTracker: VelocityTracker
    private val mScroller: Scroller
    private var mListener: OnItemClickListener? = null
    override fun onTouchEvent(e: MotionEvent): Boolean {
        mVelocityTracker.addMovement(e)
        val x = e.x.toInt()
        val y = e.y.toInt()
        when (e.action) {
            MotionEvent.ACTION_DOWN -> if (mDeleteBtnState == 0) {
                val view = findChildViewUnder(x.toFloat(), y.toFloat()) ?: return false
                val viewHolder = getChildViewHolder(view) as RemoveViewHolder
                mItemLayout =
                    viewHolder.getView<View>(R.id.item_layout) as LinearLayout
                curSelectPosition = viewHolder.adapterPosition
                if (null != mItemLayout) //                        mReaded = (TextView) mItemLayout.findViewById(R.id.item_readed);
                    mDelete =
                        mItemLayout!!.findViewById<View>(R.id.item_delete) as TextView
                mMaxLength = mDelete!!.width
                //                    mReaded.setOnClickListener(new OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            mTextView.setVisibility(View.GONE);
//                            mListener.onReadClick(mPosition);
//                            mItemLayout.scrollTo(0, 0);
//                            mDeleteBtnState = 0;
//                        }
//                    });
                mDelete!!.setOnClickListener {
                    mListener!!.onDeleteClick(curSelectPosition)
                    mItemLayout!!.scrollTo(0, 0)
                    mDeleteBtnState = 0
                }
            } else if (mDeleteBtnState == 3) {
                mScroller.startScroll(mItemLayout!!.scrollX, 0, -mMaxLength, 0, 200)
                invalidate()
                mDeleteBtnState = 0
                return false
            } else {
                return false
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = mLastX - x
                val dy = mLastY - y
                val scrollX = mItemLayout!!.scrollX
                if (Math.abs(dx) > Math.abs(dy)) { //左边界检测
                    isItemMoving = true
                    if (scrollX + dx <= 0) {
                        mItemLayout!!.scrollTo(0, 0)
                        return true
                    } else if (scrollX + dx >= mMaxLength) { //右边界检测
                        mItemLayout!!.scrollTo(mMaxLength, 0)
                        return true
                    }
                    mItemLayout!!.scrollBy(dx, 0) //item跟随手指滑动
                }
            }
            MotionEvent.ACTION_UP -> {
                if (!isItemMoving && !isDragging && mListener != null) {
                    if (mTextView != null) mTextView.visibility = View.GONE
                    mListener!!.onItemClick(mItemLayout, curSelectPosition)
                }
                isItemMoving = false
                mVelocityTracker.computeCurrentVelocity(1000) //计算手指滑动的速度
                val xVelocity = mVelocityTracker.xVelocity //水平方向速度（向左为负）
                val yVelocity = mVelocityTracker.yVelocity //垂直方向速度
                var deltaX = 0
                val upScrollX = mItemLayout!!.scrollX
                if (Math.abs(xVelocity) > 100 && Math.abs(xVelocity) > Math.abs(
                        yVelocity
                    )
                ) {
                    if (xVelocity <= -100) { //左滑速度大于100，则删除按钮显示
                        deltaX = mMaxLength - upScrollX
                        mDeleteBtnState = 2
                    } else if (xVelocity > 100) { //右滑速度大于100，则删除按钮隐藏
                        deltaX = -upScrollX
                        mDeleteBtnState = 1
                    }
                } else {
                    if (upScrollX >= mMaxLength / 2) { //item的左滑动距离大于删除按钮宽度的一半，则则显示删除按钮
                        deltaX = mMaxLength - upScrollX
                        mDeleteBtnState = 2
                    } else if (upScrollX < mMaxLength / 2) { //否则隐藏
                        deltaX = -upScrollX
                        mDeleteBtnState = 1
                    }
                }

                //item自动滑动到指定位置
                mScroller.startScroll(upScrollX, 0, deltaX, 0, 200)
                isStartScroll = true
                invalidate()
                mVelocityTracker.clear()
            }
        }
        mLastX = x
        mLastY = y
        return super.onTouchEvent(e)
    }

    override fun computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mItemLayout!!.scrollTo(mScroller.currX, mScroller.currY)
            invalidate()
        } else if (isStartScroll) {
            isStartScroll = false
            if (mDeleteBtnState == 1) {
                mDeleteBtnState = 0
            }
            if (mDeleteBtnState == 2) {
                mDeleteBtnState = 3
            }
        }
    }

    override fun onDetachedFromWindow() {
        mVelocityTracker.recycle()
        super.onDetachedFromWindow()
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        isDragging = state == SCROLL_STATE_DRAGGING
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    init {
        mScroller = Scroller(mContext, LinearInterpolator())
        mVelocityTracker = VelocityTracker.obtain()
    }
}