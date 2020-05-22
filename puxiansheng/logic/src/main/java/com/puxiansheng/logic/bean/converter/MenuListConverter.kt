package com.puxiansheng.logic.bean.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.puxiansheng.logic.bean.MenuItem

class MenuListConverter {
    @TypeConverter
    fun toObject(value: String?): List<MenuItem>? =
        Gson().fromJson(value, object : TypeToken<List<MenuItem>>() {}.type)

    @TypeConverter
    fun toString(list: List<MenuItem>?): String? = Gson().toJson(list)
}