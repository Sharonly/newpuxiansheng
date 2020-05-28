package com.puxiansheng.www.ui.search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.info.InfoListAdapter
import com.puxiansheng.www.ui.info.InfoListViewModel
import com.puxiansheng.www.ui.order.*
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.launch


class SearchActivity : MyBaseActivity() {

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var orderOutViewModel: TransferOutOrdersViewModel
    private lateinit var orderInViewModel: TransferInOrdersViewModel
    private lateinit var articleViewModel: InfoListViewModel
    var typeList = listOf("转铺", "找店", "百科", "消息")

    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }

    override fun business() {
        searchViewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        orderOutViewModel = ViewModelProvider(this)[TransferOutOrdersViewModel::class.java]
        orderInViewModel = ViewModelProvider(this)[TransferInOrdersViewModel::class.java]
        articleViewModel = ViewModelProvider(this)[InfoListViewModel::class.java]

        lifecycleScope.launch {
            searchViewModel.getHistorySearch()?.let { it ->
                it.forEach { menuItem ->
                    history_list.addView(Chip(history_list.context).apply {
                        text = menuItem.keyword
                    })
                }
            }

            searchViewModel.getRecommendSearch()?.let {
                it.forEach { menuItem ->
                    recommend_list.addView(Chip(history_list.context).apply {
                        text = menuItem.name
                    })
                }
            }
        }

        bt_cancel.setOnClickListener {
            orderOutViewModel.title = ""
            orderInViewModel.title = ""
            articleViewModel.title = ""
            if(list.isVisible){
                list.removeAllViews()
                layout_recommend.visibility = View.VISIBLE
                search_refresh.visibility = View.GONE
            }
        }

        search_content.addTextChangedListener {
            when (searchViewModel.type) {
                0 -> orderOutViewModel.title = it.toString()
                1 -> orderInViewModel.title = it.toString()
                2 -> articleViewModel.title = it.toString()
            }

            searchViewModel.searchTitle = it.toString()
        }

        search_content.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard(search_content)
                layout_recommend.visibility = View.GONE
                search_refresh.visibility = View.VISIBLE
                list.removeAllViews()
                Log.d(
                    "---search",
                    " IME_ACTION_SEARCH  searchViewModel.type = " + searchViewModel.type
                )
                when (searchViewModel.type) {
                    0 -> orderOutViewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
                    1 -> orderInViewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )
                    2 -> articleViewModel.refresh(0)
                    3 -> orderOutViewModel.refresh(
                        SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                    )

                }
                return@OnEditorActionListener true
            }
            false
        })

        DividerItemDecoration(this, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        search_refresh.setOnRefreshListener {
            list.removeAllViews()
            when (searchViewModel.type) {
                0 -> orderOutViewModel.refresh(
                    SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                )
                1 -> orderInViewModel.refresh(
                    SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                )
                2 -> articleViewModel.refresh(0)
                3 -> orderOutViewModel.refresh(
                    SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
                )
            }
            search_refresh.isRefreshing = false
        }

        list.layoutManager = LinearLayoutManager(this@SearchActivity)

        if (searchViewModel.type == 0) {
            list.adapter = OrdersAdapter(
                type = Order.Type.TRANSFER_OUT.value(),
                onItemSelect = {
                    val intent =
                        Intent(this@SearchActivity, TransferOutOrderDetailActivity::class.java)
                    intent.putExtra("shopID", it?.shop?.shopID?.toInt())
                    startActivity(intent)

                }
            ).apply {
                LivePagedListBuilder<Int, Order>(
                    orderOutViewModel.getTransferOutOrdersFromLocal(),
                    PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setPageSize(10)
                        .setInitialLoadSizeHint(20)
                        .build()
                ).let { pageBuilder ->
                    pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
                        override fun onItemAtEndLoaded(itemAtEnd: Order) {
                            super.onItemAtEndLoaded(itemAtEnd)
                            orderOutViewModel.loadMore()
                        }
                    })
                    addLoadStateListener { loadType, _, _ ->
                        if (loadType == PagedList.LoadType.END) {
                            if (getDataCount() == 0) {
                                type = Order.Type.EMPTY.value()
                                notifyDataSetChanged()
                                orderOutViewModel.refresh(
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

                    pageBuilder.build().observe(this@SearchActivity, Observer {
                        submitList(it)
                        notifyDataSetChanged()
                    })
                }
            }
        } else if (searchViewModel.type == 1) {
            list.adapter = OrdersAdapter(
                type = Order.Type.TRANSFER_IN.value(),
                onItemSelect = {
                    val intent = Intent(this, TransferInOrderDetailActivity::class.java)
                    intent.putExtra("shopID", it?.shop?.shopID?.toInt())
                    startActivity(intent)
                }
            ).apply {
                LivePagedListBuilder<Int, Order>(
                    orderInViewModel.getTransferInOrdersFromLocal(),
                    PagedList.Config.Builder()
                        .setEnablePlaceholders(true)
                        .setPageSize(10)
                        .setInitialLoadSizeHint(20)
                        .build()
                ).let { pageBuilder ->
                    pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
                        override fun onItemAtEndLoaded(itemAtEnd: Order) {
                            super.onItemAtEndLoaded(itemAtEnd)
                            orderInViewModel.loadMore()
                        }
                    })
                    addLoadStateListener { loadType, _, _ ->
                        if (loadType == PagedList.LoadType.END) {
                            if (getDataCount() == 0) {
                                type = Order.Type.EMPTY.value()
                                notifyDataSetChanged()
                                orderInViewModel.refresh(
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

                    pageBuilder.build().observe(this@SearchActivity, Observer {
                        submitList(it)
                        notifyDataSetChanged()
                    })
                }
            }
        } else if (searchViewModel.type == 2) {
            InfoListAdapter(onItemSelect = { info ->
                val intent = Intent(this@SearchActivity, InfoDetailActivity::class.java)
                intent.putExtra("url", info?.jump_param)
                startActivity(intent)
            }).let { adapter ->
                list.adapter = adapter
                lifecycleScope.launch {
                    LivePagedListBuilder<Int, InfoItem>(
                        articleViewModel.getInfoByCategoryFromRoom(category = 0),
                        5
                    ).apply {
                        setBoundaryCallback(object : PagedList.BoundaryCallback<InfoItem>() {
                            override fun onItemAtEndLoaded(itemAtEnd: InfoItem) {
                                super.onItemAtEndLoaded(itemAtEnd)
                                articleViewModel.loadMore(
                                    category = 0
                                )
                            }
                        })
                    }.build().observe(this@SearchActivity, Observer {
                        adapter.submitList(it)
                    })
                }
            }
        }


        var arrayAdapter = ArrayAdapter(this, R.layout.item_select, typeList)
        type_spinner.adapter = arrayAdapter
        arrayAdapter.setDropDownViewResource(R.layout.item_pulldown_select)

        bt_delete.setOnClickListener {
            searchViewModel.deleteHistorySearch()
        }

        searchViewModel.toastMsg.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        searchViewModel.deleteResult.observe(this, Observer {
            if (it == API.CODE_SUCCESS) {
                history_list.removeAllViews()
            }
        })

        type_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //切换选择
                searchViewModel.type = position
                Log.d("---search", "  searchViewModel.type = " + searchViewModel.type)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}