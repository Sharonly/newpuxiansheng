package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespImageCode (
    @SerializedName("result")
    val imageCode: ImageCode? = null
)

data class ImageCode(
    @SerializedName("captcha_key")
    val key:String? = null,

    @SerializedName("images")
    val image: String? = null
)