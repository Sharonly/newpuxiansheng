package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MarqueeInfo
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.logic.bean.http.SuccessVideoBean
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.common.TextSwitchView
import com.puxiansheng.www.databinding.FragmentNewHomeBinding
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.main.CityListActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import com.puxiansheng.www.ui.mine.history.HistoryInOrdersFragment
import com.puxiansheng.www.ui.mine.history.HistoryInfosFragment
import com.puxiansheng.www.ui.mine.history.HistoryOutOrdersFragment
import com.puxiansheng.www.ui.order.SuccessVideoAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_my_history.*
import kotlinx.android.synthetic.main.activity_success_order_list.*
import kotlinx.android.synthetic.main.fragment_new_home.*
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

class NewHomeFragment : AppFragment(), OnRefreshLoadMoreListener {
    private lateinit var outViewModel: HomeTransferOutOrdersViewModel
    private lateinit var inViewModel: HomeTransferInOrdersViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appModel: MainViewModel
    private var videoAdapter: SuccessVideoAdapter? = null
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
    private var cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
    private var tabTitles = listOf<String>("快速转店", "快速找店", "我的发布")
    private val cardOutFragment: Fragment = FastOutFragment()
    private val cardInFragment: Fragment = FastInFragment()
    private val cardReleaseFragment: Fragment =  FastMyReleaseFragment()
    private var fragments = listOf(cardOutFragment,cardInFragment,cardReleaseFragment)

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
    ): View? = FragmentNewHomeBinding.inflate(inflater).apply {
        MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "HomeFragment")
        MobclickAgent.onEvent(
            mContext,
            UMengKeys.LOGIN_USER_ID,
            SharedPreferencesUtil.get(API.LOGIN_USER_ID, 0).toString()
        )
        topBannerView.loop(
            ticker = ticker(
                delayMillis = 1000 * 3,
                initialDelayMillis = 1000 * 3,
                context = lifecycleScope.coroutineContext
            ),
            coroutineScope = lifecycleScope
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

        refreshlayout.setOnRefreshLoadMoreListener(this@NewHomeFragment)
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

        cardPager.adapter = MessagePagerAdapter(
            fragmentManager =requireActivity().supportFragmentManager ,
            fragments = fragments,
            titles = tabTitles
        )

        cardPager.offscreenPageLimit = 3
        transferTabs.setupWithViewPager(cardPager)

//        transferTabs.addTab(transferTabs.newTab().setText("快速转店"))
//        transferTabs.addTab(transferTabs.newTab().setText("快速找店"))
//        transferTabs.addTab(transferTabs.newTab().setText("我的发布"))

        val linearLayoutManager =
            LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        successList.layoutManager = linearLayoutManager
        videoAdapter = SuccessVideoAdapter(requireContext(), arrayListOf(), 2)
        successList.adapter = videoAdapter


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



        appModel.currentCity.observe(viewLifecycleOwner, Observer {
            buttonSelectLocation.text = it.text
            homeViewModel.currentCity = it.nodeID.toString()
            SharedPreferencesUtil.put(API.USER_CITY_ID, it.nodeID)
            isLoading = true
            initView()
        })
    }.root


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (NetUtil.isNetworkConnected(requireContext())) {
                button_select_location.text =
                    SharedPreferencesUtil.get(API.USER_CITY_NAME, "中国").toString()
                initView()
            } else {
                Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
            }
        } else {
            if (maqueeList?.size > 0) {
                pxs_headline.stopTimer()
            }
//            top_banner_view.stopBanner()
        }
    }


    override fun onResume() {
        super.onResume()
        if (NetUtil.isNetworkConnected(requireContext())) {
            button_select_location.text =
                SharedPreferencesUtil.get(API.USER_CITY_NAME, "中国").toString()
            initView()
        } else {
            Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
        }
    }

    override fun onPause() {
        super.onPause()
        if (maqueeList?.size > 0) {
            pxs_headline.stopTimer()
        }
//        top_banner_view.stopBanner()
    }


    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if (NetUtil.isNetworkConnected(requireContext())) {
            cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
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
                                it as ArrayList<OrderDetailObject>,
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
                                it as ArrayList<OrderDetailObject>,
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
        isRerfrshLeft = true
        isRerfrshRight = true
        leftCurrentPage = 1
        rightCurrentPage = 1

        lifecycleScope.launch {
            homeViewModel.requestNewMenuImage()?.let {
                (menus.adapter as HomeMenuAdapter).setMenuData(it)
            }

            homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
                top_banner_view.setImages(banners)
                banner_index.removeAllViews()
                for (i in 0 until banners.size) {
                    var tempButton: RadioButton = RadioButton(requireContext())
                    tempButton.setButtonDrawable(R.drawable.bg_red_circle) // 设置按钮的样式
                    tempButton.setPadding(10, 0, 10, 0) // 设置文字距离按钮四周的距离
                    banner_index.addView(
                        tempButton,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
            }


            pxs_headline.clearResources()
            cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
            homeViewModel.requestMarqueeMessage("1", cityId.toString())?.let { info ->
                maqueeList = info as ArrayList<MarqueeInfo>
                if (maqueeList?.size > 0) {
                    if (info.isNotEmpty()) {
                        pxs_headline.setResources(info)
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
                                        intent.putExtra("shopID", info[position].id?.toString())
                                        startActivity(intent)
                                    }
                                }
                            }
                        pxs_headline.isSelected = true
                    }
                } else {
                    pxs_headline.setCurrentText("")
                }
            }

            homeViewModel.requestHomeVideo()?.let { video ->
                videoAdapter?.addList(video as ArrayList<SuccessVideoBean>, true)
            }

            outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(
                cityId.toString(),
                leftCurrentPage
            )?.let {
                leftAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshLeft)
                isLoading = false
            }

            inViewModel.getHomeRecommendedTransferInOrdersFromRemote(
                cityId.toString(),
                rightCurrentPage
            )?.let {
                rightAdapter?.addList(
                    it as ArrayList<OrderDetailObject>,
                    isRerfrshRight
                )

            }
        }
    }

}