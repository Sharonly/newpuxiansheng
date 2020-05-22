package com.puxiansheng.www.ui.order

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.ExpandTextView
import com.puxiansheng.www.databinding.FragmentTransferOutOrderDetailBinding
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import com.puxiansheng.www.ui.release.ReleaseFacilityAdapter
import com.tencent.mm.opensdk.utils.Log
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.*
import kotlinx.android.synthetic.main.activity_release_order_transfer_in.*
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.*
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.button_back
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.expand_description
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.facilities
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.page_views
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.publish_date
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.rent
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.shop_number
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.shop_title
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.size
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlin.text.category

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class TransferOutOrderDetailActivity : MyBaseActivity() {
    private val requestCodePermissions = 10
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    private lateinit var viewModel: TransferOutOrderDetailViewModel
    private var cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)

    override fun getLayoutId(): Int {
        return R.layout.fragment_transfer_out_order_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferOutOrderDetailViewModel::class.java]
        ActivityCompat.requestPermissions(
            this,
            requiredPermissions,
            requestCodePermissions
        )
        initView()
    }

    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }
        lifecycleScope.launch {
            viewModel.requestTransferOutOrderDetail(intent.getIntExtra("shopID", 0).toString())
                ?.let { order ->
//                    if(order.shop?.images?.size == 0)
//                        imageSwitcher.setImages(list)
                    if (order.shop?.images?.size == 0) {

                    } else {
                        order.shop?.images?.map { url ->
                            BannerImage(imageUrl = url)
                        }?.let { list ->
                            image_switcher.setImages(list)
                        }
                    }

                    shop_title.text = order.shop?.title
                    shop_number.text = "店铺编号：${order.shop?.shopID}"
                    page_views.text = "浏览量：${order.shop?.formattedPageViews}"
                    publish_date.text = "发布时间：${order.shop?.formattedDate}"

                    size.text = order.shop?.formattedSize
                    rent.text = order.shop?.formattedRent
                    fee.text = order.shop?.formattedFee

                    category.text = order.shop?.industry
                    state.text = order.shop?.viewOpening
                    can_empty.text = order.shop?.viewCanEmpty


                    val tencentMap = map_view.getMap()
                    //中心点
                    val cp = CameraPosition(order.shop?.lat?.let {
                        order.shop?.lng?.let { it1 ->
                            LatLng(
                                it,
                                it1
                            )
                        }
                    }, 15f, 45f, 45f)
                    val cameraUpdate = CameraUpdateFactory.newCameraPosition(cp)
                    tencentMap.moveCamera(cameraUpdate)
                    //设置
                    val uiSettings = tencentMap.getUiSettings()
                    uiSettings.setLogoPosition(3)
                    //设置浮窗
                    val marker = tencentMap.addMarker(
                        MarkerOptions()
                            .position(order.shop?.lat?.let {
                                order.shop?.lng?.let { it1 ->
                                    LatLng(
                                        it,
                                        it1
                                    )
                                }
                            })
//                            .title("${bean.area_path_name}-${bean.address}")
                            .anchor(0.5f, 0.5f)
                            .icon(BitmapDescriptorFactory.defaultMarker())
                            .draggable(true)
                    )
                    marker.showInfoWindow()

                    if (order.shop?.isSuccess == 1) {
                        img_success.visibility = View.VISIBLE
                    } else {
                        img_success.visibility = View.GONE
                    }

                    bt_more.setOnClickListener {
                        MoreManagerDialog(order.shop?.shopID.toString(), 0, order.favorite).show(
                            supportFragmentManager,
                            MoreManagerDialog::class.java.name
                        )
                    }

                    order.shop?.formattedFacilities?.let { facilityItems ->
                        facilities.layoutManager =
                            GridLayoutManager(this@TransferOutOrderDetailActivity, 6)
                        facilities.adapter = FacilityAdapter(facilityItems)
                    }


                    var str1: String = order.shop?.description.toString()
                    expand_description.currentText = str1
                    expand_description.clickListener = object : ExpandTextView.ClickListener {
                        override fun onContentTextClick() {
                            expand_description.currentText = str1
                        }

                        override fun onSpecialTextClick(currentExpand: Boolean) {
                            expand_description.isExpand = !currentExpand
                        }
                    }

                    var strEnvironment: String = order.shop?.environment.toString()
                    expand_environment.currentText = strEnvironment
                    expand_environment.clickListener = object : ExpandTextView.ClickListener {
                        override fun onContentTextClick() {
                            expand_environment.currentText = strEnvironment
                        }

                        override fun onSpecialTextClick(currentExpand: Boolean) {
                            expand_environment.isExpand = !currentExpand
                        }
                    }

                    var strReason: String = order.shop?.reason.toString()
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
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${order.serviceAgent?.phone}")
                            startActivity(this)
                        }
                    }
                }


            recommend_orders.layoutManager =
                GridLayoutManager(this@TransferOutOrderDetailActivity, 2)
            recommend_orders.adapter = RecommendOrderAdapter(mutableListOf(), onItemSelect = {
                val intent = Intent(
                    this@TransferOutOrderDetailActivity,
                    TransferOutOrderDetailActivity::class.java
                )
                intent.putExtra("shopID", it?.shopID?.toInt())
                startActivity(intent)
            })

            viewModel.requestUserLikeShopList(
                cityId.toString(),
                intent.getIntExtra("shopID", 0).toString()
            )?.let {
                (recommend_orders.adapter as RecommendOrderAdapter).setMenuData(it)
            }


        }

    }


    override fun onStart() {
        super.onStart()
        map_view.onStart()
    }

    override fun onResume() {
        super.onResume()
        map_view.onResume()
    }

    override fun onPause() {
        super.onPause()
        map_view.onPause()
    }

    override fun onStop() {
        super.onStop()
        map_view.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        map_view.onDestroy()
    }


}