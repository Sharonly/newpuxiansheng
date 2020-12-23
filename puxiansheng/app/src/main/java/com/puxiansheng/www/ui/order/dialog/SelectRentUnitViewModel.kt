package com.puxiansheng.www.ui.order.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.data.menu.MenuDatabase
import com.puxiansheng.logic.data.menu.MenuRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectRentUnitViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())

    val selectedTopLevelMenuItem = MutableLiveData<MenuItem>()
    val selectedTopLevelMenuItemPosition = MutableLiveData<Int>()
    val selectiveTopLevelMenuData = MutableLiveData<List<MenuItem>?>()

    private suspend fun requestMenuByParentID(type: Int) =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            menuRepository.requestMenuByParentID(type)
        }

//    private suspend fun requestMenuByTypeAndParentID(type: Int, parentID: Int) =
//        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
//            menuRepository.requestMenuByTypeAndParentID(type = type, parentID = parentID)
//        }
//
//    fun loadTopLevelMenuData(type: Int) = viewModelScope.launch(Dispatchers.IO) {
//        requestMenuByTypeAndParentID(type = type, parentID = 0).let {
//            selectiveTopLevelMenuData.postValue(it)
//        }
//    }
fun getRentUnitDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
    menuRepository.requestRemoteRentUnitSelectiveData(API.currentSignatureToken).let {
        if (it.succeeded) {
            (it as APIRst.Success).data.data?.list.let { menuList ->
                selectiveTopLevelMenuData.postValue(menuList)
            }
        }
    }
}


}