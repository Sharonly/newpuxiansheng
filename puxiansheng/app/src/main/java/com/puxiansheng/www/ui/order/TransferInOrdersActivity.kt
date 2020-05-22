package com.puxiansheng.www.ui.order

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.dialog.*
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class TransferInOrdersActivity : MyBaseActivity() {

    private lateinit var viewModel: TransferInOrdersViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_order_list
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferInOrdersViewModel::class.java]
        initView()
    }

    fun initView() {
        viewModel.industryIDs = ""
        viewModel.areaIDs = ""
        viewModel.sizeRangeID = ""
        viewModel.rentIDs = ""
        viewModel.sortBy = ""
        viewModel.sortType = ""

        selected_industry.text = "行业"

        selected_area.text = "区域"

        selected_size.text = "面积"

        selected_rent.text = "租金"

        viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString())

        button_search.hint = "找店搜索"

        button_back.setOnClickListener {
            onBackPressed()
        }

        button_sort.setOnClickListener {

            SelectSortTypeDialog(
                onSelectSortType = {
                    it?.let {
                        viewModel.sortBy = it.sortBy
                        viewModel.sortType = it.sortType
                    }
                    viewModel.refresh(
                        SharedPreferencesUtil.get(
                            API.USER_CITY_ID, 0
                        ).toString()
                    )
                }
            ).show(supportFragmentManager, SelectSortTypeDialog::class.java.name)
        }


        selected_industry.setOnClickListener {
            SelectIndustryDialog(
                onSelectIndustry = { topMenuItem, secondMenuItem ->
                    selected_industry.visibility = View.VISIBLE
                    selected_industry.text =
                        "${topMenuItem?.text ?: ""}${secondMenuItem?.text ?: ""}".apply {
                            if (this.isEmpty()) selected_industry.visibility = View.GONE else {
                                viewModel.industryIDs = "" +
                                        "${topMenuItem?.menuID ?: ""}" +
                                        (if (topMenuItem == null) "" else ",") +
                                        "${secondMenuItem?.menuID ?: ""}"
                            }
                        }
                    viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString())
                }
            ).show(supportFragmentManager, SelectIndustryDialog::class.java.name)
        }

        selected_area.setOnClickListener {
            SelectAreaDialog(
                onSelectArea = {
                    selected_area.visibility = View.VISIBLE
                    selected_area.text = (it?.text ?: "").apply {
                        if (this.isEmpty()) selected_area.visibility = View.GONE else {
                            viewModel.areaIDs = it?.nodeID.toString()
                        }
                    }
                    viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString())
                }
            ).show(supportFragmentManager, SelectAreaDialog::class.java.name)
        }

        selected_size.setOnClickListener {
            SelectSizeRangeDialog(
                onSelectSize = {
                    selected_size.visibility = View.VISIBLE
                    selected_size.text = (it?.text ?: "").apply {
                        if (this.isEmpty()) selected_size.visibility = View.GONE else {
                            viewModel.sizeRangeID = it?.menuID.toString()
                        }
                    }
                    viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString())
                }
            ).show(supportFragmentManager, SelectSizeRangeDialog::class.java.name)
        }

        selected_rent.setOnClickListener {
            SelectRentRangeDialog(
                onSelectRent = {
                    it?.let {
                        selected_rent.text = (it?.text ?: "租金").apply {
                            if (this.isEmpty()) else {
                                viewModel.rentIDs = it?.menuID.toString()
                            }
                        }
                    }
                    viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString())
                }
            ).show(supportFragmentManager, SelectRentRangeDialog::class.java.name)
        }

        DividerItemDecoration(this@TransferInOrdersActivity, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            order_list.addItemDecoration(it)
        }

        order_list.layoutManager = LinearLayoutManager(this@TransferInOrdersActivity)
        order_list.adapter = OrdersAdapter(
            type = Order.Type.TRANSFER_IN.value(),
            onItemSelect = {
                val intent = Intent(this, TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt())
                startActivity(intent)
            }
        ).apply {
            LivePagedListBuilder<Int, Order>(
                viewModel.getTransferInOrdersFromLocal(),
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
                                SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                            )
                        }
                    }
                    if (loadType == PagedList.LoadType.REFRESH) {
                        if (type != Order.Type.TRANSFER_IN.value()) {
                            type = Order.Type.TRANSFER_IN.value()
                            notifyDataSetChanged()
                        }
                    }
                }

                pageBuilder.build().observe(this@TransferInOrdersActivity, Observer {
                    submitList(it)
                    notifyDataSetChanged()
                })
            }
        }


        refresh.setOnRefreshListener {
            viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString())
            refresh.isRefreshing = false
        }
    }


}