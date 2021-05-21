package com.puxiansheng.www.ui.project

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.homeinfo.InfoMarqueeRepository
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.message.MessageDatabase
import com.puxiansheng.logic.data.message.MessageRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectApprovelViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())

    var name = ""
    var phone = ""
    var money = ""
    var projectId = ""



    suspend fun submitProve(
    ) = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            userRepository.submitProjectInfo(projectId, phone, name, money).let {
                    if (it.succeeded) (it as APIRst.Success).data else null
                }
    }


}