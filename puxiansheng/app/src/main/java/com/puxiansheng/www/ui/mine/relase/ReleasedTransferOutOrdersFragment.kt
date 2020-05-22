package com.puxiansheng.www.ui.mine.relase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentMineReleasedInnerFragmentBinding
import com.puxiansheng.www.ui.order.OrdersAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferOutOrderActivity
import kotlinx.coroutines.launch

class ReleasedTransferOutOrdersFragment : AppFragment() {

    private lateinit var viewModel: ReleasedTransferOutOrdersViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[ReleasedTransferOutOrdersViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMineReleasedInnerFragmentBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refresh.setOnRefreshListener {
            viewModel.refresh()
            refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
            refresh.isRefreshing = false
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = OrdersAdapter(
            type = Order.Type.TRANSFER_OUT_PRIVATE.value(),
            onItemSelect = {
                val intent = Intent(requireActivity(), TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt() ?: 0)
                startActivity(intent)
            },

            onEdit = {
                val intent =
                    Intent(requireActivity(), InsertOrUpdateTransferOutOrderActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt() ?: 0)
                startActivity(intent)
            },
            onDelete = {
                lifecycleScope.launch {
                    viewModel.deleteTransferOutOrderFromRemote(it?.shop?.shopID.toString())
                        ?.let { rst ->
                            if (rst.code == API.CODE_SUCCESS) viewModel.refresh()
                            Toast.makeText(requireContext(), rst.data, Toast.LENGTH_SHORT)
                                .show()
                        }
                }
            }

        ).apply {
            LivePagedListBuilder<Int, Order>(
                viewModel.getMineTransferOutOrdersFromLocal(),
                PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setPageSize(10)
                    .setInitialLoadSizeHint(20)
                    .build()
            ).let { pageBuilder ->
                pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
                    override fun onItemAtEndLoaded(itemAtEnd: Order) {
                        super.onItemAtEndLoaded(itemAtEnd)
                        viewModel.loadMore()
                    }
                })
                addLoadStateListener { loadType, _, _ ->
                    if (loadType == PagedList.LoadType.END) {
                        if (itemCount == 0) {
                            type = Order.Type.EMPTY.value()
                            notifyDataSetChanged()
                        }
                    }
                    if (loadType == PagedList.LoadType.REFRESH) {
                        if (type != Order.Type.TRANSFER_OUT_PRIVATE.value()) {
                            type = Order.Type.TRANSFER_OUT_PRIVATE.value()
                            notifyDataSetChanged()
                        }
                    }
                }

                pageBuilder.build().observe(viewLifecycleOwner, Observer {
                    submitList(it)
                })
            }
        }

        viewModel.refresh()
    }.root

    override fun onDestroy() {
        println("ReleasedTransferOutOrdersFragment onDestroy")
        super.onDestroy()
    }
}