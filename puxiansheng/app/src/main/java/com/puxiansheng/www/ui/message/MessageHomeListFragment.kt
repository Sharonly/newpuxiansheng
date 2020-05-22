package com.puxiansheng.www.ui.message

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.puxiansheng.www.R
import com.puxiansheng.www.common.AppFragment
import com.puxiansheng.www.databinding.FragmentMessageHomeBinding
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi


class MessageHomeListFragment : AppFragment() {

    private lateinit var messageListViewModel: MessageListViewModel
    private lateinit var appModel: MainViewModel
    private var tabTitles = listOf<String>("系统消息", "通知消息", "推荐消息")
    private var fragments = listOf<Fragment>(
        MessageListFragment.newInstance(type = 0),
        MessageListFragment.newInstance(type = 1),
        MessageListFragment.newInstance(type = 2)
    )


    override fun onAttach(context: Context) {
        super.onAttach(context)
        messageListViewModel = ViewModelProvider(this)[MessageListViewModel::class.java]
        appModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = rootView ?: FragmentMessageHomeBinding.inflate(inflater).apply {
        lifecycleOwner = viewLifecycleOwner
        messagePager.adapter = MessagePagerAdapter(
            fragmentManager = childFragmentManager,
            fragments = fragments,
            titles = tabTitles
        )
        messagePager.offscreenPageLimit = 3
        tabs.setupWithViewPager(messagePager)
        initTabView(tabs)

    }.root



    private fun initTabView(tabs:TabLayout) {
        for (i in 0 until tabs.tabCount) {
            val tab: TabLayout.Tab? = tabs.getTabAt(i)
            if (tab != null) {
                tab.customView = getTabView(i)
            }
        }
        tabs.getTabAt(tabs.selectedTabPosition)?.let { updateTabTextView(it, true) }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                updateTabView(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabView(tab, false)
            }

            private fun updateTabView(tab: TabLayout.Tab, isSelect: Boolean) {
                if (isSelect) { //选中加粗
                    val tabSelect =
                        tab.customView?.findViewById(R.id.tab_item_textview) as TextView
                    tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
                    tabSelect.text = tab.text
                } else {
                    val tabUnSelect =
                        tab.customView?.findViewById(R.id.tab_item_textview) as TextView
                    tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
                    tabUnSelect.text = tab.text
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }


    private fun getTabView(currentPosition: Int): View? {
        val view: View = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null)
        val textView =
            view.findViewById<View>(R.id.tab_item_textview) as TextView
        textView.text = tabTitles[currentPosition]
        return view
    }
    private fun updateTabTextView(tab: TabLayout.Tab, isSelect: Boolean) {
        if (isSelect) { //选中加粗
            val tabSelect =
                tab.customView?.findViewById(R.id.tab_item_textview) as TextView
            tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            tabSelect.text = tab.text
        } else {
            val tabUnSelect =
                tab.customView?.findViewById(R.id.tab_item_textview) as TextView
            tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
            tabUnSelect.text = tab.text
        }
    }

}