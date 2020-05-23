package com.puxiansheng.logic.data.business

import com.puxiansheng.logic.bean.BusinessBean


class LocalBusinessRepository(private var businessDao:BusinessDao) {

    fun insertBusinessInfoIntoRoom(
        vararg info: BusinessBean
    ) = businessDao.insertOrUpdateIntoRoom(*info)

    fun deleteAllBusiness(
    ) = businessDao.deleteAllFromRoom()

    fun getBusinessInfoFromRoom() = businessDao.getBusinessInfoFromRoom()



}