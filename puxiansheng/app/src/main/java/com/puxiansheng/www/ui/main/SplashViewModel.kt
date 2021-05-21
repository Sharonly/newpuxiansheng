package com.puxiansheng.www.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.location.LocationRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    var countDown: MutableLiveData<Int> = MutableLiveData()

    fun startCountDown() = viewModelScope.launch(Dispatchers.IO) {
        (3 downTo 0).forEach {
            delay(1000)
            countDown.postValue(it)
        }
    }

}