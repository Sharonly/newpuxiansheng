package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.InfoItem

data class HttpRespHistoryInfoList(
    @SerializedName("result")
    val infoListObject: HistoryInfoListObject? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

    @SerializedName("total_page")
    val totalPages: Int = 0
)

data class HistoryInfoListObject(
    @SerializedName("data")
    val infoList: List<InfoItem>? = null)