package com.puxiansheng.www.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import cn.jpush.android.api.JPushInterface
import com.puxiansheng.util.BaseApplication
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.BuildConfig
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure


class App : BaseApplication(){


    private var refWatcher: RefWatcher? = null
    private var instance: App? = null

    companion object {
        lateinit var mContext: Context
        fun getMyContext(): Context {
            return mContext
        }

        fun getRefWatcher(context: Context): RefWatcher? {
            val leakApplication: App = context.applicationContext as App
            return leakApplication.refWatcher
        }

    }

    var registrationID: String = "0"
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


    override fun onCreate() {
        super.onCreate()
    instance = this
        registerActivityLifecycleCallbacks(MyActivityLifeCallback())
    UMConfigure.setLogEnabled(true)
        UMConfigure.init(this, "5f277355b4b08b653e907878", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "")
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO)
//        UMConfigure.setProcessEvent(true)
//        MobclickAgent.setSessionContinueMillis(40*1000)
        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        registrationID = JPushInterface.getRegistrationID(this)
        SharedPreferencesUtil.put("registration_id", registrationID)
        println("registrationID = $registrationID")
        if (BuildConfig.DEBUG) {
            refWatcher = setupLeakCanary()
//            LeakCanary.install(this)
//            LeakCanary.refWatcher(this)
        }
    }

    private fun setupLeakCanary(): RefWatcher? {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    fun  getInstance(): App? {
        return instance
    }



}