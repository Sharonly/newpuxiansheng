package com.puxiansheng.www.common

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.core.view.get
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.ImageSwitcherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class ImageSwitcher : FrameLayout {
    private var images: List<BannerImage> = listOf()
    private var binding: ImageSwitcherBinding
    private var position = 0
    private var shouldLoop = true

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
                    this@ImageSwitcher.position = position
                    setCurrentPos(position)
                    if (bannerIndex.visibility == View.VISIBLE) {
                        var bt: RadioButton = bannerIndex.getChildAt(position) as RadioButton
                        bt.isChecked = true
                    }
                    //2020/06/08 梁汉松
                    listener?.onScrolled(position)
                }
            })
        }
    }

    fun setImages(images: List<BannerImage>?, show: Boolean) {
        images?.let {
            this.images = it
            position = 0
            binding.imagePager.adapter?.notifyDataSetChanged()
            Log.d("imageswitcher", " isshow = " + show)
            if (show && images.size > 1) {
                binding.bannerIndex.visibility = View.VISIBLE
                binding.bannerIndex.removeAllViews()
                for (i in images.indices) {
                    var tempButton = RadioButton(context)
                    tempButton.setButtonDrawable(R.drawable.bg_index_bt) // 设置按钮的样式
                    tempButton.setPadding(10, 0, 10, 0) // 设置文字距离按钮四周的距离
                    binding.bannerIndex.addView(
                        tempButton,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                var bt: RadioButton = binding.bannerIndex.getChildAt(0) as RadioButton
                bt.isChecked = true
            } else {
                binding.bannerIndex.visibility = View.GONE
            }
        }
    }


    fun setCurrentPos(pos: Int) {
        position = pos
    }

    fun getCurrentPos(): Int {
        return if (images.isNotEmpty()) {
            position + 1
        } else {
            1
        }
    }


    @ExperimentalCoroutinesApi
    fun loop(ticker: ReceiveChannel<Unit>, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            ticker.consumeEach {
                if (images.size > 1) {
                    binding.imagePager.setCurrentItem(position, true)
                    position += 1
                    if (position == images.size) position = 0
                } else {
                    binding.imagePager.setCurrentItem(position, false)
                }
            }
        }
    }

    fun onImageClick(onImageClick: (image: BannerImage) -> Unit) {
        (binding.imagePager.adapter as ImagePagerAdapter).setOnImageClick(onImageClick)
    }


    inner class ImagePagerAdapter : PagerAdapter() {
        private var onImageClick: ((image: BannerImage) -> Unit)? = null

        override fun isViewFromObject(view: View, `object`: Any): Boolean = `object` == view

        override fun getCount(): Int = images.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any =
            ImageView(context).apply {
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER
                layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            }.let {
                it.urlBg(images[position].imageUrl)
                it.setOnClickListener {
                    onImageClick?.let {
                        it(images[position])
                    }
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

    interface OnPageChange {
        fun onScrolled(index: Int)
    }
}