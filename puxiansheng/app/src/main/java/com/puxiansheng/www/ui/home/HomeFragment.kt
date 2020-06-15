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
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.TextSwitchView
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentHomeBinding
import com.puxiansheng.www.ui.business.InvestBusinessActivity
import com.puxiansheng.www.ui.info.InfoDetailActivity
import com.puxiansheng.www.ui.main.LocationActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.TransferInOrderDetailActivity
import com.puxiansheng.www.ui.order.TransferInOrdersActivity
import com.puxiansheng.www.ui.order.TransferOutOrderActivity
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import com.puxiansheng.www.ui.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class HomeFragment : Fragment(),OnRefreshLoadMoreListener {
    private lateinit var outViewModel: HomeTransferOutOrdersViewModel
    private lateinit var inViewModel: HomeTransferInOrdersViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appModel: MainViewModel
    private var leftAdapter: RecommListOrderAdapter? = null
    private var rightAdapter: RecommListOrderAdapter? = null
    var isLoading = false
    var isRerfrshLeft = true
    var isRerfrshRight = true
    private var currentTab = 0
    private var leftCurrentPage = 1;
    private var rightCurrentPage = 1;
//    private var tabTitles = listOf("精品店铺", "找店需求")
//    private val favoriteTransferOutFragment: Fragment = HomeTransferOutOrdersFragment()
//    private val favoriteTransferInFragment: Fragment = HomeTransferInOrdersFragment()
//    private var fragments = listOf<Fragment>(
//        favoriteTransferOutFragment,
//        favoriteTransferInFragment
//    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        inViewModel = ViewModelProvider(this)[HomeTransferInOrdersViewModel::class.java]
        outViewModel = ViewModelProvider(this)[HomeTransferOutOrdersViewModel::class.java]
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater).apply {

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
        }

        btSearch.setOnClickListener {
            val intent = Intent(requireActivity(), SearchActivity::class.java)
            startActivity(intent)
        }

        resources.displayMetrics.widthPixels.times(0.65).let {
            topBannerView.layoutParams.height = it.toInt()
            topBannerView.onImageClick { image: BannerImage ->
                appModel.pictureIntent(requireActivity(), image)
            }
        }

        simpleTransferOut.setOnClickListener {
            val intent = Intent(requireActivity(), TransferOutOrderActivity::class.java)
            intent.putExtra("title", "*")
            startActivity(intent)
        }

        simpleTransferIn.setOnClickListener {
            val intent = Intent(requireActivity(), TransferInOrdersActivity::class.java)
            intent.putExtra("title", "*")
            startActivity(intent)
        }

        investmentBusiness.setOnClickListener {
            val intent = Intent(requireActivity(), InvestBusinessActivity::class.java)
            intent.putExtra("title", "*")
            startActivity(intent)
        }

        fastTransferOut.setOnClickListener {
            val intent = Intent(requireActivity(), FastTransferOutActivity::class.java)
            startActivity(intent)
        }

        fastTransferIn.setOnClickListener {
            val intent = Intent(requireActivity(), FastTransferInActivity::class.java)
            startActivity(intent)
        }

//        refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
//        refresh.setOnRefreshListener {
//            isLoading = true
//            lifecycleScope.launch {
//                homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
//                    topBannerView.setBannerImages(banners)
//                }
//
//                homeViewModel.requestBannerImage("api_home_advertising")?.let { banners ->
//                    imgThree.url(banners[0].imageUrl)
//                    imgFour.url(banners[1].imageUrl)
//                    imgTwo.url(banners[2].imageUrl)
//                    imgOne.url(banners[3].imageUrl)
//
//
//                    imgThree.setOnClickListener {
//                        appModel.pictureIntent(requireActivity(), banners[0])
//                    }
//                    imgFour.setOnClickListener {
//                        appModel.pictureIntent(requireActivity(), banners[1])
//                    }
//                    imgTwo.setOnClickListener {
//                        appModel.pictureIntent(requireActivity(), banners[2])
//                    }
//                    imgOne.setOnClickListener {
//                        appModel.pictureIntent(requireActivity(), banners[3])
//                    }
//
//                }
//
//
//
//
//            }
//            refresh.isRefreshing = false
//        }

        //动态添加tabitem
        tabs.addTab(tabs.newTab().setText("精品店铺"))
        tabs.addTab(tabs.newTab().setText("找店需求"))

        outList.visibility = View.VISIBLE
        inList.visibility = View.GONE
//        initRecommendOutListView()
//        initRecommendInListView()

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


//        orderPager.adapter = OrderPagerAdapter(fragmentManager = childFragmentManager, lifecycle = viewLifecycleOwner.lifecycle)
//        TabLayoutMediator(tabs, orderPager,
//            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
//                tab.text = when (position) {
//                    0 -> "精品店铺"
//                    1 -> "找店需求"
//                    else -> ""
//                }
//            }).attach()


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
            isLoading = true

            lifecycleScope.launch {
                //                delay(300)
                homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
                    topBannerView.setBannerImages(banners)
                }
                homeViewModel.requestBannerImage("api_home_advertising")?.let { banners ->
                    imgThree.url(banners[0].imageUrl)
                    imgFour.url(banners[1].imageUrl)
                    imgTwo.url(banners[2].imageUrl)
                    imgOne.url(banners[3].imageUrl)
                    imgThree.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[0]
                        )
                    }
                    imgFour.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[1]
                        )
                    }
                    imgTwo.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[2]
                        )
                    }
                    imgOne.setOnClickListener {
                        appModel.pictureIntent(requireActivity(), banners[3])
                    }
                }

                homeViewModel.requestMarqueeMessage("1")?.let { infos ->
                    pxsHeadline.setResources(infos)
                    // pxsHeadline.setTextStillTime(3000)
                    pxsHeadline.itemClickListener = object : TextSwitchView.OnItemClickListener {
                        override fun onItemClick(position: Int) {

                            //TODO shopId字符串会好些
                            val intent =
                                Intent(activity, TransferOutOrderDetailActivity::class.java)
                            intent.putExtra("shopID", infos[position].id?.toString())
                            startActivity(intent)
                        }
                    }
//                    pxsHeadline.setOnItemClickListener{
//                        println("跑马灯点击1--->${it}")
//                        Toast.makeText(requireContext(), "点击了" + it.apiJumpParam, Toast.LENGTH_SHORT).show()
//                    }
                }
                pxsHeadline.isSelected = true
                SharedPreferencesUtil.put(API.USER_CITY_ID, it.nodeID)
                lifecycleScope.launch {
                    outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(it.nodeID.toString(),leftCurrentPage)
                        .let {
                            Log.d("recommend ", " leftAdapter = " + leftAdapter)
                            Log.d("---recommend", "TransferOut it = " + it)
                            leftAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshLeft)
                }
                }

                lifecycleScope.launch {
                    inViewModel.getHomeRecommendedTransferInOrdersFromRemote(it.nodeID.toString(),rightCurrentPage)
                        .let {
                            Log.d(
                                "---recommend",
                                "TransferOut it = " + it + " rightAdapter = " + rightAdapter
                            )
                            rightAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshRight)
                        }
                }
            }
        })
    }.root



    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            pxs_headline.startTimer()
            top_banner_view.startBanner()
//            order_pager.adapter = OrderPagerAdapter(
//                fragmentManager = childFragmentManager,
//                lifecycle = viewLifecycleOwner.lifecycle
//            )
//            TabLayoutMediator(tabs, order_pager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
//                tab.text = when (position) {
//                    0 -> "精品店铺"
//                    1 -> "找店需求"
//                    else -> ""
//                }
//            }).attach()
        } else {
            pxs_headline.stopTimer()
            top_banner_view.stopBanner()
        }
    }



    override fun onLoadMore(refreshLayout: RefreshLayout) {
        Log.d("recommend ", " onLoadMore----- " + currentTab)
        var  cityId =   SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
        when (currentTab) {
            0 -> {
                //左边
                isRerfrshLeft=false
                leftCurrentPage +=1
                lifecycleScope.launch {
                    outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(cityId.toString(),leftCurrentPage)
                        .let {
                            Log.d("recommend ", " leftAdapter = " + leftAdapter)
                            Log.d("---recommend", "TransferOut it = " + it)
                            leftAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshLeft)
                        }
                }

            }

            1 -> {
                isRerfrshRight=false
                rightCurrentPage += 1
                lifecycleScope.launch {
                    inViewModel.getHomeRecommendedTransferInOrdersFromRemote(cityId.toString(),rightCurrentPage)
                        .let {
                            Log.d(
                                "---recommend",
                                "TransferOut it = " + it + " rightAdapter = " + rightAdapter
                            )
                            rightAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshRight)
                        }
                }

            }
        }

        refreshLayout.finishLoadMore(2000)

    }


    override fun onRefresh(refreshLayout: RefreshLayout) {
        var  cityId =   SharedPreferencesUtil.get(API.USER_CITY_ID, 0)
        isRerfrshLeft=true
        isRerfrshRight=true
        leftCurrentPage = 1
        rightCurrentPage = 1

        lifecycleScope.launch {
            homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
                top_banner_view.setBannerImages(banners)
            }

            homeViewModel.requestBannerImage("api_home_advertising")?.let { banners ->
                img_three.url(banners[0].imageUrl)
                img_four.url(banners[1].imageUrl)
                img_two.url(banners[2].imageUrl)
                img_one.url(banners[3].imageUrl)


                img_three.setOnClickListener {
                    appModel.pictureIntent(requireActivity(), banners[0])
                }
                img_four.setOnClickListener {
                    appModel.pictureIntent(requireActivity(), banners[1])
                }
                img_two.setOnClickListener {
                    appModel.pictureIntent(requireActivity(), banners[2])
                }
                img_one.setOnClickListener {
                    appModel.pictureIntent(requireActivity(), banners[3])
                }

            }

            outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(cityId.toString(),leftCurrentPage)
                .let {
                    Log.d("recommend ", " leftAdapter = " + leftAdapter)
                    Log.d("---recommend", "TransferOut it = " + it)
                    leftAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshLeft)
                }

            inViewModel.getHomeRecommendedTransferInOrdersFromRemote(cityId.toString(),rightCurrentPage)
                .let {
                    Log.d(
                        "---recommend",
                        "TransferOut it = " + it + " rightAdapter = " + rightAdapter
                    )
                    rightAdapter?.addList(it as ArrayList<OrderDetailObject>, isRerfrshRight)
                }
        }





        refreshLayout.finishRefresh(2000)
    }
}