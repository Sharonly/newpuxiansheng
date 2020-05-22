package com.puxiansheng.logic.bean

data class BusinessBean(
val code: Int,
val `data`: Data,
val msg: String
)


data class Data(
        val `data`: List<Business>,
        val this_page: String,
        val total_page: Int
)

data class Business(
        val contact_phone: String,
        val id: Int,
        val investment: String,
        val jump_param: String,
        val jump_type: Int,
        val jump_view: String,
        val keywords: List<String>,
        val large_img: String,
        val large_img_alt: String,
        val large_img_title: String,
        val name: String,
        val thumb_img: String,
        val thumb_img_alt: String,
        val thumb_img_title: String,
        val trades: String
)
