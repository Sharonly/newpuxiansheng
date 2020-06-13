package com.puxiansheng.www.ui.info

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.bean.InfoItem
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.data.info.InfoDatabase
import com.puxiansheng.logic.data.info.InfoRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InfoListViewModel (application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val infoRepository = InfoRepository(InfoDatabase.getInstance(context).infoDao())


    private var currentPage = 1
    var title = ""

    private fun deleteInfoByCategory(
        category: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        infoRepository.deleteInfoByCategoryFromRoom(category = category)
    }

    fun loadMore(category: Int, city: String? = null) = viewModelScope.launch(Dispatchers.IO) {
            getInfoByCategoryFromRemote(
                category = category,
                city = city,
                title = title)
        }


    fun refresh(
        category: Int
    ) {
        currentPage = 1
        viewModelScope.launch {
            deleteInfoByCategory(category = category)
            delay(300)
            loadMore(category = category)
        }
    }

    suspend fun getInfoCategoriesFromRemote() = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getInfoCategoriesFromRemote().let {
            println(it)
            if (it.succeeded) (it as APIRst.Success).data.data?.categories else null
        }
    }

    private suspend fun getInfoByCategoryFromRemote(
        category: Int,
        city: String? = null,
        title:String? = null
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getInfoByCategoryFromRemote(
            category = category,
            page = currentPage,
            city = city,
            title = title
        ).let {
            if (it.succeeded) (it as APIRst.Success).data.data?.infoListObject?.infoList?.let { list ->
                list.map {item ->
                    item.category = category
                }
                insertInfoIntoRoom(*list.toTypedArray())
                currentPage += 1
                list
            } else null
        }
    }

    suspend fun getInfoByCategoryFromRoom(
        category: Int
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getInfoByCategoryFromRoom(
            category = category
        ).let {
            it
        }
    }



//    fun getInfoByCategoryFromRoom(category: Int){
//        infoRepository.getInfoByCategoryFromRoom(category = category)
//    }





    private suspend fun insertInfoIntoRoom(
        vararg info: InfoItem
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.insertInfoIntoRoom(
            info = *info
        )
    }

    suspend fun deleteAll() {}
}