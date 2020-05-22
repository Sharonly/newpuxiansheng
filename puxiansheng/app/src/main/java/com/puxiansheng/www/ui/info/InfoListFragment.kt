package com.puxiansheng.www.ui.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentInfoItemBinding
import com.puxiansheng.www.databinding.FragmentInfoListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class InfoListFragment : Fragment() {
    companion object {
        fun newInstance(category: Int) = InfoListFragment().apply {
            arguments = Bundle().apply {
                putInt("category", category)
            }
        }
    }

    private lateinit var viewModel: InfoListViewModel
    private lateinit var appModel: MainViewModel
    private var category = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getInt("category") ?: 0
        println("InfoListFragment with category=${category}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[InfoListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentInfoListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refresh.setOnRefreshListener {
            viewModel.refresh(category)
            refresh.isRefreshing = false
            refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())

        InfoListAdapter().let { adapter ->
            list.adapter = adapter
            lifecycleScope.launch {
                LivePagedListBuilder<Int, InfoItem>(
                    viewModel.getInfoByCategoryFromRoom(category = category),
                    5
                ).apply {
                    setBoundaryCallback(object : PagedList.BoundaryCallback<InfoItem>() {
                        override fun onItemAtEndLoaded(itemAtEnd: InfoItem) {
                            super.onItemAtEndLoaded(itemAtEnd)
                            viewModel.loadMore(
                                category = category,
                                city = appModel.currentCity.value?.nodeID?.toString()
                            )
                        }
                    })
                }.build().observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                })
            }
        }

        viewModel.refresh(category)
    }.root

    inner class InfoListAdapter : PagedListAdapter<InfoItem, InfoListAdapter.InfoViewHolder>(
        InfoItem.DIFF
    ) {
        inner class InfoViewHolder(
            override val containerView: View
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            private val binding = FragmentInfoItemBinding.bind(containerView)

            @SuppressLint("SetTextI18n")
            fun bind(infoItem: InfoItem?) {
                binding.title.text = infoItem?.title
                binding.data.text = infoItem?.date
                binding.pageViews.text = infoItem?.pageViews.toString()
                binding.icon.url(infoItem?.image ?: "")
                binding.root.setOnClickListener {
                    val intent = Intent(requireActivity(), InfoDetailActivity::class.java)
                    intent.putExtra("url", infoItem?.url)
                    startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = InfoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fragment_info_item, parent, false)
        )

        override fun onBindViewHolder(
            holder: InfoViewHolder,
            position: Int
        ) = holder.bind(getItem(position))
    }
}