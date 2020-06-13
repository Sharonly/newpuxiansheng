package com.puxiansheng.www.ui.mine.relase

import android.app.ProgressDialog.show
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
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.release.InsertOrUpdateTransferInOrderActivity
import com.puxiansheng.www.ui.release.dialog.ReleaseDialog
import kotlinx.coroutines.launch

class ReleasedTransferInOrdersFragment : AppFragment() {

    private lateinit var viewModel: ReleasedTransferInOrdersViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[ReleasedTransferInOrdersViewModel::class.java]
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
            type = Order.Type.TRANSFER_IN_PRIVATE.value(),
            onItemSelect = {
                val intent = Intent(requireActivity(), TransferInOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.jump_param)
                startActivity(intent)
            },

            onEdit = {
                val intent =
                    Intent(requireActivity(), InsertOrUpdateTransferInOrderActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt() ?: 0)
                startActivity(intent)
            },
            onDelete = {
                var deleteDialog = DeleteOrderDialog("确定要删除该条发布吗？",Order.Type.TRANSFER_IN_PRIVATE.value(), it?.shop?.shopID)
                deleteDialog.show(childFragmentManager, DeleteOrderDialog::class.java.name)
                deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                    override fun onDiss() {
                        viewModel.refresh()
                    }
                }
//                lifecycleScope.launch {
//                    viewModel.deleteTransferInOrderFromRemote(it?.shop?.shopID.toString())
//                        ?.let { rst ->
//                            if (rst.code == API.CODE_SUCCESS) viewModel.refresh()
//                            Toast.makeText(requireContext(), rst.data, Toast.LENGTH_SHORT)
//                                .show()
//                        }
//                }
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
                        if (type != Order.Type.TRANSFER_IN_PRIVATE.value()) {
                            type = Order.Type.TRANSFER_IN_PRIVATE.value()
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
}