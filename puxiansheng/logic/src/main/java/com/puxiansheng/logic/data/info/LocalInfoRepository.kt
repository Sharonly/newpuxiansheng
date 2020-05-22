package com.puxiansheng.logic.data.info

import com.puxiansheng.logic.bean.InfoItem

class LocalInfoRepository(private val infoDao: InfoDao) {
    fun getInfoByCategoryFromRoom(
        category: Int
    ) = infoDao.getInfoByCategoryFromRoom(category = category)

    fun insertInfoIntoRoom(
        vararg info: InfoItem
    ) = infoDao.insertOrUpdateIntoRoom(*info)

    fun deleteAllFavorInfo(
    ) = infoDao.deleteAllFromRoom()

    fun getFavorInfoFromRoom() = infoDao.getFavorInfoFromRoom()

    fun deleteInfoByIdFromRoom(
        infoId: Int
    ) = infoDao.deleteByIdFromRoom(infoId)

    fun deleteInfoByCategoryFromRoom(
        category: Int
    ) = infoDao.deleteByCategoryFromRoom(category)
}