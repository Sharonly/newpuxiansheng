package com.puxiansheng.www.ui.mine.history

import android.app.Application
import android.util.Log
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

class BrowsingHistoryInfoListViewModel (application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val infoRepository = InfoRepository(InfoDatabase.getInstance(context).infoDao())

    private var currentPage = 1
    private var infoType = 0

    private fun deleteInfoAll(
    ) = viewModelScope.launch(Dispatchers.IO) {
        infoRepository.deleteAllInfoFromRoom()
    }

    fun loadMore(
    ) = viewModelScope.launch(Dispatchers.IO) {
        getHistoryInfoFromRemote()

    }

    fun refresh() {
        currentPage = 1
        viewModelScope.launch {
            deleteInfoAll()
            delay(300)
            loadMore()
        }
    }


    suspend fun getFavorInfoFromRoom(
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getFavorInfoFromRoom(
        ).let {
            it
        }
    }

     suspend fun getHistoryInfoListFromRemote(page: Int
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getHistoryInfoFromRemote( page = page
        ).let {
            if (it.succeeded) (it as APIRst.Success).data.data?.infoListObject?.infoList else null
        }
    }

    private suspend fun getHistoryInfoFromRemote(
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.getHistoryInfoFromRemote( page = currentPage
        ).let {
            if (it.succeeded) (it as APIRst.Success).data.data?.infoListObject?.infoList?.let { list ->
                insertInfoIntoRoom(*list.toTypedArray())
                currentPage += 1
                list
            } else null
        }
    }

     suspend fun deleteHistoryInfoFromRemote(
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        infoRepository.deleteHistoryInfoFromRemote(
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }




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