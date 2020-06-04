package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentHomeBinding
import com.puxiansheng.www.ui.business.InvestBusinessActivity
import com.puxiansheng.www.ui.main.LocationActivity
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.OrderPagerAdapter
import com.puxiansheng.www.ui.order.TransferInOrdersActivity
import com.puxiansheng.www.ui.order.TransferOutOrderActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferInActivity
import com.puxiansheng.www.ui.release.fasttransfer.FastTransferOutActivity
import com.puxiansheng.www.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appModel: MainViewModel
    var isLoading = false
    private var tabTitles = listOf("精品店铺", "找店需求")
    private val favoriteTransferOutFragment: Fragment = HomeTransferOutOrdersFragment()
    private val favoriteTransferInFragment: Fragment = HomeTransferInOrdersFragment()
    private var fragments = listOf<Fragment>(
        favoriteTransferOutFragment,
        favoriteTransferInFragment
    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentHomeBinding.inflate(inflater).apply {
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
            val intent= Intent(requireActivity(), SearchActivity::class.java)
            startActivity(intent)
        }

        resources.displayMetrics.widthPixels.times(0.65).let {
            topBannerView.layoutParams.height = it.toInt()

            topBannerView.onImageClick { image: BannerImage ->
                appModel.pictureIntent(requireActivity(), image)
            }

        }

        simpleTransferOut.setOnClickListener {
            val intent= Intent(requireActivity(), TransferOutOrderActivity::class.java)
            startActivity(intent)
        }

        simpleTransferIn.setOnClickListener {
            val intent= Intent(requireActivity(), TransferInOrdersActivity::class.java)
            startActivity(intent)
        }

        investmentBusiness.setOnClickListener {
            val intent= Intent(requireActivity(), InvestBusinessActivity::class.java)
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

        refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        refresh.setOnRefreshListener {
            isLoading = true
            lifecycleScope.launch {
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
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[3]
                        )
                    }

                }


                homeViewModel.requestMarqueeMessage("1")?.let { infos ->
                    pxsHeadline.setResources(infos)
                    pxsHeadline.setTextStillTime(3000)

                    pxsHeadline.setOnItemClickListener{
                        Toast.makeText(requireContext(), "点击了" + it.apiJumpParam, Toast.LENGTH_SHORT)
//                        appModel.
                    }
                }
//                homeViewModel.requestMarqueeMessage("1")?.let { infos ->
//                    var i = 0
//                    while (i < infos.size) {
//                        pxsHeadline.setCurrentText(infos[i].title)
//                        delay(4000)
//                    }
//                    pxsHeadline.setResources(infos)
//                    pxsHeadline.setTextStillTime(3000);
//                }
            }
            refresh.isRefreshing = false
        }

        pxsHeadline.isSelected = true

        orderPager.adapter = OrderPagerAdapter(
            fragmentManager = childFragmentManager,
            lifecycle = viewLifecycleOwner.lifecycle
        )
        TabLayoutMediator(
            tabs,
            orderPager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "精品店铺"
                    1 -> "找店需求"
                    else -> ""
                }
            }).attach()

//        pager.adapter = MessagePagerAdapter(
//            fragmentManager = childFragmentManager,
//            fragments = fragments,
//            titles = tabTitles
//        )
//        pager.offscreenPageLimit = 2
//        tabs.setupWithViewPager(pager)

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
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[3]
                        )
                    }
                }

                homeViewModel.requestMarqueeMessage("1")?.let { infos ->
                    pxsHeadline.setResources(infos)
                    pxsHeadline.setTextStillTime(3000)
                    pxsHeadline.setOnItemClickListener{
                        Toast.makeText(requireContext(), "点击了" + it.apiJumpParam, Toast.LENGTH_SHORT)
//                        appModel.
                    }
                }
                pxsHeadline.isSelected = true

                orderPager.adapter = OrderPagerAdapter(
                    fragmentManager = childFragmentManager,
                    lifecycle = viewLifecycleOwner.lifecycle
                )
                TabLayoutMediator(
                    tabs,
                    orderPager,
                    TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                        tab.text = when (position) {
                            0 -> "精品店铺"
                            1 -> "找店需求"
                            else -> ""
                        }
                    }).attach()

//                pager.adapter = InfoPagerAdapter(
//                    fragmentManager = childFragmentManager,
//                    fragments = fragments,
//                    titles = tabTitles
//                )
//                pager.offscreenPageLimit = 2
//                tabs.setupWithViewPager(pager)

            }
        })
    }.root

    override fun onResume() {
        super.onResume()
        top_banner_view.startBanner()
    }

    override fun onPause() {
        super.onPause()
        top_banner_view.stopBanner()
    }


}