package com.puxiansheng.logic.data.system

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.puxiansheng.logic.bean.SignatureToken
import com.puxiansheng.logic.bean.SystemConfig

@Dao
interface SystemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateLocalSystemConfig(systemConfig: SystemConfig)

    @Query("select * from table_sys_cfg where _id = 1")
    fun getLocalSystemConfig(): SystemConfig

}