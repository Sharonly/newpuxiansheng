package com.puxiansheng.www.ui.message

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.data.message.MessageDatabase
import com.puxiansheng.logic.data.message.MessageRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val messageRepository = MessageRepository(MessageDatabase.getInstance(context).messageDao())

    suspend fun getMessageDetailFromRemote(messageId:String) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        messageRepository.getMessageDetail(messageId = messageId).let {
            println(it)
            if (it.succeeded) (it as APIRst.Success).data else null
        }
    }

}