package com.puxiansheng.www.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.puxiansheng.logic.util.GlideApp
import com.puxiansheng.www.R
import kotlinx.android.synthetic.main.activity_success_video_detail.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL


fun ImageView.url(url: String) {
    GlideApp.with(this.context).load(url).error(R.mipmap.img_pxs_defult_small).placeholder(R.mipmap.img_pxs_defult_small)
        .fallback(R.mipmap.img_pxs_defult_small).into(this)
//    GlideApp.with(this.context).load(url).error(R.mipmap.img_pxs_defult_small).placeholder(R.mipmap.img_pxs_defult_small)
//        .skipMemoryCache(false)
//        .diskCacheStrategy(DiskCacheStrategy.NONE)
//        .fallback(R.mipmap.img_pxs_defult_small).into(this)
}

fun ImageView.urlBg(url: String) {
    GlideApp.with(this.context).load(url).error(R.mipmap.img_pxs_defult_big).placeholder(R.mipmap.img_pxs_defult_big)
        .fallback(R.mipmap.img_pxs_defult_big).into(this)
}

fun ImageView.urlIcon(url: String) {
    GlideApp.with(this.context).load(url).error(R.mipmap.ic_default_icon).placeholder(R.mipmap.ic_default_icon)
        .fallback(R.mipmap.ic_default_icon).into(this)
}


fun ImageView.urlCircleImg(url:Int){
    val options: RequestOptions = RequestOptions() //圆形
        .circleCrop() //占位图
    Glide.with(this)
        .load(url)
        .apply(options)
        .into(this)
}

var bitmap: Bitmap? = null
fun returnBitMap(url: String?): Bitmap? {
    Thread(Runnable {
        var imageurl: URL? = null
        try {
            imageurl = URL(url)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        try {
            val conn: HttpURLConnection = imageurl?.openConnection() as HttpURLConnection
            conn.setDoInput(true)
            conn.connect()
            val `is`: InputStream = conn.getInputStream()
            bitmap = BitmapFactory.decodeStream(`is`)
            `is`.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }).start()
    return bitmap
}