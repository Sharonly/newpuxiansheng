package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.MessageItem

data class HttpRespMessageList(
    @SerializedName("result")
    val infoList: List<MessageItem>? = null,

    @SerializedName("this_page")
    val currentPage: Int = 0,

    @SerializedName("total_page")
    val totalPages: Int = 0
)

data class MessageListObject(
    @SerializedName("result")
    val messageDetail: MessageItem? = null


)