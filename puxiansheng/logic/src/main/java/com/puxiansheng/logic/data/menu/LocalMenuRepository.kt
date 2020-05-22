package com.puxiansheng.logic.data.menu

import com.puxiansheng.logic.bean.MenuItem

class LocalMenuRepository(private val menuDao: MenuDao) {
    suspend fun insertOrUpdate(vararg menuItem: MenuItem) = menuDao.insertOrUpdateIntoRoom(*menuItem)

    suspend fun requestAllMenu() = menuDao.getAllMenuFromRoom()

    suspend fun requestMenuByType(type: Int) = menuDao.getMenuByTypeFromRoom(type)

    suspend fun requestMenuByParentID(parentID: Int) = menuDao.getMenuByParentIDFromRoom(parentID)

    suspend fun requestMenuByTypeAndParentID(type: Int, parentID: Int) =
        menuDao.getMenuByTypeAndParentIDFromRoom(type = type, parentID = parentID)
}