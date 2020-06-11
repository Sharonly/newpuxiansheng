package com.puxiansheng.www.ui.map

import android.net.Uri
import android.net.http.SslError
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.*
import androidx.lifecycle.ViewModelProvider
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.put
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderViewModel
import kotlinx.android.synthetic.main.fragment_map_find_address.*
import java.net.URLDecoder


class MapActivity : MyBaseActivity() {
    var mUrl: String = ""
    private lateinit var insertOrUpdateTransferOutOrderViewModel: InsertOrUpdateTransferOutOrderViewModel
    private lateinit var appModel: MainViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_map_find_address
    }

    override fun business() {
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        insertOrUpdateTransferOutOrderViewModel =
            ViewModelProvider(this)[InsertOrUpdateTransferOutOrderViewModel::class.java]
        val sb = StringBuilder()
        sb.append("https://apis.map.qq.com/tools/locpicker?search=1&type=0&coordtype=5&coord=")
        sb.append(intent.getStringExtra("location"))
        sb.append("&backurl=http://callback&key=O2IBZ-X2RH4-ZKNUJ-DSGQT-RQGVK-TZF4W&referer=pxs")
        mUrl = sb.toString()
        initView()
    }


    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }
        map_webview.apply {
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    if (!request?.url.toString().startsWith("http://callback")) {
                        view?.loadUrl(request?.url.toString())
                    } else {
                        //转utf-8编码
                        var decode: String = URLDecoder.decode(request?.url.toString(), "UTF-8");
                        //转uri，然后根据key取值
                        var uri: Uri = Uri.parse(decode);
                        var latng: String =
                            uri?.getQueryParameter("latng").toString()//纬度在前，经度在后，以逗号分隔
                        var split = latng?.split(",")
                        var lat: String = split[0]?.toString()//纬度
                        var lng: String = split[1]?.toString()//经度
                        var address: String = uri?.getQueryParameter("addr").toString()//地址
                        put("my_location", address)
                        intent.putExtra("mes", address);//返回值
                        Log.d("----map", "  address = " + address)
                        LiveDataBus.get().with("Map")?.value = address
                        finish()
                    }
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
            loadUrl(mUrl)
        }.settings.apply {
            setRenderPriority(WebSettings.RenderPriority.HIGH)
            setSupportMultipleWindows(true)
            javaScriptEnabled = true
            savePassword = false
            useWideViewPort = true
            loadWithOverviewMode = true
            javaScriptCanOpenWindowsAutomatically = true
            minimumFontSize = minimumFontSize + 8
            textSize = WebSettings.TextSize.NORMAL
            allowFileAccess = false
            setGeolocationEnabled(true)
            userAgentString
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }

}