package com.puxiansheng.logic.data.business

import com.puxiansheng.logic.bean.BusinessBean

class BusinessRepository (businessDao: BusinessDao){
    private val remoteRepository = RemoteBusinessRepository()
    private val localBusinessRepository = LocalBusinessRepository(businessDao = businessDao)

    fun requestRemoteBusiness(page: Int) = remoteRepository.requestBusinessList(page = page)

    fun getBusinessFromRoom(
    ) = localBusinessRepository.getBusinessInfoFromRoom()

    fun deleteBusinessFromRoom() = localBusinessRepository.deleteAllBusiness()

    fun insertBusinessInfo(vararg business: BusinessBean) = localBusinessRepository.insertBusinessInfoIntoRoom(*business)
}