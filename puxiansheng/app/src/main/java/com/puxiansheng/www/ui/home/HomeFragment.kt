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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.MarqueeInfo
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.util.ext.SharedPreferencesUtil.Companion.get
import com.puxiansheng.www.R
import com.puxiansheng.www.common.*
import com.puxiansheng.www.databinding.FragmentHomeBinding
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.main.LocationActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import com.youth.banner.listener.OnBannerClickListener
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_home.*
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
    ): View? = FragmentHomeBinding.inflate(inflater).apply {
        MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "HomeFragment")
        MobclickAgent.onEvent(
            mContext,
            UMengKeys.LOGIN_USER_ID,
            get(API.LOGIN_USER_ID, 0).toString()
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
            val intent = Intent(requireActivity(), LocationActivity::class.java)
            startActivity(intent)
            MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "LocationActivity")
        }

        btSearch.setOnClickListener {
            val intent = Intent(requireActivity(), SearchActivity::class.java)
            startActivity(intent)
            MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "SearchActivity")
        }

        resources.displayMetrics.widthPixels.times(0.65).let {
            topBannerView.layoutParams.height = it.toInt()
//            topBannerView.onImageClick { image: BannerImage ->
//                appModel.pictureIntent(requireActivity(), image)
//            }
        }

//        simpleTransferOut.setOnClickListener {
////            val intent = Intent(requireActivity(), TransferOutOrderActivity::class.java)
//            val intent = Intent(requireActivity(), NewTransferOutOrdersActivity::class.java)
//            intent.putExtra("title", "*")
//            startActivity(intent)
//        }
//
//        simpleTransferIn.setOnClickListener {
//            val intent = Intent(requireActivity(), NewTransferInOrdersActivity::class.java)
//            intent.putExtra("title", "*")
//            startActivity(intent)
//        }
//
//        investmentBusiness.setOnClickListener {
//            val intent = Intent(requireActivity(), BusinessListActivity::class.java)
//            intent.putExtra("title", "*")
//            startActivity(intent)
//        }
//
//        fastTransferOut.setOnClickListener {
//            val intent = Intent(requireActivity(), FastTransferOutActivity::class.java)
//            startActivity(intent)
//        }
//
//        fastTransferIn.setOnClickListener {
//            val intent = Intent(requireActivity(), FastTransferInActivity::class.java)
//            startActivity(intent)
//        }

        val linearLayoutManager =
            LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        menus.layoutManager = linearLayoutManager
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
                appStatusBar.setBackgroundColor(Color.argb(scrollY, 247, 137, 52))
                layoutTop.setBackgroundColor(Color.argb(scrollY, 247, 137, 52))
            } else {
                appStatusBar.setBackgroundColor(Color.argb(255, 247, 137, 52))
                layoutTop.setBackgroundColor(Color.argb(255, 247, 137, 52))
            }
        }


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
                button_select_location.text = get(API.USER_CITY_NAME, "中国").toString()
                initView()
            } else {
                Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
            }
        } else {
            if(maqueeList?.size >0) {
                pxs_headline.stopTimer()
            }
            top_banner_view.stopBanner()
        }
    }


    override fun onResume() {
        super.onResume()
        if (NetUtil.isNetworkConnected(requireContext())) {
            button_select_location.text = get(API.USER_CITY_NAME, "中国").toString()
            initView()
        } else {
            Toast.makeText(requireContext(), "请连接网络", Toast.LENGTH_SHORT)
        }
    }

    override fun onPause() {
        super.onPause()
        if(maqueeList?.size >0) {
            pxs_headline.stopTimer()
        }
        top_banner_view.stopBanner()
    }


    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if (NetUtil.isNetworkConnected(requireContext())) {
            var cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
            when (currentTab) {
                0 -> {
                    //左边
                    isRerfrshLeft = false
                    leftCurrentPage += 1
                    lifecycleScope.launch {
                        outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(
                            cityId.toString(),
                            leftCurrentPage
                        )
                            .let {
                                if (it?.isNotEmpty()!!) {
                                    leftAdapter?.addList(
                                        it as ArrayList<OrderDetailObject>,
                                        isRerfrshLeft
                                    )
                                }
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
                        )
                            .let {
                                if (!it.isNullOrEmpty()) {
                                    rightAdapter?.addList(
                                        it as ArrayList<OrderDetailObject>,
                                        isRerfrshRight
                                    )
                                }
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
        var cityId = SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
        isRerfrshLeft = true
        isRerfrshRight = true
        leftCurrentPage = 1
        rightCurrentPage = 1

        lifecycleScope.launch {
            homeViewModel.requestMenuImage()?.let {
                (menus.adapter as HomeMenuAdapter).setMenuData(it)
            }

            homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
                top_banner_view.setBannerImages(banners)
            }
            top_banner_view.startBanner()

            homeViewModel.requestBannerImage("api_home_advertising")?.let { banners ->
                img_three.url(banners[0].imageUrl)
                img_four.url(banners[1].imageUrl)
                img_two.url(banners[2].imageUrl)
                img_one.url(banners[3].imageUrl)


                img_three.setOnClickListener {
                    JumpUtils.pictureIntent(requireActivity(), banners[0])
                }
                img_four.setOnClickListener {
                    JumpUtils.pictureIntent(requireActivity(), banners[1])
                }
                img_two.setOnClickListener {
                    JumpUtils.pictureIntent(requireActivity(), banners[2])
                }
                img_one.setOnClickListener {
                    JumpUtils.pictureIntent(requireActivity(), banners[3])
                }


            }

            pxs_headline.clearResources()
            homeViewModel.requestMarqueeMessage("1",cityId.toString())?.let { info ->
                Log.e("pxs_headline"," info = "+info.size)
                maqueeList = info as ArrayList<MarqueeInfo>
                if(info.isNotEmpty()) {
                    pxs_headline.setResources(info)
                    pxs_headline.itemClickListener = object : TextSwitchView.OnItemClickListener {
                        override fun onItemClick(position: Int) {
                            //TODO shopId字符串会好些
                            val intent =
                                Intent(activity, TransferOutOrderDetailActivity::class.java)
                            intent.putExtra("shopID", info[position].id?.toString())
                            startActivity(intent)
                        }
                    }
                    pxs_headline.isSelected = true
                }
                if(maqueeList?.size >0) {
                    pxs_headline.startTimer()
                }else{
                    pxs_headline.setCurrentText("")
                }
            }


            outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(
                cityId.toString(),
                leftCurrentPage
            )?.let {
                    if (!it.isNullOrEmpty()) {
                        leftAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshLeft)
                    }
                }

            inViewModel.getHomeRecommendedTransferInOrdersFromRemote(
                cityId.toString(),
                rightCurrentPage
            )?.let {
                    if (!it.isNullOrEmpty()) {
                        rightAdapter?.addList(
                            it as ArrayList<OrderDetailObject>,
                            isRerfrshRight
                        )
                    }
                }
        }
    }

}