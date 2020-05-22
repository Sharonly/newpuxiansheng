package com.puxiansheng.logic.bean

import com.google.gson.annotations.SerializedName

data class HttpSearchObject(
    @SerializedName("result")
    val searchObjects: List<SearchItem> ?= null
)

data class SearchItem (
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("keyword")
    val keyword: String = "",

    @SerializedName("type_id")
    val typeId: Int = 0,

    @SerializedName("name")
    val name: String = ""

    )



