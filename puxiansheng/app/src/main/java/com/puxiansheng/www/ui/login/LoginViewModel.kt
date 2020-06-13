package com.puxiansheng.www.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.BuildConfig
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.bean.http.HttpRespBindMobilePhone
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.system.SystemDatabase
import com.puxiansheng.logic.data.system.SystemRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.printJson
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.*
import okhttp3.Response
import org.json.JSONObject


@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository((UserDatabase.getInstance(context).userDao()))
    private val systemRepository =
        SystemRepository(SystemDatabase.getInstance(context).systemConfigDao())
    private val commonDataRepository = CommonDataRepository()

    companion object {
        const val MODE_LOGIN_WITH_PASSWORD = 0
        const val MODE_LOGIN_WITH_CODE = 1
        const val MODE_LOGIN_WITH_WECHAT = 2
        const val MODE_RESET_PASSWORD = 3
        const val MODE_FORGET_PASSWORD = 5
        const val MODE_REGISTER = 4
    }


    var userAccount: String = ""
    var userPassword: String = ""
    var newPassword: String = ""
    var newPasswordAgain: String = ""
    var verificationCode: String = ""
    var wechatLoginCode: String = ""

    var key: String = ""
    var imageCode: String = ""
    var requestType: String = "login"

    var err: String = ""

    val toastMsg = MutableLiveData<String>()
    val loginMode = MutableLiveData<Int>()
    var countDown: MutableLiveData<Int> = MutableLiveData()
    val wechatCode = MutableLiveData<String>()

    fun startCountDown() = viewModelScope.launch(Dispatchers.IO) {
        (60 downTo 0).forEach {
            delay(1000)
            countDown.postValue(it)
        }
    }

    suspend fun loginByType(loginType: Int) = viewModelScope.async(Dispatchers.IO) {
        when (loginType) {
            MODE_LOGIN_WITH_PASSWORD -> {
                userRepository.loginByPass(userAccount = userAccount, userPassword = userPassword)
                    .let { apiRst -> dealLoginDate(apiRst) }
            }

            MODE_LOGIN_WITH_CODE -> {
                userRepository.loginByPhoneNum(
                    userAccount = userAccount,
                    verificationCode = verificationCode
                )
                    .let { apiRst -> dealLoginDate(apiRst) }
            }
            MODE_LOGIN_WITH_WECHAT -> {
                userRepository.loginByWeChat(wechatCode = wechatLoginCode)
                    .let { apiRst -> dealLoginDate(apiRst) }
            }
            MODE_REGISTER -> {
                userRepository.register(
                    userAccount = userAccount,
                    verificationCode = verificationCode
                )
                    .let { apiRst -> dealLoginDate(apiRst) }
            }

            MODE_FORGET_PASSWORD -> {
                userRepository.forgetPassword(
                    userAccount = userAccount,
                    verificationCode = verificationCode,
                    password = newPassword,
                    newPassword = newPasswordAgain
                )
                    .let { apiRst -> dealLoginDate(apiRst) }
            }

            MODE_RESET_PASSWORD -> {
//                userRepository.resetPassword(userAccount = userAccount, verificationCode = verificationCode)
//                    .let { apiRst -> dealLoginDate(apiRst) }

            }
            else -> {


            }
        }

    }.await()


//    suspend fun register() = viewModelScope.async(Dispatchers.IO) {
//        userRepository.register(userAccount = userAccount, verificationCode = verificationCode)
//            .let { apiRst ->
//                if (apiRst.succeeded) {
//                    if (com.puxiansheng.util.BuildConfig.DEBUG) println("apiRst.succeeded === ")
//                    (apiRst as APIRst.Success).let { apiResp ->
//                        apiResp.data.body?.string()?.let { body ->
//                            if (com.puxiansheng.util.BuildConfig.DEBUG) printJson("ResponseBody === $body")
//                            toastMsg.postValue(JSONObject(body).optString("msg"))
//                            when (JSONObject(body).optInt("code", 0)) {
//                                API.CODE_SUCCESS -> {
//                                    body.let { json ->
//                                        APIResp.fromJson<User>(json).data?.let { user ->
//                                            user.loginTimestamp = System.currentTimeMillis()
//                                            user.loginState = 1
//                                            userRepository.insertUser(user)
//                                            return@async user
//                                        }
//                                    }
//                                }
//                                API.CODE_BAND_MOBILE_NUMBER -> {
//                                    body.let { json ->
//                                        HttpRespBindMobilePhone.fromJson(json).let { bindMobile ->
//                                            return@async bindMobile
//                                        }
//                                    }
//                                }
//                                else -> return@async null
//                            }
//                        }
//                    }
//                } else {
//                    (apiRst as APIRst.Error).let {
//                        if (com.puxiansheng.util.BuildConfig.DEBUG) println("apiRst.Error = $apiRst")
//                        toastMsg.postValue(it.exception.message)
//                    }
//                    return@async null
//                }
//            }
//    }


    suspend fun getConfigInfo(name:String) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        commonDataRepository.getConfigUrlRemote(name = name).let {
            if (it.succeeded) (it as APIRst.Success).data?.data?.urls else null
        }
    }

    suspend fun dealLoginDate(apiRst: APIRst<Response>) = viewModelScope.async(Dispatchers.IO) {
        if (apiRst.succeeded) {
            Log.d("--login","apiRst.succeeded ")
            if (com.puxiansheng.util.BuildConfig.DEBUG) println("apiRst.succeeded === ")
            (apiRst as APIRst.Success).let { apiResp ->
                apiResp.data.body?.string()?.let { body ->
                    if (com.puxiansheng.util.BuildConfig.DEBUG) printJson("login ResponseBody === $body")
                    toastMsg.postValue(JSONObject(body).optString("msg"))
                    when (JSONObject(body).optInt("code", 0)) {
                        API.CODE_SUCCESS -> {
                            body.let { json ->
                                APIResp.fromJson<User>(json).data?.let { user ->
                                    user.loginTimestamp = System.currentTimeMillis()
                                    user.loginState = 1
                                    userRepository.insertUser(user)
                                    return@async user
                                }
                            }
                        }
                        API.CODE_BAND_MOBILE_NUMBER -> {
                            body.let { json ->
                                HttpRespBindMobilePhone.fromJson(json).let { bindMobile ->
                                    return@async bindMobile
                                }
                            }
                        }
                        else -> return@async null
                    }
                }
            }
        } else {
            (apiRst as APIRst.Error).let {
                if (com.puxiansheng.util.BuildConfig.DEBUG) println("apiRst.Error = $apiRst")
                toastMsg.postValue(it.exception.message)
            }
            return@async null
        }
    }.await()


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
                                return@async API.CODE_SUCCESS
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

    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
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