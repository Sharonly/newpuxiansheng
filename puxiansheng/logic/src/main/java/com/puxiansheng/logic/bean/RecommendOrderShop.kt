package com.puxiansheng.logic.bean

import com.google.gson.annotations.SerializedName


data class HttpRecommendOrder(
    @SerializedName("result")
    val data: List<RecommendOrderShop>? = null
)

data class RecommendOrderShop(
    @SerializedName("id")
    var shopID: Long = 0,

    @SerializedName("area_category")
    var area_category: String = "",

    @SerializedName("title")
    var title: String = "",

    @SerializedName("view_acreage")
    var view_acreage: String = "",

    @SerializedName("view_rent")
    var view_rent: String = "",

    @SerializedName("view_count_str")
    var view_count_str: String = "",

    @SerializedName("thum_img")
    var icon: String = ""

)


