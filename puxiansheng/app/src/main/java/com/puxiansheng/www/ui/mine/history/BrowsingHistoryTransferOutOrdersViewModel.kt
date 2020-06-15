package com.puxiansheng.www.ui.mine.history

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

class BrowsingHistoryTransferOutOrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private var currentPage = 1
    private var allShopId = ""

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getTransferOutOrdersBrowsingHistory()
    }

    fun refresh() {
        currentPage = 1
        viewModelScope.launch {
            deleteOrdersByType(type = Order.Type.TRANSFER_OUT_HISTORY.value())
            delay(300)
            loadMore()
        }
    }

    fun getMineTransferOutOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_OUT_HISTORY.value())

    private fun getTransferOutOrdersBrowsingHistory() {
        orderRepository.getTransferOutOrdersBrowsingHistoryFromRemote(page = currentPage).let { apiRst ->
            if (apiRst.succeeded) {
                val str = StringBuilder()
                (apiRst as APIRst.Success).data.data?.data?.orders?.let { list ->
                    list.map {
                        str.append(it.shopID).append(",")
                        Order(
                            orderType = Order.Type.TRANSFER_OUT_HISTORY.value(),
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
                                transferType = Order.Type.TRANSFER_OUT_HISTORY.value(),
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
                        if(str.isNotEmpty())
                        allShopId = str.substring(0, str.lastIndex)
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

    suspend fun deleteHistoryTransferOutOrderFromRemote(
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.deleteAllHistoryTransferOutOrderFromRemote().let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }


}