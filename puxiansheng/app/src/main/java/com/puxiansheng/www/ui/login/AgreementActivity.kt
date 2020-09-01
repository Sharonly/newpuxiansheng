package com.puxiansheng.www.ui.login

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puxiansheng.www.R
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.activity_agreement.*

class AgreementActivity : AppCompatActivity(R.layout.activity_agreement){

    var context: Context = this@AgreementActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        button_back.setOnClickListener {
            onBackPressed()
        }
    }

//    override fun onResume() {
//        super.onResume()
//        MobclickAgent.onPageStart("AgreementActivity")
//    }
//
//    override fun onPause() {
//        super.onPause()
//        MobclickAgent.onPageEnd("AgreementActivity")
//    }

}