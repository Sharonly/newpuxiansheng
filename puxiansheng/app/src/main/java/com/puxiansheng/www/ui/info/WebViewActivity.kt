package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Intent
import android.view.WindowManager
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.AndroidBug5497Workaround
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.synthetic.main.fragment_info_detail.*


class WebViewActivity : MyBaseActivity(){

    override fun getLayoutId(): Int {
       return R.layout.fragment_info_detail
    }

    @SuppressLint("JavascriptInterface")
    override fun business() {
        AndroidBug5497Workaround.assistActivity(this)
//        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        button_back.setOnClickListener {
            onBackPressed()
        }

//        button_more.setOnClickListener {
//            MoreManagerDialog(order.shop?.shopID.toString(),1,order.favorite).show(
//                supportFragmentManager,
//                MoreManagerDialog::class.java.name
//            )
//        }

        info_detail.addJavascriptInterface(MyJavascriptInterface(), "android")
        info_detail.apply {

            //webChromeClient = WebChromeClient()
            webChromeClient = MyWebView()

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
            webSettings.mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW
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



    inner class MyWebView:WebChromeClient(){
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            tv_webview_title.text=title
        }
    }


    inner class MyJavascriptInterface{
        @JavascriptInterface
        fun JAMS__mark(id:String) {
            val intent = Intent(this@WebViewActivity, TransferOutOrderDetailActivity::class.java)
            intent.putExtra("shopID", id)
            startActivity(intent)
        }

    }

}