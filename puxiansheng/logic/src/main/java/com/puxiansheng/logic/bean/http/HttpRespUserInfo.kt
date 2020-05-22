package com.puxiansheng.logic.bean.http

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HttpRespUserInfo(
    @SerializedName("userinfo")
    val user: User? = null
) : Parcelable