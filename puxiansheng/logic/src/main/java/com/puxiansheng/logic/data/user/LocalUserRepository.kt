package com.puxiansheng.logic.data.user

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.puxiansheng.logic.bean.User
import okhttp3.OkHttpClient

class LocalUserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insert(user)

    suspend fun requestLastUser(): User? = userDao.requestLastUser()

}