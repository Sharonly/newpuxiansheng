package com.puxiansheng.www.ui.order

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.order.dialog.*
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_new_order_list.*

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class NewTransferOutOrdersActivity : MyBaseActivity(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: NewTransferOutOrdersViewModel
    var adapter: ListOrdersAdapter? = null
    private var isRefresh = true
    private var mContext: Context? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_new_order_list
    }

    override fun business() {
        mContext = this
        MobclickAgent.onEvent(mContext, UMengKeys.LOGIN_USER_ID, SharedPreferencesUtil.get(
            API.LOGIN_USER_ID,
            0
        ).toString())
        viewModel = ViewModelProvider(this)[NewTransferOutOrdersViewModel::class.java]
        if (NetUtil.isNetworkConnected(this)) {
            viewModel.currentCity = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
            initView()
        }
    }


//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("NewTransferOutOrdersActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("NewTransferOutOrdersActivity")
//    }


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


        refreshlayout.setOnRefreshLoadMoreListener(this@NewTransferOutOrdersActivity)

        button_back.setOnClickListener {
            onBackPressed()
        }

        button_search.addTextChangedListener {
            viewModel.title = it.toString()
        }
        button_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && viewModel.title.isNotEmpty()) {
                hideKeyboard(button_search)
                order_list.removeAllViews()
                isRefresh = true
                lifecycleScope.launch {
                    viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                            adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                    }
                }
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
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectIndustryDialog::class.java.name)
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_TYPE, "行业")
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_STRING, selected_industry.text.toString())
        }

        selected_area.setOnClickListener {
            SelectAreaDialog(
                onSelectArea = {
                    selected_area.text = (it?.text ?: "地区").apply {
                        if (this.isEmpty()) else {
                            viewModel.areaIDs = it?.nodeID.toString()
                        }
                    }
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectAreaDialog::class.java.name)
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_TYPE, "地区")
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_STRING, selected_area.text.toString())
        }

        selected_size.setOnClickListener {
            SelectSizeRangeDialog(
                onSelectSize = {
                    selected_size.text = (it?.text ?: "面积").apply {
                        if (this.isEmpty()) else {
                            viewModel.sizeRangeID = it?.menuID.toString()
                        }
                    }
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectSizeRangeDialog::class.java.name)
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_TYPE, "面积")
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_STRING, selected_size.text.toString())
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
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectRentRangeDialog::class.java.name)
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_TYPE, "租金")
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_STRING, selected_rent.text.toString())
        }


        button_sort.setOnClickListener {
            SelectSortTypeDialog(
                onSelectSortType = {
                    it?.let {
                        viewModel.sortBy = it.sortBy
                        viewModel.sortType = it.sortType
                    }
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectSortTypeDialog::class.java.name)
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_TYPE, viewModel.sortType)
            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_SORT,  viewModel.sortBy)
        }


        DividerItemDecoration(
            this@NewTransferOutOrdersActivity,
            DividerItemDecoration.VERTICAL
        ).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            order_list.addItemDecoration(it)
        }

        order_list.layoutManager = LinearLayoutManager(this@NewTransferOutOrdersActivity)
        adapter = ListOrdersAdapter(this@NewTransferOutOrdersActivity, arrayListOf())
        order_list.adapter = adapter


        if (NetUtil.isNetworkConnected(this)) {
            not_network.visibility = View.GONE
            if (intent.getStringExtra("title") != "*") {
                button_search.setText(intent.getStringExtra("title"))
                viewModel.title = intent.getStringExtra("title")
                order_list.removeAllViews()
                isRefresh = true
                viewModel.currentPage = 1
                lifecycleScope.launch {
                    viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                            adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                    }
                }
            } else {
                isRefresh = true
                viewModel.currentPage = 1
                lifecycleScope.launch {
                    viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                            adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                    }
                }
            }
        } else {
            not_network.visibility = View.VISIBLE
            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
        }

//        lifecycleScope.launch {
//            viewModel.getTransferOutOrdersFromRemote().let {
//                adapter?.addList(it as ArrayList<OrderDetailObject>, isRefresh)
//            }
//        }

    }

    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        isRefresh = false
        viewModel.currentPage += 1
        lifecycleScope.launch {
            viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                if(list.isNotEmpty()) {
                    adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                }
            }
        }
        refreshLayout.finishLoadMore()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        isRefresh = true
        viewModel.currentPage = 1
        if (NetUtil.isNetworkConnected(this)) {
            lifecycleScope.launch {
                viewModel.getTransferOutOrdersFromRemote()?.let {list ->
                    if(list.isNotEmpty()) {
                        adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                    }
                }
            }
        } else {
            not_network.visibility = View.VISIBLE
            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
        }
        refreshLayout.finishRefresh()
    }


}