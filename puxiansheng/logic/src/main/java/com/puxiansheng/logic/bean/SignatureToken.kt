package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignatureToken(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = 1,

    @SerializedName("token")
    val token: String? = "",

    @SerializedName("new_package")
    val newPackage: NewPackage? = null,

    @SerializedName("ad_list")
    val adList: AdObject? = null

) : Parcelable

@Parcelize
data class AdObject(
    @SerializedName("api_index_pop_up_ads")
    val adInfo: Int = 0
) : Parcelable
