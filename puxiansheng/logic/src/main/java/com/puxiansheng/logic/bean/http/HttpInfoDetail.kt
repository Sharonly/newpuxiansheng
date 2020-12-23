package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

class HttpInfoDetail (
    @SerializedName("result")
    val infoObject: InfoDetailObject? = null,

    @SerializedName("top_article")
    val topArticle: InfoTopArticleObject ? = null,

    @SerializedName("desc_article")
    val descArticle: InfoTopArticleObject ? = null
)


data class InfoDetailObject(
    @SerializedName("id")
    var id: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("classify_id")
    var classify_id: String,

    @SerializedName("update_time")
    var update_time: String,

    @SerializedName("content")
    var content: String,

    @SerializedName("view_count")
    var view_count: String,

    @SerializedName("is_collect")
    var is_collect: Int ,

    @SerializedName("share_url")
    var share_url: String

)

data class InfoTopArticleObject(
    @SerializedName("id")
    var id: Int,

    @SerializedName("title")
    var title: String,

    @SerializedName("city_id")
    var city_id: String
)

