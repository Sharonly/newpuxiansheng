package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.InfoItem

data class HttpRespFavorInfoList(
    @SerializedName("result")
    val infoListObject: InfoObject? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

    @SerializedName("total_page")
    val totalPages: Int = 0
)

data class InfoObject(
    @SerializedName("data")
    val infoList: List<InfoItem>? = null


)