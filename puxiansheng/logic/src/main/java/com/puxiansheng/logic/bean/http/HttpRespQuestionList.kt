package com.puxiansheng.logic.bean.http

import com.google.gson.annotations.SerializedName

data class HttpRespQuestionList(
    @SerializedName("result")
    val infoListObject: List<QuestionObject>? = null

)

data class QuestionObject(

    @SerializedName( "_id")
    val itemID: Long = 0,

    @SerializedName("reply")
    val reply: String = "",

    @SerializedName("content")
    val content: String = "",

    @SerializedName("cate")
    val cate: String = "",

    @SerializedName("update_time")
    val update_time: String = ""

    )