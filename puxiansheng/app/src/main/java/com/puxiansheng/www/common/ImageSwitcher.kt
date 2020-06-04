package com.puxiansheng.www.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.puxiansheng.logic.bean.BannerImage
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

    fun setCurrentPos(pos: Int){
        position = pos
    }

    fun getCurrentPos():Int{
        if(images.isNotEmpty()) {
            return position + 1
        }else{
            return 1
        }
    }


    @ExperimentalCoroutinesApi
    fun loop(ticker: ReceiveChannel<Unit>, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            ticker.consumeEach {
                binding.imagePager.setCurrentItem(position, true)
                position += 1
                if (position == images.size) position = 0
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
                scaleType = ImageView.ScaleType.FIT_XY
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

        fun setOnImageClick(onImageClick: (image: BannerImage) -> Unit){
            this.onImageClick = onImageClick
        }
    }
}