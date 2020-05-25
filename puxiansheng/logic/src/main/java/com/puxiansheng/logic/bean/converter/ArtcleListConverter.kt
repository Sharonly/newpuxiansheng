package com.puxiansheng.logic.bean.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.puxiansheng.logic.bean.ArticleBean

class ArtcleListConverter {
    @TypeConverter
        fun toObject(value: String?): List<ArticleBean>? =
        Gson().fromJson(value, object : TypeToken<List<ArticleBean>>() {}.type)

    @TypeConverter
    fun toString(list: List<ArticleBean>?): String? = Gson().toJson(list)
}