package com.puxiansheng.www.ui.order

import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.PermissionUtils
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.BitMapUtil
import com.puxiansheng.www.common.ExpandTextView
import com.puxiansheng.www.common.ImageSwitcher
import com.puxiansheng.www.tools.ShareUtils
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.map.MapActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import com.puxiansheng.www.ui.order.dialog.ShopImageDialog
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_transfer_out_order_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class TransferOutOrderDetailActivity : MyBaseActivity(), OnRefreshLoadMoreListener {
    private val requestCodePermissions = 10
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    private lateinit var viewModel: TransferOutOrderDetailViewModel
    var currentPage = 1
    var type = 0
    var shopId = ""
    var shopImg = ""
    var shopTitle = ""
    var shareUrl = ""
    var shopBmp: Bitmap? = null
    var isFavor = false
    private var bitMapUtil :BitMapUtil? = null
    var cityId = 0
    var isLoading = false
    var images: List<BannerImage> = listOf()


    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this, true, R.color.color81, true)
        return R.layout.activity_transfer_out_order_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferOutOrderDetailViewModel::class.java]
        shopId = intent.getStringExtra("shopID")
        initView()
    }

    private fun initView() {
        PermissionUtils.requestPermission(this@TransferOutOrderDetailActivity, requiredPermissions)
//        ActivityCompat.requestPermissions(this, requiredPermissions, requestCodePermissions)
        cityId = SpUtils.get(API.USER_CITY_ID, 0) as Int
        bitMapUtil = BitMapUtil()
        button_back.setOnClickListener {
            onBackPressed()
        }
        refreshlayout.setOnRefreshLoadMoreListener(this@TransferOutOrderDetailActivity)

        scrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, y: Int, oldScrollX: Int, oldScrollY: Int ->
            /**  ScrollView 滚动动态改变标题栏 */
            if (y <= 0) { //未滑动
                toolbar.setBackgroundColor(Color.argb(0, 31, 100, 240))
                button_back.setColorFilter(Color.WHITE)
                if(isFavor) {
                    bt_favor.setColorFilter(Color.RED)
                }else{
                    bt_favor.setColorFilter(Color.WHITE)
                }
                bt_share.setColorFilter(Color.WHITE)
            } else if (y > 0 && y <=MyScreenUtil.dip2px(this,69f)) { //滑动过程中 并且在mHeight之内
                val scale = y / (MyScreenUtil.dip2px(this,69f)*1.0f)
                val alpha = 255 * scale
                toolbar.setBackgroundColor(Color.argb(alpha.toInt(), 255, 255, 255))
                button_back.setColorFilter(Color.argb(alpha.toInt(), 0, 0, 0))
                bt_favor.setColorFilter(Color.argb(alpha.toInt(), 0, 0, 0))
                bt_share.setColorFilter(Color.argb(alpha.toInt(), 0, 0, 0))
                bt_fast_top.visibility = View.GONE
            } else { //超过mHeight
                toolbar.setBackgroundColor(Color.WHITE)
                button_back.setColorFilter(Color.BLACK)
                if(isFavor) {
                    bt_favor.setColorFilter(Color.RED)
                }else{
                    bt_favor.setColorFilter(Color.BLACK)
                }
                bt_share.setColorFilter(Color.BLACK)
                bt_fast_top.visibility = View.VISIBLE
            }
            bt_connect_kf.rollBack()
        }


        bt_fast_top.setOnClickListener {
            scrollView.scrollTo(0,0)
        }

//        bt_more.setOnClickListener {
//            lifecycleScope.launch {
//                viewModel.requestTransferOutOrderDetail(
//                    shopId
//                )?.let { order ->
//                    if (order.images.isNullOrEmpty()) {
//                        shopImg = ""
//                    } else {
//                        shopImg = order.images?.get(0).toString()
//                    }
//                    MoreManagerDialog(
//                        order.shopID.toString(), order.title, shopImg, ""
//                        , type,
//                        order.favorite
//                    ).show(supportFragmentManager, MoreManagerDialog::class.java.name)
//                }
//            }
//        }

        bt_favor.setOnClickListener {
            if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                lifecycleScope.launch {
                    viewModel.favorite(
                        objectID = shopId,
                        type = type
                    )?.let { result ->
                        if (result.data?.result == 0) {
                            isFavor = false
                            bt_favor.setImageResource(R.mipmap.ic_favor_white)
//                            btFavor.text = "收藏"
                        } else {
                            isFavor = true
                            bt_favor.setImageResource(R.mipmap.ic_favor_red)
//                            btFavor.text = "取消收藏"
                        }
                        Toast.makeText(
                            this@TransferOutOrderDetailActivity,
                            result.msg,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }


        bt_share.setOnClickListener {
            if (shopImg.isNullOrEmpty()) {
                shopBmp = BitmapFactory.decodeResource(resources, R.mipmap.img_pxs_defult_small)
            } else {
                shopBmp = bitMapUtil?.returnBitMap(shopImg)
                val baos = ByteArrayOutputStream()
                shopBmp?.compress(Bitmap.CompressFormat.JPEG, 30, baos)
                if (shopBmp != null) {
                    shopBmp.let {
                        shopBmp = ShareUtils.compressScale(this, it!!)
                    }
                } else {
                    shopBmp = BitmapFactory.decodeResource(resources, R.mipmap.img_pxs_defult_small)
                }
            }
            Log.d("shareUrlAAA"," shopTitle = "+shopTitle+"  shopBmp = "+shopBmp+"  shareUrl = "+shareUrl)
            ShareUtils.share(this, shopTitle, shopBmp, shareUrl)
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
            if (Utils.isFastClick()) {
                ShopImageDialog.getInstance().setData(context = this, banersList = images)
                ShopImageDialog.getInstance().show(
                    supportFragmentManager,
                    ShopImageDialog::class.java.name
                )
            }
        }
        initData()




    }

    private fun initData(){
         lifecycleScope.launch {
//            viewModel.requestTransferOutOrderDetail(intent.getStringExtra("shopID"))

             viewModel.requestTransferOutOrderDetail(shopId)?.let { order ->
//                    if(order.shop?.images?.size == 0)
//                        imageSwitcher.setImages(list)
                 Log.d("shopcheck", " order.checkId = " + order.checkId)
                 if (order.images?.size == 0) {

                 } else {
                     order.images?.map { url ->
                         BannerImage(imageUrl = url)
                     }?.let { list ->
                         image_switcher.setImages(list, false)
                         img_index.text =
                             image_switcher.getCurrentPos().toString() + "/" + list.size
                         image_switcher.listener = object : ImageSwitcher.OnPageChange {
                             override fun onScrolled(index: Int) {
                                 img_index.text =
                                     image_switcher.getCurrentPos().toString() + "/" + list.size
                             }
                         }
                         images = list
                     }
                 }

                 if (order.images != null) {
                     if (order.images!!.isNotEmpty()) {
                         shopImg = order.images?.get(0).toString()
                     }
                 }
                 if (order.checkId == 1 && order.status == 1) {
                     bt_more.visibility = View.VISIBLE
                     bt_share.visibility = View.VISIBLE
                     bt_favor.visibility = View.VISIBLE
                 } else {
                     bt_more.visibility = View.INVISIBLE
                     bt_share.visibility = View.INVISIBLE
                     bt_favor.visibility = View.INVISIBLE
                 }
                 if (order.isSuccess != 1) {
                     type = 0
                     bt_favor.visibility = View.VISIBLE
                 } else {
                     type = 999
                     bt_favor.visibility = View.INVISIBLE
                 }
                 if (order.favorite == 1) {
                     isFavor = true
                     bt_favor.setImageResource(R.mipmap.ic_favor_red)
                 } else {
                     isFavor = false
                     bt_favor.setImageResource(R.mipmap.ic_favor_white)
                 }
                 shopTitle = order.title
                 shop_title.text = order.title
                 shop_number.text = "店铺编号：${order.shopID}"
                 page_views.text = "浏览量：${order.formattedPageViews}"
                 publish_date.text = "更新时间：${order.formattedDate}"

                 rent.text = order.formattedRent
                 size.text = order.formattedSize
                 fee.text = order.formattedTransferFee

                 category.text = order.categoryStr
                 state.text = order.viewOpening
                 can_empty.text = order.viewCanEmpty

                 address.text = order.address?.addressDetail

                 address.setOnClickListener {
                     if (order.lat.isNullOrEmpty() || order.lng.isNullOrEmpty()) {
                         Toast.makeText(
                             this@TransferOutOrderDetailActivity,
                             "当前店铺没有获取定位",
                             Toast.LENGTH_SHORT
                         ).show()
                     } else {
                         val intent =
                             Intent(this@TransferOutOrderDetailActivity, MapActivity::class.java)
//                        intent.putExtra("location", order.lat + "," + order.lng)
                         intent.putExtra("lat", order.lat!!.toDouble())
                         intent.putExtra("lng", order.lng!!.toDouble())
                         intent.putExtra("destination", order.address?.addressDetail)
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
//                    recommend_orders.visibility = View.GONE
                     bg_map.visibility = View.GONE
                     bt_connect_kf.visibility = View.INVISIBLE
                 } else {
                     img_success.visibility = View.GONE
//                    recommend_orders.visibility = View.VISIBLE
                     bg_map.visibility = View.VISIBLE
                     bt_connect_kf.visibility = View.VISIBLE
                 }


                 order.formattedFacilities?.let { facilityItems ->
                     facilities.layoutManager =
                         GridLayoutManager(this@TransferOutOrderDetailActivity, 6)
                     facilities.adapter = FacilityAdapter(facilityItems)
                 }

//                var str1: String = order.description
//                expand_description.loadDataWithBaseURL(null,order.description, "text/html", "utf-8", null)
//                bt_show_more.setOnClickListener {
//                    if(!showMore){
//                        showMore = true
//                        val webParams = web.layoutParams
//                        webParams.height = TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_DIP,
//                            700f,
//                            resources.displayMetrics).toInt()
//                        web.layoutParams = webParams
//
//                        val linearParams = expand_description.layoutParams
//                        linearParams.height = TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_DIP,
//                            700f,
//                            resources.displayMetrics).toInt()
//                        expand_description.layoutParams = linearParams
//                        bt_show_more.text = "收起"
//                    }else{
//                        showMore = false
//                        val webParams = web.layoutParams
//                        webParams.height = TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_DIP,
//                            74f,
//                            resources.displayMetrics).toInt()
//                        web.layoutParams = webParams
//                        val linearParams = expand_description.layoutParams
//                        linearParams.height = TypedValue.applyDimension(
//                            TypedValue.COMPLEX_UNIT_DIP,
//                            74f,
//                            resources.displayMetrics
//                        ).toInt()
//
//                        expand_description.layoutParams = linearParams
//                        bt_show_more.text = "展开"
//                    }
//                }

                 var str1: String = order.description
                 expand_description.text = str1

//                expand_description.currentText = str1
//                expand_description.clickListener = object : ExpandTextView.ClickListener {
//                    override fun onContentTextClick() {
//                        expand_description.currentText = str1
//                    }
//
//                    override fun onSpecialTextClick(currentExpand: Boolean) {
//                        expand_description.isExpand = !currentExpand
//                    }
//                }

//                var strEnvironment: String = order.environment.toString()
//                expand_environment.currentText = strEnvironment
//                expand_environment.clickListener = object : ExpandTextView.ClickListener {
//                    override fun onContentTextClick() {
//                        expand_environment.currentText = strEnvironment
//                    }
//
//                    override fun onSpecialTextClick(currentExpand: Boolean) {
//                        expand_environment.isExpand = !currentExpand
//                    }
//                }
//
//                var strReason: String = order.reason.toString()
//                expand_reason.currentText = strReason
//                expand_reason.clickListener = object : ExpandTextView.ClickListener {
//                    override fun onContentTextClick() {
//                        expand_reason.currentText = strReason
//                    }
//
//                    override fun onSpecialTextClick(currentExpand: Boolean) {
//                        expand_reason.isExpand = !currentExpand
//                    }
//                }

                 bt_connect_kf.setOnClickListener {
                     if (SpUtils.get(API.LOGIN_USER_TOKEN, "").toString()
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

                 viewModel.getConfigInfo("transfer_share_url")?.let { configInfo ->
                     shareUrl = "$configInfo$shopId.html"
                 }

             }


             if (recommend_orders.visibility == View.VISIBLE) {
                 recommend_orders.layoutManager =
                     GridLayoutManager(this@TransferOutOrderDetailActivity, 2)
                 recommend_orders.adapter = RecommendOrderAdapter(arrayListOf(), onItemSelect = {
                     val intent = Intent(
                         this@TransferOutOrderDetailActivity,
                         TransferOutOrderDetailActivity::class.java
                     )
                     intent.putExtra("shopID", it?.shopID.toString())
                     startActivity(intent)
                 })
                 isLoading = true
                 viewModel.requestUserLikeShopList(
                     cityId.toString(), shopId,
                     currentPage
                 )?.let {
                     (recommend_orders.adapter as RecommendOrderAdapter).setMenuData(it, true)
                     isLoading = false
                 }
             }
         }
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        initData()
        refreshLayout.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if(!isLoading) {
            isLoading= true
            currentPage += 1
            lifecycleScope.launch {
                viewModel.requestUserLikeShopList(
                    cityId.toString(), shopId,
                    currentPage
                )?.let {
                    if (it.isNotEmpty()) {
                        (recommend_orders.adapter as RecommendOrderAdapter).setMenuData(it, false)
                    } else {
                        Toast.makeText(
                            this@TransferOutOrderDetailActivity,
                            "数据全部加装完毕",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    isLoading= false
                }
            }
        }
        refreshLayout.finishLoadMore(1000)
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("TransferOutOrderDetailActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("TransferOutOrderDetailActivity")
//    }

}