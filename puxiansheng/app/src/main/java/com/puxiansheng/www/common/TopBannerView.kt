package com.puxiansheng.www.common

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.graphics.ColorUtils
import androidx.viewpager.widget.ViewPager
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.TopBannerViewBinding
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener

class TopBannerView : FrameLayout {
    private var images: List<BannerImage> = listOf()
    private var binding: TopBannerViewBinding
    private var position = 0
    private var shouldLoop = true
    private val colorList: MutableList<ColorInfo> = mutableListOf()
    private val bgList: MutableList<String> = mutableListOf()
    private val bannerList: MutableList<String> = mutableListOf()
    private var imageLoader: BannerImageLoader? = null
    private var count = 0
    private var isInit = true

    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        binding = TopBannerViewBinding.inflate(LayoutInflater.from(context), this, true).apply {
            banner.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    var position = position
                    if (positionOffset > 1) { //会出现极个别大于1的数据
                        return
                    }
                    //修正position，解决两头颜色错乱，来自Banner控件源码
                    if (position == 0) {
                        position = count
                    }
                    if (position > count) {
                        position = 1
                    }
                    val pos: Int = (position + 1) % count //很关键
                    val vibrantColor = ColorUtils.blendARGB(
                        imageLoader!!.getVibrantColor(pos),
                        imageLoader!!.getVibrantColor(pos + 1),
                        positionOffset
                    )
                    if (vibrantColor != null) {
                        bgBanner.setBackgroundColor(vibrantColor)
                    }
//                    MainActivity.setStatusBarColor(this@MainActivity, vibrantColor)
                }

                override fun onPageSelected(position: Int) {
                    // 第一次,延时加载才能拿到颜色
                    if (isInit) {
                        isInit = false
                        Handler().postDelayed({
                            val vibrantColor: Int? = imageLoader?.getVibrantColor(1)
                            if (vibrantColor != null) {
                                bgBanner.setBackgroundColor(vibrantColor)
                            }
//                            bgBanner.setImageDrawable(resources.getDrawable(R.mipmap.bg_banner_blue))
                        }, 200)
                    }
                }

                override fun onPageScrollStateChanged(i: Int) {}
            })
        }

    }

    fun setBannerImages(images: List<BannerImage>?) {
        images?.let {
            bannerList.clear()
            for (i in images.indices) {
                val imageUrl = images[i].imageUrl
                bannerList.add(imageUrl)
            }
            count = bannerList.size
            if (count > 0) {
                initBannerView()
            }
        }
    }

    private fun initBannerView() {
        colorList.clear()
        for (i in 0..count + 1) {
            val info = ColorInfo()
            if (i == 0) {
                info.imgUrl = bannerList[count - 1]
            } else if (i == count + 1) {
                info.imgUrl = bannerList[0]
            } else {
                info.imgUrl = bannerList[i - 1]
            }
            colorList.add(info)
        }
        imageLoader = BannerImageLoader(colorList)
        binding.banner.setImageLoader(imageLoader)

        binding.banner.setImages(bannerList)
        binding.banner.setDelayTime(3000)
        binding.banner.setOnBannerListener { OnBannerListener { } }
        binding.banner.start()
    }

    fun startBanner(){
        binding.banner.startAutoPlay()
    }

     fun stopBanner(){
        binding.banner.stopAutoPlay()
    }

    fun onImageClick(onImageClick: (image: BannerImage) -> Unit) {
        binding.banner.setOnBannerListener(onImageClick)
    }

}

fun Banner.setOnBannerListener(onImageClick: (image: BannerImage) -> Unit) {

}
