package com.puxiansheng.logic.data.info

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puxiansheng.logic.bean.InfoItem

@Dao
interface InfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateIntoRoom(vararg info: InfoItem)

    @Query("delete from table_info")
    fun deleteAllFromRoom()

    @Query("delete from table_info where _category = :category")
    fun deleteByCategoryFromRoom(category: Int)

    @Query("select * from table_info where _category = :category")
    fun getInfoByCategoryFromRoom(category: Int): DataSource.Factory<Int, InfoItem>

    @Query("select * from table_info")
    fun getFavorInfoFromRoom(): DataSource.Factory<Int, InfoItem>

    @Query("delete from table_info where _info_id = :infoId")
    fun deleteByIdFromRoom(infoId: Int)

}