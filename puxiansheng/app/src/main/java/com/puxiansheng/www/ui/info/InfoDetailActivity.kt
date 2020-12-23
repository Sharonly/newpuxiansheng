package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.http.SslError
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.fragment_info_detail.*
import kotlinx.android.synthetic.main.fragment_info_detail.button_back
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.coroutines.launch


class InfoDetailActivity : MyBaseActivity(){
    var infoUrl = ""
    var infoId = ""
    var infoTitle = ""
    var infoImg = ""


    override fun getLayoutId(): Int {
       return R.layout.fragment_info_detail
    }

    @SuppressLint("JavascriptInterface")
    override fun business() {
        infoUrl = intent.getStringExtra("url").toString()
        infoId =  intent.getIntExtra("shop_Id",0).toString()
        infoTitle =  intent.getStringExtra("title")
        infoImg =  intent.getStringExtra("img")
         if(infoId.isEmpty()){
             button_more.visibility = View.GONE
         }

        button_back.setOnClickListener {
            onBackPressed()
        }

        tv_webview_title.text = "百科详情"
        button_more.setOnClickListener {
                lifecycleScope.launch {
                    MoreManagerDialog(
                        infoId, infoTitle, infoImg, infoUrl
                        , 2,
                        0
                    ).show(supportFragmentManager, MoreManagerDialog::class.java.name)
            }
        }

//        description.apply {
////                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
////                            settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
////                        }
//
//            /*addJavascriptInterface(object {
//                @JavascriptInterface
//                fun resize(height: Float) {
//                    lifecycleScope.launch(Dispatchers.Main) {
//                        description.layoutParams = LinearLayout.LayoutParams(
//                            resources.displayMetrics.widthPixels,
//                            (height * resources.displayMetrics.density).toInt()
//                        )
//                    }
//                }
//            }, "js")*/
//
//            webChromeClient = WebChromeClient()
//            webViewClient = object : WebViewClient() {
//                override fun onPageFinished(view: WebView?, url: String?) {
//                    //description.loadUrl("javascript:js.resize(document.body.getBoundingClientRect().height)")
//                    super.onPageFinished(view, url)
//                }
//
//                override fun shouldOverrideUrlLoading(
//                    view: WebView?,
//                    request: WebResourceRequest?
//                ): Boolean {
//                    view?.loadUrl(request?.url.toString())
//                    return true
//                }
//
//                override fun onReceivedSslError(
//                    view: WebView?,
//                    handler: SslErrorHandler?,
//                    error: SslError?
//                ) {
//                    super.onReceivedSslError(view, handler, error)
//                    handler?.proceed()
//                }
//            }
//
//            loadUrl(order.shop?.descriptionUrl)
//        }.settings.apply {
//            javaScriptEnabled = true
//            useWideViewPort = true
//            loadWithOverviewMode = true
//            javaScriptCanOpenWindowsAutomatically = true
//            userAgentString
//            setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW)
//        }

        info_detail.apply {
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    super.onReceivedSslError(view, handler, error)
                    handler?.proceed()
                }
            }
            loadUrl(intent.getStringExtra("url").toString())
        }.settings.apply {
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            userAgentString
            setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW)
        }

    }
//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("InfoDetailActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("InfoDetailActivity")
//    }


override fun onDestroy() {
    super.onDestroy()
    if (info_detail != null) {
        val parent: ViewParent = info_detail.parent
        if (parent != null) {
            (parent as ViewGroup).removeView(info_detail)
        }
        info_detail.removeAllViews()
        info_detail.destroy()
    }
}

}