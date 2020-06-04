package com.puxiansheng.www.ui.order

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.common.ExpandTextView
import com.puxiansheng.www.ui.order.dialog.MoreManagerDialog
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.*
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.button_back
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.expand_description
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.facilities
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.page_views
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.publish_date
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.rent
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.shop_number
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.shop_title
import kotlinx.android.synthetic.main.activity_transfer_in_order_detail.size
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class TransferInOrderDetailActivity : MyBaseActivity() {
    private lateinit var viewModel: TransferInOrderDetailViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_transfer_in_order_detail
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferInOrderDetailViewModel::class.java]
        initView()
    }

    private fun initView(){
        button_back.setOnClickListener {
           onBackPressed()
        }

        lifecycleScope.launch {
            viewModel.requestTransferInOrderDetail(intent.getIntExtra("shopID", 0).toString())
                ?.let { order ->
                    button_more.setOnClickListener {
                        MoreManagerDialog(order.shop?.shopID.toString(),1,order.favorite).show(
                            supportFragmentManager,
                            MoreManagerDialog::class.java.name
                        )
                    }
                    shop_title.text = order.shop?.title

                    publish_date.text = order.shop?.formattedDate

                    shop_number.text = "编号：${order.shop?.shopID}"

                    page_views.text = "浏览量：${order.shop?.formattedPageViews}"

                    format_address.text =  "地址： ${order.shop?.formattedLocationNodes} ${order.shop?.address?.addressDetail}"

                    rent.text = order.shop?.formattedRent

                    size.text = order.shop?.formattedSize

                    industry.text = order.shop?.formattedFinalIndustry

                    order.shop?.formattedFacilities?.let { facilityItems ->
                        facilities.layoutManager = GridLayoutManager(this@TransferInOrderDetailActivity, 6)
                        facilities.adapter = FacilityAdapter(facilityItems)
                    }

                    bt_connect_kf.setOnClickListener {
                        Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${order.serviceAgent?.phone}")
                            startActivity(this)
                        }
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

                }
        }
    }
}