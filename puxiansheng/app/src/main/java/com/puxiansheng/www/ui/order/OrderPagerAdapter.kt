package com.puxiansheng.www.ui.order

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.puxiansheng.www.ui.home.HomeTransferInOrdersFragment
import com.puxiansheng.www.ui.home.HomeTransferOutOrdersFragment

class OrderPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HomeTransferOutOrdersFragment()
        else -> HomeTransferInOrdersFragment()
    }
}