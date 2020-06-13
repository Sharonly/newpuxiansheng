package com.puxiansheng.www.ui.main

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.*
import com.puxiansheng.www.R
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.puxiansheng.www.databinding.FragmentMainBinding
import com.puxiansheng.www.ui.home.HomeFragment
import com.puxiansheng.www.ui.home.HomeViewModel
import com.puxiansheng.www.ui.info.InfoHomeListFragment
import com.puxiansheng.www.ui.message.MessageHomeListFragment
import com.puxiansheng.www.ui.mine.MineFragment
import com.puxiansheng.www.ui.release.ReleaseFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class MainFragment : NavHostFragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var appModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_main,
        container,
        false
    )

    private val homeFragment: Fragment = HomeFragment()
    private val infoHomeFragment: Fragment = InfoHomeListFragment()
    private val releaseFragment: Fragment = ReleaseFragment()
    private val mineFragment: Fragment = MineFragment()
    private var messageHomeFragment: Fragment = MessageHomeListFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentMainBinding.bind(view).let { binding ->
            binding.lifecycleOwner = viewLifecycleOwner

            binding.bottomNavBar?.itemIconTintList = null
            binding.bottomNavBar?.setupWithNavController(navController)
//            adjustNavigationIcoSize(binding.bottomNavBar);

            binding.bottomNavBar.setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_home -> {
                        if (appModel.lastFragment !is HomeFragment) {
                            childFragmentManager.beginTransaction().hide(appModel.lastFragment)
                                .show(
                                    homeFragment
                                ).commit()
                            appModel.lastFragment = homeFragment

                        }
                    }
                    R.id.navigation_info -> {
                        if (appModel.lastFragment !is InfoHomeListFragment) {
                            childFragmentManager.beginTransaction().hide(appModel.lastFragment)
                                .show(
                                    infoHomeFragment
                                ).commit()
                            appModel.lastFragment = infoHomeFragment
                        }
                    }

                    R.id.navigation_release -> {
                        if (appModel.lastFragment !is ReleaseFragment) {
                            childFragmentManager.beginTransaction().hide(appModel.lastFragment).show(releaseFragment).commit()
                            appModel.lastFragment = releaseFragment

                        }
                    }
                    R.id.navigation_message -> {
                        if (appModel.lastFragment !is MessageHomeListFragment) {
                            childFragmentManager.beginTransaction().hide(appModel.lastFragment)
                                .show(
                                    messageHomeFragment
                                ).commit()
                            appModel.lastFragment = messageHomeFragment

                        }
                    }

                    R.id.navigation_mine -> {
                        if (appModel.lastFragment !is MineFragment) {
                            childFragmentManager.beginTransaction().hide(appModel.lastFragment)
                                .show(mineFragment).commit()
                            appModel.lastFragment = mineFragment
                        }
                    }
                }
                return@setOnNavigationItemSelectedListener true
            }
            addFragmentView()
        }
    }


    private fun removeAllView() {
        var begin: FragmentTransaction? = fragmentManager?.beginTransaction()
        childFragmentManager.fragments.forEach { it ->
            if (it != null && it.isAdded) {
                begin?.remove(it);
            }
        }
        begin?.commit()
    }


    private fun addFragmentView() {
        if (childFragmentManager.fragments.size == 0) {
            childFragmentManager.beginTransaction()
                .add(
                    R.id.tabNavHost,
                    mineFragment,
                    MineFragment::class.java.name
                ).hide(mineFragment)
                .add(
                    R.id.tabNavHost,
                    messageHomeFragment,
                    MessageHomeListFragment::class.java.name
                ).hide(messageHomeFragment)
                .add(
                    R.id.tabNavHost,
                    releaseFragment,
                    ReleaseFragment::class.java.name
                ).hide(releaseFragment)

                .add(
                    R.id.tabNavHost,
                    infoHomeFragment,
                    InfoHomeListFragment::class.java.name
                ).hide(infoHomeFragment)
                .add(
                    R.id.tabNavHost,
                    homeFragment,
                    HomeFragment::class.java.name
                )
                .commit()
            appModel.lastFragment = homeFragment
        }
    }



    private fun adjustNavigationIcoSize(navigation: BottomNavigationView) {
        val menuView: BottomNavigationMenuView =
            navigation.getChildAt(0) as BottomNavigationMenuView
        menuView.clipChildren = false
        val menuItemView: ViewGroup = menuView.getChildAt(2) as ViewGroup
        menuItemView.clipChildren = false
        val iconView: View =
            menuView.getChildAt(2).findViewById(androidx.navigation.R.id.icon)
        val icondisplayMetrics = resources.displayMetrics
        val iconLayoutParams: FrameLayout.LayoutParams =
            iconView.layoutParams as FrameLayout.LayoutParams
        iconLayoutParams.height = 100
//            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90f, icondisplayMetrics).toInt()
        iconLayoutParams.width = 100
//            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60f, icondisplayMetrics).toInt()
        iconLayoutParams.topMargin = -25
        iconLayoutParams.gravity = Gravity.CENTER_HORIZONTAL
        iconView.layoutParams = iconLayoutParams
    }
}