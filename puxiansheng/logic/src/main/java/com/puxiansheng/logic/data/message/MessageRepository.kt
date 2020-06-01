package com.puxiansheng.logic.data.message

import com.puxiansheng.logic.bean.MessageItem


class MessageRepository(messageDao: MessageDao) {
    private val remoteMessageRepository = RemoteMessageRepository()
    private val localMessageRepository = LocalMessageRepository(messageDao = messageDao)

    fun getMessageCategoriesFromRemote(

    ) = remoteMessageRepository.getMessageCategoriesFromRemote()

    fun getInfoByCategoryFromRemote(
        category: Int,
        page: Int,
        city: String? = null,
        title:String? = null
    ) = remoteMessageRepository.getMessageByCategoryFromRemote(
        category = category,
        page = page,
        city = city,
        title = title
    )

    fun getMessageDetail(messageId: String)=  remoteMessageRepository.getMessagedDetailFromRemote(messageId = messageId)

    fun getMessageByCategoryFromRoom(
        category: Int
    ) = localMessageRepository.getInfoByCategoryFromRoom(category = category)

    fun getFavorInfoFromRemote(page: Int) = remoteMessageRepository.getFavoriteInfoFromRemote(page = page)
    fun getHistoryInfoFromRemote(page: Int) = remoteMessageRepository.getHistoryInfoFromRemote(page = page)

    fun deleteHistoryInfoFromRemote() = remoteMessageRepository.deleteAllHistroyInfoFromRemote()

    fun deleteFavorInfoFromRemote(infoId: String) = remoteMessageRepository.deleteFavorInfoFromRemote(infoId = infoId)

    fun getFavorInfoFromRoom(
    ) = localMessageRepository.getFavorInfoFromRoom()

    fun deleteInfoByCategoryFromRoom(
        category: Int
    ) = localMessageRepository.deleteInfoByCategoryFromRoom(category = category)

    fun deleteInfoByIdFromRoom(
        infoId: Int
    ) = localMessageRepository.deleteInfoByIdFromRoom(infoId = infoId)

    fun insertInfoIntoRoom(
        vararg info: MessageItem
    ) = localMessageRepository.insertInfoIntoRoom(*info)

    fun deleteAllInfoFromRoom(
    ) = localMessageRepository.deleteAllFavorInfo()
}