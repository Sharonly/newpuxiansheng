package com.puxiansheng.logic.bean

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.ArtcleListConverter
import com.puxiansheng.logic.bean.converter.MenuListConverter
import com.puxiansheng.logic.bean.converter.StringListConverter

@TypeConverters(StringListConverter::class)
@Entity(tableName = "table_business")
data class BusinessBean(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_shop_id")
        var shopID: Int = 0,

        @ColumnInfo(name = "_id")
        @SerializedName("id")
        val id: Int = 0,

        @ColumnInfo(name = "_name")
        @SerializedName("name")
        val name:  String = "",


        @ColumnInfo(name = "_trades")
        @SerializedName("trades")
        val trades: String,

        @SerializedName("contact_phone")
        @ColumnInfo(name = "_contact_phone")
        val contact_phone: String = "",

        @ColumnInfo(name = "_investment")
        @SerializedName("investment")
        val investment:  String = "",


        @ColumnInfo(name = "_item_keywords")
        @SerializedName("keywords")
         var keywords :List<String>? =null,


        @ColumnInfo(name = "_large_img")
        @SerializedName("large_img")
        val large_img:  String = "",

        @ColumnInfo(name = "_large_img_alt")
        @SerializedName("large_img_alt")
        val large_img_alt:  String = "",

        @ColumnInfo(name = "_large_img_title")
        @SerializedName("large_img_title")
        val large_img_title:  String = "",


        @ColumnInfo(name = "_thumb_img")
        @SerializedName("thumb_img")
        val thumb_img:  String = "",

        @ColumnInfo(name = "_thumb_img_alt")
        @SerializedName("thumb_img_alt")
        val thumb_img_alt:  String = "",

        @ColumnInfo(name = "_thumb_img_title")
        @SerializedName("thumb_img_title")
        val thumb_img_title:  String = ""

){
        companion object {
                val DIFF = object : DiffUtil.ItemCallback<BusinessBean>() {
                        override fun areItemsTheSame(oldItem: BusinessBean, newItem: BusinessBean): Boolean {
                                return oldItem == newItem
                        }

                        override fun areContentsTheSame(oldItem: BusinessBean, newItem: BusinessBean): Boolean {
                                return oldItem.id == newItem.id
                        }
                }
        }
}
