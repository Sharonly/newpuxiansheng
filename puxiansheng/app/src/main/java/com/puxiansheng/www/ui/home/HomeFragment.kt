package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MarqueeInfo
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.*
import com.puxiansheng.www.databinding.FragmentHomeTwoBinding

import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.main.CityListActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.fragment_home_two.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class HomeFragment : AppFragment(), OnRefreshLoadMoreListener {
    private lateinit var outViewModel: HomeTransferOutOrdersViewModel
    private lateinit var inViewModel: HomeTransferInOrdersViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appModel: MainViewModel
    private var leftAdapter: RecommListOrderAdapter? = null
    private var rightAdapter: RecommListOrderAdapter? = null
    private var isLoading = false
    private var isRerfrshLeft = true
    private var isRerfrshRight = true
    private var currentTab = 0
    private var leftCurrentPage = 1
    private var rightCurrentPage = 1
    private var mContext: Context? = null
    private var maqueeList = ArrayList<MarqueeInfo>()
    private var cityId = SpUtils.get(API.USER_CITY_ID, 0)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        inViewModel = ViewModelProvider(this)[HomeTransferInOrdersViewModel::class.java]
        outViewModel = ViewModelProvider(this)[HomeTransferOutOrdersViewModel::class.java]
        mContext = context
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeTwoBinding.inflate(inflater).apply {
        MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "HomeFragment")
        MobclickAgent.onEvent(
            mContext,
            UMengKeys.LOGIN_USER_ID,
            SpUtils.get(API.LOGIN_USER_ID, 0).toString()
        )



        //跑马灯绑定lifecycle
        lifecycle.addObserver(pxsHeadline)

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(
                resources.getDrawable(
                    R.drawable.recyclerview_divider_order,
                    null
                )
            )
            outList.addItemDecoration(it)
        }

        refreshlayout.setOnRefreshLoadMoreListener(this@HomeFragment)
        //左右适配器
        leftAdapter = RecommListOrderAdapter(requireActivity(), ArrayList())
        outList.adapter = leftAdapter

        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
            it.setDrawable(
                resources.getDrawable(
                    R.drawable.recyclerview_divider_order,
                    null
                )
            )
            inList.addItemDecoration(it)
        }

        rightAdapter = RecommListOrderAdapter(requireActivity(), ArrayList())
        inList.adapter = rightAdapter

        lifecycleOwner = viewLifecycleOwner
        appModel?.saveLoginUser()?.let {
            appModel?.currentUser?.postValue(it)
            API.setAuthToken(it.token)
        }

        buttonSelectLocation.setOnClickListener {
            if (Utils.isFastClick() && !isLoading) {
                val intent = Intent(requireActivity(), CityListActivity::class.java)
                intent.putExtra("locationCity", buttonSelectLocation.text)
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "LocationActivity")
            }
        }

        btSearch.setOnClickListener {
            if (Utils.isFastClick()) {
                val intent = Intent(requireActivity(), SearchActivity::class.java)
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "SearchActivity")
            }
        }

//        resources.displayMetrics.widthPixels.times(0.65).let {
//            topBannerView.layoutParams.height = it.toInt()
//        }

        val gridLayoutManager =
            GridLayoutManager(requireContext(), 4)
        menus.layoutManager = gridLayoutManager
        menus.adapter = HomeMenuAdapter(requireActivity(), mutableListOf())


        //动态添加tabitem
        tabs.addTab(tabs.newTab().setText("精品店铺"))
        tabs.addTab(tabs.newTab().setText("找店需求"))

        outList.visibility = View.VISIBLE
        inList.visibility = View.GONE

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    currentTab = tab.position
                    when (currentTab) {
                        0 -> {
                            outList.visibility = View.VISIBLE
                            inList.visibility = View.GONE
                            // initRecommendOutListView(outList)
                        }
                        1 -> {
                            outList.visibility = View.GONE
                            inList.visibility = View.VISIBLE
//                            initRecommendInListView(inList)
                        }
                    }
                }
            }
        })



        scroll.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY in 0..255) {
//                appStatusBar.setBackgroundColor(Color.argb(scrollY, 247, 137, 52))
//                layoutTop.setBackgroundColor(Color.argb(scrollY, 247, 137, 52))
            } else {
//                appStatusBar.setBackgroundColor(Color.argb(255, 247, 137, 52))
//                layoutTop.setBackgroundColor(Color.argb(255, 247, 137, 52))
            }
        }


    }.root


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (NetUtil.isNetworkConnected(requireContext())) {
                button_select_location.text = SpUtils.get(API.USER_CITY_NAME, "中国").toString()
                initView()
            } else {
                Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
            }
        } else {
//            if (maqueeList?.size > 0) {
//                pxs_headline.stopTimer()
//            }
//            top_banner_view.stopBanner()
        }
    }


    override fun onResume() {
        super.onResume()
        if (NetUtil.isNetworkConnected(requireContext())) {
            button_select_location.text = SpUtils.get(API.USER_CITY_NAME, "中国").toString()
            initView()
        } else {
            Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
        }
    }

    override fun onPause() {
        super.onPause()
//        if (maqueeList?.size > 0) {
//            pxs_headline.stopTimer()
//        }
//        top_banner_view.stopBanner()
    }


    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if (NetUtil.isNetworkConnected(requireContext())) {
            cityId = SpUtils.get(API.USER_CITY_ID, 0)
            when (currentTab) {
                0 -> {
                    //左边
                    isRerfrshLeft = false
                    leftCurrentPage += 1
                    lifecycleScope.launch {
                        outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(
                            cityId.toString(),
                            leftCurrentPage
                        )?.let {
                            leftAdapter?.addList(
                                it.orders as ArrayList<OrderDetailObject>,
                                isRerfrshLeft
                            )
                        }
                    }

                }

                1 -> {
                    isRerfrshRight = false
                    rightCurrentPage += 1
                    lifecycleScope.launch {
                        inViewModel.getHomeRecommendedTransferInOrdersFromRemote(
                            cityId.toString(),
                            rightCurrentPage
                        )?.let {
                            rightAdapter?.addList(
                                it.orders as ArrayList<OrderDetailObject>,
                                isRerfrshRight
                            )
                        }
                    }

                }
            }
        } else {
            Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
        }
        refreshLayout.finishLoadMore(1000)
    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        if (NetUtil.isNetworkConnected(requireContext())) {
            initView()
        } else {
            Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
        }
        refreshLayout.finishRefresh(1000)
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("HomeFragment")
//    }
//
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("HomeFragment")
//    }


    private fun initView() {
        Log.d("跑马灯", " = initView = ")
        isRerfrshLeft = true
        isRerfrshRight = true
        leftCurrentPage = 1
        rightCurrentPage = 1

        lifecycleScope.launch {
            homeViewModel.requestNewMenuImage()?.let {
                Log.d("bannerfragment yhhhh"," banner it.size = "+it.size)
                (menus.adapter as HomeMenuAdapter).setMenuData(it)
            }

//            homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
//                top_banner_view.setBannerImages(banners)
//            }
//            top_banner_view.startBanner()


            pxs_headline.clearResources()
            cityId = SpUtils.get(API.USER_CITY_ID, 0)
            homeViewModel.requestMarqueeMessage(cityId.toString())?.let { data ->


                maqueeList = data.infos as ArrayList<MarqueeInfo>
                if (maqueeList?.size > 0) {
                        pxs_headline.setResources(maqueeList)
                        pxs_headline.itemClickListener =
                            object : TextSwitchView.OnItemClickListener {
                                override fun onItemClick(position: Int) {
                                    if (Utils.isFastClick()) {
                                        //TODO shopId字符串会好些
                                        val intent =
                                            Intent(
                                                activity,
                                                TransferOutOrderDetailActivity::class.java
                                            )
                                        intent.putExtra("shopID", maqueeList[position].id?.toString())
                                        startActivity(intent)
                                    }
                                }
                            }
                        pxs_headline.isSelected = true
                } else {
                    pxs_headline.setCurrentText("")
                }
            }

            outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(
                cityId.toString(),
                leftCurrentPage
            )?.let {
                leftAdapter?.addList(it.orders as ArrayList<OrderDetailObject>, isRerfrshLeft)
                isLoading = false
            }

            inViewModel.getHomeRecommendedTransferInOrdersFromRemote(
                cityId.toString(),
                rightCurrentPage
            )?.let {
                rightAdapter?.addList(
                    it.orders as ArrayList<OrderDetailObject>,
                    isRerfrshRight
                )
            }
        }
    }

}