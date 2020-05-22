package com.puxiansheng.logic.data.location

import androidx.room.Dao

@Dao
interface LocationDao {
    fun requestLocalCityByID()

    fun requestLocalProvinceByID()

    fun requestLocalProvinces()

    fun requestLocalCitiesByProvince()
}