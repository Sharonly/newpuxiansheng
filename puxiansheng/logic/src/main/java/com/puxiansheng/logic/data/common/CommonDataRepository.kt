package com.puxiansheng.logic.data.common

class CommonDataRepository {
    private val remoteCommonDataRepository = RemoteCommonDataRepository()

    fun getStatisticsDataFromRemote() = remoteCommonDataRepository.getStatisticsDataFromRemote()

    fun getServiceLinkFromRemote() = remoteCommonDataRepository.getServiceLinkFromRemote()
}