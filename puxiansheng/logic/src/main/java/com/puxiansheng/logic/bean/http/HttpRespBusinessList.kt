package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.BannerImage
import com.puxiansheng.logic.bean.BusinessBean

data class HttpRespBusinessList(
    @SerializedName("data")
    val business:List<BusinessBean>? = null
)

data class  HttpRespBusinessDetail(
    @SerializedName("data")
    val businessObject:BusinessBean? = null
)