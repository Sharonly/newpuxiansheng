package com.puxiansheng.www.ui.mine

import android.net.http.SslError
import android.os.Build
import android.webkit.*
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.fragment_service_home.*


class ServiceActivity : MyBaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_service_home
    }

    override fun business() {
        initView()
    }



   fun initView() {
            onlineService.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && onlineService!= null) {
                    onlineService.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
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
//                    allowContentAccess = true
//                    allowFileAccess = true
//                    allowFileAccessFromFileURLs = false
//                    setSupportZoom(true)
                userAgentString
                setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
            }
        }



}