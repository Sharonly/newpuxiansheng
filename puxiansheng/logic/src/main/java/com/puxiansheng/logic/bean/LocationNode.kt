package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
data class LocationNode(

    @SerializedName("id")
    var nodeID: Int = 0,

    @SerializedName("parent_id")
    val parentID: Int = 0,

    @SerializedName("pid")
    var pId: Int = 0,

    @SerializedName("name")
    var text: String = "",

    @SerializedName("text")
    var btText: String = "",

    @SerializedName("short_name")
    val textForShort: String = "",

    @SerializedName("path_id")
    val path_id: String = "",

    @SerializedName("pinyin")
    val spell: String = "",

    @SerializedName("children")
    var children: List<LocationNode>? = null,

    @SerializedName("selected")
    var isSelect:  Int = 0,

    @SerializedName("children_selected_count")
    var children_selected_count:  Int = 0,

    var parentName:String = ""

) : Parcelable