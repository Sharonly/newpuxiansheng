package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.MenuItem

data class HttpRespMenuDate(
    @SerializedName("result")
    val list: List<MenuItem>? = null
)