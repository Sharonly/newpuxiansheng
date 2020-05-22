package com.puxiansheng.logic.data.device

import androidx.lifecycle.LiveData
import androidx.room.*
import com.puxiansheng.logic.bean.Device

@Dao
interface DeviceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(device: Device)

    @Query("delete from table_device where _id = :id")
    fun delete(id: Int = 1)

    @Delete
    fun delete(device: Device)

    @Query("select * from table_device where _id = 1")
    fun get(): LiveData<Device>
}