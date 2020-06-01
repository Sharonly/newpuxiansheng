package com.puxiansheng.www.ui.message

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentInfoListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MessageListFragment(): Fragment() {
    companion object {

        fun newInstance(category: Int) = MessageListFragment().apply {
            arguments = Bundle().apply {
                putInt("category", category)
            }
        }
    }

    private lateinit var viewModel: MessageListViewModel
    private lateinit var appModel: MainViewModel
    private var category = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = arguments?.getInt("category") ?: 0
        println("MessageListFragment with type=${category}")
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
            viewModel.refresh(category)
            refresh.isRefreshing = false
            refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        list.layoutManager = LinearLayoutManager(requireContext())

        MessageListAdapter(onItemSelect = {item ->
            val intent = Intent(requireActivity(), MessageDetailActivity::class.java)
            intent.putExtra("noticeId", item?.messageID.toString())
            startActivity(intent)
        }).let { adapter ->
            list.adapter = adapter
            lifecycleScope.launch {
                LivePagedListBuilder<Int, MessageItem>(
                    viewModel.getMessageByCategoryFromRoom(category = category),
                    3
                ).apply {
                    setBoundaryCallback(object : PagedList.BoundaryCallback<MessageItem>() {
                        override fun onItemAtEndLoaded(itemAtEnd: MessageItem) {
                            super.onItemAtEndLoaded(itemAtEnd)
                            viewModel.loadMore(
                                category = category,
                                city = appModel.currentCity.value?.nodeID?.toString()
                            )
                        }
                    })
                }.build().observe(viewLifecycleOwner, Observer {
                    adapter.submitList(it)
                    notifyChange()
                })
            }
        }

        viewModel.refresh(category)
    }.root

}