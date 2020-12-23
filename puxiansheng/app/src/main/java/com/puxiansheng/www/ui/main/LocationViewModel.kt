package com.puxiansheng.www.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.location.LocationRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val locationRepository = LocationRepository()

    suspend fun getRemoteSupportedCities(

    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO){
        locationRepository.getRemoteSupportedCities().let {
            if(it.succeeded) (it as APIRst.Success).data.data else null
        }
    }


}