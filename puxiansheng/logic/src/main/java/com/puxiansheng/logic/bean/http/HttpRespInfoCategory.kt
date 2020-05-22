package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.MenuItem

data class HttpRespInfoCategory(
    @SerializedName("result")
    val categories: List<MenuItem>? = null
)