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

class SelectFacilityViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())

    val selectedFacilityItemPosition = MutableLiveData<Int>()

    var selectiveFacilityMenuData = MutableLiveData<List<MenuItem>?>()
    var selectedFacilityList = MutableLiveData<List<MenuItem>?>()

    val selectedFacilities = mutableListOf<MenuItem>()

    fun getFacilityMenuData(type: Int) =  viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestMenuByTypeAndParentID(type = type, parentID = 0)?.let {
            selectiveFacilityMenuData.postValue(it)
        }
    }


     fun getPropertySelectiveMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemotePropertySelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.list?.let { menuList ->
                    selectiveFacilityMenuData.postValue(menuList)
                }
            }
        }
    }
}