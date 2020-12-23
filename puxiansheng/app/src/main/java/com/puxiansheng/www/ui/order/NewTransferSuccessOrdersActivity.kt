//package com.puxiansheng.www.ui.order
//
//import android.content.Context
//import android.util.Log
//import android.view.View
//import android.view.inputmethod.EditorInfo
//import android.view.inputmethod.InputMethodManager
//import android.widget.TextView
//import android.widget.Toast
//import androidx.core.widget.addTextChangedListener
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.lifecycleScope
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.puxiansheng.logic.api.API
//import com.puxiansheng.logic.bean.http.OrderDetailObject
//import com.puxiansheng.util.ext.NetUtil
//import com.puxiansheng.util.ext.SharedPreferencesUtil
//import com.puxiansheng.www.R
//import com.puxiansheng.www.app.MyBaseActivity
//import com.puxiansheng.www.tools.UMengKeys
//import com.puxiansheng.www.ui.order.dialog.*
//import com.scwang.smartrefresh.layout.api.RefreshLayout
//import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
//import com.umeng.analytics.MobclickAgent
//import kotlinx.android.synthetic.main.activity_new_order_list.*
//import kotlinx.android.synthetic.main.activity_new_order_list.button_back
//import kotlinx.android.synthetic.main.activity_new_order_list.button_search
//import kotlinx.android.synthetic.main.activity_new_order_list.button_sort
//import kotlinx.android.synthetic.main.activity_new_order_list.order_list
//import kotlinx.android.synthetic.main.activity_new_order_list.selected_area
//import kotlinx.android.synthetic.main.activity_new_order_list.selected_industry
//import kotlinx.android.synthetic.main.activity_new_order_list.selected_rent
//import kotlinx.android.synthetic.main.activity_new_order_list.selected_size
//import kotlinx.android.synthetic.main.activity_order_list.*
//
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.ObsoleteCoroutinesApi
//import kotlinx.coroutines.launch
//
//@ExperimentalCoroutinesApi
//@ObsoleteCoroutinesApi
//class NewTransferSuccessOrdersActivity : MyBaseActivity(), OnRefreshLoadMoreListener {
//    private lateinit var viewModel: NewTransferOutOrdersViewModel
//    var adapter: ListOrdersAdapter? = null
//    private var isRefresh = true
//    private var mContext: Context? = null
//
//    override fun getLayoutId(): Int {
//        return R.layout.activity_new_order_list
//    }
//
//    override fun business() {
//        mContext = this
//        MobclickAgent.onEvent(mContext, UMengKeys.LOGIN_USER_ID, SharedPreferencesUtil.get(
//            API.LOGIN_USER_ID,
//            0
//        ).toString())
//        viewModel = ViewModelProvider(this)[NewTransferOutOrdersViewModel::class.java]
//        if(NetUtil.isNetworkConnected(this)) {
//            viewModel.currentCity = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
//            initView()
//        }
//    }
//
////    override fun onResume() {
////        super.onResume()
////        MobclickAgent.onPageStart("NewTransferSuccessOrdersActivity") //统计页面，"MainScreen"为页面名称，可自定义
////    }
////
////    override fun onPause() {
////        super.onPause()
////        MobclickAgent.onPageEnd("NewTransferSuccessOrdersActivity")
////    }
//
//
//    private fun initView() {
//        viewModel.industryIDs = ""
//        viewModel.areaIDs = ""
//        viewModel.sizeRangeID = ""
//        viewModel.rentIDs = ""
//        viewModel.sortBy = ""
//        viewModel.sortType = ""
//
//        selected_industry.text = "行业"
//
//        selected_area.text = "区域"
//
//        selected_size.text = "面积"
//
//        selected_rent.text = "租金"
//
//        button_search.hint = "成功案例搜索"
//
//
//        refreshlayout.setOnRefreshLoadMoreListener(this@NewTransferSuccessOrdersActivity)
//
//        button_back.setOnClickListener {
//            onBackPressed()
//        }
//
//        button_search.addTextChangedListener {
//            viewModel.title = it.toString()
//        }
//        button_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH && viewModel.title.isNotEmpty()) {
//                hideKeyboard(button_search)
//                order_list.removeAllViews()
//                isRefresh = true
//                lifecycleScope.launch {
//                    viewModel.getTransferSuccessFromRemote()?.let {list ->
//                            adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                    }
//                }
//                return@OnEditorActionListener true
//            }
//            false
//        })
//
//        selected_industry.setOnClickListener {
//            SelectIndustryDialog(
//                onSelectIndustry = { topMenuItem, secondMenuItem ->
//                    selected_industry.text =
//                        "${topMenuItem?.text ?: "行业"}${secondMenuItem?.text ?: ""}".apply {
//                            if (this.isEmpty()) else {
//                                viewModel.industryIDs = "" +
//                                        "${topMenuItem?.menuID ?: ""}" +
//                                        (if (topMenuItem == null) "" else ",") +
//                                        "${secondMenuItem?.menuID ?: ""}"
//                            }
//                        }
//                    isRefresh = true
//                    viewModel.currentPage = 1
//                    lifecycleScope.launch {
//                        viewModel.getTransferSuccessFromRemote()?.let {list ->
//                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                        }
//                    }
//                }
//            ).show(supportFragmentManager, SelectIndustryDialog::class.java.name)
//        }
//
//        selected_area.setOnClickListener {
//            SelectAreaDialog(
//                onSelectArea = {
//                    selected_area.text = (it?.text ?: "地区").apply {
//                        if (this.isEmpty()) else {
//                            viewModel.areaIDs = it?.nodeID.toString()
//                        }
//                    }
//                    isRefresh = true
//                    viewModel.currentPage = 1
//                    lifecycleScope.launch {
//                        viewModel.getTransferSuccessFromRemote()?.let {list ->
//                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                        }
//                    }
//                }
//            ).show(supportFragmentManager, SelectAreaDialog::class.java.name)
//        }
//
//        selected_size.setOnClickListener {
//            SelectSizeRangeDialog(
//                onSelectSize = {
//                    selected_size.text = (it?.text ?: "面积").apply {
//                        if (this.isEmpty()) else {
//                            viewModel.sizeRangeID = it?.menuID.toString()
//                        }
//                    }
//                    isRefresh = true
//                    viewModel.currentPage = 1
//                    lifecycleScope.launch {
//                        viewModel.getTransferSuccessFromRemote()?.let {list ->
//                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                        }
//                    }
//                }
//            ).show(supportFragmentManager, SelectSizeRangeDialog::class.java.name)
//        }
//
//        selected_rent.setOnClickListener {
//            SelectRentRangeDialog(
//                onSelectRent = {
//                    it?.let {
//                        selected_rent.text = (it?.text ?: "租金").apply {
//                            if (this.isEmpty()) else {
//                                viewModel.rentIDs = it?.menuID.toString()
//                            }
//                        }
//                    }
//                    isRefresh = true
//                    viewModel.currentPage = 1
//                    lifecycleScope.launch {
//                        viewModel.getTransferSuccessFromRemote()?.let {list ->
//                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                        }
//                    }
//                }
//            ).show(supportFragmentManager, SelectRentRangeDialog::class.java.name)
//        }
//
//
//        button_sort.setOnClickListener {
//            SelectSortTypeDialog(
//                onSelectSortType = {
//                    it?.let {
//                        viewModel.sortBy = it.sortBy
//                        viewModel.sortType = it.sortType
//                    }
//                    isRefresh = true
//                    viewModel.currentPage = 1
//                    lifecycleScope.launch {
//                        viewModel.getTransferSuccessFromRemote()?.let {list ->
//                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                        }
//                    }
//                }
//            ).show(supportFragmentManager, SelectSortTypeDialog::class.java.name)
//        }
//
//
//        DividerItemDecoration(
//            this@NewTransferSuccessOrdersActivity,
//            DividerItemDecoration.VERTICAL
//        ).let {
//            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
//            order_list.addItemDecoration(it)
//        }
//
//        order_list.layoutManager = LinearLayoutManager(this@NewTransferSuccessOrdersActivity)
//        adapter = ListOrdersAdapter(this, arrayListOf())
//        order_list.adapter = adapter
//
//        isRefresh = true
//        viewModel.currentPage = 1
//        if (NetUtil.isNetworkConnected(this)) {
//            not_network.visibility = View.GONE
//            lifecycleScope.launch {
//                viewModel.getTransferSuccessFromRemote()?.let {list ->
//                        adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//                }
//            }
//        } else {
//            not_network.visibility = View.VISIBLE
//            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
//        }
//
//    }
//
//    fun hideKeyboard(view: View) {
//        val manager: InputMethodManager = view.context
//            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        manager.hideSoftInputFromWindow(view.windowToken, 0)
//    }
//
//    override fun onLoadMore(refreshLayout: RefreshLayout) {
//        isRefresh = false
//        viewModel.currentPage += 1
//        Log.d(" onLoadMore-----  ", "  viewModel.currentPage----- " + viewModel.currentPage)
//        lifecycleScope.launch {
//            viewModel.getTransferSuccessFromRemote()?.let {list ->
//                    adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//            }
//        }
//        refreshLayout.finishLoadMore()
//    }
//
//    override fun onRefresh(refreshLayout: RefreshLayout) {
//        isRefresh = true
//        viewModel.currentPage = 1
//        if (NetUtil.isNetworkConnected(this)) {
//        lifecycleScope.launch {
//            viewModel.getTransferSuccessFromRemote()?.let {list ->
//                    adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
//            }
//        }
//        } else {
//            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
//        }
//        refreshLayout.finishRefresh()
//    }
//
//
//}