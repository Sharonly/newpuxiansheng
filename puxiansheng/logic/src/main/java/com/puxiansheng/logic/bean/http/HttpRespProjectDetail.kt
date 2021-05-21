package com.puxiansheng.logic.bean.http

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName
import com.puxiansheng.logic.bean.*

data class HttpRespProjectDetail(
    @SerializedName("result")
    val obj: ProjectDetailObject? = null
)


data class ProjectListObject(
    @SerializedName("result")
    val projects: List<ProjectDetailObject>? = null
)


data class ProjectDetailObject(
    @SerializedName("id")
    var shopID: Long = 0,

    @SerializedName("title")
    var title: String = "",

    @SerializedName("is_collect")
    var is_collect: Int = 0,

    @SerializedName("is_attestation")
    var is_attestation: Int = 0,

    @SerializedName("category_prev")
    var category_prev:String = "",

    @SerializedName("category_path_id")
    var category_path_id:String = "",

    @SerializedName("brand")
    var brand: String = "",

    @SerializedName("is_join")
    var is_join: String = "",

    @SerializedName("is_trainee")
    var is_trainee: String = "",

    @SerializedName("address")
    var shop_address: String = "",

    @SerializedName("update_time")
    var update_time:String = "",

//    @SerializedName("img_ids")
//    var img_ids:String = "",

    @SerializedName("invest_money")
    var invest_money: String = "",

    @SerializedName("category_str")
    var category_str: String = "",

    @SerializedName("category_end")
    var category_end: String = "",

    @SerializedName("img_ids")
    var img_ids: ArrayList<String> = arrayListOf(),

    @SerializedName("theme_img")
    var theme_img: String = "",


    @SerializedName("years")
    var years: String = "",

    @SerializedName("superiority")
    var superiority: String = "",

    @SerializedName("environment")
    var environment: String = "",

    @SerializedName("project_situation")
    var project_situation: String = "",

    @SerializedName("operation_status")
    var operation_status: String = "",

    @SerializedName("video")
    var video: String = "",

    @SerializedName("jump_type")
    var jump_type: Int = 0,

    @SerializedName("jump_view")
    var jump_view: String = "",

    @SerializedName("jump_param")
    var jump_param: String = ""

) {
    companion object {
        val DIFF = object : DiffUtil.ItemCallback<ProjectDetailObject>() {
            override fun areItemsTheSame(oldItem: ProjectDetailObject, newItem: ProjectDetailObject): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ProjectDetailObject, newItem: ProjectDetailObject): Boolean {
                return oldItem.shopID == newItem.shopID
            }
        }
    }

    enum class Type {
        PROJECT_FAVOR {
            override fun value() = 30
        },
        PROJECT_HISTORY {
            override fun value() = 31

        };

        abstract fun value(): Int
    }
}




