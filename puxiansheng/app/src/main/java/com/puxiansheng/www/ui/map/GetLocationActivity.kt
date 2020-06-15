package com.puxiansheng.www.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.net.http.SslError
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.put
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.LiveDataBus
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderViewModel
import kotlinx.android.synthetic.main.fragment_map_find_address.*
import java.net.URLDecoder


class GetLocationActivity : MyBaseActivity() {
    var mUrl: String = ""
    private lateinit var insertOrUpdateTransferOutOrderViewModel: InsertOrUpdateTransferOutOrderViewModel
    private lateinit var appModel: MainViewModel
    val LOCATION_CODE = 1315
    var isNeedCheck = true
    private val requestCodePermissions = 100
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun getLayoutId(): Int {
        return R.layout.fragment_map_find_address
    }

    override fun business() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        ActivityCompat.requestPermissions(
            this,
            requiredPermissions,
            requestCodePermissions
        )
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        insertOrUpdateTransferOutOrderViewModel =
            ViewModelProvider(this)[InsertOrUpdateTransferOutOrderViewModel::class.java]
        initView()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, paramArrayOfInt: IntArray) {
        when (requestCode) {
            requestCodePermissions -> if (!verifyPermissions(paramArrayOfInt)) {
                isNeedCheck = false
            }
            LOCATION_CODE -> {
                if (paramArrayOfInt.isNotEmpty() && paramArrayOfInt[0] == PackageManager.PERMISSION_GRANTED) {
                    initView()
                } else {
                    // 权限被用户拒绝了。
                    Toast.makeText(this, "定位权限被禁止，相关地图功能无法使用！", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


    /**
     * 检测是否说有的权限都已经授权
     */
    fun verifyPermissions(grantResults: IntArray): Boolean {
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }


    private fun initView() {
        if(intent.getStringExtra("location")=="0.0,0.0"){
            Toast.makeText(this, "请打开GPS来获取您的定位", Toast.LENGTH_LONG).show()
        }else {
            Log.d("---location---", " intent.getStringExtra " + intent.getStringExtra("location"))
            val sb = StringBuilder()
            sb.append("https://apis.map.qq.com/tools/locpicker?search=1&type=0&coordtype=5&coord=")
            sb.append(intent.getStringExtra("location"))
            sb.append("&backurl=http://callback&key=O2IBZ-X2RH4-ZKNUJ-DSGQT-RQGVK-TZF4W&referer=pxs")
            mUrl = sb.toString()
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