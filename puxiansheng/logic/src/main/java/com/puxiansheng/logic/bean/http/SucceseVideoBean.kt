package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpSuccessVideo(
    @SerializedName("result")
    val data: SuccessVideoList? = null,
    @SerializedName("this_page")
    var thisPage: Long = 0,
    @SerializedName("total_page")
    var totalPage: Long = 0
)

data class SuccessVideoList(
    @SerializedName("data")
    var videoList: List<SuccessVideoBean>? = null
)

data class RecommendSuccessVideoList(
    @SerializedName("result")
    val videos: List<SuccessVideoBean>? = null
)


data class SuccessVideoBean (
        @SerializedName("id")
        var videoId: Long = 0,

        @SerializedName("title")
        var title: String = "",

        @SerializedName("jump_type")
        var jump_type: Int = 0,

        @SerializedName("jump_view")
        var jump_view: String = "",

        @SerializedName("jump_param")
        var jump_param: String = "",

        @SerializedName("images")
        var img: String = ""
    )
