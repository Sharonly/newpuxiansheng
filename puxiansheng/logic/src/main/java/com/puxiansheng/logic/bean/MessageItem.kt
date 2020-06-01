package com.puxiansheng.logic.bean

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.converter.MenuListConverter

@Entity(tableName = "table_message")
@TypeConverters(
    MenuListConverter::class)
data class MessageItem (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    val itemID: Long = 0,

    @SerializedName("id")
    @ColumnInfo(name = "_message_id")
    val messageID: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("content")
    val content:  String = "",

    @ColumnInfo(name = "_category")
    var category: Int = 0,

    @SerializedName("view_time")
    val view_time: String = "",

    @SerializedName("read_log_id")
    val read_log_id: Int = 0,

    @SerializedName("button_list")//显示按钮
    var buttonList: List<MenuItem>? = null

//
//
//    @SerializedName("title")
//    val title: String = "0",

    ) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<MessageItem>() {
            override fun areItemsTheSame(oldItem: MessageItem, newItem: MessageItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MessageItem, newItem: MessageItem): Boolean {
                return oldItem.messageID == newItem.messageID
            }
        }
    }


    enum class Type {
        ARTICLE_FAVOR {
            override fun value() = 20
        },
        ARTICLE_HISTORY {
            override fun value() = 21

        };

        abstract fun value(): Int
    }
}
