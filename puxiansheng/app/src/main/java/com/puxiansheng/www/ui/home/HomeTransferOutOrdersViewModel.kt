package com.puxiansheng.www.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.ext.SharedPreferencesUtil
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeTransferOutOrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private var currentPage = 1;
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    var currentCity = SharedPreferencesUtil.get(API.USER_CITY_ID, 0).toString()

     fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getHomeRecommendedTransferOutOrdersFromRemote(currentCity)
        currentPage += 1
    }

    fun refresh(city: String) {
        deleteOrdersByType(type = Order.Type.TRANSFER_OUT_RECOMMEND.value())
        currentCity = city
        currentPage = 1
        loadMore()
    }

    fun getTransferOutOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_OUT_RECOMMEND.value())


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
                            jump_param = it.jump_param,
                            articles = it.articles
                        )

                    )
                }?.let { orders ->
//                    return@withContext orders
//                    delay(800)
                    orderRepository.insertOrders(*orders.toTypedArray())
                }
            } else null
        }
    }
}