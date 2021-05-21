package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.MarqueeInfo
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.http.OrderDetailObject
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.logic.bean.http.SuccessVideoBean
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.common.JumpUtils
import com.puxiansheng.www.common.TextSwitchView
import com.puxiansheng.www.databinding.FragmentHomeTwoBinding
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.tools.UMengKeys
import com.puxiansheng.www.tools.Utils
import com.puxiansheng.www.ui.main.CityListActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.message.MessageHomeListActivity
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import com.puxiansheng.www.ui.order.NewSuccessOrdersActivity
import com.puxiansheng.www.ui.order.SuccessVideoAdapter
import com.puxiansheng.www.ui.order.TransferOutOrderDetailActivity
import com.puxiansheng.www.ui.project.ProjectHomeAdapter
import com.puxiansheng.www.ui.project.ProjectHomeListActivity
import com.puxiansheng.www.ui.search.SearchActivity
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_my_history.*
import kotlinx.android.synthetic.main.activity_success_order_list.*
import kotlinx.android.synthetic.main.activity_transfer_out_order_detail.*
import kotlinx.android.synthetic.main.fragment_home_two.*
import kotlinx.android.synthetic.main.fragment_home_two.button_select_location
import kotlinx.android.synthetic.main.fragment_invest_business.*
import kotlinx.android.synthetic.main.fragment_transfer_out_order_detail.*
import kotlinx.android.synthetic.main.layout_top_search.*
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch

class NewHomeFragment : AppFragment(), OnRefreshLoadMoreListener {
    private lateinit var outViewModel: HomeTransferOutOrdersViewModel
    private lateinit var inViewModel: HomeTransferInOrdersViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appModel: MainViewModel
    private var videoAdapter: SuccessVideoAdapter? = null
    private var projectAdapter:ProjectHomeAdapter? = null
    private var leftAdapter: RecommHomeAdapter? = null
    private var rightAdapter: RecommHomeAdapter? = null
    private var isLoading = false
    private var isRerfrshLeft = true
    private var isRerfrshRight = true
    private var currentTab = 0
    private var leftCurrentPage = 1
    private var rightCurrentPage = 1
    private var leftTotalPage = 1
    private var rightTotalPage = 1
    private var mContext: Context? = null
    private var maqueeList = ArrayList<MarqueeInfo>()
    private var cityId = SpUtils.get(API.USER_CITY_ID, 0)
    private var tabTitles = listOf<String>("快速转店", "快速找店", "我的发布")
    private val cardOutFragment: Fragment = FastOutFragment()
    private val cardInFragment: Fragment = FastInFragment()
    private val cardReleaseFragment: Fragment = FastMyReleaseFragment()
    private var fragments = listOf(cardOutFragment, cardInFragment, cardReleaseFragment)
    private var isTopShow = false

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
        MyScreenUtil.setStateBarStyle(requireActivity(), true, R.color.color81, false)
//        val a=AndroidBug5497Workaround(activity)
//        mViewTopSearch = (topSearch.root as ViewStub).inflate()
        MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "HomeFragment")
        MobclickAgent.onEvent(
            mContext,
            UMengKeys.LOGIN_USER_ID,
            SpUtils.get(API.LOGIN_USER_ID, 0).toString()
        )
        topBannerView.loop(
            ticker = ticker(
                delayMillis = 1000 * 3,
                initialDelayMillis = 1000 * 3,
                context = lifecycleScope.coroutineContext
            ),
            coroutineScope = lifecycleScope
        )

        bannerEvent.loop(
            ticker = ticker(
                delayMillis = 1000 * 3,
                initialDelayMillis = 1000 * 3,
                context = lifecycleScope.coroutineContext
            ),
            coroutineScope = lifecycleScope
        )



        //跑马灯绑定lifecycle
        lifecycle.addObserver(pxsHeadline)

//        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
//            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
//            outList.addItemDecoration(it)
//        }

        refreshlayout.setOnRefreshLoadMoreListener(this@NewHomeFragment)
        //左右适配器
        leftAdapter = RecommHomeAdapter(requireActivity(), ArrayList())
        outList.adapter = leftAdapter

//        DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL).let {
//            it.setDrawable(resources.getDrawable(R.drawable.recyclerview_divider_order, null))
//            inList.addItemDecoration(it)
//        }

        rightAdapter = RecommHomeAdapter(requireActivity(), ArrayList())
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
                intent.putExtra("inType", 1)
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "LocationActivity")
            }
        }

        btTopLocation.setOnClickListener {
            if (Utils.isFastClick() && !isLoading) {
                val intent = Intent(requireActivity(), CityListActivity::class.java)
                intent.putExtra("locationCity", btTopLocation.text)
                intent.putExtra("inType", 1)
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

        btTopSearch.setOnClickListener {
            if (Utils.isFastClick()) {
                val intent = Intent(requireActivity(), SearchActivity::class.java)
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "SearchActivity")
            }
        }


          btMessage.setOnClickListener {
            if (Utils.isFastClick()) {
                val intent = Intent(requireActivity(), MessageHomeListActivity::class.java)
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "MessageHomeListActivity")
            }
        }

        btTopMessage.setOnClickListener {
            if (Utils.isFastClick()) {
                val intent = Intent(requireActivity(), ProjectHomeListActivity::class.java)
                startActivity(intent)
                MobclickAgent.onEvent(mContext, UMengKeys.PAGE_NAME, "MessageHomeListActivity")
            }
        }

        scroll.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY in 0..150) {
//                appStatusBar.setBackgroundColor(Color.argb(scrollY, 247, 137, 52))
//                layoutTop.setBackgroundColor(Color.argb(scrollY, 247, 137, 52))
                if (isTopShow) {
                    layoutSearchTop.visibility = View.GONE
                    btFastTop.visibility =  View.GONE
                    isTopShow = false
                    MyScreenUtil.setStateBarStyle(requireActivity(), true, R.color.color81, false)
                }
            } else {
//                appStatusBar.setBackgroundColor(Color.argb(255, 247, 137, 52))
//                layoutTop.setBackgroundColor(Color.argb(255, 247, 137, 52))
                if (!isTopShow) {
                    layoutSearchTop.visibility = View.VISIBLE
                    btFastTop.visibility =  View.VISIBLE
                    isTopShow = true
                    MyScreenUtil.setStateBarStyle(requireActivity(), true, R.color.color81, true)
                }
            }
            btKf.rollBack()
        }

        btKf.setOnClickListener {
//            FastConnectDialog(
//            ).show(requireActivity().supportFragmentManager, FastConnectDialog::class.java.name)

            val intent = Intent(requireActivity(), ProjectHomeListActivity::class.java)
            startActivity(intent)
        }

        btFastTop.setOnClickListener {
            scroll.scrollTo(0,0)
        }

//        resources.displayMetrics.widthPixels.times(0.65).let {
//            topBannerView.layoutParams.height = it.toInt()
//        }

//菜单按钮
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 5)
        menus.layoutManager = gridLayoutManager
        menus.adapter = HomeMenuAdapter(requireActivity(), mutableListOf())

        moreSuccee.setOnClickListener {
            if (Utils.isFastClick()) {
                val intent =
                    Intent(requireActivity(), NewSuccessOrdersActivity::class.java)
                intent.putExtra("type", 1)
                startActivity(intent)
            }
        }

        cardPager.adapter = MessagePagerAdapter(
            fragmentManager = childFragmentManager,
            fragments = fragments,
            titles = tabTitles
        )

        transferTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        layout_transfer_card.setBackgroundResource(R.mipmap.bg_fast_orange)
                    }
                    1 -> {
                        layout_transfer_card.setBackgroundResource(R.mipmap.bg_fast_blue)
                    }
                    2 -> {
                        layout_transfer_card.setBackgroundResource(R.mipmap.bg_fast_white)
                    }
                }
            }

        })

//        transferTabs.addTab(transferTabs.newTab().setText("快速转店"))
//        transferTabs.addTab(transferTabs.newTab().setText("快速找店"))
//        transferTabs.addTab(transferTabs.newTab().setText("我的发布"))

        //客户见证

        val linearLayoutManager =
            LinearLayoutManager(requireContext())
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        successList.layoutManager = linearLayoutManager
        videoAdapter = SuccessVideoAdapter(requireContext(), arrayListOf(), 2)
        successList.adapter = videoAdapter


        //好项目
        projectAdapter = ProjectHomeAdapter(requireActivity(), ArrayList())
        projectList.adapter = projectAdapter


        khMore.setOnClickListener {
            val intent = Intent(requireActivity(), NewSuccessOrdersActivity::class.java)
            intent.putExtra("type", 2)
            requireActivity().startActivity(intent)
            MobclickAgent.onEvent(context, UMengKeys.PAGE_NAME, "NewTransferSuccessOrdersActivity")
        }


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
            Log.d("currentCity ", "home cityName =  " + it.text)
            buttonSelectLocation.text = it.text
            btTopLocation.text = it.text
            homeViewModel.currentCity = it.nodeID.toString()
            SpUtils.put(API.USER_CITY_ID, it.nodeID)
            isLoading = true
            initView()
        })
    }.root


    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            if (NetUtil.isNetworkConnected(requireContext())) {
                button_select_location.text =
                    SpUtils.get(API.USER_CITY_NAME, "中国").toString()
                Log.d("---onLoadMore","onHiddenChanged = "+currentTab)
//                initView()
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
                SpUtils.get(API.USER_CITY_NAME, "中国").toString()
//            initView()
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
            cityId = SpUtils.get(API.USER_CITY_ID, 0)
            Log.d("---onLoadMore","currentTab = "+currentTab)
            when (currentTab) {
                0 -> {
                    //左边
                    isRerfrshLeft = false
                    if (leftCurrentPage < leftTotalPage) {
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
                }

                1 -> {
                    isRerfrshRight = false
                    Log.d("---onLoadMore","rightCurrentPage = "+rightCurrentPage+"   rightTotalPage = "+rightTotalPage)
                    if (rightCurrentPage < rightTotalPage) {
                        rightCurrentPage += 1
                        lifecycleScope.launch {
                            inViewModel.getHomeRecommendedTransferInOrdersFromRemote(
                                cityId.toString(),
                                rightCurrentPage
                            )?.let {
                                Log.d("---onLoadMore","-----addList")
                                rightAdapter?.addList(
                                    it.orders as ArrayList<OrderDetailObject>,
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


    private fun initView() {
        isRerfrshLeft = true
        isRerfrshRight = true
        leftCurrentPage = 1
        rightCurrentPage = 1

        //快速找卡片

        card_pager.offscreenPageLimit = 3
        transfer_tabs.setupWithViewPager(card_pager)


        lifecycleScope.launch {
            homeViewModel.requestNewMenuImage()?.let {
                (menus.adapter as HomeMenuAdapter).setMenuData(it)
            }

            homeViewModel.requestBannerImage("app_model_images_banner")?.let { banners ->
                top_banner_view.setImages(banners, false)
//                banner_index.removeAllViews()
//                for (i in 0 until banners.size) {
//                    var tempButton: RadioButton = RadioButton(requireContext())
//                    tempButton.setButtonDrawable(R.drawable.bg_index_bt) // 设置按钮的样式
//                    tempButton.setPadding(10, 0, 10, 0) // 设置文字距离按钮四周的距离
//                    banner_index.addView(
//                        tempButton,
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT
//                    )
//                }

                top_banner_view.onImageClick {
                    if (Utils.isFastClick()) {
                        JumpUtils.pictureIntent(requireContext(), it)
                    }
                }

//                top_banner_view.listener = object : ImageSwitcher.OnPageChange {
//                    override fun onScrolled(index: Int) {
//                        var bt: RadioButton = banner_index.getChildAt(index) as RadioButton
//                        bt.isChecked = true
//                    }
//                }
//
//                var bt: RadioButton = banner_index.getChildAt(0) as RadioButton
//                bt.isChecked = true
            }


            homeViewModel.requestBannerImage("app_home_capsule_images")?.let { banners ->
                banner_event.setImages(banners, true)


                banner_event.onImageClick {
                    if (Utils.isFastClick()) {
                        JumpUtils.pictureIntent(requireContext(), it)
                    }
                }

            }


            pxs_headline.clearResources()
            cityId = SpUtils.get(API.USER_CITY_ID, 0)

            homeViewModel.requestMarqueeMessage(cityId.toString())?.let { data ->
                maqueeList = data.infos as ArrayList<MarqueeInfo>
                if (maqueeList?.size > 0) {
                    pxs_headline.setResources(maqueeList)
                    pxs_headline.itemClickListener =
                        object : TextSwitchView.OnItemClickListener {
                            override fun onItemClick(position: Int) {
                                var marqueeInfo = maqueeList[position]
                                if (Utils.isFastClick()) {
                                    JumpUtils.zixunIntent(requireActivity(),marqueeInfo)
                                    //TODO shopId字符串会好些
//                                    val intent =
//                                        Intent(
//                                            activity,
//                                            TransferOutOrderDetailActivity::class.java
//                                        )
//                                    intent.putExtra("shopID", maqueeList[position].id?.toString())
//                                    startActivity(intent)
                                }
                            }
                        }
                    pxs_headline.isSelected = true
                } else {
                    pxs_headline.setCurrentText("")
                }
            }

//            homeViewModel.requestMarqueeMessage(cityId.toString())?.let { data ->
//                maqueeList = data.infos as ArrayList<MarqueeInfo>
//                if (maqueeList?.size > 0) {
//                    if (maqueeList.isNotEmpty()) {
//                        pxs_headline.setResources(maqueeList)
//                        pxs_headline.itemClickListener = object : TextSwitchView.OnItemClickListener {
//                                override fun onItemClick(position: Int) {
//                                    if (Utils.isFastClick()) {
//                                        //TODO shopId字符串会好些
//                                        val intent = Intent(activity, TransferOutOrderDetailActivity::class.java)
//                                        intent.putExtra("shopID", maqueeList[position].id?.toString())
//                                        startActivity(intent)
//                                    }
//                                }
//                            }
//                        pxs_headline.isSelected = true
//                    }
//                } else {
//                    pxs_headline.setCurrentText("")
//                }
//                if (data.topInfo != null) {
//                    pxs_title.text = data.topInfo!!.title
//                    pxs_title.setOnClickListener {
//                        if(data.topInfo!!.id != 0) {
//                            if (Utils.isFastClick()) {
//                                val intent =
//                                    Intent(
//                                        activity,
//                                        NewInfoDetailActivity::class.java
//                                    )
//                                intent.putExtra("shop_Id", data.topInfo!!.id)
//                                startActivity(intent)
//                            }
//                        }
//                    }
//                }
//            }

            homeViewModel.requestHomeVideo()?.let { video ->
                videoAdapter?.addList(video as ArrayList<SuccessVideoBean>, true)
            }

            homeViewModel.requestHomeProject()?.let { project ->
                projectAdapter?.addList(project as ArrayList<ProjectDetailObject>, true)
            }


            outViewModel.getHomeRecommendedTransferOutOrdersFromRemote(
                cityId.toString(),
                leftCurrentPage
            )?.let {
                leftAdapter?.addList(it.orders as ArrayList<OrderDetailObject>, isRerfrshLeft)
                leftTotalPage = it.totalPages
                isLoading = false
            }

            Log.d("---onLoadMore","  initview   rightCurrentPage = "+rightCurrentPage+"   isRerfrshRight = "+isRerfrshRight)
            inViewModel.getHomeRecommendedTransferInOrdersFromRemote(
                cityId.toString(),
                rightCurrentPage
            )?.let {
                rightAdapter?.addList(
                    it.orders as ArrayList<OrderDetailObject>,
                    isRerfrshRight
                )
                rightTotalPage = it.totalPages
                isLoading = false
            }
        }
    }

}