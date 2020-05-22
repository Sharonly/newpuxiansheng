package com.puxiansheng.logic.bean

import com.google.gson.annotations.SerializedName

data class BannerImage(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("img")
    val imageUrl: String = "",

    @SerializedName("bg_images")
    val bgImgUrl: String = "",

    @SerializedName("jump_type")
    val jump_type: Int?= 0,

    @SerializedName("jump_view")
    val jump_view: String = "",

    @SerializedName("jump_param")
    val jump_param: String = ""
)