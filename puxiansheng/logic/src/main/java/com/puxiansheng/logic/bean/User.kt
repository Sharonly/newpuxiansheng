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
//xinzeng
    @ColumnInfo(name = "_name")
    @SerializedName("name")
    var name: String = "",

    @ColumnInfo(name = "_nickname")
    @SerializedName("nick_name")
    var nickName: String = "",

    //xinzeng
    @ColumnInfo(name = "_user_sex")
    @SerializedName("sex")
    var userSex: Int = 0,

    @ColumnInfo(name = "_icon")
    @SerializedName("header_img")
    var icon: String = "",

    @ColumnInfo(name = "_login_timestamp")
    var loginTimestamp: Long = 0,

    //xinzeng
    @ColumnInfo(name = "_tips_msg")
    @SerializedName("tips_msg")
    var tipsMsg: String = "",

    @ColumnInfo(name = "_login_state")
    var loginState: Int = 0,

    @ColumnInfo(name = "_user_contact_name")
    @SerializedName("actual_name")
    var actualName: String = "",

    @ColumnInfo(name = "_user_contact_phone")
    @SerializedName("phone")
    var userPhoneNumber: String = "",

    //xinzeng
    @ColumnInfo(name = "_city_path_id")
    @SerializedName("city_path_id")
    var cityPathId: String = "",

    //xinzeng
    @ColumnInfo(name = "_city_id")
    @SerializedName("city_id")
    var cityId: Int = 0,

    //xinzeng
    @ColumnInfo(name = "_view_city_name")
    @SerializedName("view_path_city")
    var cityName: String = "",

    //xinzeng
    @ColumnInfo(name = "_view_city_path")
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