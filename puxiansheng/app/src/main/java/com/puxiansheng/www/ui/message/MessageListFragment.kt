package com.puxiansheng.www.ui.message

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentNewInfoListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MessageListFragment() : Fragment(), OnRefreshLoadMoreListener {
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
    private var adapter: NewMessageListAdapter? = null
    private var isRefresh = true

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
    ): View? = FragmentNewInfoListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        viewModel.cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()
        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            list.addItemDecoration(it)
        }

        refreshlayout.setOnRefreshLoadMoreListener(this@MessageListFragment)

        list.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewMessageListAdapter(requireContext(), arrayListOf())
        list.adapter = adapter

        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.getMessageListByCategory(category).let {
                    if (!it.isNullOrEmpty()) {
                        adapter?.addList(it as ArrayList<MessageItem>, isRefresh)
                    }
                }
            }
        }

//        LiveDataBus.get().with("infoTitle", String::class.java)
//            ?.observe(requireActivity(), Observer {
//                viewModel.title = it.toString()
//                isRefresh = true
//                lifecycleScope.launch {
//                    viewModel.getMessageListByCategory(category).let {
//                        adapter?.addList(it as ArrayList<MessageItem>, isRefresh)
//                    }
//                }
//            })

    }.root


    override fun onResume() {
        super.onResume()
        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.title = ""
                viewModel.currentPage = 1
                isRefresh = true
                viewModel.getMessageListByCategory(category).let {
                    if (!it.isNullOrEmpty()) {
                        adapter?.addList(it as ArrayList<MessageItem>, isRefresh)
                    }
                }
            }
        } else {
            Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
        }
    }


    override fun onLoadMore(refreshLayout: RefreshLayout) {
        viewModel.currentPage += 1
        isRefresh = false
        lifecycleScope.launch {
            viewModel.getMessageListByCategory(category).let {
                adapter?.addList(it as ArrayList<MessageItem>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        viewModel.title = ""
        viewModel.currentPage = 1
        isRefresh = true
        if (NetUtil.isNetworkConnected(requireContext())) {
            lifecycleScope.launch {
                viewModel.getMessageListByCategory(category).let {
                    adapter?.addList(it as ArrayList<MessageItem>, isRefresh)
                }
            }
        } else {
            Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
        }
        refreshLayout.finishRefresh()
    }


}