package com.puxiansheng.www.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import cn.jpush.android.api.JPushInterface
import com.puxiansheng.util.BaseApplication
import com.puxiansheng.www.BuildConfig
import com.squareup.leakcanary.LeakCanary

class App : BaseApplication() {

    companion object{
        lateinit var mContext: Context
        fun getMyContext():Context{
            return mContext
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
        registrationID = JPushInterface.getRegistrationID(this);
        println("registrationID = $registrationID")
        if(BuildConfig.DEBUG){
        LeakCanary.install(this)
        }
    }
}