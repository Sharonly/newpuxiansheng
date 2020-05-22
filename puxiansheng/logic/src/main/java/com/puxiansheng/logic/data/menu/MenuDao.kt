package com.puxiansheng.logic.data.menu

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puxiansheng.logic.bean.MenuItem

@Dao
interface MenuDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateIntoRoom(vararg menu: MenuItem)

    @Query("delete from table_menu where _type = :type")
    suspend fun deleteMenuByTypeFromRoom(type: Int)

    @Query("delete from table_menu")
    suspend fun deleteAllFromRoom()

    @Query("select * from table_menu where _type = :type")
    suspend fun getMenuByTypeFromRoom(type: Int): List<MenuItem>?

    @Query("select * from table_menu where _parent_id = :parentID")
    suspend fun getMenuByParentIDFromRoom(parentID: Int): List<MenuItem>?

    @Query("select * from table_menu where _type = :type and _parent_id = :parentID")
    suspend fun getMenuByTypeAndParentIDFromRoom(type: Int, parentID: Int): List<MenuItem>?

    @Query("select * from table_menu")
    suspend fun getAllMenuFromRoom(): List<MenuItem>?

    @Query("select * from table_menu where _menu_id = :id")
    suspend fun getMenuByIDFromRoom(id: Long): MenuItem?
}