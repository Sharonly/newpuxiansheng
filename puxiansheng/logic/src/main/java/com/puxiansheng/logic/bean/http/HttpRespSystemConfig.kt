package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.SystemConfig

data class HttpRespSystemConfig(
    @SerializedName("result")
    val config: SystemConfig? = null
)