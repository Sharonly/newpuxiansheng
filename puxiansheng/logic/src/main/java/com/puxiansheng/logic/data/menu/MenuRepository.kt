package com.puxiansheng.logic.data.menu

import com.puxiansheng.logic.bean.MenuItem

class MenuRepository(menuDao: MenuDao) {
    private val remoteMenuRepository = RemoteMenuRepository()
    private val localMenuRepository = LocalMenuRepository(menuDao)

    suspend fun requestAllMenu(): List<MenuItem>? {
        return localMenuRepository.requestAllMenu()
    }

    suspend fun insertOrUpdate(vararg menuItem: MenuItem) {
        localMenuRepository.insertOrUpdate(*menuItem)
    }

    suspend fun requestMenuByParentID(parentID: Int) =
        localMenuRepository.requestMenuByParentID(parentID)

    suspend fun requestMenuByType(type: Int) = localMenuRepository.requestMenuByType(type)

    suspend fun requestMenuByTypeAndParentID(type: Int, parentID: Int) =
        localMenuRepository.requestMenuByTypeAndParentID(type = type, parentID = parentID)

    fun requestRemoteIndustrySelectiveData(signatureToken: String) =
        remoteMenuRepository.requestRemoteIndustrySelectiveData(signatureToken = signatureToken)

    fun requestRemoteIndustrySelectiveDataByPid(signatureToken: String,id:Int) =
        remoteMenuRepository.requestRemoteIndustrySelectiveById(signatureToken = signatureToken,id = id)

    fun requestRemoteSizeSelectiveData(signatureToken: String) =
        remoteMenuRepository.requestRemoteSizeSelectiveData(signatureToken = signatureToken)

    fun requestRemoteRentUnitSelectiveData(signatureToken: String) =
        remoteMenuRepository.requestRemoteRentUnitSelectiveData(signatureToken = signatureToken)

    fun requestRemoteRentSelectiveData(signatureToken: String) =
        remoteMenuRepository.requestRemoteRentSelectiveData(signatureToken = signatureToken)

    fun requestRemotePropertySelectiveData(signatureToken: String) =
        remoteMenuRepository.requestRemotePropertySelectiveData(signatureToken = signatureToken)

    fun requestRemoteNewAreaSelectiveData(tree:String,signatureToken: String) =
        remoteMenuRepository.requestRemoteNewAreaSelectiveData(tree = tree,signatureToken = signatureToken)

    fun requestRemoteMultiAreaSelectiveData(tree:String,ids:String,signatureToken: String) =
        remoteMenuRepository.requestRemoteMultiAreaSelectiveData(tree = tree,ids = ids,signatureToken = signatureToken)

    fun requestRemoteNewAreaSelectiveDataByPid(signatureToken: String,id:Int) =
        remoteMenuRepository.requestRemoteNewAreaById(signatureToken = signatureToken,id = id)

}