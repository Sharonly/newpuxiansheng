package com.puxiansheng.logic.bean

import androidx.recyclerview.widget.DiffUtil
import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "table_orders",
    indices = [Index(value = ["_shop_id", "_transfer_type"], unique = true)]
)
data class Order(

    @ColumnInfo(name = "_order_type")
    var orderType: Int = 0,

    @ColumnInfo(name = "_favorite")
    var favorite: Int = 0,

    @Embedded
    var shop: Shop? = null,

//    @Embedded
//    var articleList: List<ArticleBean>? = null,

    @Embedded
    var shopOwner: User? = null,

    @Embedded
    var state: State? = null,

    @Embedded
    var status: orderStatus? = null,

    @Ignore
    var serviceAgent: ServiceAgent? = null
) {
    companion object {
        const val TRANSFER_TYPE = "TRANSFER_TYPE"
        const val TRANSFER_TYPE_OUT = 0
        const val TRANSFER_TYPE_IN = 1

        val DIFF = object : DiffUtil.ItemCallback<Order>() {
            override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem.orderID == newItem.orderID
            }

            override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
                return oldItem == newItem
            }
        }

        data class State(
            @ColumnInfo(name = "_state_text")
            @SerializedName(value = "text")
            val text: String = "",

            @ColumnInfo(name = "_state_color")
            @SerializedName(value = "color")
            val color: String = ""
        )


        data class orderStatus(
            @ColumnInfo(name = "_status_name")
            @SerializedName(value = "name")
            val text: String = "",

            @ColumnInfo(name = "_status_color")
            @SerializedName(value = "color")
            val color: String = ""
        )
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_order_id")
    var orderID: Long = 0

    enum class Type {
        TRANSFER_OUT {
            override fun value() = 0
        },
        TRANSFER_IN {
            override fun value() = 1
        },
        TRANSFER_OUT_PRIVATE {
            override fun value() = 2
        },
        TRANSFER_IN_PRIVATE {
            override fun value() = 3
        },
        TRANSFER_OUT_RECOMMEND {
            override fun value() = 4
        },
        TRANSFER_IN_RECOMMEND {
            override fun value() = 5
        },
        TRANSFER_OUT_FAVORITE {
            override fun value() = 6
        },
        TRANSFER_IN_FAVORITE {
            override fun value() = 7
        },
        TRANSFER_OUT_HISTORY {
            override fun value() = 8
        },
        TRANSFER_IN_HISTORY {
            override fun value() = 9
        },
        RECOMMEND_INFO{
            override fun value() = 10
        },
        EMPTY {
            override fun value() = 9999
        };

        abstract fun value(): Int
    }
}