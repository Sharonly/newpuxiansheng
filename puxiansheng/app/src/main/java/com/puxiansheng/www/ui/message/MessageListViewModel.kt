package com.puxiansheng.www.ui.message

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.data.info.InfoDatabase
import com.puxiansheng.logic.data.info.InfoRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessageListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val infoRepository = InfoRepository(InfoDatabase.getInstance(context).infoDao())

    private var currentPage = 1

    private fun deleteInfoByCategory(
        category: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        infoRepository.deleteInfoByCategoryFromRoom(category = category)
    }

    fun loadMore(
        type: Int,
        city: String? = null
    ) = viewModelScope.launch(Dispatchers.IO) {
//        getMessageByTypeFromRemote(
//            category = category,
//            city = city
//        )
    }

    fun refresh(
        type: Int
    ) {
        currentPage = 1
        viewModelScope.launch {
//            deleteInfoByCategory(category = category)
            delay(300)
            loadMore(type = type)
        }
    }

    private suspend fun getMessageByTypeFromRemote() = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getInfoCategoriesFromRemote().let {
            println(it)
            if (it.succeeded) (it as APIRst.Success).data.data?.categories else null
        }
    }





    suspend fun deleteAll() {}
}