package com.puxiansheng.www.ui.mine

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MineViewModel (application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    private val imageRepository = ImageRepository()
    private val commonDataRepository = CommonDataRepository()

    suspend fun getUserInformationFromRemote() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.requireRemoteUserInfo().let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.user else null
        }
    }

    suspend fun requestBannerImage(where: String) =
        withContext(Dispatchers.IO) {
            imageRepository.requestRemoteImage(where).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.bannerImg else null
            }
        }


    suspend fun getReleaseCount() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.releaseCount().let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data else null
        }
    }

    suspend fun getServiceLink() = withContext(Dispatchers.IO) {
        commonDataRepository.getServiceLinkFromRemote().let {
            return@let if (it.succeeded) (it as APIRst.Success).data.data?.link else null
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