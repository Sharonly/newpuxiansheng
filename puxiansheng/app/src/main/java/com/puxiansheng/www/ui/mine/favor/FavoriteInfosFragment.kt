package com.puxiansheng.www.ui.mine.favor

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.coroutines.launch

class FavoriteInfosFragment : Fragment(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: FavoriteInfoListViewModel
    private var adapter: FavorInfoListAdapter? = null
    var currentPage = 1
    private var isRefresh = true

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[FavoriteInfoListViewModel::class.java]
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

        refreshlayout.setOnRefreshLoadMoreListener(this@FavoriteInfosFragment)

        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavorInfoListAdapter(
            requireActivity(),
            arrayListOf(),
            deleteListener = object : FavorInfoListAdapter.DeleteListener {
                override fun delete(item: InfoItem) {
                    var deleteDialog =
                        DeleteOrderDialog(
                            "确定要删除该条收藏资讯吗？",
                            InfoItem.Type.ARTICLE_FAVOR.value(),
                            item?.infoID?.toLong()
                        )
                    deleteDialog.show(childFragmentManager, DeleteOrderDialog::class.java.name)
                    deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                        override fun onDiss() {
                            isRefresh = true
                            currentPage= 1
                            lifecycleScope.launch {
                                viewModel.getTransferInfos(currentPage)?.let {
                                    adapter?.addList(it as ArrayList<InfoItem>, isRefresh)
                                }
                            }
                        }
                    }
                }
            })


        list.adapter = adapter
        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.getTransferInfos(currentPage)?.let { list ->
                    adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
                }
            }
        } else {
            Toast.makeText(requireContext(), "网络连接失败", Toast.LENGTH_SHORT)
        }

    }.root

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        currentPage += 1
        isRefresh = false
        lifecycleScope.launch {
            viewModel.getTransferInfos(currentPage)?.let { list ->
                adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore(2000)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        isRefresh = true
        lifecycleScope.launch {
            viewModel.getTransferInfos(currentPage)?.let { list ->
                adapter?.addList(list as ArrayList<InfoItem>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(2000)
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("FavoriteInfosFragment")
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("FavoriteInfosFragment")
    }
}