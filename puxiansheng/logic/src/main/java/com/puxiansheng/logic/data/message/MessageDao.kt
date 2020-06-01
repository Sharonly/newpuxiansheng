package com.puxiansheng.logic.data.message

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puxiansheng.logic.bean.MessageItem

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateIntoRoom(vararg info: MessageItem)

    @Query("delete from table_message")
    fun deleteAllFromRoom()

    @Query("delete from table_message where _category = :category")
    fun deleteByCategoryFromRoom(category: Int)

    @Query("select * from table_message where _category = :category")
    fun getInfoByCategoryFromRoom(category: Int): DataSource.Factory<Int, MessageItem>

    @Query("select * from table_message")
    fun getFavorInfoFromRoom(): DataSource.Factory<Int, MessageItem>

    @Query("delete from table_message where _message_id = :infoId")
    fun deleteByIdFromRoom(infoId: Int)

}