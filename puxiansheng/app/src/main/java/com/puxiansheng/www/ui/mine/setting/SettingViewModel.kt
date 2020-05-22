package com.puxiansheng.www.ui.mine.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SettingViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())

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

    suspend fun submitUserInfo() =
        withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            userRepository.submitUserInfo(
                nickName,
                sex.toString(),
                actualName,
                iconImg,
                address,
                cityId.toString()
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
}
