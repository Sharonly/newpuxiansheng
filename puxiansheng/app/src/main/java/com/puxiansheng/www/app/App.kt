package com.puxiansheng.www.app

import android.content.Context
import androidx.multidex.MultiDex
import cn.jpush.android.api.JPushInterface
import com.puxiansheng.util.BaseApplication
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.www.BuildConfig
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher

class App : BaseApplication() {
    private var refWatcher: RefWatcher? = null
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
        MultiDex.install(this);
    }

    override fun onCreate() {
        super.onCreate()
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
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


}