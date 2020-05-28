package com.puxiansheng.www.ui.mine.relase

import com.google.android.material.tabs.TabLayoutMediator
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_my_all_release.*

class MyReleaseAllActivity :MyBaseActivity(){
    override fun getLayoutId(): Int {
        return R.layout.activity_my_all_release
    }

    override fun business() {
        initView()
    }


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

        button_back.setOnClickListener {
           onBackPressed()
        }
    }
}