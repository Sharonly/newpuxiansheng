package com.puxiansheng.www.ui.mine.relase

import android.content.Intent
import android.view.View
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
import kotlinx.android.synthetic.main.activity_my_pulished.*
import kotlinx.coroutines.launch

class OrderPublicActivity : MyBaseActivity() {
    private lateinit var pulishedViewModel: OrderPublicViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_my_pulished
    }

    override fun business() {
        pulishedViewModel = ViewModelProvider(this)[OrderPublicViewModel::class.java]
        initView()
    }


    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }
        DividerItemDecoration(
            this@OrderPublicActivity,
            DividerItemDecoration.VERTICAL
        ).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            order_list.addItemDecoration(it)
        }

        order_list.layoutManager = LinearLayoutManager(this@OrderPublicActivity)
        lifecycleScope.launch {
            pulishedViewModel.getRemoteUserPublicOrders().let {
                var orderType: Int = Order.Type.EMPTY.value()
                if (!it.isNullOrEmpty()) {
                    orderType = Order.Type.USER_PUBLIC_ORDER.value()
                    order_list.adapter =
                        ReleaseStateOrdersAdapter(
                            this@OrderPublicActivity,
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
                                        pulishedViewModel.refresh()
                                    }
                                }
                            }, dataList = it as List<Order>, type = orderType
                        )
                }else{
                    show_null.visibility = View.VISIBLE
                }
            }
        }
    }
}