package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.http.SslError
import android.view.View
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import kotlinx.android.synthetic.main.fragment_info_detail.*
import kotlinx.android.synthetic.main.fragment_info_detail.button_back
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.coroutines.launch


class InfoDetailActivity : MyBaseActivity(){

    override fun getLayoutId(): Int {
       return R.layout.fragment_info_detail
    }

    @SuppressLint("JavascriptInterface")
    override fun business() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        tv_webview_title.text = "百科详情"
        button_more.visibility = View.INVISIBLE
        button_more.setOnClickListener {
//                lifecycleScope.launch {
//                    viewModel.requestTransferOutOrderDetail(
//                        intent.getStringExtra("shopID")
//                    )?.let { order ->
//                        var shopImg = ""
//                        if (order.images.isNullOrEmpty()) {
//                            shopImg = ""
//                        } else {
//                            shopImg = order.images?.get(0).toString()
//                        }
//                        MoreManagerDialog(
//                            order.shopID.toString(), order.title, shopImg, ""
//                            , 0,
//                            order.favorite
//                        ).show(supportFragmentManager, MoreManagerDialog::class.java.name)
//                    }
//            }
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