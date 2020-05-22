package com.puxiansheng.www.ui.mine.favor

import androidx.fragment.app.Fragment
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import com.puxiansheng.www.ui.mine.relase.DeleteOrderDialog
import kotlinx.android.synthetic.main.activity_my_history.*

class MyfarvorActivity : MyBaseActivity() {

    private var tabTitles = listOf<String>("转铺", "找铺", "资讯")
    private val favoriteTransferOutFragment: Fragment = FavoriteTransferOutOrdersFragment()
    private val favoriteTransferInFragment: Fragment = FavoriteTransferInOrdersFragment()
    private val favoriteInfoFragment: Fragment = FavoriteInfoFragment()
    private var fragments = listOf<Fragment>(
        favoriteTransferOutFragment,
        favoriteTransferInFragment,
        favoriteInfoFragment
    )

    override fun getLayoutId(): Int {
        return R.layout.activity_my_history
    }

    override fun business() {
        initView()
    }

    private fun initView() {
        tab_title.text = "我的收藏"
        bt_delete.text = "管理"

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

//        bt_delete.setOnClickListener {
//            DeleteOrderDialog(
//                getString(R.string.delete_history_title)
//            ).show(supportFragmentManager,
//                DeleteOrderDialog::class.java.name) }
    }
}