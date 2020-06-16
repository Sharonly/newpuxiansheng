package com.puxiansheng.www.ui.mine.history

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import kotlinx.android.synthetic.main.activity_my_history.*
import kotlinx.android.synthetic.main.fragment_info_list.*
import java.util.*

class MyHistoryActivity : MyBaseActivity(){

    private var tabTitles = listOf<String>("转铺", "找铺", "资讯")
    private val historyTransferOutFragment: Fragment = BrowsingHistoryTransferOutOrdersFragment()
    private val historyTransferInFragment: Fragment = BrowsingHistoryTransferInOrdersFragment()
    private val historyInfoFragment: Fragment = BrowsingHistoryInfoFragment()
    private lateinit var historyViewModel: HistoryListViewModel


    private var selectType = Order.Type.TRANSFER_OUT_HISTORY.value()
    private var fragments = listOf(historyTransferOutFragment, historyTransferInFragment, historyInfoFragment)

    override fun getLayoutId(): Int {
        return R.layout.activity_my_history
    }

    override fun business() {
        historyViewModel = ViewModelProvider(this)[HistoryListViewModel::class.java]
        initView()
    }

    private fun initView() {
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


        tabs.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        selectType = Order.Type.TRANSFER_OUT_HISTORY.value()
                    }
                    1 -> {
                        selectType = Order.Type.TRANSFER_IN_HISTORY.value()
                    }
                    2 -> {
                        selectType = InfoItem.Type.ARTICLE_HISTORY.value()
                    }
                }
            }

        })

        bt_delete.setOnClickListener {
            var deleteDialog = DeleteOrderDialog(getString(R.string.delete_history_title), selectType, 0)
            deleteDialog.show(supportFragmentManager, DeleteOrderDialog::class.java.name)
            deleteDialog.listener = object : DeleteOrderDialog.OnDissListener {
                override fun onDiss() {
                    when (selectType) {
                        Order.Type.TRANSFER_OUT_HISTORY.value() -> {
                            historyViewModel.refreshType.postValue(Order.Type.TRANSFER_OUT_HISTORY.value())
                        }
                        Order.Type.TRANSFER_IN_HISTORY.value() -> {
                            historyViewModel.refreshType.postValue(Order.Type.TRANSFER_IN_HISTORY.value())
                        }
                        InfoItem.Type.ARTICLE_HISTORY.value() -> {
                            historyViewModel.refreshType.postValue(InfoItem.Type.ARTICLE_HISTORY.value())
                        }
                    }
                }
            }
        }


    }



}