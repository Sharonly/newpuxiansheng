package com.puxiansheng.www.ui.order

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

class TransferOutOrdersViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>().applicationContext
    private val orderRepository = OrderRepository(OrderDatabase.getInstance(context).getOrderDao())

    private var currentPage = 1
    var currentCity = ""
    var industryIDs = ""
    var sizeRangeID = ""
    var areaIDs = ""
    var rentIDs = ""
    var sortBy = ""
    var sortType = ""
    var title = ""
    var type = 0

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        if (type == 1) {
            getTransferSuccessFromRemote()
        } else {
            getTransferOutOrdersFromRemote()
        }
        currentPage += 1
    }

    fun refresh(city: String) {
        currentCity = city
        currentPage = 1
        deleteOrdersByType(type = Order.Type.TRANSFER_OUT.value())
        loadMore()
    }

    fun getTransferOutOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_OUT.value())

    private fun getTransferOutOrdersFromRemote() {
        orderRepository.getTransferOutOrdersFromRemote(
            title = title,
            page = currentPage,
            industry = industryIDs,
            size = sizeRangeID,
            area = areaIDs,
            sortBy = sortBy,
            sortType = sortType,
            hot = null,
            top = null,
            recommend = null,
            city = currentCity
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.let {
                    it.map { shop ->
                        Order(
                            state = shop.state,
                            shop = Shop(
                                shopID = shop.shopID,
                                title = shop.title,
                                size = shop.size,
                                rent = shop.rent,
                                fee = shop.fee,
                                address = shop.address,
                                industry = shop.industry,
                                runningState = shop.runningState,
//                                includeFacilities = shop.includeFacilities,
                                isTop = shop.isTop,
                                isHot = shop.isHot,
                                isRecommend = shop.isRecommend,
                                isLargeOrder = shop.isLargeOrder,
                                image = shop.image,
                                images = shop.images,
                                floor = shop.floor,
                                labels = shop.labelList,
                                facilities = shop.facilities,
                                description = shop.description,
                                descriptionUrl = shop.descriptionUrl,
                                environment = shop.environment,
                                reason = shop.reason,
                                transferType = Order.Type.TRANSFER_OUT.value(),
                                formattedArea = shop.area_point_str,
                                formattedDate = shop.formattedDate,
                                formattedSize = shop.formattedSize,
                                formattedRent = shop.formattedRent,
                                formattedFee = shop.formattedTransferFee,
                                formattedFinalIndustry = shop.formattedFinalIndustry,
                                formattedFinalLocationNode = shop.formattedFinalLocationNode
                            )
                        )
                    }.let { orderList ->
                        orderRepository.insertOrders(*orderList.toTypedArray())
                    }
                }
            }
        }
    }


    private fun getTransferSuccessFromRemote() {
        orderRepository.getTransferSuccessFromRemote(
            page = currentPage,
            industry = industryIDs,
            size = sizeRangeID,
            area = areaIDs,
            sortBy = sortBy,
            sortType = sortType,
            hot = null,
            top = null,
            recommend = null,
            city = currentCity
        ).let { apiRst ->
            if (apiRst.succeeded) {
                (apiRst as APIRst.Success).data.data?.data?.orders?.let {
                    it.map { shop ->
                        Order(
                            state = shop.state,
                            shop = Shop(
                                shopID = shop.shopID,
                                title = shop.title,
                                size = shop.size,
                                rent = shop.rent,
                                fee = shop.fee,
                                address = shop.address,
                                industry = shop.industry,
                                runningState = shop.runningState,
                                includeFacilities = shop.includeFacilities,
                                image = shop.image,
                                images = shop.images,
                                floor = shop.floor,
                                labels = shop.labelList,
                                facilities = shop.facilities,
                                description = shop.description,
                                descriptionUrl = shop.descriptionUrl,
                                environment = shop.environment,
                                reason = shop.reason,
                                transferType = Order.Type.TRANSFER_OUT.value(),

                                formattedDate = shop.formattedDate,
                                formattedSize = shop.formattedSize,
                                formattedRent = shop.formattedRent,
                                formattedFee = shop.formattedTransferFee,
                                formattedFinalIndustry = shop.formattedFinalIndustry,
                                formattedFinalLocationNode = shop.formattedFinalLocationNode
                            )
                        )
                    }.let { orderList ->
                        orderRepository.insertOrders(*orderList.toTypedArray())
                    }
                }
            }
        }
    }
}