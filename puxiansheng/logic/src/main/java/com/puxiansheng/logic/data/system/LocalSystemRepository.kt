package com.puxiansheng.logic.data.system

import com.puxiansheng.logic.bean.SystemConfig

class LocalSystemRepository(private val systemDao: SystemDao) {

    fun getSystemConfig() = systemDao.getLocalSystemConfig()

    fun insertOrUpdateLocalSystemConfig(config: SystemConfig) =
        systemDao.insertOrUpdateLocalSystemConfig(config)
}