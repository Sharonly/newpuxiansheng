package com.puxiansheng.www.app

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.ui.login.LoginActivity

abstract class MyBaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLandscape(this, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
//        MyScreenUtils.setStatusBar(this,true,true, R.color.colorFFF)
        checkNet()

    }


    abstract fun getLayoutId(): Int

    abstract fun business()

    /**
     * 设置横屏
     */
    fun setLandscape(activity: Activity, isLandscape: Boolean) {
        if (isLandscape) {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    //    fun launchActivity(clazz: Class<out Activity>){
//        val intent=Intent(this,clazz)
//        startActivity(intent)
//        overridePendingTransition(R.anim.enter_right,R.anim.exit_left)
//    }
//
    fun launchLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
//        overridePendingTransition(R.anim.enter_bottom,R.anim.alpha)
    }

    private fun checkNet() {
        if (!NetUtil.isNetworkConnected(this)) {
            setContentView(R.layout.activity_not_net)
        } else {
            setContentView(getLayoutId())
            business()
        }


    }


}