package com.puxiansheng.www.app

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.puxiansheng.www.R
import com.puxiansheng.www.ui.login.LoginActivity

abstract class MyBaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLandscape(this,false)
//        MyScreenUtils.setStatusBar(this,true,true, R.color.colorFFF)
        setContentView(getLayoutId())
        business()
    }


    abstract fun getLayoutId():Int

    abstract fun business()

    /**
     * 设置横屏
     */
    fun setLandscape(activity: Activity, isLandscape:Boolean){
        if (isLandscape){
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else{
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

//    fun launchActivity(clazz: Class<out Activity>){
//        val intent=Intent(this,clazz)
//        startActivity(intent)
//        overridePendingTransition(R.anim.enter_right,R.anim.exit_left)
//    }
//
    fun launchLoginActivity(){
        val intent= Intent(this, LoginActivity::class.java)
        startActivity(intent)
//        overridePendingTransition(R.anim.enter_bottom,R.anim.alpha)
    }



}