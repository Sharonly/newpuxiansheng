package com.puxiansheng.www.tools

import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import com.puxiansheng.util.BaseApplication
import com.puxiansheng.util.BuildConfig
import com.puxiansheng.util.ext.md5

class DevicesUtils {

    companion object{
        /**
         * 获取设备imei
         */
        fun getIMEI():String{
            return Settings.System.getString(BaseApplication.getAppContext().getContentResolver(), Settings.System.ANDROID_ID)
        }

        /**
         * 获取版本号
         */
        fun getAppVersionCode() :Long{
            var appVersionCode = 0L;
            try {
                val packageInfo =BaseApplication.getAppContext().getApplicationContext().getPackageManager().getPackageInfo(BaseApplication.getAppContext().getPackageName(), 0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    appVersionCode = packageInfo.getLongVersionCode();
                } else {
                    appVersionCode = packageInfo.versionCode.toLong();
                }
            } catch (e: PackageManager.NameNotFoundException) {
                println("版本号--->${e.printStackTrace()}")
            }
            return appVersionCode;
        }


       // const val API_APP_ID = "0ac2e9316779d536"
//        const val API_APP_ID = "cee34b0e9989df19"
//        const val API_SECRET = "6385dab0cee34b0e9989df190522d449"
       // const val API_VERSION = "1"
        const val DEVICE_CHANNEL = "Android"

        fun signNew(signatureToken: String, fieldMap: Map<String, String>, method: String): String = StringBuilder().apply {
            if (!signatureToken.isNullOrEmpty()) append(signatureToken)
//            append(it.key).append(it.value)//"${key}->${map[key]}"
            val appStr = fieldMap["appid"]
            val channelStr = fieldMap["channel"]
            val deviceNoStr = fieldMap["device_no"]
            val secretStr = fieldMap["secret"]
            val versionStr = fieldMap["version"]
            append("appid").append(appStr).append("channel").append(channelStr).append("device_no").append(deviceNoStr).append("secret").append(secretStr).append("version").append(versionStr)
            val ss=  append(method)
            if (BuildConfig.DEBUG) println("API.sign() {original no signatureToken "+ss.toString())
            if (!signatureToken.isNullOrEmpty()) append(signatureToken)
        }.toString().also {
            if (BuildConfig.DEBUG) println("API.sign() {original sign string = $it}")
        }.md5(null).also {
            if (BuildConfig.DEBUG) println("API.sign() {md5 sign string = $it}")
        }

        fun sign(
            signatureToken: String?,
            fieldMap: MutableMap<String, String>,
            method: String
        ): String = StringBuilder().apply {
            if (!signatureToken.isNullOrEmpty()) append(signatureToken)
            fieldMap.toSortedMap().forEach {
                append(it.key).append(it.value)
            }
            append(method)
            if (!signatureToken.isNullOrEmpty()) append(signatureToken)
        }.toString().also {
            if (com.zhihu.matisse.BuildConfig.DEBUG) println("API.sign() {original sign string = $it}")
        }.md5(null).also {
            if (com.zhihu.matisse.BuildConfig.DEBUG) println("API.sign() {md5 sign string = $it}")
        }



    }
}