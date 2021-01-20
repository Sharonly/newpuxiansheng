package com.puxiansheng.www.ui.mine.favor

import android.view.View
import androidx.fragment.app.Fragment
import com.puxiansheng.util.ext.MyScreenUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.puxiansheng.www.ui.message.MessagePagerAdapter
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_my_favor.*

class MyfarvorActivity : MyBaseActivity() {

    private var tabTitles = listOf<String>("转铺", "找铺", "资讯")
    private val favoriteTransferOutFragment: Fragment = FavoriteOutOrdersFragment()
    private val favoriteTransferInFragment: Fragment = FavoriteInOrdersFragment()
    private val favoriteInfoFragment: Fragment = FavoriteInfosFragment()
    private var fragments = listOf<Fragment>(favoriteTransferOutFragment, favoriteTransferInFragment, favoriteInfoFragment)

    override fun getLayoutId(): Int {
        MyScreenUtil.setStateBarStyle(this,true,R.color.color81,true)
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
        pager.adapter = MessagePagerAdapter(fragmentManager = supportFragmentManager, fragments = fragments, titles = tabTitles)
        pager.offscreenPageLimit = 3
        tabs.setupWithViewPager(pager)
        pager.setNoScroll(true)
        bt_delete.visibility = View.INVISIBLE


    }


//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("MyfarvorActivity")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("MyfarvorActivity")
//    }
}