package com.puxiansheng.www.ui.order.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.data.menu.MenuDatabase
import com.puxiansheng.logic.data.menu.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SelectRentRangeViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())


    val selectedTopLevelMenuItem = MutableLiveData<MenuItem>()
    val selectedTopLevelMenuItemPosition = MutableLiveData<Int>()
    val selectiveTopLevelMenuData = MutableLiveData<List<MenuItem>?>()


    private suspend fun requestMenuByTypeAndParentID(type: Int, parentID: Int) =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            menuRepository.requestMenuByTypeAndParentID(type = type, parentID = parentID)
        }

    fun loadTopLevelMenuData(type: Int) = viewModelScope.launch(Dispatchers.IO) {
        requestMenuByTypeAndParentID(type = type, parentID = 0).let {
            selectiveTopLevelMenuData.postValue(it)
        }
    }


}