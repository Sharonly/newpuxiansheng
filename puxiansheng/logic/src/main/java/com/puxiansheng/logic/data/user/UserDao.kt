package com.puxiansheng.logic.data.user

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.puxiansheng.logic.bean.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("select * from user_table where _account = :userAccount")
    suspend fun isUserExist(userAccount: String): User?

    @Query("select * from user_table order by _login_timestamp desc limit 1")
    suspend  fun requestLastUser(): User?

}