package com.puxiansheng.www.ui.mine

import android.app.Application
import androidx.lifecycle.*
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

    suspend fun requestBannerImage(where: String) =
        withContext(Dispatchers.IO) {
            imageRepository.requestRemoteImages(where).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.banners else null
            }
        }

    suspend fun getReleaseCount() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.releaseCount().let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.data else null
        }
    }
}