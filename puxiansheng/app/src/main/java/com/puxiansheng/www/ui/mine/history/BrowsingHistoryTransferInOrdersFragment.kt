package com.puxiansheng.www.ui.mine.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentMineBrowsingHistoryInnerFragmentBinding
import com.puxiansheng.www.ui.order.OrdersAdapter
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity

class BrowsingHistoryTransferInOrdersFragment : Fragment() {

    private lateinit var viewModel: BrowsingHistoryTransferInOrdersViewModel
    private lateinit var hisViewModel: HistoryListViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BrowsingHistoryTransferInOrdersViewModel::class.java]
        hisViewModel =  ViewModelProvider(requireActivity())[HistoryListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentMineBrowsingHistoryInnerFragmentBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refresh.setOnRefreshListener {
            viewModel.refresh()
            refresh.isRefreshing = false
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = OrdersAdapter(
            type = Order.Type.TRANSFER_IN_HISTORY.value(),
            onItemSelect = {
                val intent = Intent(requireActivity(), TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID.toString())
                startActivity(intent)

            }
        ).apply {
            LivePagedListBuilder<Int, Order>(
                viewModel.getMineTransferInOrdersFromLocal(),
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
                        if (type != Order.Type.TRANSFER_IN_HISTORY.value()) {
                            type = Order.Type.TRANSFER_IN_HISTORY.value()
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

        hisViewModel.refreshType.observe(requireActivity(), Observer {
            if(it == Order.Type.TRANSFER_IN_HISTORY.value()){
                viewModel.refresh()
            }
        })

    }.root


}