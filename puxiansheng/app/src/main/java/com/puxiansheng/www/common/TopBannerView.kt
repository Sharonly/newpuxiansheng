package com.puxiansheng.www.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.graphics.ColorUtils
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.TopBannerViewBinding
import com.puxiansheng.www.ui.business.BusinessListActivity
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.info.WebViewActivity
import com.puxiansheng.www.ui.main.HomeActivity
import com.puxiansheng.www.ui.message.MessageDetailActivity
import com.puxiansheng.www.ui.mine.setting.AboutUsActivity
import com.puxiansheng.www.ui.order.*
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.loader.ImageLoader

class TopBannerView : FrameLayout , LifecycleEventObserver {
    private var imageList: List<BannerImage> = listOf()
    private var binding: TopBannerViewBinding
    private var pos = 0
    private var shouldLoop = true
    private val colorList: MutableList<ColorInfo> = mutableListOf()
    private val bgList: MutableList<String> = mutableListOf()
    private val bannerList: MutableList<String> = mutableListOf()
    private var imageLoader: BannerImageLoader? = null
    private var count = 0
    private var isInit = true
    var itemClickListener: OnBannerListener? = null

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
                @SuppressLint("ResourceAsColor")
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    pos = position
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
                    }else{
                        bgBanner.setBackgroundColor(R.color.white)
                    }
//                    MainActivity.setStatusBarColor(this@MainActivity, vibrantColor)
                }

                @SuppressLint("ResourceAsColor")
                override fun onPageSelected(position: Int) {
                    // 第一次,延时加载才能拿到颜色
                    if (isInit) {
                        isInit = false
//                        Handler().postDelayed({
//                            val vibrantColor: Int? = imageLoader?.getVibrantColor(1)
//                            if (vibrantColor != null) {
//                                bgBanner.setBackgroundColor(vibrantColor)
//                            }
//                        }, 200)
                        bgBanner.setBackgroundColor(R.color.white)
                        postDelayed(object :Runnable{
                            override fun run() {
                             val vibrantColor: Int? = imageLoader?.getVibrantColor(1)
                               if (vibrantColor != null) {
                                bgBanner.setBackgroundColor(vibrantColor)
                              }
                            }

                        },200)
                    }
                }

                override fun onPageScrollStateChanged(i: Int) {}
            })

        }

    }

    fun setBannerImages(images: List<BannerImage>?) {
        images?.let {
            imageList = images
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
        imageLoader?.setOnclickListener { Log.d("banner","  pppppp---")
           var banner:BannerImage = imageList[pos]
            JumpUtils.pictureIntent(context,banner)
        }


        binding.banner.setImages(bannerList)
        binding.banner.setDelayTime(3000)
        binding.banner.start()


    }

    fun startBanner(){
        binding.banner.startAutoPlay()
    }

     fun stopBanner(){
        binding.banner.stopAutoPlay()
    }


    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when(event){
            Lifecycle.Event.ON_PAUSE->{
                stopBanner()
            }

            Lifecycle.Event.ON_RESUME->{
                startBanner()
            }
        }
    }


//    private fun pictureIntent(context: Context, image: BannerImage) {
//        Log.d("---jump--"," pictureIntent--->${image.jump_param}--->${image.imageUrl}"+"   image.jump_type = "+image.jump_type+"  image.jump_view =  "+image.jump_view+"  image.jump_param = "+image.jump_param)
//        when (image.jump_type) {
//            1 -> {
//                when (image.jump_view) {
//                    "index" -> {
//                    }
//                    "transfer_list" -> {
//                        val intent = Intent(context, NewTransferOutOrdersActivity::class.java)
//                        intent.putExtra("title", "*")
//                        context.startActivity(intent)
//                    }
//                    "find_list" -> {
//                        val intent = Intent(context, NewTransferInOrdersActivity::class.java)
//                        intent.putExtra("title", "*")
//                        context.startActivity(intent)
//                    }
//
//
//                    "activity_list" -> {//文章列表
//                        val intent = Intent(context, HomeActivity::class.java)
//                        intent.putExtra("name", "5")
//                        context.startActivity(intent)
//                    }
//                    "user_center" -> {
//
//                    }
//
//                    "join_list" ->{
//                        val intent = Intent(context, BusinessListActivity::class.java)
//                        intent.putExtra("title", "*")
//                        context.startActivity(intent)
//                    }
//
//                    "quick_transfer" ->{
//                        val intent = Intent(context, FastTransferOutActivity::class.java)
//                        context.startActivity(intent)
//                    }
//
//                    "quick_find" ->{
//                        val intent = Intent(context, FastTransferInActivity::class.java)
//                        context.startActivity(intent)
//                    }
//                    "about_us" ->{
//                        val intent = Intent(context, AboutUsActivity::class.java)
//                        context.startActivity(intent)
//                    }
//
//                    "shop_success" -> {//成功案例
//                        val intent = Intent(context, NewTransferSuccessOrdersActivity::class.java)
//                        context.startActivity(intent)
//                    }
//                }
//            }
//            2 -> {//打开链接
////                val intent = Intent(Intent.ACTION_VIEW)
////                intent.data = Uri.parse(image.jump_param)
////                context.startActivity(intent)
//
//                //TODO  2020/6/8
//                val intent = Intent(context, WebViewActivity::class.java)
//                intent.putExtra("url", image.jump_param)
//                context.startActivity(intent)
//            }
//
//            3 -> {//找店详情
//                val intent = Intent(context, TransferInOrderDetailActivity::class.java)
//                intent.putExtra("shopID", image.jump_param)
//                context.startActivity(intent)
//            }
//
//            4 -> {//转铺详情
//                val intent = Intent(context, TransferOutOrderDetailActivity::class.java)
//                intent.putExtra("shopID", image.jump_param)
//                context.startActivity(intent)
//            }
//
//            5 -> {//文章详情
//                val intent = Intent(context, InfoDetailActivity::class.java)
//                intent.putExtra("url", image.jump_param)
//                context.startActivity(intent)
//            }
//
//            6 ->{
//                val intent = Intent(context,
//                    InsertOrUpdateTransferOutOrderActivity::class.java
//                )
//                intent.putExtra("shopID", image.jump_param)
//                context.startActivity(intent)
//
//            }
//            7->{
//                val intent = Intent( context,
//                    InsertOrUpdateTransferInOrderActivity::class.java
//                )
//                intent.putExtra("shopID", image.jump_param)
//                context.startActivity(intent)
//            }
//            8 ->{
//                val intent = Intent( context,
//                    TransferOutOrderDetailActivity::class.java
//                )
//                intent.putExtra("shopID", image.jump_param)
//                context.startActivity(intent)
//            }
//            9 ->{
//                val intent = Intent( context,
//                    MessageDetailActivity::class.java
//                )
//                intent.putExtra("noticeId", image.jump_param)
//                context.startActivity(intent)
//            }
//
//        }
//
//    }


}





