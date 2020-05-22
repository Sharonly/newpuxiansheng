package com.puxiansheng.uio.system

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResetPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    val resetResult = MutableLiveData<Int>()

    var originalPassword = ""
    var newPassword = ""

    var toastMsg = MutableLiveData<String>()

    fun resetPassword() = viewModelScope.launch(Dispatchers.IO) {
        userRepository.resetPassword(
            originalPassword = originalPassword,
            newPassword = newPassword
        ).let {
            if (it.succeeded) {
                resetResult.postValue((it as APIRst.Success).data.code)
                toastMsg.postValue((it as APIRst.Success).data.msg)
            } else {
                toastMsg.postValue((it as APIRst.Error).exception.message)
            }
        }
    }

}