package com.puxiansheng.www.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.puxiansheng.www.R
import com.puxiansheng.www.app.MyBaseActivity
import kotlinx.android.synthetic.main.activity_permisson.*
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.layout_register.*
import kotlinx.android.synthetic.main.layout_register.requestVerificationCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : MyBaseActivity() {
    private lateinit var viewModel: SplashViewModel
    var context: Context = this@SplashActivity

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun business() {
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        getSharedPreferences("pxs_privacy", Context.MODE_PRIVATE).let {
            it.getInt("show_privacy", 0).let { isShow ->
                if (isShow == 0) {
                    viewModel.startCountDown()
                }else{
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }

        viewModel?.countDown?.observe(this@SplashActivity, Observer {
            if (it == 0) {
                val intent = Intent(this, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                time.text = "${it}s"
            }
        })
    }


}