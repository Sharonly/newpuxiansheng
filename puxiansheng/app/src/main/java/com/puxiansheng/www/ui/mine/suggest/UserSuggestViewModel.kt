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

class UserSuggestViewModel(application: Application) : AndroidViewModel(application){
    val context = getApplication<Application>().applicationContext
    private val userRepository = UserRepository(UserDatabase.getInstance(context).userDao())
    val suggestResult = MutableLiveData<Int>()
    var suggesttion = ""

    var toastMsg = MutableLiveData<String>()

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
