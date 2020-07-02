package com.puxiansheng.www.common

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.databinding.ImageSwitcherBinding
import com.tencent.map.tools.internal.v
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlin.math.sqrt


class ShopImageSwitcher : FrameLayout {
    private var images: List<BannerImage> = listOf()
    private var binding: ImageSwitcherBinding
    private var position = 0
    private var shouldLoop = true
    private var matri = Matrix()
    private var savedMatrix = Matrix()

    //    // 不同状态的表示：
    private val NONE = 0
    private val DRAG = 1
    private val ZOOM = 2
    private var mode = NONE
    private var isMove = false

    // 定义第一个按下的点，两只接触点的重点，以及出事的两指按下的距离：
    private var startPoint = PointF()
    private var midPoint = PointF()
    private var oriDis = 1f

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        binding = ImageSwitcherBinding.inflate(LayoutInflater.from(context), this, true).apply {
            imagePager.adapter = ImagePagerAdapter()
            imagePager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    this@ShopImageSwitcher.position = position
                    setCurrentPos(position)
                    listener?.onScrolled(position)
                }
            })
        }
    }

    fun setImages(images: List<BannerImage>?) {
        images?.let {
            this.images = it
            position = 0
            binding.imagePager.adapter?.notifyDataSetChanged()
        }
    }

    fun setCurrentPos(pos: Int) {
        position = pos
    }

    fun getCurrentPos(): Int {
        if (images.isNotEmpty()) {
            return position + 1
        } else {
            return 1
        }
    }


    @ExperimentalCoroutinesApi
    fun loop(ticker: ReceiveChannel<Unit>, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
//            ticker.consumeEach {
//                binding.imagePager.setCurrentItem(position, true)
//                position += 1
//                if (position == images.size) position = 0
//            }
        }
    }

    fun onImageClick(onImageClick: (image: BannerImage) -> Unit) {
        (binding.imagePager.adapter as ImagePagerAdapter).setOnImageClick(onImageClick)
    }


    inner class ImagePagerAdapter : PagerAdapter() {
        private var onImageClick: ((image: BannerImage) -> Unit)? = null

        override fun isViewFromObject(view: View, `object`: Any): Boolean = `object` == view

        override fun getCount(): Int = images.size

        @SuppressLint("ClickableViewAccessibility")
        override fun instantiateItem(container: ViewGroup, position: Int): Any =
            ImageView(context).apply {
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            }.let {
                it.urlBg(images[position].imageUrl)
                it.setOnClickListener {
                    onImageClick?.let {
                        it(images[position])
                    }
                }
                it.setOnTouchListener { v, event ->
                    val view = v as ImageView
                    when (event.actionMasked) {
                        MotionEvent.ACTION_DOWN -> {
                            matri.set(view.imageMatrix)
                            savedMatrix.set(matri)
                            startPoint.set(event.x, event.y)
                            mode = DRAG
                        }
                        MotionEvent.ACTION_POINTER_DOWN -> {
                            oriDis = distance(event)
                            if (oriDis > 10f) {
                                savedMatrix.set(matri)
                                midPoint = middle(event)
                                mode = ZOOM
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                        }
                        MotionEvent.ACTION_POINTER_UP -> {
                            mode = NONE
                        }
                        MotionEvent.ACTION_MOVE -> {
                            if (mode == DRAG) {
                                // 是一个手指拖动
                                matri.set(savedMatrix)
                                matri.postTranslate(event.x - startPoint.x, event.y - startPoint.y)
                                isMove = event.x - startPoint.x > 20f
                            } else if (mode == ZOOM) {
                                it.scaleType = ImageView.ScaleType.MATRIX
                                // 两个手指滑动
                                var newDist = distance(event)
                                if (newDist > 10f) {
                                    matri.set(savedMatrix)
                                    var scale = newDist / oriDis
                                    matri.postScale(scale, scale, midPoint.x, midPoint.y)
                                    isMove = true
                                }
                            }
                        }
                    }
                    v.imageMatrix = matri
                    return@setOnTouchListener isMove
                }
                container.addView(it)
                it
            }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
            container.removeView(
                `object` as View?
            )

        fun setOnImageClick(onImageClick: (image: BannerImage) -> Unit) {
            this.onImageClick = onImageClick
        }


    }

    var listener: OnPageChange? = null

    // 计算两个触摸点之间的距离
    fun distance(event: MotionEvent): Float {
        try {
            var x = event.getX(0) - event.getX(1)
            var y = event.getY(0) - event.getY(1)
            return sqrt(x * x + y * y)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
        return 0f
    }

    // 计算两个触摸点的中点
    fun middle(event: MotionEvent): PointF {
        var x = event.getX(0) + event.getX(1)
        var y = event.getY(0) + event.getY(1)
        return PointF(x / 2, y / 2)
    }


    interface OnPageChange {
        fun onScrolled(index: Int)
    }
}