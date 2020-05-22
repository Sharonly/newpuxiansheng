package com.puxiansheng.www.ui.order

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.www.R
import com.puxiansheng.www.databinding.FragmentOrderListBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class OrderListFragment : Fragment() {
    companion object {
        fun newInstance(type: Int) = OrderListFragment().apply {
            arguments = Bundle().apply {
                putInt("orderType", type)
            }
        }
    }

    private lateinit var viewModel: OrderListViewModel
    private lateinit var appModel: MainViewModel
    private var type = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = arguments?.getInt("orderType") ?: 0
        Log.d("","InfoListFragment with orderType=${type}")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[OrderListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentOrderListBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refresh.setOnRefreshListener {
//            viewModel.refresh(category)
            refresh.isRefreshing = false
            refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        }

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_thin, null))
            orderList.addItemDecoration(it)
        }

        orderList.layoutManager = LinearLayoutManager(requireContext())

        OrderListAdapter().let { adapter ->
//            list.adapter = adapter
//            lifecycleScope.launch {
//
//            }
        }
    }.root

}