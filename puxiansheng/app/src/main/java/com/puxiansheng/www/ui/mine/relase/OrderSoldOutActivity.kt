package com.puxiansheng.www.ui.mine.relase

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.mine.relase.adapter.PublicOrdersAdapter
import com.puxiansheng.www.ui.mine.relase.adapter.ReleaseStateOrdersAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.synthetic.main.activity_my_all_release.button_back
import kotlinx.android.synthetic.main.activity_my_pulished.*
import kotlinx.android.synthetic.main.activity_order_list.order_list
import kotlinx.coroutines.launch

class OrderSoldOutActivity : MyBaseActivity() {
    private lateinit var viewModel: OrderSoldOutViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_my_pulished
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[OrderSoldOutViewModel::class.java]
        initView()
    }

    private fun initView() {
        bar_title.text = "已下架"
        button_back.setOnClickListener {
            onBackPressed()
        }
        DividerItemDecoration(
            this@OrderSoldOutActivity,
            DividerItemDecoration.VERTICAL
        ).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            order_list.addItemDecoration(it)
        }
        order_list.layoutManager = LinearLayoutManager(this@OrderSoldOutActivity)
        lifecycleScope.launch {
            viewModel.getRemoteSoldOutOrders().let {
                var orderType: Int = Order.Type.EMPTY.value()
                if (!it.isNullOrEmpty()) {
                    orderType = Order.Type.USER_PUBLIC_ORDER.value()
                }
                order_list.adapter =
                    ReleaseStateOrdersAdapter(
                        this@OrderSoldOutActivity,
                        onItemDelete = {
                            var deleteDialog = DeleteOrderDialog(
                                "确定要删除该条发布吗？",
                                Order.Type.TRANSFER_IN_PRIVATE.value(),
                                it?.shop?.shopID
                            )
                            deleteDialog.show(
                                supportFragmentManager,
                                DeleteOrderDialog::class.java.name
                            )
                            deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                                override fun onDiss() {

                                }
                            }
                        }, dataList = it as List<Order>, type = orderType
                    )
            }
        }
    }
}