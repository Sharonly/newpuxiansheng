package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Intent
import android.net.http.SslError
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.webkit.*
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import kotlinx.android.synthetic.main.activity_info_detail.*
import kotlinx.coroutines.launch


class NewInfoDetailActivity : MyBaseActivity(){
    var infoUrl = ""
    var infoId = ""
    var cityId = ""
    var infoTitle = ""
    var infoImg = ""
    var isFavor = 0
    private lateinit var viewModel: InfoDetailViewModel

    override fun getLayoutId(): Int {
       return R.layout.activity_info_detail
    }

    @SuppressLint("JavascriptInterface")
    override fun business() {
        infoId =  intent.getIntExtra("shop_Id",0).toString()
        cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
        viewModel= ViewModelProvider(this)[InfoDetailViewModel::class.java]
        initView()
        initData()
    }

    fun initView() {
        if (infoId.isEmpty()) {
            button_more.visibility = View.GONE
        }

        button_back.setOnClickListener {
            onBackPressed()
        }

        tv_webview_title.text = "百科详情"
        button_more.setOnClickListener {
            lifecycleScope.launch {
                viewModel.getInfoDetail(
                    id = infoId,
                    city_id = cityId
                )?.let { httpDetail ->
                    infoTitle = httpDetail.infoObject?.title.toString()
                    isFavor = httpDetail.infoObject?.is_collect!!
                    MoreManagerDialog(
                        infoId, infoTitle, infoImg, infoUrl, 2,
                        isFavor
                    ).show(supportFragmentManager, MoreManagerDialog::class.java.name)
                }
            }
        }

    }


  private fun initData(){
        lifecycleScope.launch {
            viewModel.getInfoDetail(
                id = infoId,
                city_id = cityId
            )?.let { httpDetail ->
                infoTitle = httpDetail.infoObject?.title.toString()
                info_title.text = infoTitle
                view_num.text = httpDetail.infoObject?.view_count + "人观看"
                time.text = httpDetail.infoObject?.update_time
                infoUrl = httpDetail.infoObject?.share_url.toString()
                isFavor = httpDetail.infoObject?.is_collect!!
                last.text = "上一篇: "+ httpDetail.topArticle?.title
                next.text = "下一篇: "+ httpDetail.descArticle?.title

                last.setOnClickListener {
                    val intent = Intent(
                        this@NewInfoDetailActivity,
                        NewInfoDetailActivity::class.java
                    )
                    intent.putExtra("shop_Id", httpDetail.topArticle?.id)
                    intent.putExtra("city_Id", httpDetail.topArticle?.city_id)
                    startActivity(intent)
                }


                next.setOnClickListener {
                    val intent = Intent(
                        this@NewInfoDetailActivity,
                        NewInfoDetailActivity::class.java
                    )
                    intent.putExtra("shop_Id", httpDetail.descArticle?.id)
                    intent.putExtra("city_Id", httpDetail.descArticle?.city_id)
                    startActivity(intent)
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
//                    loadUrl(httpDetail.infoObject?.content)
                    loadDataWithBaseURL(null,httpDetail.infoObject?.content, "text/html" , "utf-8", null)
                }.settings.apply {
                    javaScriptEnabled = true
                    useWideViewPort = true
                    loadWithOverviewMode = true
                    defaultFontSize =50
                    textZoom = 130
                    javaScriptCanOpenWindowsAutomatically = true
                    domStorageEnabled = true
                    userAgentString
                    setMixedContentMode(MIXED_CONTENT_ALWAYS_ALLOW)
                }
            }
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