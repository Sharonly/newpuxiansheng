package com.puxiansheng.www.ui.release

import androidx.lifecycle.ViewModel
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReleaseViewModel : ViewModel() {

    private val imageRepository = ImageRepository()
    var currentCity = ""
    private var currentPage = 1;

    suspend fun requestBannerImage(where: String) =
        withContext(Dispatchers.IO) {
            imageRepository.requestRemoteImages(where).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.banners else null
            }
        }



}