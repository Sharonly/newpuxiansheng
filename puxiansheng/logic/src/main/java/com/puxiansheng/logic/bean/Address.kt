package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.LocationNodeListConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@TypeConverters(LocationNodeListConverter::class)
data class Address(
    @ColumnInfo(name = "_location_nodes")
    @SerializedName("location_nodes")
    var locationNodes: List<LocationNode>? = null,

    @ColumnInfo(name = "_address_description")
    @SerializedName("address")
    var addressDetail: String = "",

    @ColumnInfo(name = "_post_code")
    @SerializedName("post_code")
    var postCode: Int = 0,

    @Embedded
    @SerializedName("coordinates")
    var coordinates: Coordinates? = null
) : Parcelable