package com.puxiansheng.logic.data.message

import com.puxiansheng.logic.bean.MessageItem

class LocalMessageRepository(private val messageDao: MessageDao) {
    fun getInfoByCategoryFromRoom(
        category: Int
    ) = messageDao.getInfoByCategoryFromRoom(category = category)

    fun insertInfoIntoRoom(
        vararg info:MessageItem
    ) = messageDao.insertOrUpdateIntoRoom(*info)

    fun deleteAllFavorInfo(
    ) = messageDao.deleteAllFromRoom()

    fun getFavorInfoFromRoom() = messageDao.getFavorInfoFromRoom()

    fun deleteInfoByIdFromRoom(
        infoId: Int
    ) = messageDao.deleteByIdFromRoom(infoId)

    fun deleteInfoByCategoryFromRoom(
        category: Int
    ) = messageDao.deleteByCategoryFromRoom(category)
}