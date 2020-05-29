package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.MenuItem

data class HttpRespRequest(
    @SerializedName("result")
    val list: List<RequestBean>? = null
)

data class RequestBean(

    @SerializedName("id")
    var requestId: Long = 0,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("content")
    var content: String = "",

    @SerializedName("reply")
    var reply: String = "",

    @SerializedName("cate")
    var cate: String = "",

    @SerializedName("update_time")
    var update_time: String = ""

    )