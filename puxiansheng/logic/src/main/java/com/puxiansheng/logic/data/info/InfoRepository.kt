package com.puxiansheng.logic.data.info

import com.puxiansheng.logic.bean.InfoItem

class InfoRepository(infoDao: InfoDao) {
    private val localInfoRepository = LocalInfoRepository(infoDao = infoDao)
    private val remoteInfoRepository = RemoteInfoRepository()

    fun getInfoCategoriesFromRemote(

    ) = remoteInfoRepository.getInfoCategoriesFromRemote()

    fun getInfoByCategoryFromRemote(
        category: Int,
        page: Int,
        city: String? = null,
        title:String? = null
    ) = remoteInfoRepository.getInfoByCategoryFromRemote(
        category = category,
        page = page,
        city = city,
        title = title
    )

    fun getInfoByCategoryFromRoom(
        category: Int
    ) = localInfoRepository.getInfoByCategoryFromRoom(category = category)

    fun getFavorInfoFromRemote(page: Int) = remoteInfoRepository.getFavoriteInfoFromRemote(page = page)
    fun getHistoryInfoFromRemote(page: Int) = remoteInfoRepository.getHistoryInfoFromRemote(page = page)

    fun getFavorInfoFromRoom(
    ) = localInfoRepository.getFavorInfoFromRoom()

    fun deleteInfoByCategoryFromRoom(
        category: Int
    ) = localInfoRepository.deleteInfoByCategoryFromRoom(category = category)

    fun deleteInfoByIdFromRoom(
        infoId: Int
    ) = localInfoRepository.deleteInfoByIdFromRoom(infoId = infoId)

    fun insertInfoIntoRoom(
        vararg info: InfoItem
    ) = localInfoRepository.insertInfoIntoRoom(*info)

    fun deleteAllInfoFromRoom(
    ) = localInfoRepository.deleteAllFavorInfo()
}