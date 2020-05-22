package com.puxiansheng.logic.bean.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.puxiansheng.logic.bean.LocationNode

class LocationNodeListConverter {
    @TypeConverter
    fun toObject(value: String?): List<LocationNode>? =
        Gson().fromJson(value, object : TypeToken<List<LocationNode>>() {}.type)

    @TypeConverter
    fun toString(list: List<LocationNode>?): String? = Gson().toJson(list)
}