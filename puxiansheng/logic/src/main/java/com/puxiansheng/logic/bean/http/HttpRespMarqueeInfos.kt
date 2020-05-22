package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.MarqueeInfo

data class HttpRespMarqueeInfos(
    @SerializedName("result")
    val data: MarqueeHeadLine? = null
)


data class MarqueeHeadLine(
    @SerializedName("data")
    val infos: List<MarqueeInfo>? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

    @SerializedName("total_page")
    val totalPages: Int = 0
)