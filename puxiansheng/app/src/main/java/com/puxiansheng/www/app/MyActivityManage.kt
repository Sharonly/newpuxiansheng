package com.puxiansheng.www.app

import android.app.Activity
import java.lang.ref.WeakReference

object MyActivityManage {


     private var activityMap=HashMap<String, WeakReference<Activity>>()

    /**
     * 添加Activity
     */
    fun addActivity(activity: Activity){
        val weakReference=WeakReference<Activity>(activity)
        activityMap.put(activity.javaClass.name,weakReference)
    }


    /**
     * 删除Activity
     */
    fun delActivity(activity: Activity){
        activityMap.remove(activity.toString())
    }

    /**
     * 关闭指定Activity
     */
    fun finshActivity(key:String){
        val weakReference = activityMap.get(key)
        weakReference?.get()?.finish()
        if(activityMap.containsKey(key)) {
            activityMap.remove(key)
        }
    }


    /**
     * 退出应用
     */
    fun exitApp(){
        val iterator = activityMap.iterator()
        while (iterator.hasNext()){
            val activity =iterator.next().value.get()
            activity?.finish()
            iterator.remove()
        }
        System.exit(0)
    }

}