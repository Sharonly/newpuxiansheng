package com.puxiansheng.www.ui.release.fasttransfer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SimpleOrderViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private val imageRepository = ImageRepository()

    var phone = ""
    var city = ""

    suspend fun requestMyFastTransferCount() =
        withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            orderRepository.getFastMineTransferFromRemote()
                .let { return@let if (it.succeeded) (it as APIRst.Success).data.data?.data else null }
        }


    suspend fun requestFastNum() =
        withContext(context = viewModelScope.coroutineContext + Dispatchers.IO) {
            orderRepository.getFastTransferCount()
                .let { return@let if (it.succeeded) (it as APIRst.Success).data.data?.result else null }
        }

    suspend fun submitSimpleTransferInOrder(

    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        orderRepository.submitSimpleTransferInOrder(
            phone = phone,
            area = city
        ).let {
            it
        }
    }


    suspend fun requestBannerImage(where: String) =
        withContext(Dispatchers.IO) {
            imageRepository.requestRemoteImages(where).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.banners else null
            }
        }

    suspend fun submitSimpleTransferOutOrder(

    ) = withContext(
        context = viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        orderRepository.submitSimpleTransferOutOrder(
            phone = phone,
            area = city
        ).let {
            it
        }
    }
}