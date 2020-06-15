package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.BannerImage

data class HttpRespIconUpload (
    @SerializedName("header_img")
    val imgIcon:String? = null
)


