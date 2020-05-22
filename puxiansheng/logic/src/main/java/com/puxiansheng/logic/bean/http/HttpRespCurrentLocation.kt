package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.LocationNode

data class HttpRespCurrentLocation (
    @SerializedName("result")
    val locationNode: LocationNode? = null
)