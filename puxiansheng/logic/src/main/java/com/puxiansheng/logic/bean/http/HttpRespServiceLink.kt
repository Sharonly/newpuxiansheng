package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespServiceLink(
    @SerializedName("result")
    val link: String = ""
)