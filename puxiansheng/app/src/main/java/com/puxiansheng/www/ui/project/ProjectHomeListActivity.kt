package com.puxiansheng.www.ui.project

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.order.dialog.*
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_relase_order_transfer_out.*
import kotlinx.android.synthetic.main.fragment_pfoject_list.*
import kotlinx.coroutines.launch


class ProjectHomeListActivity : MyBaseActivity(), OnRefreshLoadMoreListener {

    private lateinit var viewModel: ProjectListViewModel
    override fun getLayoutId(): Int {
        return R.layout.fragment_pfoject_list
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[ProjectListViewModel::class.java]
        initView()
    }

    private var isRefresh = true
    var adapter: ProjectListAdapter? = null


    private fun initView() {
        viewModel.industryIDs = ""
        viewModel.areaIDs = ""
        viewModel.viewCount = ""

        selected_industry.text = "行业"

        selected_area.text = "区域"

        selected_size.text = "热度"

        refreshlayout.setOnRefreshLoadMoreListener(this@ProjectHomeListActivity)

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
                        viewModel.requestHomeProject()?.let { list ->
                            adapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectIndustryDialog::class.java.name)

        }

        selected_area.setOnClickListener {
            SelectAreaDialog(
                onSelectArea = { it, reset ->
                    if (reset) {
                        selected_area.text = "地区"
                        viewModel.areaIDs = ""
                    } else {
                        selected_area.text = (it?.text ?: "地区").apply {
                            if (this.isEmpty()) else {
                                viewModel.areaIDs = it?.nodeID.toString()
                            }
                        }
                    }
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.requestHomeProject()?.let { list ->
                            adapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectAreaDialog::class.java.name)
        }

        selected_size.setOnClickListener {
            SelectViewCountDialog(
                onSelectViewCount = {
                    selected_size.text = (it ?: "热度").apply {
                        if (it == "从高到低") {
                            viewModel.viewCount = "desc"
                        } else if (it == "从低到高") {
                            viewModel.viewCount = "asc"
                        } else {
                            viewModel.viewCount =""
                        }
                    }
                    isRefresh = true
                    viewModel.currentPage = 1
                    lifecycleScope.launch {
                        viewModel.requestHomeProject()?.let { list ->
                            adapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
                        }
                    }
                }
            ).show(supportFragmentManager, SelectViewCountDialog::class.java.name)

//            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_TYPE, "面积")
//            MobclickAgent.onEvent(mContext, UMengKeys.SELECTION_STRING, selected_size.text.toString())
        }


//        DividerItemDecoration(
//            this,
//            DividerItemDecoration.VERTICAL
//        ).let {
//            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
//            project_list.addItemDecoration(it)
//        }

        project_list.layoutManager = LinearLayoutManager(this)
        adapter = ProjectListAdapter(this, arrayListOf())
        project_list.adapter = adapter


        if (NetUtil.isNetworkConnected(this)) {
            not_network.visibility = View.GONE
            isRefresh = true
            viewModel.currentPage = 1
            lifecycleScope.launch {
                viewModel.requestHomeProject()?.let { list ->
                    adapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
                }
            }
        } else {
            not_network.visibility = View.VISIBLE
            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
        }

        lifecycleScope.launch {
            viewModel.requestHomeProject()?.let { list ->
                adapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
            }
        }

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        lifecycleScope.launch {
            viewModel.requestHomeProject()?.let { list ->
                adapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(1000)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {

    }

}