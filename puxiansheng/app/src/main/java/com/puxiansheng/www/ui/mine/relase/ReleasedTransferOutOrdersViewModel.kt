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
import java.lang.StringBuilder

class ReleasedTransferOutOrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private var currentPage = 1
    private var shouldLoad = true

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getRemoteMineTransferOutOrders()
    }

    fun refresh() {
        currentPage = 1
        viewModelScope.launch {
            deleteOrdersByType(type = Order.Type.TRANSFER_OUT_PRIVATE.value())
            //delay(300)
            loadMore()
        }
    }

    fun getMineTransferOutOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_OUT_PRIVATE.value())

    suspend  fun getRemoteMineTransferOutOrders(currentPage:Int)= withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
        orderRepository.getMineTransferOutOrdersFromRemote(page = currentPage).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders
            } else null
        }
    }

    private fun getRemoteMineTransferOutOrders() {
        orderRepository.getMineTransferOutOrdersFromRemote(page = currentPage).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.let { list ->
                    list.map {
                        Order(
                            orderType = Order.Type.TRANSFER_OUT_PRIVATE.value(),
                            shop = Shop(
                                shopID = it.shopID,
                                title = it.title,
                                size = it.size,
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
                                transferType = Order.Type.TRANSFER_OUT_PRIVATE.value(),
                                //formatted data
                                formattedDate = it.formattedDate,
                                formattedPageViews = it.formattedPageViews,
                                formattedRent = it.formattedRent,
                                formattedSize = it.formattedSize,
                                formattedFee = it.formattedTransferFee,
                                formattedArea = it.formattedFinalLocationNode,
                                formattedFinalIndustry = it.formattedFinalIndustry,
                                formattedLocationNodes = it.formattedLocationNodes,
                                formattedIndustry = it.view_category,
                                data_type = it.data_type,
                                jump_type = it.jump_type,
                                jump_view = it.jump_view,
                                jump_param = it.jump_param
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
}