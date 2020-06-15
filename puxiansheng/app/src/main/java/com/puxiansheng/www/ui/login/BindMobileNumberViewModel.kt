package com.puxiansheng.www.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.bean.http.HttpRespBindMobilePhone
import com.puxiansheng.logic.data.system.SystemDatabase
import com.puxiansheng.logic.data.system.SystemRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.json.JSONObject

class BindMobileNumberViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository =
        UserRepository(UserDatabase.getInstance(context).userDao())
    private val systemRepository =
        SystemRepository(SystemDatabase.getInstance(context).systemConfigDao())

    var userAccount: String = ""
    var verificationCode: String = ""
    var requestType: String = "login"
    var key: String = ""
    var imageCode: String = ""

    val toastMsg = MutableLiveData<String>()



    suspend fun requestVerificationCode(

    ) = viewModelScope.async(Dispatchers.IO) {
        systemRepository.requestVerificationCode(
            phoneNumber = userAccount,
            key = key,
            code = imageCode,
            type = requestType
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).let { apiResp ->
                    apiResp.data.body?.string()?.let { body ->
                        println(body)
                        toastMsg.postValue(JSONObject(body).optString("msg"))
                        when (JSONObject(body).optInt("code", 0)) {
                            API.CODE_SUCCESS -> {
                                return@async  API.CODE_SUCCESS
                            }
                            else -> {
                                return@async 0
                            }
                        }
                    }
                }
            } else 0
        }
    }.await()

    suspend fun getImageCodeFromRemote(

    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        systemRepository.requestImageCode().let {
            if (it.succeeded) {
                (it as APIRst.Success).data.data?.imageCode?.let { imageCode ->
                    key = imageCode.key ?: ""
                    imageCode
                }
            } else null
        }
    }
}