package com.puxiansheng.www.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.www.R
import com.puxiansheng.www.common.url
import com.puxiansheng.www.databinding.FragmentHomeBinding
import com.puxiansheng.www.ui.main.MainViewModel
import com.puxiansheng.www.ui.order.OrderPagerAdapter
import com.puxiansheng.www.ui.order.TransferInOrdersActivity
import com.puxiansheng.www.ui.order.TransferOutOrderActivity
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
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
                R.id.action_mainFragment_to_locationFragment
            )
        }

        btSearch.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
                R.id.action_mainFragment_to_searchFragment
            )
        }

        resources.displayMetrics.widthPixels.times(0.65).let {
            topBannerView.layoutParams.height = it.toInt()

            topBannerView.onImageClick { image: BannerImage ->
                appModel.pictureIntent(requireActivity(), image)
            }

//            topBannerView.loop(
//                ticker = ticker(
//                    delayMillis = 1000 * 3,
//                    initialDelayMillis = 1000 * 3,
//                    context = lifecycleScope.coroutineContext
//                ),
//                coroutineScope = lifecycleScope
//            )
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
            Navigation.findNavController(requireActivity(), R.id.homeNavHost).navigate(
                R.id.action_mainFragment_to_investBusinessFragment
            )
        }

        fastTransferOut.setOnClickListener {

        }

        fastTransferIn.setOnClickListener {

        }

        refresh.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.appMain))
        refresh.setOnRefreshListener {
            isLoading = true
            lifecycleScope.launch {
                homeViewModel.requestBannerImage("mobile_index_banner")?.let { banners ->
                    topBannerView.setBannerImages(banners)
                }

                homeViewModel.requestBannerImage("api_home_advertising")?.let { banners ->
                    imgOne.url(banners[0].imageUrl)
                    imgTwo.url(banners[1].imageUrl)
                    imgThree.url(banners[2].imageUrl)
                    imgFour.url(banners[3].imageUrl)
                    imgOne.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[0]
                        )
                    }
                    imgTwo.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[1]
                        )
                    }
                    imgThree.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[2]
                        )
                    }
                    imgFour.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[3]
                        )
                    }

                }

                homeViewModel.requestMarqueeMessage("1")?.let { infos ->
                    var i = 0
                    while (i < infos.size) {
                        pxsHeadline.text = infos[i].title
                        delay(7000)
                        if (i == infos.size - 1) {
                            i = 0
                        } else {
                            i++
                        }
                    }
                }
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
                    imgOne.url(banners[0].imageUrl)
                    imgTwo.url(banners[1].imageUrl)
                    imgThree.url(banners[2].imageUrl)
                    imgFour.url(banners[3].imageUrl)
                    imgOne.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[0]
                        )
                    }
                    imgTwo.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[1]
                        )
                    }
                    imgThree.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[2]
                        )
                    }
                    imgFour.setOnClickListener {
                        appModel.pictureIntent(
                            requireActivity(),
                            banners[3]
                        )
                    }


                }

                homeViewModel.requestMarqueeMessage("1")?.let { infos ->
                    Log.d("---marquee--", "infos = " + infos.size)
                    var i = 0
                    while (i < infos.size) {
                        pxsHeadline.text = infos[i].title
                        delay(10000)
                        if (i == infos.size - 1) {
                            i = 0
                        } else {
                            i++
                        }
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
            }
        })
    }.root


}