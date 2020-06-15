package com.puxiansheng.www.ui.order

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
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
class TransferSuccessOrderActivity : MyBaseActivity() {
    private lateinit var viewModel: TransferOutOrdersViewModel

    override fun getLayoutId(): Int {
        return R.layout.activity_order_list
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[TransferOutOrdersViewModel::class.java]
        viewModel.type = 1
        initView()
    }


    private fun initView() {
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

        viewModel.refresh(
            SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
        )
        button_back.setOnClickListener {
            onBackPressed()
        }

        button_search.hint = "成功案例搜索"
        button_search.addTextChangedListener {
            viewModel.title = it.toString()
        }
        button_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && viewModel.title.isNotEmpty()) {
                hideKeyboard(button_search)
                order_list.removeAllViews()
                viewModel.refresh(
                    SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                )
                return@OnEditorActionListener true
            }
            false
        })

        selected_industry.setOnClickListener {
            SelectIndustryDialog(
                onSelectIndustry = { topMenuItem, secondMenuItem ->
                    selected_industry.text =
                        "${topMenuItem?.text ?: "行业"}${secondMenuItem?.text ?: ""}".apply {
                            if (this.isEmpty()) else {
                                viewModel.industryIDs = "" +
                                        "${topMenuItem?.menuID ?: ""}" +
                                        (if (topMenuItem == null) "" else ",") +
                                        "${secondMenuItem?.menuID ?: ""}"
                            }
                        }
                    viewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
                }
            ).show(supportFragmentManager, SelectIndustryDialog::class.java.name)
        }

        selected_area.setOnClickListener {
            SelectAreaDialog(
                onSelectArea = {
                    selected_area.text = (it?.text ?: "地区").apply {
                        if (this.isEmpty()) else {
                            viewModel.areaIDs = it?.nodeID.toString()
                        }
                    }
                    viewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
                }
            ).show(supportFragmentManager, SelectAreaDialog::class.java.name)
        }

        selected_size.setOnClickListener {
            SelectSizeRangeDialog(
                onSelectSize = {
                    selected_size.text = (it?.text ?: "面积").apply {
                        if (this.isEmpty()) else {
                            viewModel.sizeRangeID = it?.menuID.toString()
                        }
                    }
                    viewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
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
                    viewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
                }
            ).show(supportFragmentManager, SelectRentRangeDialog::class.java.name)
        }


        button_sort.setOnClickListener {
            SelectSortTypeDialog(
                onSelectSortType = {
                    it?.let {
                        viewModel.sortBy = it.sortBy
                        viewModel.sortType = it.sortType
                    }
                    viewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
                }
            ).show(supportFragmentManager, SelectSortTypeDialog::class.java.name)
        }


        DividerItemDecoration(
            this@TransferSuccessOrderActivity,
            DividerItemDecoration.VERTICAL
        ).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            order_list.addItemDecoration(it)
        }

        order_list.layoutManager = LinearLayoutManager(this@TransferSuccessOrderActivity)
        order_list.adapter = OrdersAdapter(
            type = Order.Type.TRANSFER_OUT.value(),
            onItemSelect = {
                val intent = Intent(this, TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID.toString())
                startActivity(intent)

            }
        ).apply {
            LivePagedListBuilder<Int, Order>(
                viewModel.getTransferOutOrdersFromLocal(),
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
                        if (type != Order.Type.TRANSFER_OUT.value()) {
                            type = Order.Type.TRANSFER_OUT.value()
                            notifyDataSetChanged()
                        }
                    }
                }

                pageBuilder.build().observe(this@TransferSuccessOrderActivity, Observer {
                    submitList(it)
                    notifyDataSetChanged()
                })
            }
        }

        refresh.setOnRefreshListener {
            viewModel.refresh(
                SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
            )
            refresh.isRefreshing = false
        }
    }

    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}