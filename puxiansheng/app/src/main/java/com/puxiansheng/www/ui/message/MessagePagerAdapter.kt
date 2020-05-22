package com.puxiansheng.www.ui.message

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class MessagePagerAdapter(fragmentManager: FragmentManager,
                             private val fragments: List<Fragment>,
                             private val titles: List<String>
): FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int) = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int) = titles[position]
}