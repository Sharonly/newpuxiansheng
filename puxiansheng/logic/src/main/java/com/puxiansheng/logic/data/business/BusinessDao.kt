package com.puxiansheng.logic.data.business

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puxiansheng.logic.bean.BusinessBean

@Dao
interface BusinessDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateIntoRoom(vararg info: BusinessBean)

    @Query("delete from table_business")
    fun deleteAllFromRoom()


    @Query("select * from table_business")
    fun getBusinessInfoFromRoom(): DataSource.Factory<Int, BusinessBean>
}