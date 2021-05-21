package com.puxiansheng.www.ui.message

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.info.InfoPagerAdapter
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_message_home.*
import kotlinx.coroutines.launch


class MessageHomeListActivity : MyBaseActivity() {

    private lateinit var messageListViewModel: MessageListViewModel
    private lateinit var appModel: MainViewModel



    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this, true, R.color.color81, true)
        return R.layout.fragment_message_home
    }

    override fun business() {
        // context = this
        messageListViewModel = ViewModelProvider(this)[MessageListViewModel::class.java]
        appModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (NetUtil.isNetworkConnected(this)) {
            initData()
        } else {
            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
        }
    }



    override fun onResume() {
        super.onResume()
        if (NetUtil.isNetworkConnected(this)) {
//            initData()
        } else {
            Toast.makeText(this, "网络连接失败", Toast.LENGTH_SHORT)
        }
    }



    private fun initData(){
        lifecycleScope.launch {
            //isLoaded = true
            messageListViewModel.getMessageCategoriesFromRemote()?.let {
                message_pager.adapter = InfoPagerAdapter(
                    fragmentManager = supportFragmentManager,
                    fragments = it.map { category ->
                        MessageListFragment.newInstance(category = category.menuID.toInt())
                    },
                    titles = it.map { text ->
                        text.text
                    })
                message_pager.offscreenPageLimit = 3
                tabs.setupWithViewPager(message_pager)
            }
        }
    }

//
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        if (!hidden) {
//            appModel.currentSignatureToken.observe(viewLifecycleOwner, Observer {
//                lifecycleScope.launch {
//                    //isLoaded = true
//                    messageListViewModel.getMessageCategoriesFromRemote()?.let {
//                        message_pager.adapter = InfoPagerAdapter(
//                            fragmentManager = childFragmentManager,
//                            fragments = it.map { category ->
//                                MessageListFragment.newInstance(category = category.menuID.toInt())
//                            },
//                            titles = it.map { text ->
//                                text.text
//                            })
//                        message_pager.offscreenPageLimit = 3
//                        tabs.setupWithViewPager(message_pager)
//                    }
//                }
//            })
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        appModel.currentSignatureToken.observe(viewLifecycleOwner, Observer {
//            lifecycleScope.launch {
//                //isLoaded = true
//                messageListViewModel.getMessageCategoriesFromRemote()?.let {
//                    message_pager.adapter = InfoPagerAdapter(
//                        fragmentManager = childFragmentManager,
//                        fragments = it.map { category ->
//                            MessageListFragment.newInstance(category = category.menuID.toInt())
//                        },
//                        titles = it.map { text ->
//                            text.text
//                        })
//                    message_pager.offscreenPageLimit = 3
//                    tabs.setupWithViewPager(message_pager)
//                }
//            }
//        })
//    }


    //
//    private fun initTabView(tabs:TabLayout) {
//        for (i in 0 until tabs.tabCount) {
//            val tab: TabLayout.Tab? = tabs.getTabAt(i)
//            if (tab != null) {
//                tab.customView = getTabView(i)
//            }
//        }
//        tabs.getTabAt(tabs.selectedTabPosition)?.let { updateTabTextView(it, true) }
//
//        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                updateTabView(tab, true)
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {
//                updateTabView(tab, false)
//            }
//
//            private fun updateTabView(tab: TabLayout.Tab, isSelect: Boolean) {
//                if (isSelect) { //选中加粗
//                    val tabSelect =
//                        tab.customView?.findViewById(R.id.tab_item_textview) as TextView
//                    tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
//                    tabSelect.text = tab.text
//                } else {
//                    val tabUnSelect =
//                        tab.customView?.findViewById(R.id.tab_item_textview) as TextView
//                    tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
//                    tabUnSelect.text = tab.text
//                }
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
//    }
//
//
//    private fun getTabView(currentPosition: Int): View? {
//        val view: View = LayoutInflater.from(requireContext()).inflate(R.layout.tab_item, null)
//        val textView =
//            view.findViewById<View>(R.id.tab_item_textview) as TextView
//        textView.text = tabTitles[currentPosition]
//        return view
//    }
//    private fun updateTabTextView(tab: TabLayout.Tab, isSelect: Boolean) {
//        if (isSelect) { //选中加粗
//            val tabSelect =
//                tab.customView?.findViewById(R.id.tab_item_textview) as TextView
//            tabSelect.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
//            tabSelect.text = tab.text
//        } else {
//            val tabUnSelect =
//                tab.customView?.findViewById(R.id.tab_item_textview) as TextView
//            tabUnSelect.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
//            tabUnSelect.text = tab.text
//        }
//    }
//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("MessageHomeListFragment")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("MessageHomeListFragment")
//    }

}