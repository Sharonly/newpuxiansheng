package com.puxiansheng.www.ui.order

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderListViewModel (application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    fun loadMore(
        category: Int,
        city: String? = null
    ) = viewModelScope.launch(Dispatchers.IO) {

    }


}
