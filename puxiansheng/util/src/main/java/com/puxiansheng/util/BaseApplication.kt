package com.puxiansheng.util

import android.app.Application
import android.content.Context
import com.squareup.leakcanary.LeakCanary


open class BaseApplication:Application() {

    companion object {

        var mInstance: BaseApplication? = null

        /**
         * 获取App context
         *
         * @return 上下文
         */
        @JvmStatic
        fun getAppContext(): Context {
            return mInstance!!.applicationContext
        }

    }
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            LeakCanary.install(this)
        }
        mInstance = this
    }
}