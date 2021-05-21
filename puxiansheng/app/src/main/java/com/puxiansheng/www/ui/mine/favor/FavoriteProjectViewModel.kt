package com.puxiansheng.www.ui.mine.favor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.bean.http.ProjectDetailObject
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.logic.data.project.ProjectRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class FavoriteProjectViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val projectRepository = ProjectRepository()
    private var currentPage = 1



    suspend fun getFavoriteProject(page:Int) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        projectRepository.getFavorProjectListFromRemote(page = page).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.projects
            } else null
        }
    }

}