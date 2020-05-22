package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.InfoItem

data class HttpRespInfoList(
    @SerializedName("result")
    val infoListObject: InfoListObject? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

    @SerializedName("total_page")
    val totalPages: Int = 0
)

data class InfoListObject(
    @SerializedName("article_list")
    val infoList: List<InfoItem>? = null


)