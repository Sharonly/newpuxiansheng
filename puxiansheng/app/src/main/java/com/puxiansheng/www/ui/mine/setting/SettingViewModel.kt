package com.puxiansheng.www.ui.mine.setting

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.order.UploadImageWorker
import com.puxiansheng.logic.data.user.UploadIconWorker
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class SettingViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    private val commonDataRepository = CommonDataRepository()

    /*suspend fun logout(user: User) = viewModelScope.launch(Dispatchers.IO) {
        //userRepository.insertUser(user)

    }*/

    var nickName = ""
    var actualName = ""
    var sex = 0
    var contactPhone: String? = null
    var address: String? = null
    var cityId = 0
    var iconImg: String? = null
    val toastMsg = MutableLiveData<String>()
    val currentUser = MutableLiveData<User>()
    var configTitle = ""


    suspend fun getUserInformationFromRemote() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.requireRemoteUserInfo().let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.user else null
        }
    }


    suspend fun submitUserIcon(iconImg: String) =
        withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            val file = File(iconImg)
            userRepository.submitUserIcon(file)
                .let {
                    if (it.succeeded) (it as APIRst.Success).data else null
                }
        }

    fun uploadIcon(imageUrl:String) =viewModelScope.launch(Dispatchers.IO) {
        OneTimeWorkRequest.Builder(UploadIconWorker::class.java).setInputData(
            Data.Builder()
                .putString(UploadIconWorker.ImagePath, imageUrl)
                .putString(UploadIconWorker.TOKEN, API.currentSignatureToken)
                .build()
        ).build().let {
            WorkManager.getInstance(context).enqueue(it)
        }
    }


    suspend fun submitUserInfo() =
        withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            userRepository.submitUserInfo(
                nickName,
                sex.toString(),
                actualName,
                iconImg,
                address,
                cityId
            )
                .let {
                    if (it.succeeded) (it as APIRst.Success).data else null
                }
        }

    suspend fun logout() = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        userRepository.logout().let {
            if (it.succeeded) (it as APIRst.Success).data else null
        }
    }

    suspend fun getConfigInfo(name:String) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        commonDataRepository.getConfigUrlRemote(name = name).let {
            if (it.succeeded) (it as APIRst.Success).data?.data?.urls else null
        }
    }

}
