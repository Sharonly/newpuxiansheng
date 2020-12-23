package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpCardFastObject(
    @SerializedName("result")
    val result: CardFastObject? = null
)

data class CardFastObject(
    @SerializedName("transfer_count")
    var transfer_count: TransferCount? = null,

    @SerializedName("find_shop_count")
    var find_shop_count: TransferCount? = null

)


data class TransferCount(
    @SerializedName("count")
    var count: String,

    @SerializedName("count_day")
    var count_day: String
)