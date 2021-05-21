package com.puxiansheng.www.ui.mine.favor

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentFavorListOrdersBinding
import com.puxiansheng.www.databinding.FragmentMineFavorInnerFragmentBinding
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.coroutines.launch

class FavoriteProjectFragment() : Fragment(), OnRefreshLoadMoreListener {

    private lateinit var viewModel: FavoriteProjectViewModel
    private lateinit var projectdapter: FavorListProjectAdapter
    private var currentPage = 1
    private var isRefresh = true


    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[FavoriteProjectViewModel::class.java]
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

        refreshlayout.setOnRefreshLoadMoreListener(this@FavoriteProjectFragment)

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())

        projectdapter = FavorListProjectAdapter( requireActivity(),
            arrayListOf(),
            deleteListener = object : FavorListProjectAdapter.onDeleteListener {
                override fun delete(order: ProjectDetailObject) {
                    var deleteDialog = DeleteOrderDialog(
                        "确定要删除该条收藏吗？",
                        Order.Type.TRANSFER_OUT_FAVORITE.value(), order.shopID
                    )
                    deleteDialog.show(childFragmentManager, DeleteOrderDialog::class.java.name)
                    deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                        override fun onDiss() {
                            isRefresh = true
                            currentPage= 1
                            lifecycleScope.launch {
                                viewModel.getFavoriteProject(currentPage)?.let {list->
                                    projectdapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
                                }
                            }
                        }
                    }
                }
            })


        list.adapter = projectdapter

        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.getFavoriteProject(currentPage)?.let { list ->
                    projectdapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
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
            viewModel.getFavoriteProject(currentPage)?.let {list->
                projectdapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore(1000)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        isRefresh = true
        lifecycleScope.launch {
            viewModel.getFavoriteProject(currentPage)?.let {list->
                projectdapter?.addList(list as ArrayList<ProjectDetailObject>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(1000)
    }

}