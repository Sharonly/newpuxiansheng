package com.puxiansheng.util.ext

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

class MyScreenUtil {

    companion object{

        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        fun dip2px(context: Context, dpValue: Float): Int {
            val scale: Float = context.getResources().getDisplayMetrics().density
            return (dpValue * scale + 0.5f).toInt()
        }


        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        fun px2dip(context: Context, pxValue: Float): Int {
            val scale: Float = context.getResources().getDisplayMetrics().density
            return (pxValue / scale + 0.5f).toInt()
        }


        /**
         * 设置状态栏(Android 6.0（API 23）之后，才支持修改状态栏上面的文字和图标颜色，默认是白色的
         * isTransparent:是否透明
         * isTextDark：是否设置状态栏文字为黑色
         */
        fun setStateBarStyle(
            activity: Activity,
            isTransparent: Boolean,
            stateBarColorId: Int,
            isTextDark: Boolean
        ){
            val window = activity.window
             if (isTransparent){
                 window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                 if (isTextDark){
                     window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                 }else{
                     window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                 }

                 window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                 window.setStatusBarColor(Color.TRANSPARENT)
             }else{
                 window.statusBarColor =ContextCompat.getColor(activity, stateBarColorId)
                 if (isTextDark) {
                     window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                 } else {
                     window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                 }
             }
        }

         fun fullscreen( activity: Activity,enable: Boolean) {
             val window = activity.window
            if (enable) { //显示状态栏
                val lp: WindowManager.LayoutParams = window.attributes
                lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
                window.attributes = lp
                window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            } else { //隐藏状态栏
                val lp: WindowManager.LayoutParams = window.attributes
                lp.flags = lp.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
                window.attributes = lp
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
            }
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


        /**
         * 设置Activity的透明度
         */
        fun backgroundAlpha(bgAlpha: Float, activity: Activity) {
            val lp: WindowManager.LayoutParams =activity.getWindow().getAttributes()
            lp.alpha = bgAlpha //0.0-1.0
            activity.getWindow().setAttributes(lp)
        }


        fun getScreenWidth(context: Context):Int{
            val dm =context.resources.displayMetrics
            val width = dm.widthPixels
            return width
        }

        fun getScreenHeight(context: Context):Int{
            val dm =context.resources.displayMetrics
            val height = dm.heightPixels
            return height
        }

        fun getScreenDensity(context: Context):Float{
            val dm =context.resources.displayMetrics
            val density = dm.density
            return density
        }


        /**
         * 获取状态栏高度
         */
        fun getStatusBarHeight(context: Context): Int {
            val resources: Resources = context.resources
            val resourceId: Int = resources.getIdentifier("status_bar_height", "dimen", "android")
            return resources.getDimensionPixelSize(resourceId)
        }

    }
}