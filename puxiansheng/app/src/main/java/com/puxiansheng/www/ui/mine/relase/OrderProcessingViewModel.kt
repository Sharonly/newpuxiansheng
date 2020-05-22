package com.puxiansheng.www.ui.mine.relase

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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderProcessingViewModel (application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private var currentPage = 1

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getRemoteUserProcessingOrders()
    }

    fun refresh() {
        currentPage = 1
        viewModelScope.launch {
            deleteOrdersByType(type = Order.Type.TRANSFER_IN_PRIVATE.value())
            //delay(300)
            loadMore()
        }
    }

    fun getMineTransferInOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_IN_PRIVATE.value())

    private fun getRemoteUserProcessingOrders() {
        orderRepository.getUserProcessingOrder().let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.let { list ->
                    list.map {
                        Order(
                            orderType = Order.Type.TRANSFER_IN_PRIVATE.value(),
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
                                transferType = Order.Type.TRANSFER_IN_PRIVATE.value(),
                                //formatted data
                                formattedDate = it.formattedDate,
                                formattedSize = it.view_acreage_un_prefix,
                                formattedRent = it.view_rent_un_prefix,
                                formattedFee = it.formattedTransferFee,
                                formattedFinalIndustry = it.formattedFinalIndustry,
                                formattedFinalLocationNode = it.formattedFinalLocationNode
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

    suspend fun deleteTransferInOrderFromRemote(
        shopID: String
    ) = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.deleteTransferInOrderFromRemote(shopID = shopID).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data
            }else{
                null
            }
        }
    }
}