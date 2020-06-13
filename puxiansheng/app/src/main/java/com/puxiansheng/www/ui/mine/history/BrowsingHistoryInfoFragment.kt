package com.puxiansheng.www.ui.mine.history

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentInfoListBinding
import com.puxiansheng.www.ui.mine.favor.FavorInfoAdapter
import kotlinx.android.synthetic.main.fragment_mine_browsing_history_inner_fragment.*
import kotlinx.coroutines.launch

class BrowsingHistoryInfoFragment : Fragment() {
    private lateinit var viewModel: BrowsingHistoryInfoListViewModel
    private lateinit var hisViewModel: HistoryListViewModel
    private lateinit var infoAdapter: FavorInfoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BrowsingHistoryInfoListViewModel::class.java]
        hisViewModel =  ViewModelProvider(requireActivity())[HistoryListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentInfoListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refresh.setOnRefreshListener {
            viewModel.refresh()
            refresh.isRefreshing = false
            refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())

        infoAdapter =  FavorInfoAdapter(requireContext(),type = InfoItem.Type.ARTICLE_HISTORY.value())
        infoAdapter.addLoadStateListener{loadType, _, _ ->
                if (loadType == PagedList.LoadType.END) {
                    if (infoAdapter.itemCount == 0) {
                        infoAdapter.type = Order.Type.EMPTY.value()
                        infoAdapter.notifyDataSetChanged()
                    }
                }
                if (loadType == PagedList.LoadType.REFRESH) {
                    if (infoAdapter.type != InfoItem.Type.ARTICLE_HISTORY.value()) {
                        infoAdapter.type = InfoItem.Type.ARTICLE_HISTORY.value()
                        infoAdapter.notifyDataSetChanged()
                    }
                }
            }
            list.adapter = infoAdapter
            lifecycleScope.launch {
                LivePagedListBuilder<Int, InfoItem>(
                    viewModel.getFavorInfoFromRoom(),
                    5
                ).apply {
                    setBoundaryCallback(object : PagedList.BoundaryCallback<InfoItem>() {
                        override fun onItemAtEndLoaded(itemAtEnd: InfoItem) {
                            super.onItemAtEndLoaded(itemAtEnd)
                            viewModel.loadMore()
                        }
                    })
                }.build().observe(viewLifecycleOwner, Observer {
                    infoAdapter.submitList(it)
                })
            }

        viewModel.refresh()

        hisViewModel.refreshType.observe(requireActivity(), Observer {
            if(it == InfoItem.Type.ARTICLE_HISTORY.value()){
                viewModel.refresh()
            }
        })


    }.root

}