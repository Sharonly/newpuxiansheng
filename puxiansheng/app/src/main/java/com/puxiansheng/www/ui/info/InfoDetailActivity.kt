package com.puxiansheng.www.ui.info

import android.net.http.SslError
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.*
import kotlinx.android.synthetic.main.fragment_info_detail.*
import kotlinx.android.synthetic.main.fragment_info_detail.button_back
import kotlinx.android.synthetic.main.fragment_info_detail.button_more

class InfoDetailActivity : MyBaseActivity(){

    override fun getLayoutId(): Int {
       return R.layout.fragment_info_detail
    }

    override fun business() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        button_more.setOnClickListener {
//            MoreManagerDialog(order.shop?.shopID.toString(),1,order.favorite).show(
//                supportFragmentManager,
//                MoreManagerDialog::class.java.name
//            )
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


}