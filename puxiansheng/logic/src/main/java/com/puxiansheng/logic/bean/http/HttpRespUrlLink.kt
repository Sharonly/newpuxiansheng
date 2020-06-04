package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespUrlLink(
    @SerializedName("result")
    val urls: String = ""
)

data class ConfigBean(

    @SerializedName("default_header_img")
    val default_header_img: String = "",

    @SerializedName("default_shop_thumb_img")
    val default_shop_thumb_img: String = "",


    @SerializedName("default_shop_img")
    val default_shop_img: String = "",


    @SerializedName("api_kf_url")
    val api_kf_url: String = "",

    @SerializedName("register_success")
    val register_success: String = "",


    @SerializedName("about_us")
    val about_us: String = "",


    @SerializedName("counselor_group")
    val counselor_group: String = "",


    @SerializedName("about_us_url")
    val about_us_url: String = "",

    @SerializedName("privacy_url")
    val privacy_url: String = ""

)