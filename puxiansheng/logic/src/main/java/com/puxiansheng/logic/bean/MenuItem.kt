package com.puxiansheng.logic.bean

import android.os.Parcelable
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.MenuListConverter
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "table_menu", indices = [Index(value = ["_menu_id", "_type"], unique = true)])
@Parcelize

data class MenuItem(

    @ColumnInfo(name = "_menu_id")
    @SerializedName("id")
    var menuID: Long = 0,

    @ColumnInfo(name = "_type")
    var type: Int = 0,

    @SerializedName("pid")
    @ColumnInfo(name = "_parent_id")
    var parentID: Long = 0,

    @SerializedName("name")
    @ColumnInfo(name = "_text")
    var text: String = "",

    @SerializedName("text")
    var btText: String = "",

    @SerializedName("color")
    @ColumnInfo(name = "_color")
    var color: String = "",


    @ColumnInfo(name = "_value")
    @SerializedName("value")
    var value: Int = 0,

    @SerializedName("jump_type")
    var jump_type: Int = 0,

    @SerializedName("jump_view")
    var jump_view: String = "",

    @SerializedName("jump_param")
    var jump_param: String = "",

    @SerializedName("icon")
    @ColumnInfo(name = "_icon_enable")
    var icon_enable: String = "",

    @SerializedName("colorless_icon")
    @ColumnInfo(name = "_icon_disable")
    var icon_disable: String = ""

    /*@SerializedName("children")
    val list: List<MenuItem>? = null*/
):Parcelable {

    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var itemID: Long = 0

    enum class TYPE {
        INDUSTRY {
            override fun value(): Int = 1
        },
        SIZE {
            override fun value(): Int = 2
        },
        RENT_UNIT {
            override fun value(): Int = 3
        },
        RENT {
            override fun value(): Int = 4
        },
        PROPERTY {
            override fun value(): Int = 5
        };

        abstract fun value(): Int
    }
}