package com.puxiansheng.www.ui.mine.favor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.bean.Shop
import com.puxiansheng.logic.data.order.OrderDatabase
import com.puxiansheng.logic.data.order.OrderRepository
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.succeeded
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class FavoriteTransferOutOrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private var currentPage = 1

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getFavoriteTransferOutOrders()
    }

    fun refresh() {
        currentPage = 1
        viewModelScope.launch {
            deleteOrdersByType(type = Order.Type.TRANSFER_OUT_FAVORITE.value())
            delay(300)
            loadMore()
        }
    }

    fun getFavoriteTransferOutOrdersFromRoom() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_OUT_FAVORITE.value())

    private fun getFavoriteTransferOutOrders() {
        orderRepository.getFavoriteTransferOutOrdersFromRemote(page = currentPage).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.let { list ->
                    list.map {
                        Order(
                            orderType = Order.Type.TRANSFER_OUT_FAVORITE.value(),
                            shop = Shop(
                                shopID = it.shopID,
                                title = it.title,
                                size = it.size,
                                rent = it.rent,
                                fee = it.fee,
                                address = it.address,
                                industry = it.industry,
                                runningState = it.runningState,
                                includeFacilities = it.includeFacilities,
                                images = it.images,
                                floor = it.floor,
                                labels = it.labelList,
                                facilities = it.facilities,
                                environment = it.environment,
                                reason = it.reason,
                                transferType = Order.Type.TRANSFER_OUT_FAVORITE.value(),
                                //formatted data
                                formattedDate = it.day_time,
                                formattedPageViews = it.formattedPageViews,
                                formattedArea = it.area_point_str,
                                formattedRent = it.formattedRent,
                                formattedSize = it.formattedSize,
                                formattedFee = it.formattedTransferFee,
                                formattedLocationNodes = it.formattedLocationNodes,
                                formattedIndustry = it.formattedFinalIndustry
                            ),
                            state = it.state
                        )
                    }.let { orderList ->
                        orderRepository.insertOrders(*orderList.toTypedArray())
                    }
                }
                currentPage += 1
            } else null
        }
    }

    suspend fun deleteTransferOutOrderFromRemote(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.deleteTransferOutOrderFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }

    suspend fun deleteFavorTransferOutOrderFromRemote(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.deleteFavorTransferOutOrderFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }
}