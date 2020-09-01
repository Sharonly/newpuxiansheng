package com.puxiansheng.www.ui.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.User
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.www.common.JumpUtils
import com.puxiansheng.www.common.urlBg
import com.puxiansheng.www.common.urlIcon
import com.puxiansheng.www.databinding.FragmentNewInfoListBinding
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.mine.ServiceActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class NewInfoListFragment : Fragment(), OnRefreshLoadMoreListener {
    companion object {
        fun newInstance(category: Int) = NewInfoListFragment().apply {
            arguments = Bundle().apply {
                putInt("category", category)
            }
        }
    }

    private lateinit var viewModel: InfoListViewModel
    private lateinit var appModel: MainViewModel
    private var category = 0
    private var adapter: NewInfoListAdapter? = null
    private var isRefresh = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getInt("category") ?: 0
        println("NewInfoListFragment with category=${category}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[InfoListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentNewInfoListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        viewModel.cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        refreshlayout.setOnRefreshLoadMoreListener(this@NewInfoListFragment)

        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewInfoListAdapter(requireContext(), arrayListOf())
        list.adapter = adapter
        LiveDataBus.get().with("infoTitle", String::class.java)
                ?.observe(requireActivity(), Observer {text->
                    viewModel.title = text.toString()
                    viewModel.currentPage = 1
                    isRefresh = true
                    lifecycleScope.launch {
                        viewModel.getInfoListByCategory(category)?.let {
                            if (!it.isNullOrEmpty()) {
                                adapter?.addList(it as ArrayList<InfoItem>, isRefresh)
                            }
                        }
                    }
                })
    }.root


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
                if (NetUtil.isNetworkConnected(requireContext())) {
                    viewModel.currentPage = 1
                    isRefresh = true
                    lifecycleScope.launch {
                        viewModel.getInfoListByCategory(category)?.let {
//                            if (!it.isNullOrEmpty()) {
                                adapter?.addList(it as ArrayList<InfoItem>, isRefresh)
//                            }
                        }
                    }
                } else {
                    Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
                }
            }

    }


    override fun onResume() {
        super.onResume()
        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.currentPage = 1
                isRefresh = true
                viewModel.getInfoListByCategory(category)?.let {
//                    if (!it.isNullOrEmpty()) {
                        adapter?.addList(it as ArrayList<InfoItem>, isRefresh)
//                    }
                }
            }
        } else {
            Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
        }
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.currentPage += 1
        isRefresh = false
        if (NetUtil.isNetworkConnected(requireContext())) {
        lifecycleScope.launch {
            viewModel.getInfoListByCategory(category)?.let {
                adapter?.addList(it as ArrayList<InfoItem>, isRefresh)
            }
        }
        } else {
            Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
        }
        refreshLayout.finishLoadMore()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.title =""
        viewModel.currentPage = 1
        isRefresh = true
        if (NetUtil.isNetworkConnected(requireContext())) {
        lifecycleScope.launch {
            viewModel.getInfoListByCategory(category)?.let {
                if (!it.isNullOrEmpty()) {
                    adapter?.addList(it as ArrayList<InfoItem>, isRefresh)
                }
            }
        }
    } else {
        Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
    }
        refreshLayout.finishRefresh()
    }


}