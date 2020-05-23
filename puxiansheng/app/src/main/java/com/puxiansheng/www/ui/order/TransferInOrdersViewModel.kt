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

class TransferInOrdersViewModel(application: Application) : AndroidViewModel(application) {
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

    private fun deleteOrdersByType(
        type: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        orderRepository.deleteOrdersByTypeFromRoom(type)
    }

    fun loadMore() = viewModelScope.launch(Dispatchers.IO) {
        getTransferInOrdersFromRemote()
    }

    fun refresh(city: String) {
        currentCity = city
        currentPage = 1
        deleteOrdersByType(type = Order.Type.TRANSFER_IN.value())
        loadMore()
    }

    fun getTransferInOrdersFromLocal() =
        orderRepository.getOrdersByTypeFromRoom(Order.Type.TRANSFER_IN.value())

    private fun getTransferInOrdersFromRemote() {
        orderRepository.getTransferInOrdersFromRemote(
            title = title,
            page = currentPage,
            industry = industryIDs,
            size = sizeRangeID,
            area = areaIDs,
            rent = rentIDs,
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
                                image = shop.image,
                                images = shop.images,
                                floor = shop.floor,
                                labels = shop.labelList,
                                facilities = shop.facilities,
                                description = shop.description,
                                descriptionUrl = shop.descriptionUrl,
                                environment = shop.environment,
                                reason = shop.reason,
                                transferType = Order.Type.TRANSFER_IN.value(),
                                formattedArea = shop.area_point_str,
                                formattedDate = shop.formattedDate,
                                formattedSize = shop.view_acreage_un_prefix,
                                formattedRent = shop.view_rent_un_prefix,
                                formattedFee = shop.formattedTransferFee,
                                formattedIndustry = shop.formattedFinalIndustry,
                                formattedFinalLocationNode = shop.formattedFinalLocationNode
                            )
                        )
                    }.let { orderList ->
                        orderRepository.insertOrders(*orderList.toTypedArray())
                    }
                    currentPage += 1
                }
            }
        }
    }
}