package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespStatisticsData(
    @SerializedName("result")
    val data:StatisticsData? = null
)

data class StatisticsData(
    @SerializedName("transfer_shop")
    val countTransferringOut: Int = 0,

    @SerializedName("find_shop")
    val countTransferringIn: Int = 0,

    @SerializedName("transfer_success")
    val countTransferred: Int = 0
)