package com.puxiansheng.www.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.util.ext.NetUtil
import com.puxiansheng.www.R
import com.puxiansheng.www.tools.SpUtils
import com.puxiansheng.www.ui.login.LoginActivity
import com.puxiansheng.www.ui.main.MainViewModel
import kotlinx.coroutines.launch


abstract class MyBaseActivity : AppCompatActivity() {

    private var TAG :String = ""
    private var appModel: MainViewModel? = null
    private var context:Context ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context= this
        TAG = this.javaClass.name
        setLandscape(this, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //修改为深色，因为我们把状态栏的背景色修改为主题色白色，默认的文字及图标颜色为白色，导致看不到了。
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
//        MyScreenUtils.setStatusBar(this,true,true, R.color.colorFFF)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        appModel = ViewModelProvider(this)[MainViewModel::class.java]
        checkNet()
        API.getToken.observe(this, Observer {
            if (it == API.CODE_ERROR_AUTH_TOKEN ||
                it == API.CODE_AUTO_CODE_INVALID ||
                it == API.CODE_AUTO_CODE_EXPIRED ||
                it == API.CODE_AUTO_CODE_ERROR ||
                it == API.CODE_AUTO_CODE_EMPTY||
                it == API.CODE_ERROR_SIGNATURE_TOKEN||
                it == API.CODE_EMPTY_SIGNATURE_TOKEN||
                it == API.CODE_UNKNOWN_SIGNATURE_TOKEN
            )  {
                appModel?.requireLocalDevice()?.observe(this, Observer {
                    it?.let {
                        lifecycleScope.launch {
                            Log.d("GET_TOKEN----","requireLocalDevice= ----222 ")
                            appModel?.getSignatureVersion(
                                it,
                                SpUtils.get("registration_id", "") as String
                            )
                        }
                    } ?: appModel?.requireDevice()
                })
            }
        })
        Log.e("activity","onCreate = "+TAG)
    }




    abstract fun getLayoutId(): Int

    abstract fun business()

    override fun onResume() {
        super.onResume()
        Log.e("activity","onResume = "+TAG)
//        MobclickAgent.onResume(this)
//        appModel?.requireLocalDevice()?.observe(this, Observer {
//            it?.let {
//                lifecycleScope.launch {
//                    Log.d("GET_TOKEN----","requireLocalDevice -- -- onResume----222 ")
//                    appModel?.getSignatureVersion(
//                        it,
//                        SharedPreferencesUtil.get("registration_id", "") as String
//                    )
//                }
//            } ?: appModel?.requireDevice()
//        })
    }


    /**
     * 设置横屏
     */
    fun setLandscape(activity: Activity, isLandscape: Boolean) {
        if (isLandscape) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
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



    override fun onPause() {
        super.onPause()
        Log.e("activity","onPause = "+TAG)
//        MobclickAgent.onPause(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("activity","onDestroy = "+TAG)
        context?.let { App.getRefWatcher(it) }
    }



}