package com.puxiansheng.logic.data.order

import com.puxiansheng.logic.bean.Order

class OrderRepository(orderDao: OrderDao) {
    private val remoteOrderRepository = RemoteOrderRepository()
    private val localOrderRepository = LocalOrderRepository(orderDao)

    fun submitSimpleTransferInOrder(
        phone: String,
        area: String
    ) = remoteOrderRepository.submitSimpleTransferInOrder(
        phone = phone,
        area = area
    )

    fun submitSimpleTransferOutOrder(
        phone: String,
        area: String
    ) = remoteOrderRepository.submitSimpleTransferOutOrder(
        phone = phone,
        area = area
    )

    fun submitTransferOutOrder(
        type: String = "",
        title: String = "",
        industry: String = "",
        size: String = "",
        rent: String = "",
        fee: String = "",
        area: String = "",
        address: String = "",
        lng:Double =0.0,
        lat:Double =0.0,
        exclusive: Int = 0,
        state: Int = 0,
        floor:Int = 0,

        //
        images: String? = null,
        contactName: String? = null,
        contactPhone: String? = null,
        description: String? = null,
        environment: String? = null,
        facility: String? = null,
        reason: String? = null,
        label: String? = null
    ) = remoteOrderRepository.submitTransferOutOrder(
        type = type,
        size = size,
        state = state,
        exclusive = exclusive,
        fee = fee,
        images = images,
        title = title,
        industry = industry,
        rent = rent,
        reason = reason,
        description = description,
        environment = environment,
        contactName = contactName,
        contactPhone = contactPhone,
        facility = facility,
        label = label,
        area = area,
        address = address,
        lng = lng,
        lat = lat,
        floor = floor
    )

    fun submitTransferInOrder(
        type: String = "",
        title: String = "",
        size: String = "",
        rent: String = "",
        industry: String = "",
        area: String = "",
        description: String = "",
        contactName: String = "",
        contactPhone: String = "",
        facility: String= ""
    ) = remoteOrderRepository.submitTransferInOrder(
        type = type,
        size = size,
        title = title,
        industry = industry,
        rent = rent,
        description = description,
        contactName = contactName,
        contactPhone = contactPhone,
        area = area,
        facility =facility
    )

    fun getTransferSuccessFromRemote(
        industry: String = "",
        size: String = "",
        area: String = "",
        sortBy: String = "",
        sortType: String = "",
        page: Int,
        hot: Int?,
        top: Int?,
        recommend: Int?,
        city: String?
    ) = remoteOrderRepository.getTransferSuccessFromRemote(
        industry = industry,
        size = size,
        area = area,
        sortBy = sortBy,
        sortType = sortType,
        page = page,
        hot = hot,
        top = top,
        recommend = recommend,
        city = city
    )

    fun getTransferOutOrdersFromRemote(
        title: String = "",
        industry: String = "",
        size: String = "",
        area: String = "",
        sortBy: String = "",
        sortType: String = "",
        page: Int,
        hot: Int?,
        top: Int?,
        recommend: Int?,
        city: String?
    ) = remoteOrderRepository.getTransferOutOrdersFromRemote(
        title = title,
        industry = industry,
        size = size,
        area = area,
        sortBy = sortBy,
        sortType = sortType,
        page = page,
        hot = hot,
        top = top,
        recommend = recommend,
        city = city
    )

    fun getTransferInOrdersFromRemote(
        title: String,
        industry: String,
        size: String,
        area: String,
        rent: String,
        sortBy: String,
        sortType: String,
        page: Int,
        hot: Int?,
        top: Int?,
        recommend: Int?,
        city: String? = null
    ) = remoteOrderRepository.getTransferInOrdersFromRemote(
        title = title,
        industry = industry,
        size = size,
        area = area,
        rent = rent,
        sortBy = sortBy,
        sortType = sortType,
        page = page,
        hot = hot,
        top = top,
        recommend = recommend,
        city = city
    )

    fun getOrdersByTypeFromRoom(
        type: Int
    ) = localOrderRepository.getOrdersByTypeFromRoom(
        type = type
    )

    fun getOrdersByTransferTypeFromRoomWithLimit(
        type: Int,
        limit: Int
    ) = localOrderRepository.getOrdersByTransferTypeFromRoomWithLimit(
        type = type,
        limit = limit
    )

    fun insertOrders(
        vararg orders: Order
    ) = localOrderRepository.insertOrders(
        orders = *orders
    )


    fun deleteOrdersByTypeFromRoom(
        type: Int
    ) = localOrderRepository.deleteOrdersByTypeFromRoom(
        type = type
    )

    fun getTransferOutOrderDetailFromRemote(
        shopID: String
    ) = remoteOrderRepository.getTransferOutOrderDetailFromRemote(
        shopID = shopID
    )


    fun getUserLikeShopFromRemote(cityId:String,shopID: String)=remoteOrderRepository.getUserLikeShopFromRemote(city=cityId,shopID = shopID)

    fun getEditTransferOutOrderDetailFromRemote(
        shopID: String
    ) = remoteOrderRepository.getEditTransferOutOrderDetailFromRemote(
        shopID = shopID
    )

    fun getTransferInOrderDetailFromRemote(
        shopID: String
    ) = remoteOrderRepository.getTransferInOrderDetailFromRemote(
        shopID = shopID
    )

    fun getEditTransferInOrderDetailFromRemote(
        shopID: String
    ) = remoteOrderRepository.getEditTransferInOrderDetailFromRemote(
        shopID = shopID
    )

    fun getMineTransferOutOrdersFromRemote(
        page: Int
    ) = remoteOrderRepository.getMineTransferOutOrdersFromRemote(
        page = page
    )

    fun getMineTransferInOrdersFromRemote(
        page: Int
    ) = remoteOrderRepository.getMineTransferInOrdersFromRemote(
        page = page
    )

    fun getRecommendedTransferOutOrdersFromRemote(
        page: Int,
        city: String? = null
    ) = remoteOrderRepository.getRecommendedTransferOutOrdersFromRemote(
        page = page,
        city = city
    )

    fun getRecommendedTransferInOrdersFromRemote(
        page: Int,
        city: String? = null
    ) = remoteOrderRepository.getRecommendedTransferInOrdersFromRemote(
        page = page,
        city = city
    )

    fun getHomeRecommendedTransferOutOrdersFromRemote(
        page: Int,
        city: String? = null
    ) = remoteOrderRepository.getHomeRecommendedTransferOutOrdersFromRemote(
        page = page,
        city = city
    )

    fun getHomeRecommendedTransferInOrdersFromRemote(
        page: Int,
        city: String? = null
    ) = remoteOrderRepository.getHomeRecommendedTransferInOrdersFromRemote(
        page = page,
        city = city
    )

    fun getUserPublicOrder() = remoteOrderRepository.getPublicFromRemote()
    fun getUserProcessingOrder() = remoteOrderRepository.getProcessingFromRemote()
    fun getUserSoldOutOrder() = remoteOrderRepository.getSoldOutFromRemote()

    fun deleteTransferOutOrderFromRemote(
        shopID: String
    ) = remoteOrderRepository.deleteTransferOutOrderFromRemote(shopID = shopID)

    fun deleteTransferInOrderFromRemote(
        shopID: String
    ) = remoteOrderRepository.deleteTransferInOrderFromRemote(shopID = shopID)

    fun getFavoriteTransferOutOrdersFromRemote(
        page: Int
    ) = remoteOrderRepository.getFavoriteTransferOutOrdersFromRemote(page = page)

    fun getFavoriteTransferInOrdersFromRemote(
        page: Int
    ) = remoteOrderRepository.getFavoriteTransferInOrdersFromRemote(page = page)

    fun getTransferOutOrdersBrowsingHistoryFromRemote(
        page: Int
    ) = remoteOrderRepository.getTransferOutOrdersBrowsingHistoryFromRemote(page = page)

    fun getTransferInOrdersBrowsingHistoryFromRemote(
        page: Int
    ) = remoteOrderRepository.getTransferInOrdersBrowsingHistoryFromRemote(page = page)
}