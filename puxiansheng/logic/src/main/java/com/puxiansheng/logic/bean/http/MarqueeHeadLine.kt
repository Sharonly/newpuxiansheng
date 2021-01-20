package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.MarqueeInfo

data class MarqueeHeadLine (
    @SerializedName("recommend_one")
    val topInfo: MarqueeInfo? = null,

    @SerializedName("recommend_two")
    val infos: List<MarqueeInfo>? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

    @SerializedName("total_page")
    val totalPages: Int = 0
)
