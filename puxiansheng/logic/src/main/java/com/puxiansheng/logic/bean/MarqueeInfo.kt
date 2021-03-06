package com.puxiansheng.logic.bean

import com.google.gson.annotations.SerializedName

data class MarqueeInfo(
        @SerializedName("id")
        val id: Int = 0,

        @SerializedName("title")
        val title: String = "",

        @SerializedName("checked_id")
        val checkedId: Int = 0,

        @SerializedName("status")
        val status: Int? = 0,

        @SerializedName("is_vip")
        val isVip: String = "",

        @SerializedName("is_success")
        val isSuccess: Int = 0,

        @SerializedName("update_time")
        val updateTime: String = "",

        @SerializedName("user_id")
        val userId:  Int = 0,

        @SerializedName("nick_name")
        val nickName: String = "",

        @SerializedName("rent_name")
        val rentName: String = "",

        @SerializedName("jump_type")
        val jump_type: Int? = 0,

        @SerializedName("jump_view")
        val jump_view: String = "",

        @SerializedName("jump_param")
        val jump_param: String = ""
)

