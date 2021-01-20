package com.puxiansheng.www.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.logic.data.system.SystemDatabase
import com.puxiansheng.logic.data.system.SystemRepository
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.*
import org.json.JSONObject

class FastReleaseViewModel (application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val systemRepository =
        SystemRepository(SystemDatabase.getInstance(context).systemConfigDao())
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private val imageRepository = ImageRepository()

    var phone = ""
    var city = ""
    var code = ""

    var err: String = ""
    var key: String = ""
    var imageCode: String = ""
    var phoneNum = ""

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

    suspend fun requestVerificationCode(
    ) = viewModelScope.async(Dispatchers.IO) {
        systemRepository.requestVerificationCode(
            phoneNumber = phoneNum,
            key = key,
            code = imageCode,
            type = "quick_submit"
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


    suspend fun submitSimpleTransferInOrder(

    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        orderRepository.submitSimpleTransferInOrder(
            phone = phone,
            area = city,
            code = code
        ).let {
            it
        }
    }


    suspend fun submitSimpleTransferOutOrder(

    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        orderRepository.submitSimpleTransferOutOrder(
            phone = phone,
            area = city,
            code = code
        ).let {
            it
        }
    }
}