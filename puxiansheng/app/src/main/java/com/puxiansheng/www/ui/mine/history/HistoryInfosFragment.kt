package com.puxiansheng.www.ui.mine.history

import android.content.Context
import android.os.Bundle
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
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentNewInfoListBinding
import com.puxiansheng.www.ui.info.NewInfoListAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.coroutines.launch

class HistoryInfosFragment : Fragment(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: BrowsingHistoryInfoListViewModel
    private lateinit var hisViewModel: HistoryListViewModel
    private var adapter: NewInfoListAdapter? = null
    var currentPage = 1
    private var isRefresh = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BrowsingHistoryInfoListViewModel::class.java]
        hisViewModel = ViewModelProvider(requireActivity())[HistoryListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentNewInfoListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        refreshlayout.setOnRefreshLoadMoreListener(this@HistoryInfosFragment)

        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewInfoListAdapter(
            requireActivity(),
            arrayListOf()
        )

        list.adapter = adapter


        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.getHistoryInfoListFromRemote(currentPage)?.let { list ->
                    adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
                }
            }
            hisViewModel.refreshType.observe(requireActivity(), Observer {
                if (it == InfoItem.Type.ARTICLE_HISTORY.value()) {
                    lifecycleScope.launch {
                        viewModel.getHistoryInfoListFromRemote(currentPage)?.let { list ->
                            adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
                        }
                    }
                }
            })

        } else {
            Toast.makeText(requireContext(), "网络连接失败", Toast.LENGTH_SHORT)
        }


    }.root

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        currentPage += 1
        isRefresh = false
        lifecycleScope.launch {
            viewModel.getHistoryInfoListFromRemote(currentPage)?.let { list ->
                adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore(1000)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        isRefresh = true
        lifecycleScope.launch {
            viewModel.getHistoryInfoListFromRemote(currentPage)?.let { list ->
                adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(1000)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("HistoryInfosFragment")
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("HistoryInfosFragment")
    }

}