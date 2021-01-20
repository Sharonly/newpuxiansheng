package com.puxiansheng.www.ui.order

import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.ExpandTextView
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.*
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.bt_connect_kf
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.button_back
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.expand_description
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.facilities
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.page_views
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.publish_date
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.rent
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.shop_number
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.shop_title
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.size
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class TransferInOrderDetailActivity : MyBaseActivity() {
    private lateinit var viewModel: TransferInOrderDetailViewModel

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.color81,true)
        return R.layout.activity_transfer_in_order_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferInOrderDetailViewModel::class.java]
        initView()
    }

    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }

        button_more.setOnClickListener {
            lifecycleScope.launch {
                viewModel.requestTransferInOrderDetail(intent.getStringExtra("shopID"))
                    ?.let { order ->
                        MoreManagerDialog(order.shopID.toString(), order.title,order.image,"",1, order.favorite).show(
                            supportFragmentManager,
                            MoreManagerDialog::class.java.name
                        )
                    }
            }
        }

            lifecycleScope.launch {
                viewModel.requestTransferInOrderDetail(intent.getStringExtra("shopID"))
                    ?.let { order ->

                        if(order.checkId == 1 && order.status == 1){
                            button_more.visibility = View.VISIBLE
                        }else{
                            button_more.visibility = View.INVISIBLE
                        }

                        shop_title.text = order.title

                        publish_date.text = order.formattedDate

                        shop_number.text = "编号：${order.shopID}"

                        page_views.text = "浏览量：${order.formattedPageViews}"

//                        format_address.text =
//                            "地址： ${order.formattedLocationNodes} ${order.address?.addressDetail}"

                        format_address.text =
                            "地址： ${order.newAreaStr}"

                        rent.text = order.formattedRent

                        size.text = order.formattedSize

                        industry.text = order.formattedFinalIndustry

                        order.formattedFacilities?.let { facilityItems ->
                            facilities.layoutManager =
                                GridLayoutManager(this@TransferInOrderDetailActivity, 6)
                            facilities.adapter = FacilityAdapter(facilityItems)
                        }

                        bt_connect_kf.setOnClickListener {
//                            Intent(Intent.ACTION_DIAL).apply {
//                                data = Uri.parse("tel:${order.serviceAgent?.phone}")
//                                startActivity(this)
//                            }

                            if (SharedPreferencesUtil.get(API.LOGIN_USER_TOKEN, "").toString().isNotEmpty()) {
                                Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${order.serviceAgentPhone}")
                                    startActivity(this)
                                }
                            } else {
                                val intent = Intent(this@TransferInOrderDetailActivity, LoginActivity::class.java)
                                startActivity(intent)
                            }
                        }

                        var str1: String = order.description
                        expand_description.currentText = str1
                        expand_description.clickListener = object : ExpandTextView.ClickListener {
                            override fun onContentTextClick() {
                                expand_description.currentText = str1
                            }

                            override fun onSpecialTextClick(currentExpand: Boolean) {
                                expand_description.isExpand = !currentExpand
                            }
                        }

                    }


            }
        }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("TransferInOrderDetailActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("TransferInOrderDetailActivity")
//    }
}