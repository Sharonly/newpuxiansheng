package com.puxiansheng.www.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.data.common.CommonDataRepository
import com.puxiansheng.logic.data.homeinfo.InfoMarqueeRepository
import com.puxiansheng.logic.data.image.ImageRepository
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private val imageRepository = ImageRepository()
    private val marqueeInfoRepository = InfoMarqueeRepository()
    private val commonDataRepository = CommonDataRepository()
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    var currentCity = ""
    private var currentPage = 1;

    suspend fun requestMenuImage() =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            imageRepository.requestMenuImages().let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.banners else null
            }
        }

    suspend fun requestBannerImage(where: String) =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO)  {
            imageRepository.requestRemoteImages(where).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.banners else null
            }
        }

    suspend fun requestMarqueeMessage(page: String,city:String) =
        withContext(viewModelScope.coroutineContext + Dispatchers.IO)  {
            marqueeInfoRepository.requestRemoteInfoMarquee(page,city).let {
                return@let if (it.succeeded) (it as APIRst.Success).data.data?.data?.infos else null
            }
        }


    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getHomeRecommendedTransferOutOrdersFromRemote(currentCity)
        currentPage += 1
    }

    fun refresh(city: String) {
        currentCity = city
        currentPage = 1
        deleteOrdersByType(type = Order.Type.TRANSFER_OUT.value())
        loadMore()
    }


    private suspend fun getHomeRecommendedTransferOutOrdersFromRemote(
        city: String?
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getHomeRecommendedTransferOutOrdersFromRemote(
            page = currentPage,
            city = city
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.map {
                    Order(
                        orderType = Order.Type.TRANSFER_OUT_RECOMMEND.value(),
                        shop = Shop(
                            shopID = it.shopID,
                            image = it.image,
                            title = it.title,
                            formattedSize = it.formattedSize,
                            formattedRent = it.formattedRent,
                            formattedArea = it.area_point_str,
                            formattedFinalIndustry = it.formattedFinalIndustry,
                            transferType = Order.Type.TRANSFER_OUT_RECOMMEND.value(),
                            formattedDate = it.day_time,
                            data_type = it.data_type,
                            jump_type = it.jump_type,
                            jump_view = it.jump_view,
                            jump_param = it.jump_param
                        )
                    )
                }?.let { orders ->
//                    return@withContext orders
//                    delay(500)
                    orderRepository.insertOrders(*orders.toTypedArray())
                }
            } else null
        }
    }



    fun getRecommendedTransferOutOrdersFromRoom() =
        orderRepository.getOrdersByTransferTypeFromRoomWithLimit(
            type = Order.Type.TRANSFER_OUT_RECOMMEND.value(),
            limit = 10
        )

    fun getRecommendedTransferInOrdersFromRoom() =
        orderRepository.getOrdersByTransferTypeFromRoomWithLimit(
            type = Order.Type.TRANSFER_IN_RECOMMEND.value(),
            limit = 10
        )
}