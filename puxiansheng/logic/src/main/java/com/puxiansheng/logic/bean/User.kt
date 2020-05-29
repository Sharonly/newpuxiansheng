package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user_table", indices = [Index(value = ["_user_contact_phone"], unique = true)])
@Parcelize
data class User(
    @ColumnInfo(name = "_account")
    var account: String = "",

    @ColumnInfo(name = "_token")
    @SerializedName("token")
    var token: String = "",

    @ColumnInfo(name = "_nickname")
    @SerializedName("name")
    var name: String = "",

    @SerializedName("nick_name")
    var nickName: String = "",

    @ColumnInfo(name = "_sex")
    @SerializedName("sex")
    var userSex: Int = 0,

    @ColumnInfo(name = "_icon")
    @SerializedName("header_img")
    var icon: String = "",

    @ColumnInfo(name = "_login_timestamp")
    var loginTimestamp: Long = 0,

    @ColumnInfo(name = "_login_state")
    var loginState: Int = 0,

    @ColumnInfo(name = "_user_contact_name")
    @SerializedName("actual_name")
    var actualName: String = "",

    @ColumnInfo(name = "_user_contact_phone")
    @SerializedName("phone")
    var userPhoneNumber: String = "",

    @ColumnInfo(name = "_city_path_id")
    @SerializedName("city_path_id")
    var cityPathId: String = "",

//    @ColumnInfo(name = "_city_id")
    @SerializedName("city_id")
    var cityId: Int = 0,

    @ColumnInfo(name = "_view_path_city")
    @SerializedName("view_path_city")
    var cityName: String = "",

    @SerializedName("view_city_path_id")
    var userCityPath: String = "",

    @Ignore
    var password: String = "",

    @Ignore
    var verificationCode: String = "",

    @Ignore
    var wechatCode: String = ""
) : Parcelable {
    companion object {
        fun fromJson(json: String): User = Gson().fromJson<User>(json, User::class.java)
    }

    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_user_id")
    var userID: Int = 0

    @Ignore
    var isLogin: Boolean = false
        set(_) {
            field = loginState > 0
        }
        get() {
            return loginState > 0
        }
}