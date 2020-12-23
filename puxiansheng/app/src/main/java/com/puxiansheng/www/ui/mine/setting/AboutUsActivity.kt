package com.puxiansheng.www.ui.mine.setting

import android.net.http.SslError
import android.view.ViewGroup
import android.view.ViewParent
import android.webkit.*
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_about_us.button_back

class AboutUsActivity : MyBaseActivity(){

    override fun getLayoutId(): Int {
        return R.layout.activity_about_us
    }

    override fun business() {
        button_back.setOnClickListener {
            onBackPressed()
        }


        us_detail.apply {
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
            setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
        }
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("AboutUsActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("AboutUsActivity")
//    }


    override fun onDestroy() {
        super.onDestroy()
        if (us_detail != null) {
            val parent: ViewParent = us_detail.parent
            if (parent != null) {
                (parent as ViewGroup).removeView(us_detail)
            }
            us_detail.removeAllViews()
            us_detail.destroy()
        }
    }
}