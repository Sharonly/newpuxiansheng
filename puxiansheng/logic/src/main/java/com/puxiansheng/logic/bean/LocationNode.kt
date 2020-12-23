package com.puxiansheng.logic.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationNode(
    @SerializedName("id")
    val nodeID: Int = 0,

    @SerializedName("parent_id")
    val parentID: Int = 0,


    @SerializedName("pid")
    val pId: Int = 0,

    @SerializedName("name")
    val text: String = "",

    @SerializedName("short_name")
    val textForShort: String = "",

    @SerializedName("path_id")
    val path_id: String = "",

    @SerializedName("pinyin")
    val spell: String = ""
) : Parcelable