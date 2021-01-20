package com.puxiansheng.util.ext

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class PermissionUtils {

    companion object {
        private var context: AppCompatActivity? = null
        private var requiredPermissions = arrayOf<String>()
        private val requestCodePermissions = 101
        private var myContext: Context? = null
        private var callBack: IPermissionCheckListener? = null


        fun requestPermission(mContext: AppCompatActivity, permissions: Array<String>) {
            this.context = mContext
            this.myContext = myContext?.applicationContext
            if (!checkPermission(permissions)) {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    permissions,
                    requestCodePermissions
                )
            }
        }


        private fun checkPermission(permissions: Array<String>): Boolean {
            if (permissions != null && permissions.isNotEmpty()) {
                this.requiredPermissions = permissions
                for (p in permissions) {
                    if (!permissionIsPermitted(p)) {
                        return false
                    }
                }
            }
            return true
        }

        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray,
            checkListener: IPermissionCheckListener
        ) {
            when (requestCode) {
                requestCodePermissions -> {
                    if (grantResults.isNotEmpty()) {
                        var denies = arrayListOf<String>()
                        if (grantResults.size == permissions.size) {
                            for (i in grantResults.indices)
                                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                                    denies.add(requiredPermissions[i])
                                }
                        }
                        if (checkListener != null) {
                            callBack = checkListener
                            if (denies.size > 0) {
                                callBack?.onUnPermit(denies)
                            } else {
                                callBack?.onPermit()
                            }
                        }
                    } else {
                        callBack?.onException()
                    }
                }
                else -> {
                    callBack?.onException()
                }
            }
        }


        fun permissionIsPermitted(permission: String): Boolean {
            return myContext?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    permission
                )
            } == PackageManager.PERMISSION_GRANTED
        }
    }

   interface IPermissionCheckListener {
        fun onPermit()
        fun onException()
        fun onUnPermit(permissions: ArrayList<String>)
    }

}