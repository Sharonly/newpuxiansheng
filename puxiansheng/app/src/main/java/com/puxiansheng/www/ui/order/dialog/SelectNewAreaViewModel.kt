package com.puxiansheng.www.ui.order.dialog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.LocationNode
import com.puxiansheng.logic.bean.MenuItem
import com.puxiansheng.logic.data.location.LocationRepository
import com.puxiansheng.logic.data.menu.MenuDatabase
import com.puxiansheng.logic.data.menu.MenuRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectNewAreaViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())

    val selectedTopLevelAreaItem = MutableLiveData<MenuItem>()
    val selectedTopLevelAreaItemPosition = MutableLiveData<Int>()

    val selectedSecondLevelAreaItem = MutableLiveData<MenuItem>()
    val selectedSecondLevelAreaItemPosition = MutableLiveData<Int>()

    val selectiveTopLevelAreaData = MutableLiveData<List<MenuItem>?>()
    val selectiveSecondLevelAreaData = MutableLiveData<List<MenuItem>?>()

//    fun requestRemoteCitiesByParentID(parentID: String) = viewModelScope.launch(Dispatchers.IO) {
//        locationRepository.requestRemoteCitiesByParentID(parentID).let { apiRst ->
//            if (apiRst.succeeded) {
//                (apiRst as APIRst.Success).data.data?.nodes?.let {
////                    selectiveAreaData.postValue(it)
//                }
//            }
//        }
//    }


    fun getNewAreaMenuDataFromRemote() = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemoteNewAreaSelectiveData(API.currentSignatureToken).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.areaObject?.let { menuList ->
                    selectiveTopLevelAreaData.postValue(menuList)
                }
            }
        }
    }

    fun getNewAreaSecondMenuDataFromRemote(pid:Int) = viewModelScope.launch(Dispatchers.IO) {
        menuRepository.requestRemoteNewAreaSelectiveDataByPid(API.currentSignatureToken,pid).let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.areaObject?.let { menuList ->
                    selectiveSecondLevelAreaData.postValue(menuList)
                }
            }
        }
    }


}