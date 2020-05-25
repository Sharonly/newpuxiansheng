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
import com.puxiansheng.www.databinding.FragmentHomeTransferListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class HomeTransferOutOrdersFragment : AppFragment() {

    private lateinit var viewModel: HomeTransferOutOrdersViewModel
    private lateinit var appModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[HomeTransferOutOrdersViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeTransferListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            orderList.addItemDecoration(it)
        }

        val pageBuilder = LivePagedListBuilder<Int, Order>(
            viewModel.getTransferOutOrdersFromLocal(),
            PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build()
        )
        pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
            override fun onItemAtEndLoaded(itemAtEnd: Order) {
                super.onItemAtEndLoaded(itemAtEnd)
                viewModel.loadMore()
            }
        })

        var adapter = RecommOrderAdapter(
//            type = Order.Type.TRANSFER_OUT.value(),
            requireContext(),
            onItemSelect = {
                val intent = Intent(requireActivity(), TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt())
                startActivity(intent)
            }
        )

        adapter.addLoadStateListener { loadType, _, _ ->
                if (loadType == PagedList.LoadType.END) {
                    if (adapter.getDataCount() == 0) {
                        adapter.type = Order.Type.EMPTY.value()
                        adapter.notifyDataSetChanged()
//                            viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID,0).toString())
                    }
                }
                if (loadType == PagedList.LoadType.REFRESH) {
                    if (adapter.type != Order.Type.TRANSFER_OUT.value()) {
                        adapter.type = Order.Type.TRANSFER_OUT.value()
                        adapter.notifyDataSetChanged()
                    }
                }
            }

        pageBuilder.build().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })

        orderList.layoutManager = LinearLayoutManager(requireContext())
        orderList.adapter = adapter

//        orderList.adapter = RecommOrderAdapter(
//            type = Order.Type.TRANSFER_OUT.value(),
//            onItemSelect = {
//                val intent = Intent(requireActivity(), TransferOutOrderDetailActivity::class.java)
//                intent.putExtra("shopID", it?.shop?.shopID?.toInt())
//                startActivity(intent)
//            }
//        ).apply {
//        LivePagedListBuilder<Int, Order>(
//            viewModel.getTransferOutOrdersFromLocal(),
//            PagedList.Config.Builder()
//                .setEnablePlaceholders(true)
//                .setPageSize(20)
//                .setInitialLoadSizeHint(20)
//                .build()
//        ).let { pageBuilder ->
//            pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
//                override fun onItemAtEndLoaded(itemAtEnd: Order) {
//                    super.onItemAtEndLoaded(itemAtEnd)
//                    viewModel.loadMore()
//                }
//            })
//            addLoadStateListener { loadType, _, _ ->
//                if (loadType == PagedList.LoadType.END) {
//                    if (getDataCount() == 0) {
//                        type = Order.Type.EMPTY.value()
//                        notifyDataSetChanged()
////                            viewModel.refresh(SharedPreferencesUtil.get(API.USER_CITY_ID,0).toString())
//                    }
//                }
//                if (loadType == PagedList.LoadType.REFRESH) {
//                    if (type != Order.Type.TRANSFER_OUT.value()) {
//                        type = Order.Type.TRANSFER_OUT.value()
//                        notifyDataSetChanged()
//                    }
//                }
//            }
//
//            pageBuilder.build().observe(viewLifecycleOwner, Observer {
//                submitList(it)
//                notifyDataSetChanged()
//            })
//        }
//        }

        appModel.currentCity.observe(viewLifecycleOwner, Observer {
            SharedPreferencesUtil.put(API.USER_CITY_ID, it.nodeID)
            viewModel.refresh(it.nodeID.toString())
        })

    }.root


}