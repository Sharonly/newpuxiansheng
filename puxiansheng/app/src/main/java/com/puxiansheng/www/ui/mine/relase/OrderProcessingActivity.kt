package com.puxiansheng.www.ui.mine.relase

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.OrdersAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.android.synthetic.main.activity_my_all_release.button_back
import kotlinx.android.synthetic.main.activity_my_pulished.*
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.activity_order_list.order_list
import kotlinx.android.synthetic.main.activity_order_list.refresh

class OrderProcessingActivity : MyBaseActivity() {
    private lateinit var viewModel: OrderProcessingViewModel
    override fun getLayoutId(): Int {
        return R.layout.activity_my_pulished
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[OrderProcessingViewModel::class.java]
        initView()
    }


    private fun initView() {
        bar_title.text = "处理中"
        button_back.setOnClickListener {
            onBackPressed()
        }
        DividerItemDecoration(
            this@OrderProcessingActivity,
            DividerItemDecoration.VERTICAL
        ).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            order_list.addItemDecoration(it)
        }

        order_list.layoutManager = LinearLayoutManager(this@OrderProcessingActivity)
        order_list.adapter = OrdersAdapter(
            type = Order.Type.TRANSFER_OUT.value(),
            onItemSelect = {
                val intent = Intent(this, TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt())
                startActivity(intent)

            }
        ).apply {
            LivePagedListBuilder<Int, Order>(
                viewModel.getMineTransferInOrdersFromLocal(),
                PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setPageSize(10)
                    .setInitialLoadSizeHint(20)
                    .build()
            ).let { pageBuilder ->
                pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
                    override fun onItemAtEndLoaded(itemAtEnd: Order) {
                        super.onItemAtEndLoaded(itemAtEnd)
                        viewModel.loadMore()
                    }
                })
                addLoadStateListener { loadType, _, _ ->
                    if (loadType == PagedList.LoadType.END) {
                        if (getDataCount() == 0) {
                            type = Order.Type.EMPTY.value()
                            notifyDataSetChanged()
                            viewModel.refresh(
                            )
                        }
                    }
                    if (loadType == PagedList.LoadType.REFRESH) {
                        notifyDataSetChanged()
                    }
                }

                pageBuilder.build().observe(this@OrderProcessingActivity, Observer {
                    submitList(it)
                    notifyDataSetChanged()
                })
            }
        }

        refresh.setOnRefreshListener {
            viewModel.refresh(
            )
            refresh.isRefreshing = false
        }


    }
}