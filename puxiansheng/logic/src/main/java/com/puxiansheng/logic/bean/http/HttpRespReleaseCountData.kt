package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespReleaseCountData(
    @SerializedName("result")
    val data:ReleaseCountData? = null
)

data class ReleaseCountData(
    @SerializedName("issue")
    val releaseData: Int = 0,

    @SerializedName("finish")
    val finishData: Int = 0,

    @SerializedName("dispose")
    val processingData: Int = 0
)