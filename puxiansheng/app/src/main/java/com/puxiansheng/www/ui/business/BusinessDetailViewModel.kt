package com.puxiansheng.www.ui.business

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.business.BusinessDatabase
import com.puxiansheng.logic.data.business.BusinessRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusinessDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val businessRepository = BusinessRepository(BusinessDatabase.getInstance(context).businessDao())

    suspend fun requestInvestBusinessDetail(
        id: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        businessRepository.requestRemoteBusinessDetail(id = id).let { apiRst ->
            if (apiRst.succeeded) (apiRst as APIRst.Success).data.data else null
        }
    }



//    suspend fun favorite(
//        objectID: String,
//        type: Int
//    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
//        userRepository.favorite(
//            objectID = objectID,
//            type = type
//        ).let {
//            if (it.succeeded) (it as APIRst.Success).data else null
//        }
//    }
}