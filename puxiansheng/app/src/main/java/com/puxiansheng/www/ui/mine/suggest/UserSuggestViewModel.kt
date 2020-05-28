package com.puxiansheng.www.ui.mine.suggest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.user.UserDatabase
import com.puxiansheng.logic.data.user.UserRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserSuggestViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    val suggestResult = MutableLiveData<Int>()
    var suggesttion = ""

    var toastMsg = MutableLiveData<String>()

    suspend  fun getRequestType() = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        userRepository.getRequestType().let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data?.list else null
        }
    }

    fun submitSuggest() = viewModelScope.launch(Dispatchers.IO) {
        userRepository.submitSuggestion(
            content = suggesttion
        ).let {
            if (it.succeeded) {
                suggestResult.postValue((it as APIRst.Success).data.code)
                toastMsg.postValue((it as APIRst.Success).data.msg)
            } else {
                toastMsg.postValue((it as APIRst.Error).exception.message)
            }
        }
    }


}
