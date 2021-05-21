package com.puxiansheng.www.ui.business

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.common.ImageSwitcher
import com.puxiansheng.www.databinding.FragmentInvestBusinessBinding
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_invest_business.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

class BusinessListFragment : AppFragment(), OnRefreshLoadMoreListener {
    private lateinit var viewModel: InvestBusnessViewModel
    var adapter: BusinessListAdapter? = null
    private var isRefresh = true
    private var mContext: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[InvestBusnessViewModel::class.java]
        mContext = context
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = rootView ?: FragmentInvestBusinessBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner

        refreshlayout.setOnRefreshLoadMoreListener(this@BusinessListFragment)

        buttonSearch.addTextChangedListener {
            viewModel.title = it.toString()
        }

        buttonSearch.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && viewModel.title.isNotEmpty()) {
                hideKeyboard(button_search)
                business_list.removeAllViews()
                isRefresh = true
                lifecycleScope.launch {
                    viewModel.getBusinessList()?.let {
                        adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
                    }
                }
                return@OnEditorActionListener true
            }
            false
        })



        DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL).let {
            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
            businessList.addItemDecoration(it)
        }

        businessList.layoutManager = LinearLayoutManager(mContext)
        adapter = BusinessListAdapter(
            requireActivity(),
            arrayListOf(),
            object : BusinessListAdapter.CallListener {
                override fun call(info: BusinessBean) {
                    ConsultDialog(info?.id.toString()).show(
                        childFragmentManager,
                        ConsultDialog::class.java.name
                    )
                }
            })
        businessList.adapter = adapter
        if (NetUtil.isNetworkConnected(requireContext())) {
            initData()
        } else {
            Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
        }

    }.root

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (NetUtil.isNetworkConnected(requireContext())) {
                initData()
            } else {
                Toast.makeText(requireActivity(), "网络连接失败", Toast.LENGTH_SHORT)
            }
        }
    }


    private fun initData() {
        lifecycleScope.launch {
            viewModel.requestBannerImage("api_join_images")?.let { banners ->
                image_switcher.setImages(banners, false)

                banner_index.removeAllViews()
                for (i in 0 until banners.size) {
                    var tempButton: RadioButton = RadioButton(mContext)
                    tempButton.setButtonDrawable(R.drawable.bg_index_bt) // 设置按钮的样式
                    tempButton.setPadding(10, 0, 10, 0) // 设置文字距离按钮四周的距离
                    banner_index.addView(
                        tempButton,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                image_switcher.listener = object : ImageSwitcher.OnPageChange {
                    override fun onScrolled(index: Int) {
                        var bt: RadioButton = banner_index.getChildAt(index) as RadioButton
                        bt.isChecked = true
                    }
                }

                var bt: RadioButton = banner_index.getChildAt(0) as RadioButton
                bt.isChecked = true
            }

            isRefresh = true
            viewModel.getBusinessList()?.let {
                adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
            }
        }
    }


    fun hideKeyboard(view: View) {
        val manager: InputMethodManager = view.context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        isRefresh = false
        Log.d("---business", "isRefresh = " + isRefresh)
        viewModel.currentPage += 1
        lifecycleScope.launch {
            viewModel.getBusinessList()?.let {
                Log.d("---business", "it = " + it.size)
                adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
            }
        }
        refreshLayout.finishLoadMore(800)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        button_search.setText("")
        viewModel.title = ""
        isRefresh = true
        viewModel.currentPage = 1
        lifecycleScope.launch {
            viewModel.requestBannerImage("api_join_images")?.let { banners ->
                image_switcher.setImages(banners, false)
            }

            viewModel.getBusinessList()?.let {
                Log.d("---business", "onRefresh it = " + it.size)
                adapter?.addList(it as ArrayList<BusinessBean>, isRefresh)
            }
        }
        refreshLayout.finishRefresh(800)
    }
}