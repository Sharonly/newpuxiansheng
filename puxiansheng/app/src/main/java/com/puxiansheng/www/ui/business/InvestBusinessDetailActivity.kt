package com.puxiansheng.www.ui.business


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.webkit.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.fragment_invest_business_detail.*
import kotlinx.android.synthetic.main.fragment_invest_business_detail.button_back
import kotlinx.android.synthetic.main.fragment_invest_business_detail.button_more
import kotlinx.android.synthetic.main.fragment_invest_business_detail.info_detail
import kotlinx.coroutines.launch


class InvestBusinessDetailActivity : MyBaseActivity() {
    private lateinit var viewModel: BusinessDetailViewModel
    private var imges = mutableListOf<String>()
    var shopUrl = ""

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.color81,true)
        return R.layout.fragment_invest_business_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[BusinessDetailViewModel::class.java]
        initView()
    }

    @SuppressLint("SetTextI18n")
    fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        shopUrl = intent.getStringExtra("url").toString()

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
            setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
        }


        lifecycleScope.launch {
            viewModel.requestInvestBusinessDetail(intent.getIntExtra("id", 0).toString())
                ?.let { business ->
                    var phone = business.contact_phone
////                    imges.add(business.thumb_img)
//                    image_switcher.url(business.thumb_img)
//                    var insdustry = business.trades
//                    var money = business.investment
//                    shop_title.text = business.name
//                    shop_industry.text = "所属行业：$insdustry"
//                    invest_money.text = "投资资金：$money"
                    connect_phone.text = "加盟热线：$phone"

                    connect_phone.setOnClickListener {
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${phone}")
                            startActivity(this)
                        }
                    }

                    button_more.visibility = View.INVISIBLE
//                    button_more.setOnClickListener {
//                        MoreManagerDialog(business.id.toString(),business.name, business.thumb_img,shopUrl,2, 0).show(
//                            supportFragmentManager,
//                            MoreManagerDialog::class.java.name
//                        )
//                    }
                }
        }

    }

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

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("InvestBusinessDetailActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("InvestBusinessDetailActivity")
//    }

}

