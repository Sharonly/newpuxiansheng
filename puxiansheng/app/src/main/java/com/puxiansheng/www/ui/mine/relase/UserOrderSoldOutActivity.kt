package com.puxiansheng.www.ui.mine.relase


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.mine.relase.adapter.UserOrderStateAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_my_pulished.button_back
import kotlinx.android.synthetic.main.activity_user_pulished.*
import kotlinx.coroutines.launch

class UserOrderSoldOutActivity : MyBaseActivity(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: OrderSoldOutViewModel
    var adapter: UserOrderStateAdapter? = null
    private var isRefresh = true
    override fun getLayoutId(): Int {
        return R.layout.activity_user_pulished
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
                this@UserOrderSoldOutActivity,
                DividerItemDecoration.VERTICAL
        ).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        refreshlayout.setOnRefreshLoadMoreListener(this@UserOrderSoldOutActivity)
        adapter =
                UserOrderStateAdapter(this@UserOrderSoldOutActivity, arrayListOf(), deleteListener = object : UserOrderStateAdapter.DeleteListener {
                    override fun delete(order: OrderDetailObject) {
                        var orderType = Order.Type.TRANSFER_OUT_PRIVATE.value()
                        if (order?.data_type == "transfer_shop") {
                            orderType = Order.Type.TRANSFER_OUT_PRIVATE.value()
                        } else {
                            orderType = Order.Type.TRANSFER_IN_PRIVATE.value()
                        }
                        var deleteDialog = DeleteOrderDialog("确定要删除该条发布吗？", orderType, order?.shopID)
                        deleteDialog.show(supportFragmentManager, DeleteOrderDialog::class.java.name)
                        deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                            override fun onDiss() {
                                isRefresh = true
                                lifecycleScope.launch {
                                    viewModel.getRemoteSoldOutOrders().let { list ->
                                        if (!list.isNullOrEmpty()) {
                                            adapter?.addList(
                                                list as ArrayList<OrderDetailObject>,
                                                isRefresh
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                )
        list.layoutManager = LinearLayoutManager(this@UserOrderSoldOutActivity)
        list.adapter = adapter

        if (NetUtil.isNetworkConnected(this)) {
            lifecycleScope.launch {
                viewModel.getRemoteSoldOutOrders().let { list ->
                    if (!list.isNullOrEmpty()) {
                        adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                    }
                }
            }
        } else {
            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        isRefresh = true
        lifecycleScope.launch {
            viewModel.getRemoteSoldOutOrders().let { list ->
                if (!list.isNullOrEmpty()) {
                    adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                }
            }
        }
        refreshLayout.finishRefresh(1000)
    }

}