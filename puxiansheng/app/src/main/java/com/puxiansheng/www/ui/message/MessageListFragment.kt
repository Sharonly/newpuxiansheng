package com.puxiansheng.www.ui.message

import android.annotation.SuppressLint
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
import com.puxiansheng.www.databinding.FragmentMessageItemBinding
import com.puxiansheng.www.ui.info.InfoListFragment
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MessageListFragment(): Fragment() {
    companion object {

        fun newInstance(type: Int) = MessageListFragment().apply {
            arguments = Bundle().apply {
                putInt("type", type)
            }
        }
    }

    private lateinit var viewModel: MessageListViewModel
    private lateinit var appModel: MainViewModel
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getInt("type") ?: 0
        println("MessageListFragment with type=${type}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[MessageListViewModel::class.java]
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

        MessageListAdapter().let { adapter ->
            list.adapter = adapter
            lifecycleScope.launch {
//                LivePagedListBuilder<Int, InfoItem>(
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

    inner class MessageListAdapter : PagedListAdapter<InfoItem, MessageListAdapter.InfoViewHolder>(
        InfoItem.DIFF
    ) {
        inner class InfoViewHolder(
            override val containerView: View
        ) : RecyclerView.ViewHolder(containerView), LayoutContainer {
            private val binding = FragmentMessageItemBinding.bind(containerView)

            @SuppressLint("SetTextI18n")
            fun bind(infoItem: InfoItem?) {
                binding.messageTitle.text = infoItem?.title
                binding.messageInfo.text
                binding.root.setOnClickListener {
//                    Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
//                        R.id.action_mainFragment_to_infoDetailFragment,
//                        Bundle().apply {
//                            putString("url", infoItem?.url)
//                        }
//                    )
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ) = InfoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.fragment_message_item, parent, false)
        )

        override fun onBindViewHolder(
            holder: InfoViewHolder,
            position: Int
        ) = holder.bind(getItem(position))
    }
}