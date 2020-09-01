package com.puxiansheng.www.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.net.http.SslError
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.put
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderViewModel
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_map_location.*
import java.net.URLDecoder


class MapActivity : MyBaseActivity() {
    var mUrl: String =
        "https://apis.map.qq.com/uri/v1/geocoder?coord=39.904956,116.389449&referer=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77"
    private lateinit var insertOrUpdateTransferOutOrderViewModel: InsertOrUpdateTransferOutOrderViewModel
    private lateinit var appModel: MainViewModel
    private var tencentMap: TencentMap? = null
    private var lat = 23.02067
    private var lng = 113.75179

    val LOCATION_CODE = 1315
    var isNeedCheck = true
    private val requestCodePermissions = 100
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_map_location
    }

    override fun business() {
        toptitle.text = "位置信息"
        button_back.setOnClickListener {
            onBackPressed()
        }

        lat =  intent.getDoubleExtra("lat",23.02067)
        lng =  intent.getDoubleExtra("lng",113.75179)

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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        paramArrayOfInt: IntArray
    ) {
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
//        Log.d("get_location---", " intent.getStringExtra " + intent.getStringExtra("location"))
        val sb = StringBuilder()
        sb.append("https://apis.map.qq.com/uri/v1/geocoder?coord=")
        sb.append(intent.getStringExtra("location"))
        sb.append("&referer=pxs")
        mUrl = sb.toString()
        map_webview.apply {
            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
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

        initMap()
    }


    private fun initMap() {
        tencentMap = map_view.getMap()
        val uiSettings = tencentMap?.getUiSettings()
        uiSettings?.setLogoPosition(3)
        uiSettings?.setZoomControlsEnabled(true)
        uiSettings?.setZoomPosition(0)
        uiSettings?.setMyLocationButtonEnabled(true)
        tencentMap?.setMyLocationEnabled(true)

        //中心点
        val cp = CameraPosition(lat?.let {
            lng?.let { it1 ->
                LatLng(it, it1)
            }
        }, 15f, 45f, 45f)
        val cameraUpdate = CameraUpdateFactory.newCameraPosition(cp)
        tencentMap?.moveCamera(cameraUpdate)

        //设置浮窗
        tencentMap?.addMarker(
            MarkerOptions().position(lat?.let {
                lng?.let { it1 ->
                    LatLng(it, it1)
                }
            })
//                            .title("${bean.area_path_name}-${bean.address}")
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.defaultMarker())
                .draggable(true)
        )?.showInfoWindow()
    }

    override fun onStart() {
        map_view.onStart()
        super.onStart()

    }

    override fun onResume() {
        map_view.onResume()
        super.onResume()
        MobclickAgent.onPageStart("MapActivity")

    }

    override fun onPause() {
        map_view.onPause()
        super.onPause()
        MobclickAgent.onPageEnd("MapActivity")
    }

    override fun onStop() {
        map_view.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        map_view.onDestroy()
        super.onDestroy()
    }


}