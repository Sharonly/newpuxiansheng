package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespOrders(
    @SerializedName("result")
    val data: OrdersData? = null
)

data class OrdersData(
    @SerializedName("data")
    val orders: List<OrderDetailObject>? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

//    @SerializedName("page")
//    val currentPage: Int = 0,


    @SerializedName("total_page")
    val totalPages: Int = 0
)

data class HttpRespReleaseOrders(
    @SerializedName("result")
    val data: List<OrderDetailObject>? = null
)