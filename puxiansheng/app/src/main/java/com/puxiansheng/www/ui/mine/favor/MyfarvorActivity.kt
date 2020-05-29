package com.puxiansheng.www.ui.mine.favor

import android.view.View
import androidx.fragment.app.Fragment
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import kotlinx.android.synthetic.main.activity_my_favor.*

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
        return R.layout.activity_my_favor
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

        pager.setNoScroll(true)
        bt_delete.visibility = View.INVISIBLE
//        bt_delete.setOnClickListener {
//            if( bt_delete.text == "管理") {
//                bt_delete.text = "正在管理"
//                pager.setNoScroll(true)
//            }else{
//                bt_delete.text = "管理"
//                pager.setNoScroll(false)
//            }
//        }
    }
}