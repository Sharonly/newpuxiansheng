package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coordinates(
    @ColumnInfo(name = "_latitude")
    @SerializedName("latitude")
    var latitude: Double = 0.0,

    @ColumnInfo(name = "_longitude")
    @SerializedName("longitude")
    var longitude: Double = 0.0
) : Parcelable