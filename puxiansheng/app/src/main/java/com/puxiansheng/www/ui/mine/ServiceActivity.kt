package com.puxiansheng.www.ui.mine

import android.annotation.SuppressLint
import android.content.Intent
import android.net.http.SslError
import android.os.Build
import android.webkit.*
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_info_detail.*
import kotlinx.android.synthetic.main.fragment_service_home.*
import kotlinx.android.synthetic.main.fragment_service_home.button_back
import kotlinx.android.synthetic.main.fragment_service_home.info_detail


class ServiceActivity : MyBaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_service_home
    }

    override fun business() {
        button_back.setOnClickListener {
            if (intent.getStringExtra("title").toString() == "用户协议") {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                onBackPressed()
            }
        }

//        button_more.setOnClickListener {
//            MoreManagerDialog(order.shop?.shopID.toString(),1,order.favorite).show(
//                supportFragmentManager,
//                MoreManagerDialog::class.java.name
//            )
//        }

        info_detail.apply {
            web_title.text = intent.getStringExtra("title").toString()
            webChromeClient = WebChromeClient()

            loadUrl(intent.getStringExtra("url").toString())


        }.settings.apply {
//            javaScriptEnabled = true
//            useWideViewPort = true
//            loadWithOverviewMode = true
//            javaScriptCanOpenWindowsAutomatically = true
//            domStorageEnabled = true
//        设置允许JS中的弹窗
//            userAgentString
//            setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW)


            //声明WebSettings子类

            //声明WebSettings子类
            val webSettings: WebSettings = info_detail.getSettings()
            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
            webSettings.javaScriptEnabled = true
            // 解决图片不显示
            // 解决图片不显示
            webSettings.blockNetworkImage = false
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            //设置自适应屏幕，两者合用,缩放操作
            //设置自适应屏幕，两者合用,缩放操作
            webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

            webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。

            webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放

            webSettings.displayZoomControls = false //隐藏原生的缩放控件

            webSettings.useWideViewPort = true //WebView双击变大，再双击后变小，当手动放大后，双击可以恢复到原始大小

            webSettings.pluginState = WebSettings.PluginState.ON //没加的话，视频会加载失败

            //其他细节操作
            //其他细节操作
            webSettings.allowUniversalAccessFromFileURLs =
                true // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false

            webSettings.cacheMode = WebSettings.LOAD_DEFAULT //设置此缓存模式,需要设置缓存目录

            webSettings.allowFileAccess = true //设置可以访问文件

            webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口

            webSettings.loadsImagesAutomatically = true //支持自动加载图片

            webSettings.defaultTextEncodingName = "utf-8" //设置编码格式

            webSettings.domStorageEnabled = true //设置适应Html5,开启 DOM storage API 功能

            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH) //渲染等级

        }
    }


}