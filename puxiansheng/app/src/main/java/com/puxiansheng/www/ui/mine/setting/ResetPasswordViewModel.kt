package com.puxiansheng.www.ui.mine.setting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResetPasswordViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    val resetResult = MutableLiveData<Int>()

    var userId = SharedPreferencesUtil.get(API.LOGIN_USER_ID,0).toString()
    var originalPassword = ""
    var newPassword = ""
    var newSecondPassword = ""

    var toastMsg = MutableLiveData<String>()

    fun resetPassword() = viewModelScope.launch(Dispatchers.IO) {
        userRepository.resetPassword(
            userId = userId,
            password = originalPassword,
            newPassword = newPassword,
            newPasswordAgain = newSecondPassword
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