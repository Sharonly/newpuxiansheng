package com.puxiansheng.www.ui.mine.relase

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject

import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentMineReleasedInnerFragmentBinding
import com.puxiansheng.www.databinding.FragmentMineReleasedOutnerFragmentBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.coroutines.launch

class ReleasedNewTransferInOrdersFragment : Fragment() ,OnRefreshLoadMoreListener {

    private lateinit var viewModel: ReleasedTransferInOrdersViewModel
    var adapter: ReleaseInAdapter? = null
    var currentPage = 1
    private var isRefresh=true

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

        adapter = ReleaseInAdapter(requireContext(), arrayListOf(),deleteListener = object :ReleaseInAdapter.onDeleteListener{
            override fun delete(it:OrderDetailObject) {
                var deleteDialog = DeleteOrderDialog("确定要删除该条发布吗？",Order.Type.TRANSFER_IN_PRIVATE.value(), it?.shopID)
                deleteDialog.show(childFragmentManager, DeleteOrderDialog::class.java.name)
                deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                    override fun onDiss() {
                        lifecycleScope.launch {
                            viewModel.getRemoteMineTransferInOrders(currentPage).let { list ->
                                Log.d("---info-- ", " list = " + list)
                                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
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
        list.adapter = adapter

        refreshlayout.setOnRefreshLoadMoreListener(this@ReleasedNewTransferInOrdersFragment)

        lifecycleScope.launch {
            viewModel.getRemoteMineTransferInOrders(currentPage).let { list ->
                Log.d("---info-- ", " list = " + list)
                adapter?.addList(list as ArrayList<OrderDetailObject>, isRefresh)
            }
        }


//        list.adapter = OrdersAdapter(
//            type = Order.Type.TRANSFER_IN_PRIVATE.value(),
//            onItemSelect = {
//                val intent = Intent(requireActivity(), TransferInOrderDetailActivity::class.java)
//                intent.putExtra("shopID", it?.shop?.shopID?.toString())
//                startActivity(intent)
//            },
//
//            onEdit = {
//                val intent =
//                    Intent(requireActivity(), InsertOrUpdateTransferInOrderActivity::class.java)
//                intent.putExtra("shopID", it?.shop?.shopID?.toInt() ?: 0)
//                startActivity(intent)
//            },
//            onDelete = {
//                var deleteDialog = DeleteOrderDialog("确定要删除该条发布吗？",Order.Type.TRANSFER_IN_PRIVATE.value(), it?.shop?.shopID)
//                deleteDialog.show(childFragmentManager, DeleteOrderDialog::class.java.name)
//                deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
//                    override fun onDiss() {
////                        viewModel.refresh()
//                    }
//                }
////                lifecycleScope.launch {
////                    viewModel.deleteTransferInOrderFromRemote(it?.shop?.shopID.toString())
////                        ?.let { rst ->
////                            if (rst.code == API.CODE_SUCCESS) viewModel.refresh()
////                            Toast.makeText(requireContext(), rst.data, Toast.LENGTH_SHORT)
////                                .show()
////                        }
////                }
//            }
//
//        ).apply {
////            LivePagedListBuilder<Int, Order>(
////                viewModel.getMineTransferInOrdersFromLocal(),
////                PagedList.Config.Builder()
////                    .setEnablePlaceholders(true)
////                    .setPageSize(10)
////                    .setInitialLoadSizeHint(20)
////                    .build()
////            ).let { pageBuilder ->
////                pageBuilder.setBoundaryCallback(object : PagedList.BoundaryCallback<Order>() {
////                    override fun onItemAtEndLoaded(itemAtEnd: Order) {
////                        super.onItemAtEndLoaded(itemAtEnd)
////                        Log.d("----loadmore-----","  end ")
////                        viewModel.loadMore()
////                    }
////                })
////
////                addLoadStateListener { loadType, _, _ ->
////                    if (loadType == PagedList.LoadType.END) {
////                        if (itemCount == 0) {
////                            type = Order.Type.EMPTY.value()
////                            notifyDataSetChanged()
////                        }
////                    }
////                    if (loadType == PagedList.LoadType.REFRESH) {
////                        if (type != Order.Type.TRANSFER_IN_PRIVATE.value()) {
////                            type = Order.Type.TRANSFER_IN_PRIVATE.value()
////                            notifyDataSetChanged()
////                        }
////                    }
////                }
////
////                pageBuilder.build().observe(viewLifecycleOwner, Observer {
////                    submitList(it)
////                })
////            }

//        }

    }.root

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        currentPage += 1
        isRefresh=false
        lifecycleScope.launch {
            viewModel.getRemoteMineTransferInOrders(currentPage)
        }
        refreshLayout.finishLoadMore(2000)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        isRefresh=true
        lifecycleScope.launch {
            viewModel.getRemoteMineTransferInOrders(currentPage)
        }
        refreshLayout.finishRefresh(2000)
    }
}