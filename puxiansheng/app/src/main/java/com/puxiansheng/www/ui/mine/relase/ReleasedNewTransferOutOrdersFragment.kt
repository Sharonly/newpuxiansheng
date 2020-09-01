package com.puxiansheng.www.ui.mine.relase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil

import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentMineReleasedOutnerFragmentBinding

import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.coroutines.launch

class ReleasedNewTransferOutOrdersFragment : Fragment() ,OnRefreshLoadMoreListener {

    private lateinit var viewModel: ReleasedTransferOutOrdersViewModel
    var mAdapter: ReleaseOutAdapter? = null
    var currentPage = 1
    private var isRefresh=true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[ReleasedTransferOutOrdersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMineReleasedOutnerFragmentBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refreshlayout.setOnRefreshLoadMoreListener(this@ReleasedNewTransferOutOrdersFragment)

        mAdapter = ReleaseOutAdapter(requireContext(),arrayListOf(),deleteListener = object :ReleaseOutAdapter.onDeleteListener{
            override fun delete(it:OrderDetailObject) {
                var deleteDialog = DeleteOrderDialog("确定要删除该条发布吗？",Order.Type.TRANSFER_OUT_PRIVATE.value(), it?.shopID)
                deleteDialog.show(childFragmentManager, DeleteOrderDialog::class.java.name)
                deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                    override fun onDiss() {
                        isRefresh = true
                        currentPage = 1
                        lifecycleScope.launch {
                            viewModel.getRemoteMineTransferOutOrders(currentPage).let { list ->
                                mAdapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
                            }
                        }
                    }
                }
            }
        })

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = mAdapter

        if (NetUtil.isNetworkConnected(requireContext())) {
            isRefresh = true
           currentPage = 1
            lifecycleScope.launch {
                viewModel.getRemoteMineTransferOutOrders(currentPage)?.let { data ->
                    mAdapter?.addList(data as ArrayList<OrderDetailObject> , isRefresh)
                }
            }
        } else {
            Toast.makeText(requireContext(), "网络连接失败", Toast.LENGTH_SHORT)
        }

    }.root

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        currentPage += 1
        isRefresh=false
        lifecycleScope.launch {
            viewModel.getRemoteMineTransferOutOrders(currentPage)?.let { data ->
                mAdapter?.addList(data as ArrayList<OrderDetailObject> , isRefresh)
            }
        }
        refreshLayout.finishLoadMore()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        isRefresh=true
        lifecycleScope.launch {
            viewModel.getRemoteMineTransferOutOrders(currentPage)?.let { data ->
                mAdapter?.addList(data as ArrayList<OrderDetailObject> , isRefresh)
            }
        }
        refreshLayout.finishRefresh()
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("ReleasedNewTransferOutOrdersFragment")
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("ReleasedNewTransferOutOrdersFragment")
    }
}