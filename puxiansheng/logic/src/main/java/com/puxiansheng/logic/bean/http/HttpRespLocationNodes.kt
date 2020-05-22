package com.puxiansheng.logic.bean.http

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.LocationNode
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HttpRespLocationNodes(
    @SerializedName("result")
    val nodes: List<LocationNode>? = null
): Parcelable