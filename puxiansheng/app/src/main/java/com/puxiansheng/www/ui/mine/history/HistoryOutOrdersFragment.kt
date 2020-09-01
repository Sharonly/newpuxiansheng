package com.puxiansheng.www.ui.mine.history

import android.content.Context
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
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentFavorListOrdersBinding
import com.puxiansheng.www.ui.order.ListOrdersAdapter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.coroutines.launch

class HistoryOutOrdersFragment : Fragment(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: BrowsingHistoryTransferOutOrdersViewModel
    private lateinit var hisViewModel: HistoryListViewModel
    private var adapter: ListOrdersAdapter? = null
    var currentPage = 1
    private var isRefresh = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BrowsingHistoryTransferOutOrdersViewModel::class.java]
        hisViewModel = ViewModelProvider(requireActivity())[HistoryListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentFavorListOrdersBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        refreshlayout.setOnRefreshLoadMoreListener(this@HistoryOutOrdersFragment)

        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = ListOrdersAdapter(
            requireContext(),
            arrayListOf()
        )

        list.adapter = adapter


        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.getHistoryOutOrders(currentPage)?.let { list ->
                    adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                }
            }
            hisViewModel.refreshType.observe(requireActivity(), Observer {
                if (it == Order.Type.TRANSFER_OUT_HISTORY.value()) {
                    lifecycleScope.launch {
                        viewModel.getHistoryOutOrders(currentPage)?.let { list ->
                            adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
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
            viewModel.getHistoryOutOrders(currentPage)?.let { list ->
                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore(2000)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        isRefresh = true
        lifecycleScope.launch {
            viewModel.getHistoryOutOrders(currentPage)?.let { list ->
                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(2000)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("HistoryOutOrdersFragment")
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("HistoryOutOrdersFragment")
    }

}