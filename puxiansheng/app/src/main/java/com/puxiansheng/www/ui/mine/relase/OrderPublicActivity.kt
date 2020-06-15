package com.puxiansheng.www.ui.mine.relase

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.mine.relase.adapter.ReleaseStateOrdersAdapter
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
        var adapter =
                ReleaseStateOrdersAdapter(
                        this@OrderPublicActivity,
                        onItemFresh = { order ->
                            pulishedViewModel.shopId = order?.shop?.shopID.toString()
                            pulishedViewModel.type = order?.shop?.data_type.toString()
                            lifecycleScope.launch {
                                pulishedViewModel.refreshShopFromRemote().let {it
//                                    if (it?.code == API.CODE_SUCCESS)
                                        Toast.makeText(this@OrderPublicActivity, it?.msg, Toast.LENGTH_SHORT)
                                                .show()
                                }
                            }
                        },
                        onItemDelete = { order ->
                            var orderType = Order.Type.TRANSFER_OUT_PRIVATE.value()
                            if(order?.shop?.data_type=="transfer_shop"){
                                 orderType = Order.Type.TRANSFER_OUT_PRIVATE.value()
                            }else{
                                orderType = Order.Type.TRANSFER_IN_PRIVATE.value()
                            }
                            var deleteDialog = DeleteOrderDialog(
                                    "确定要删除该条发布吗？",
                                orderType,
                                order?.shop?.shopID
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
                        }
                )
        lifecycleScope.launch {

            pulishedViewModel.getRemoteUserPublicOrders().let { it ->
                var orderType: Int = Order.Type.EMPTY.value()
                if (!it.isNullOrEmpty()) {
                    show_null.visibility = View.GONE
                    orderType = Order.Type.USER_PUBLIC_ORDER.value()
                    adapter.setType(orderType)
                    adapter.setMenuData(it as List<Order>)
                    order_list.adapter = adapter
                }else{
                    orderType =  Order.Type.EMPTY.value()
                    adapter.setType(orderType)
                    show_null.visibility = View.VISIBLE
                }
            }
        }
    }
}