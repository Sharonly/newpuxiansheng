package com.puxiansheng.logic.bean.http

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class HttpRespBindMobilePhone(
    @SerializedName("code")
    val code: Int = 0,

    @SerializedName("msg")
    val msg: String = "",

    @SerializedName("data")
    val dataObject: DataObject? = null
) {
    companion object {
        fun fromJson(json: String) =
            Gson().fromJson<HttpRespBindMobilePhone>(json, HttpRespBindMobilePhone::class.java)
    }
}

data class DataObject(
    @SerializedName("result")
    val result: Int = 0
)