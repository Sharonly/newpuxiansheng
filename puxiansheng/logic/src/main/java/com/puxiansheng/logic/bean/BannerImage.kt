package com.puxiansheng.logic.bean

import com.google.gson.annotations.SerializedName

data class BannerImage(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("img")
    val imageUrl: String = "",

    @SerializedName("title")
    val title: String = "",

    @SerializedName("img_height")
    val imageHeight: Int = 0,

    @SerializedName("img_width")
    val imageWidth: Int = 0,

    @SerializedName("bg_images")
    val bgImgUrl: String = "",

    @SerializedName("jump_type")
    val jump_type: Int?= 0,

    @SerializedName("jump_view")
    val jump_view: String = "",

    @SerializedName("jump_param")
    val jump_param: String = "",

    @SerializedName("api_jump_type")
    val api_jump_type: Int?= 0,

    @SerializedName("api_jump_view")
    val api_jump_view: String = "",

    @SerializedName("api_jump_param")
    val api_jump_param: String = ""
)