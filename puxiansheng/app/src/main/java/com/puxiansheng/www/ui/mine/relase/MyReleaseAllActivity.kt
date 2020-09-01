package com.puxiansheng.www.ui.mine.relase

import android.annotation.SuppressLint
import com.google.android.material.tabs.TabLayoutMediator
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_my_all_release.*

class MyReleaseAllActivity :MyBaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_my_all_release
    }

    override fun business() {
        initView()
    }


    @SuppressLint("ResourceType")
    private fun initView(){
        pager.adapter = ReleasedOrdersPagerAdapter(
            fragmentManager = supportFragmentManager,
            lifecycle = this.lifecycle
        )

        TabLayoutMediator(tabs, pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = when (position) {
                    0 -> "转店发布"
                    1 -> "找店发布"
                    else -> ""
                }
            }).attach()

//        tabs.setSelectedTabIndicator(1)

        button_back.setOnClickListener {
           onBackPressed()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("MyReleaseAllActivity")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("MyReleaseAllActivity")
//    }
}