package com.puxiansheng.logic.data.business

import android.provider.ContactsContract
import com.puxiansheng.logic.bean.BusinessBean

class BusinessRepository (businessDao: BusinessDao){
    private val remoteRepository = RemoteBusinessRepository()
    private val localBusinessRepository = LocalBusinessRepository(businessDao = businessDao)

    fun requestRemoteBusiness(page: Int,title :String?) = remoteRepository.requestBusinessList(page = page, title= title)

    fun requestRemoteBusinessDetail(id: String) = remoteRepository.requestBusinessDetail(id = id)

    fun getBusinessFromRoom(
    ) = localBusinessRepository.getBusinessInfoFromRoom()

    fun deleteBusinessFromRoom() = localBusinessRepository.deleteAllBusiness()

    fun insertBusinessInfo(vararg business: BusinessBean) = localBusinessRepository.insertBusinessInfoIntoRoom(*business)

    fun submitBusinessUserInfo(name: String,phone: String) = remoteRepository.submitBusinessUserInfo(name = name,phone = phone)
}