package com.puxiansheng.util.http

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize

/**
 * the basic type of http response.
 * @param T the type of http data class.
 * */
open class APIResp<T>(
    @SerializedName("data")
    val data: T? = null,

    @SerializedName("code")
    val code: Int = HTTP_DEF_CODE,

    @SerializedName("msg")
    val msg: String = HTTP_DEF_MSG
) {
    companion object {
        const val HTTP_DEF_CODE = 0
        const val HTTP_DEF_MSG = "HTTP_DEF_MSG"

        inline fun <reified T> fromJson(json: String): APIResp<T> =
            Gson().fromJson(json, object : TypeToken<APIResp<T>>() {}.type)
    }
}