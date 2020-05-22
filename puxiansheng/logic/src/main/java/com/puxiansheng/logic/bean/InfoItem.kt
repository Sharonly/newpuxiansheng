package com.puxiansheng.logic.bean

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_info")
data class InfoItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val itemID: Long = 0,

    @SerializedName("user_id")
    @ColumnInfo(name = "_user_id")
    val userId: Int = 0,

    @SerializedName("id")
    @ColumnInfo(name = "_info_id")
    val infoID: Int = 0,

    @SerializedName("title")
    @ColumnInfo(name = "_title")
    val title: String = "0",

    @SerializedName("update_time")
    @ColumnInfo(name = "_release_date")
    val date: String = "",

    @SerializedName("article_images")
    @ColumnInfo(name = "_image")
    val image: String = "",

    @ColumnInfo(name = "_category")
    var category: Int = 0,

    @SerializedName("author")
    @ColumnInfo(name = "_author")
    val author: String = "",

    @SerializedName("url")
    @ColumnInfo(name = "_url")
    val url: String = "",

    @SerializedName("view_count")
    @ColumnInfo(name = "_page_views")
    val pageViews: Int = 0
//@SerializedName("new_view_count")
//@ColumnInfo(name = "_page_views")
//val pageViews: Int = 0


) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<InfoItem>() {
            override fun areItemsTheSame(oldItem: InfoItem, newItem: InfoItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: InfoItem, newItem: InfoItem): Boolean {
                return oldItem.infoID == newItem.infoID
            }
        }
    }
}