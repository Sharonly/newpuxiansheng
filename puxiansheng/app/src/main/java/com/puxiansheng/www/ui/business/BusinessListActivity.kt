package com.puxiansheng.www.ui.business


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.tools.UMengKeys
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_order_list.*
import kotlinx.android.synthetic.main.fragment_invest_business.*
import kotlinx.android.synthetic.main.fragment_invest_business.button_back
import kotlinx.android.synthetic.main.fragment_invest_business.button_search
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class BusinessListActivity : MyBaseActivity(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: InvestBusnessViewModel
    var adapter: BusinessListAdapter? = null
    private var isRefresh = true
    private var mContext: Context? = null
    private val mPageName = "BusinessListActivity"

    override fun getLayoutId(): Int {
        return R.layout.fragment_invest_business
    }

    override fun business() {
        mContext = this@BusinessListActivity
//        MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, mPageName)
        viewModel = ViewModelProvider(this)[InvestBusnessViewModel::class.java]
        button_back.setOnClickListener {
            onBackPressed()
        }
        refreshlayout.setOnRefreshLoadMoreListener(this@BusinessListActivity)

        button_search.addTextChangedListener {
            viewModel.title = it.toString()
        }

        button_search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && viewModel.title.isNotEmpty()) {
                hideKeyboard(button_search)
                business_list.removeAllViews()
                isRefresh = true
                lifecycleScope.launch {
                    viewModel.getBusinessList()?.let {
                        adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
                    }
                }
                return@OnEditorActionListener true
            }
            false
        })

        if (intent.getStringExtra("title") != "*") {
            button_search.setText(intent.getStringExtra("title"))
            viewModel.title = intent.getStringExtra("title")
            business_list.removeAllViews()
        }

        DividerItemDecoration(this@BusinessListActivity, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            business_list.addItemDecoration(it)
        }

        business_list.layoutManager = LinearLayoutManager(this@BusinessListActivity)
        adapter = BusinessListAdapter(
            this,
            arrayListOf(),
            object : BusinessListAdapter.CallListener {
                override fun call(info: BusinessBean) {
                    ConsultDialog(info?.id.toString()).show(
                        supportFragmentManager,
                        ConsultDialog::class.java.name
                    )
                }
            })
        business_list.adapter = adapter

        lifecycleScope.launch {
            viewModel.requestBannerImage("api_join_images")?.let { banners ->
                image_switcher.setImages(banners)
            }

            isRefresh = true
            viewModel.getBusinessList()?.let {
                adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
            }
        }

    }


    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        isRefresh = false
        Log.d("---business","isRefresh = "+isRefresh)
        viewModel.currentPage += 1
        lifecycleScope.launch {
            viewModel.getBusinessList()?.let {
                Log.d("---business","it = "+it.size)
                adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore(800)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        button_search.setText("")
        viewModel.title = ""
        isRefresh = true
        viewModel.currentPage = 1
        lifecycleScope.launch {
            viewModel.requestBannerImage("api_join_images")?.let { banners ->
                image_switcher.setImages(banners)
            }

            viewModel.getBusinessList()?.let {
                Log.d("---business","onRefresh it = "+it.size)
                adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(800)
    }
//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("BusinessListActivity") //统计页面，"MainScreen"为页面名称，可自定义
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("BusinessListActivity")
//    }

}