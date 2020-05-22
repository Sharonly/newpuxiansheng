package com.puxiansheng.www.ui.mine.history

import androidx.fragment.app.Fragment
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import kotlinx.android.synthetic.main.activity_my_history.*

class MyHistoryActivity : MyBaseActivity() {

    private var tabTitles = listOf<String>("转铺", "找铺", "资讯")
    private val historyTransferOutFragment: Fragment = BrowsingHistoryTransferOutOrdersFragment()
    private val historyTransferInFragment: Fragment = BrowsingHistoryTransferInOrdersFragment()
    private val historyInfoFragment: Fragment = BrowsingHistoryInfoFragment()
    private var fragments = listOf(
        historyTransferOutFragment,
        historyTransferInFragment,
        historyInfoFragment
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_my_history
    }

    override fun business() {
        initView()
    }

    private fun initView(){
        button_back.setOnClickListener {
            onBackPressed()
        }
        pager.adapter = MessagePagerAdapter(
            fragmentManager = supportFragmentManager,
            fragments = fragments,
            titles = tabTitles
        )
        pager.offscreenPageLimit = 3
        tabs.setupWithViewPager(pager)

        bt_delete.setOnClickListener {
            DeleteOrderDialog(
                getString(R.string.delete_history_title)
            ).show(supportFragmentManager,
                DeleteOrderDialog::class.java.name) }
    }

}