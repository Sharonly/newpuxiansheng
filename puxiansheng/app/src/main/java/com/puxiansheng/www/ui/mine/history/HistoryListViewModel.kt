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
import java.lang.StringBuilder

class HistoryListViewModel(application: Application) : AndroidViewModel(application){
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())
    private var currentPage = 1

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore(type: Int) = viewModelScope.launch(Dispatchers.IO) {
        when(type){
            Order.Type.TRANSFER_OUT_HISTORY.value() ->    getTransferOutOrdersBrowsingHistory()
            Order.Type.TRANSFER_IN_HISTORY.value() -> getTransferInOrdersBrowsingHistory()
        }

    }

    fun refresh(type: Int) {
        currentPage = 1
        viewModelScope.launch {
            deleteOrdersByType(type)
            delay(300)
            loadMore(type)
        }
    }

    fun getMineTransferOutOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_OUT_HISTORY.value())

    fun getMineTransferInOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_IN_HISTORY.value())

    private fun getTransferInOrdersBrowsingHistory() {
        orderRepository.getTransferInOrdersBrowsingHistoryFromRemote(page = currentPage).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.let { list ->
                    list.map {
                        Order(
                            orderType = Order.Type.TRANSFER_IN_HISTORY.value(),
                            shop = Shop(
                                shopID = it.shopID,
                                title = it.title,
                                size = it.size,
                                rent = it.rent,
                                fee = it.fee,
                                address = it.address,
                                industry = it.industry,
                                runningState = it.runningState,
//                                includeFacilities = it.includeFacilities,
                                images = it.images,
                                floor = it.floor,
                                labels = it.labelList,
                                facilities = it.facilities,
                                environment = it.environment,
                                reason = it.reason,
                                transferType = Order.Type.TRANSFER_IN_HISTORY.value(),
                                //formatted data
                                formattedDate = it.formattedDate,
                                formattedPageViews = it.formattedPageViews,
                                formattedRent = it.formattedRent,
                                formattedSize = it.formattedSize,
                                formattedFee = it.formattedTransferFee,
                                formattedLocationNodes = it.formattedLocationNodes,
                                formattedFinalLocationNode = it.formattedFinalLocationNode,
                                formattedFinalIndustry = it.formattedFinalIndustry,
                                formattedIndustry = it.formattedIndustry?.let { strList ->
                                    val sb = StringBuilder()
                                    strList.forEach { str ->
                                        sb.append(str)
                                        sb.append(" ")
                                    }
                                    sb.toString()
                                } ?: ""
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


    private fun getTransferOutOrdersBrowsingHistory() {
        orderRepository.getTransferOutOrdersBrowsingHistoryFromRemote(page = currentPage).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.let { list ->
                    list.map {
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
//                                includeFacilities = it.includeFacilities,
                                images = it.images,
                                floor = it.floor,
                                labels = it.labelList,
                                facilities = it.facilities,
                                environment = it.environment,
                                reason = it.reason,
                                transferType = Order.Type.TRANSFER_OUT_HISTORY.value(),
                                //formatted data
                                formattedDate = it.formattedDate,
                                formattedPageViews = it.formattedPageViews,
                                formattedRent = it.formattedRent,
                                formattedSize = it.formattedSize,
                                formattedFee = it.formattedTransferFee,
                                formattedLocationNodes = it.formattedLocationNodes,
                                formattedIndustry = it.formattedIndustry?.let { strList ->
                                    val sb = StringBuilder()
                                    strList.forEach { str ->
                                        sb.append(str)
                                        sb.append(" ")
                                    }
                                    sb.toString()
                                } ?: ""
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
}