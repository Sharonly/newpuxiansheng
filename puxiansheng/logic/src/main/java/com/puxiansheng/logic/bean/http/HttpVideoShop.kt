package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName


data class HttpSuccessVideoInfo(
    @SerializedName("result")
    val data: VideoShopDetail ?= null

)




data class VideoShopDetail(
    @SerializedName("id")
    var shopID: Long = 0,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("video")
    var video: String = "",

    @SerializedName("detail")
    var detail: String = ""

)


