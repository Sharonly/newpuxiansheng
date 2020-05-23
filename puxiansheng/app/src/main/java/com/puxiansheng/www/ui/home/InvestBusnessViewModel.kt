package com.puxiansheng.www.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.logic.data.business.BusinessDatabase
import com.puxiansheng.logic.data.business.BusinessRepository
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InvestBusnessViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val imageRepository = ImageRepository()
    private val businessRepository = BusinessRepository(BusinessDatabase.getInstance(context).businessDao())
    var currentPage = 0

    suspend fun requestBannerImage(where: String) =
        withContext(Dispatchers.IO) {
            imageRepository.requestRemoteImages(where).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.banners else null
            }
        }


    private fun deleteBusinessByType(
    ) = viewModelScope.launch(Dispatchers.IO) {
        businessRepository.deleteBusinessFromRoom()
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        requestBusinessList()
    }

    fun refresh() {
        currentPage = 1
        deleteBusinessByType()
        loadMore()
    }

    fun getBusinesssFromLocal() =
        businessRepository.getBusinessFromRoom()

    suspend fun requestBusinessList()= withContext(Dispatchers.IO) {
        businessRepository.requestRemoteBusiness(currentPage).let {
//            return@let if (it.succeeded) (it as APIRst.Success).data.data?.business else null
            if (it.succeeded) (it as APIRst.Success).data.data?.business?.let { list ->
                insertBusinessIntoRoom(*list.toTypedArray())
                currentPage += 1
                list
            } else null
        }
    }

    private suspend fun insertBusinessIntoRoom(
        vararg info: BusinessBean
    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        businessRepository.insertBusinessInfo(
            business = *info
        )
    }

}