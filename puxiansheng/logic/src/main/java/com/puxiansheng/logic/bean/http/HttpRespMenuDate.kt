package com.puxiansheng.logic.bean.http

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.MenuItem

data class HttpRespMenuDate(
    @SerializedName("result")
    val list: List<MenuItem>? = null
)


data class HttpRespAreaObject(
    @SerializedName("result")
    val areaObject: List<MenuItem>? = null
)

//data class HttpRespNewCity(
//    @SerializedName("pid")
//    var parentID: Long = 0,
//
//    @SerializedName("value")
//    var value: String = "",
//
//    @SerializedName("text")
//    var text: String = "",
//
//    @SerializedName("children")
//    val arealist: List<MenuItem>? = null
//)

