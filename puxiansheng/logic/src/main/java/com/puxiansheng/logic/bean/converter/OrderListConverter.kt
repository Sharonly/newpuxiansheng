package com.puxiansheng.logic.bean.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.bean.Order

class OrderListConverter {
    @TypeConverter
    fun toObject(value: String?): List<Order>? =
        Gson().fromJson(value, object : TypeToken<List<Order>>() {}.type)

    @TypeConverter
    fun toString(list: List<Order>?): String? = Gson().toJson(list)
}