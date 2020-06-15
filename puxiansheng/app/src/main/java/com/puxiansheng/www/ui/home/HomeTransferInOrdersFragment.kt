package com.puxiansheng.www.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentHomeTransferListInBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

class HomeTransferInOrdersFragment  : AppFragment() {

    private lateinit var viewModel: HomeTransferInOrdersViewModel
    private lateinit var appModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[HomeTransferInOrdersViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeTransferListInBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            orderListIn.addItemDecoration(it)
        }


        val pageBuilder = LivePagedListBuilder<Int, Order>(
            viewModel.getTransferInOrdersFromLocal(),
            PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build()
        )
        pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
            override fun onItemAtEndLoaded(itemAtEnd: Order) {
                super.onItemAtEndLoaded(itemAtEnd)
//                viewModel.loadMore()
            }
        })

        var adapter = RecommOrderAdapter(
            requireContext(),
            onItemSelect = {
                val intent = Intent(requireActivity(), TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.jump_param)
                startActivity(intent)
            }
        )

        adapter.addLoadStateListener { loadType, _, _ ->
            if (loadType == PagedList.LoadType.END) {
                if (adapter.getDataCount() == 0) {
                    adapter.type = Order.Type.EMPTY.value()
                    adapter.notifyDataSetChanged()
                }
            }
            if (loadType == PagedList.LoadType.REFRESH) {
                if (adapter.type != Order.Type.TRANSFER_IN.value()) {
                    adapter.type = Order.Type.TRANSFER_IN.value()
                    adapter.notifyDataSetChanged()
                }
            }
        }

        pageBuilder.build().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        orderListIn.layoutManager = LinearLayoutManager(requireContext())
        orderListIn.adapter = adapter

        appModel.currentCity.observe(viewLifecycleOwner, Observer {
            SharedPreferencesUtil.put(API.USER_CITY_ID, it.nodeID)
            viewModel.refresh(it.nodeID.toString())
        })


    }.root


}