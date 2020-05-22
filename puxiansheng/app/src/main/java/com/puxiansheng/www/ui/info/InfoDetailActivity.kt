package com.puxiansheng.www.ui.info

import android.net.http.SslError
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.fragment_info_detail.*

class InfoDetailActivity : MyBaseActivity(){

    override fun getLayoutId(): Int {
       return R.layout.fragment_info_detail
    }

    override fun business() {
        button_back.setOnClickListener {
            onBackPressed()
        }

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
            loadUrl(intent.getStringExtra("url").toString())//intent.getIntExtra("shopID", 0).toString()
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


}