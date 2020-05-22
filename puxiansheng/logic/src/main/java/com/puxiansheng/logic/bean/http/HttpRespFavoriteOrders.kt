package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.Order

data class HttpRespFavoriteOrders (

    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("msg")
    val msg: String = "",

    @SerializedName("data")
    val data: FavoriteOrder? = null
)

data class FavoriteOrder(
    @SerializedName("data")
    val data: List<OrderDetailObject>? = null
)