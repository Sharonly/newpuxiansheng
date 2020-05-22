package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespFavorite(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("msg")
    val msg: String = "",

    @SerializedName("data")
    val data: Favorite? = null
)

data class Favorite(
    @SerializedName("result")
    val result: Int = 0
)