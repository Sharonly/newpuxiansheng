package com.puxiansheng.www.ui.search

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
import kotlinx.coroutines.withContext

class SearchViewModel (application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    var toastMsg = MutableLiveData<String>()
    val deleteResult = MutableLiveData<Int>()
    var searchTitle = ""

    var type = 0
    var usrId =SharedPreferencesUtil.get(API.LOGIN_USER_ID,0).toString()

    suspend fun getHistorySearch() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.requireHistorySearch(type,usrId).let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.searchObjects else null
        }
    }

     fun deleteHistorySearch() = viewModelScope.launch(Dispatchers.IO) {
        userRepository.deleteHistorySearch().let { apiRst ->
//            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data else null
            if (apiRst.succeeded) {
                deleteResult.postValue((apiRst as APIRst.Success).data.code)
                toastMsg.postValue((apiRst as APIRst.Success).data.msg)
            } else {
                toastMsg.postValue((apiRst as APIRst.Error).exception.message)
            }
        }
    }

    suspend fun getRecommendSearch() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.requireRecommendSearch().let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.searchObjects else null
        }
    }


}