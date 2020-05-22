package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.Shop

data class HttpRespSubmitOrder(
    @SerializedName("transfer_shop")
    val submitResult: HttpRespSubmitOrderRstObj? = null
)

data class HttpRespSubmitOrderRstObj(
    val id: String? = null
)