package com.puxiansheng.www.ui.project

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.bean.MessageItem
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.homeinfo.InfoMarqueeRepository
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.message.MessageDatabase
import com.puxiansheng.logic.data.message.MessageRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext

    private val imageRepository = ImageRepository()

    var cityId = ""
    var title = ""
    var currentPage = 1
    var currentCity = ""
    var industryIDs = ""
    var viewCount = ""
    var areaIDs = ""
    val mViewCount = MutableLiveData<String>()

    suspend fun requestHomeProject() =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO)  {
            imageRepository.requestProjectList(industryIDs,viewCount,currentPage,cityId).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.projects else null
            }
        }


}