package com.puxiansheng.www.ui.order

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.ExpandTextView
import com.puxiansheng.www.common.ImageSwitcher
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.dialog.AdvertmentDialog
import com.puxiansheng.www.ui.map.GetLocationActivity
import com.puxiansheng.www.ui.map.MapActivity
import com.puxiansheng.www.ui.mine.setting.UserSettingActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import com.puxiansheng.www.ui.order.dialog.ShopImageDialog
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory
import com.tencent.tencentmap.mapsdk.maps.TencentMap

import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class TransferOutOrderDetailActivity : MyBaseActivity() {

    private val requestCodePermissions = 10
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var viewModel: TransferOutOrderDetailViewModel
    private var tencentMap: TencentMap? = null
    private var cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
    private var images: List<BannerImage> = listOf()

    override fun getLayoutId(): Int {
        return R.layout.fragment_transfer_out_order_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferOutOrderDetailViewModel::class.java]
        ActivityCompat.requestPermissions(this, requiredPermissions, requestCodePermissions)
        initView()
    }

    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        bt_more.setOnClickListener {
            lifecycleScope.launch {
                viewModel.requestTransferOutOrderDetail(
                    intent.getStringExtra("shopID")
                )?.let { order ->
                    var shopImg = ""
                    if (order.images.isNullOrEmpty()) {
                        shopImg = ""
                    } else {
                        shopImg = order.images?.get(0).toString()
                    }
                    MoreManagerDialog(
                        order.shopID.toString(), order.title, shopImg, ""
                        , 0,
                        order.favorite
                    ).show(supportFragmentManager, MoreManagerDialog::class.java.name)
                }
            }
        }

        //TODO
//        tencentMap = map_view.getMap()
//        val uiSettings = tencentMap?.getUiSettings()
//        uiSettings?.setLogoPosition(3)
//        uiSettings?.setZoomControlsEnabled(true)
//        uiSettings?.setZoomPosition(0)
//        uiSettings?.setMyLocationButtonEnabled(true)
//        tencentMap?.setMyLocationEnabled(true)

        image_switcher.onImageClick {
            Log.d("---imgswitch", "onclick")
            ShopImageDialog(this@TransferOutOrderDetailActivity, images).show(
                supportFragmentManager,
                AdvertmentDialog::class.java.name
            )
        }

        lifecycleScope.launch {
//            viewModel.requestTransferOutOrderDetail(intent.getStringExtra("shopID"))
            viewModel.requestTransferOutOrderDetail(intent.getStringExtra("shopID"))?.let { order ->
//                    if(order.shop?.images?.size == 0)
//                        imageSwitcher.setImages(list)
                Log.d("shopcheck", " order.checkId = " + order.checkId)

                if (order.images?.size == 0) {

                } else {
                    order.images?.map { url ->
                        BannerImage(imageUrl = url)
                    }?.let { list ->
                        image_switcher.setImages(list)
                        img_index.text = image_switcher.getCurrentPos().toString() + "/" + list.size
                        image_switcher.listener = object : ImageSwitcher.OnPageChange {
                            override fun onScrolled(index: Int) {
                                img_index.text =
                                    image_switcher.getCurrentPos().toString() + "/" + list.size
                            }
                        }
                        images = list
                    }
                }

                if(order.checkId == 1 && order.status == 1){
                    bt_more.visibility = View.VISIBLE
                } else {
                    bt_more.visibility = View.INVISIBLE
                }

                shop_title.text = order.title
                shop_number.text = "店铺编号：${order.shopID}"
                page_views.text = "浏览量：${order.formattedPageViews}"
                publish_date.text = "发布时间：${order.formattedDate}"

                rent.text = order.formattedRent
                size.text = order.formattedSize
                fee.text = order.formattedTransferFee

                category.text = order.categoryStr
                state.text = order.viewOpening
                can_empty.text = order.viewCanEmpty

                address.text = order.address?.addressDetail

                address.setOnClickListener {
                    if (order.lat.isNullOrEmpty() || order.lng.isNullOrEmpty() ) {
                        Toast.makeText(
                            this@TransferOutOrderDetailActivity,
                            "当前店铺没有获取定位",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val intent =
                            Intent(this@TransferOutOrderDetailActivity, MapActivity::class.java)
                        intent.putExtra("location", order.lat + "," + order.lng)
                        intent.putExtra("lat", order.lat!!.toDouble())
                        intent.putExtra("lng", order.lng!!.toDouble())
                        startActivity(intent)
                    }
                }

                //TODO  现获取经纬度是0，初步怀疑是本地double类型与后台冲突，牵涉太多不好改，现用东莞经纬度测试
//                order.shop?.lng = 113.75179
//                order.shop?.lat = 23.02067
//
//                //中心点
//                val cp = CameraPosition(order.shop?.lat?.let {
//                    order.shop?.lng?.let { it1 ->
//                        LatLng(it, it1)
//                    }
//                }, 15f, 45f, 45f)
//                val cameraUpdate = CameraUpdateFactory.newCameraPosition(cp)
//                tencentMap?.moveCamera(cameraUpdate)
//
//                //设置浮窗
//                tencentMap?.addMarker(
//                    MarkerOptions().position(order.shop?.lat?.let {
//                        order.shop?.lng?.let { it1 ->
//                            LatLng(it, it1)
//                        }
//                    })
////                            .title("${bean.area_path_name}-${bean.address}")
//                        .anchor(0.5f, 0.5f)
//                        .icon(BitmapDescriptorFactory.defaultMarker())
//                        .draggable(true)
//                )?.showInfoWindow()


                if (order.isSuccess == 1) {
                    img_success.visibility = View.VISIBLE
                    recommend_orders.visibility = View.GONE
                    bg_map.visibility = View.GONE
                    bt_connect_kf.visibility = View.INVISIBLE
                } else {
                    img_success.visibility = View.GONE
                    recommend_orders.visibility = View.VISIBLE
                    bg_map.visibility = View.VISIBLE
                    bt_connect_kf.visibility = View.VISIBLE
                }


                order.formattedFacilities?.let { facilityItems ->
                    facilities.layoutManager =
                        GridLayoutManager(this@TransferOutOrderDetailActivity, 6)
                    facilities.adapter = FacilityAdapter(facilityItems)
                }


                var str1: String = order.description.toString()
                expand_description.currentText = str1
                expand_description.clickListener = object : ExpandTextView.ClickListener {
                    override fun onContentTextClick() {
                        expand_description.currentText = str1
                    }

                    override fun onSpecialTextClick(currentExpand: Boolean) {
                        expand_description.isExpand = !currentExpand
                    }
                }

                var strEnvironment: String = order.environment.toString()
                expand_environment.currentText = strEnvironment
                expand_environment.clickListener = object : ExpandTextView.ClickListener {
                    override fun onContentTextClick() {
                        expand_environment.currentText = strEnvironment
                    }

                    override fun onSpecialTextClick(currentExpand: Boolean) {
                        expand_environment.isExpand = !currentExpand
                    }
                }

                var strReason: String = order.reason.toString()
                expand_reason.currentText = strReason
                expand_reason.clickListener = object : ExpandTextView.ClickListener {
                    override fun onContentTextClick() {
                        expand_reason.currentText = strReason
                    }

                    override fun onSpecialTextClick(currentExpand: Boolean) {
                        expand_reason.isExpand = !currentExpand
                    }
                }

                bt_connect_kf.setOnClickListener {
                    if (SharedPreferencesUtil.get(API.LOGIN_USER_TOKEN, "").toString()
                            .isNotEmpty()
                    ) {
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${order.serviceAgentPhone}")
                            startActivity(this)
                        }
                    } else {
                        val intent =
                            Intent(this@TransferOutOrderDetailActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
            }


            if (recommend_orders.visibility == View.VISIBLE) {
                recommend_orders.layoutManager =
                    GridLayoutManager(this@TransferOutOrderDetailActivity, 2)
                recommend_orders.adapter = RecommendOrderAdapter(mutableListOf(), onItemSelect = {
                    val intent = Intent(
                        this@TransferOutOrderDetailActivity,
                        TransferOutOrderDetailActivity::class.java
                    )
                    intent.putExtra("shopID", it?.shopID.toString())
                    startActivity(intent)
                })
                viewModel.requestUserLikeShopList(
                    cityId.toString(), intent.getIntExtra("shopID", 0).toString()
                )?.let {
                    (recommend_orders.adapter as RecommendOrderAdapter).setMenuData(it)
                }
            }

        }

    }


}