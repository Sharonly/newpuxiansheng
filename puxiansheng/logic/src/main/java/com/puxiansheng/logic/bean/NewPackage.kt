package com.puxiansheng.logic.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
@Parcelize
data class NewPackage(
//    @SerializedName("new_package")
//    val newpackage: PackageData? = null
//)
//
//data class PackageData(
    @SerializedName("new_version")
    val newVersion: Int = 0,

    @SerializedName("new_package")
    val newPackage: Int = 0,

    @SerializedName("show_version")
    val showVersion: String = "",

    @SerializedName("download_url")
    val downloadUrl: String = "",

    @SerializedName("tips_msg")
    val tipsMsg: String = ""
) : Parcelable