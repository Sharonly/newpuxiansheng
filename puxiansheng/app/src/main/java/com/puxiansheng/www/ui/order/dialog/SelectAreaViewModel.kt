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

class SelectAreaViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val menuRepository = MenuRepository(MenuDatabase.getInstance(context).menuDao())
    private val locationRepository = LocationRepository()

    var selectedLocationNode: LocationNode? = null
    val selectedLocationNodePosition = MutableLiveData<Int>()

    val selectiveAreaData = MutableLiveData<List<LocationNode>?>()

    fun requestRemoteCitiesByParentID(parentID: String) = viewModelScope.launch(Dispatchers.IO) {
        locationRepository.requestRemoteCitiesByParentID(parentID).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.nodes?.let {
                    selectiveAreaData.postValue(it)
                }
            }
        }
    }

}