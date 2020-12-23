package com.puxiansheng.www.app

import android.app.Activity
import android.app.Application
import android.os.Bundle

class MyActivityLifeCallback: Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStarted(activity: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
              MyActivityManage.delActivity(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                MyActivityManage.addActivity(activity)
    }

    override fun onActivityResumed(activity: Activity) {

    }
}