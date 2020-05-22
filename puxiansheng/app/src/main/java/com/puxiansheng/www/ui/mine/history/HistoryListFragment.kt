package com.puxiansheng.www.ui.mine.history

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentInfoListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.OrdersAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import kotlinx.coroutines.launch

class HistoryListFragment : Fragment() {
    companion object {
        fun newInstance(type: Int) = HistoryListFragment()
            .apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }

    private lateinit var viewModel: HistoryListViewModel
    private lateinit var appModel: MainViewModel
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getInt("type") ?: 0
        println("HistoryListFragment with type=${type}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[HistoryListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentInfoListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refresh.setOnRefreshListener {
            viewModel.refresh(type)
            refresh.isRefreshing = false
            refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_thin, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())

        OrdersAdapter(
            type = type,
            onItemSelect = {
                val intent= Intent(requireActivity(), TransferOutOrderDetailActivity::class.java)
                intent.putExtra("shopID", it?.shop?.shopID?.toInt())
                startActivity(intent)
            }).let { adapter ->
            list.adapter = adapter
            lifecycleScope.launch {
//                                LivePagedListBuilder<Int, InfoItem>(
//                    viewModel.getInfoByCategoryFromRoom(type = type),
//                    3
//                ).apply {
//                    setBoundaryCallback(object : PagedList.BoundaryCallback<InfoItem>() {
//                        override fun onItemAtEndLoaded(itemAtEnd: InfoItem) {
//                            super.onItemAtEndLoaded(itemAtEnd)
//                            viewModel.loadMore(
//                                category = category,
//                                city = appModel.currentCity.value?.nodeID?.toString()
//                            )
//                        }
//                    })
//                }.build().observe(viewLifecycleOwner, Observer {
//                    adapter.submitList(it)
//                })
            }
        }

        viewModel.refresh(type)
    }.root


}