package com.puxiansheng.www.ui.info

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.info.InfoDatabase
import com.puxiansheng.logic.data.info.InfoRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InfoDetailViewModel(application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    private val infoRepository = InfoRepository(InfoDatabase.getInstance(context).infoDao())


    suspend fun getInfoDetail(
        id: String,city_id:String
    ) = withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
        infoRepository.getInfoDetail(
            id = id,
            city_id = city_id
        ).let {
//            if (it.succeeded) (it as APIRst.Success).data.data?.infoListObject?.infoList else null
            if (it.succeeded) (it as APIRst.Success).data.data?.let { list ->
                println(list)
                list
            } else null
        }
    }

}