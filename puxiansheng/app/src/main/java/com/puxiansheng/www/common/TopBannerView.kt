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
//            bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613936&di=3769695217e3424f18c3d23966ecd4dc&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fback_pic%2Fqk%2Fback_origin_pic%2F00%2F04%2F19%2F70e2846ebc02ae10161f25bf7f5461a1.jpg")
//            bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532665664&di=9ead9eb8a9fe2af9a01b0dd39f3e41f4&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Fback_pic%2F05%2F37%2F28%2F475a43591370453.jpg")
//            bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613934&di=0be1c6bbf0441bd19ef6d4e3ce799263&imgtype=0&src=http%3A%2F%2Fpic96.nipic.com%2Ffile%2F20160430%2F7036970_215739900000_2.jpg")
//            bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556532613936&di=4dd453940f49d9801826e6b820490957&imgtype=0&src=http%3A%2F%2Fpic161.nipic.com%2Ffile%2F20180410%2F26429156_154754410034_2.jpg")
//            bannerList.add("https://pics7.baidu.com/feed/3bf33a87e950352a3726d2d6528450f4b0118bf3.jpeg?token=6e853ebadb1edc0e17378e8249cfebac&s=7167587ECE742C9A54B32FB80300B01A")
            bannerList.clear()
            for (i in images.indices) {
                val imageUrl = images[i].imageUrl
                bannerList.add(imageUrl)
//                val bgImg =images[i].bgImgUrl
//                    bgList.add(bgImg)
                Log.d("---topbanner--", " i = " + i + " imageUrl = " + imageUrl)
            }
            count = bannerList.size
            if(count>0){
                initBannerView()
            }
        }
    }

  private fun initBannerView(){
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

    fun onImageClick(onImageClick: (image: BannerImage) -> Unit) {
        binding.banner.setOnBannerListener(onImageClick)
    }

}

fun Banner.setOnBannerListener(onImageClick: (image: BannerImage) -> Unit) {

}
