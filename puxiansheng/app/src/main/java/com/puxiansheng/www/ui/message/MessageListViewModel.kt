package com.puxiansheng.www.ui.message

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.logic.data.message.MessageDatabase
import com.puxiansheng.logic.data.message.MessageRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MessageListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val messageRepository =
        MessageRepository(MessageDatabase.getInstance(context).messageDao())
    var cityId = ""
    var currentPage = 1
    var title = ""

    private fun deleteMessageByCategory(
        category: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        messageRepository.deleteInfoByCategoryFromRoom(category = category)
    }


    fun loadMore(category: Int, city: String? = null) = viewModelScope.launch(Dispatchers.IO) {
        getMessageByCategoryFromRemote(
            category = category,
            city = city
        )
    }


    fun refresh(
        category: Int
    ) {
        currentPage = 1
        viewModelScope.launch {
            deleteMessageByCategory(category = category)
            delay(300)
            loadMore(category = category)
        }
    }



    private suspend fun getMessageByCategoryFromRemote(
        category: Int,
        city: String? = null, title: String? = null
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        messageRepository.getInfoByCategoryFromRemote(
            category = category,
            page = currentPage,
            city = city, title = title
        ).let {
            if (it.succeeded) (it as APIRst.Success).data.data?.infoList?.let { list ->
                list.map { item ->
                    item.category = category
                }
                insertInfoIntoRoom(*list.toTypedArray())
                currentPage += 1
                list
            } else null
        }
    }


    suspend fun getMessageListByCategory(category: Int) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            messageRepository.getInfoByCategoryFromRemote(
                category = category,
                page = currentPage,
                city = cityId
            ).let {
                if (it.succeeded) (it as APIRst.Success).data.data?.infoList else null
            }
        }

    suspend fun getMessageByCategoryFromRoom(
        category: Int
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        messageRepository.getMessageByCategoryFromRoom(
            category = category
        ).let {
            it
        }
    }


    private suspend fun insertInfoIntoRoom(
        vararg info: MessageItem
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        messageRepository.insertInfoIntoRoom(
            info = *info
        )
    }

    suspend fun getMessageCategoriesFromRemote() = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        messageRepository.getMessageCategoriesFromRemote().let {
            println(it)
            if (it.succeeded) (it as APIRst.Success).data.data?.categories else null
        }
    }

}