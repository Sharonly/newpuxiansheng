package com.puxiansheng.logic.data.order

import com.puxiansheng.logic.bean.Order

class LocalOrderRepository(private val orderDao: OrderDao) {
    fun getOrdersByTypeFromRoom(
        type: Int
    ) = orderDao.getLocalOrdersByTransferType(type)

    fun getOrdersByTransferTypeFromRoomWithLimit(
        type: Int,
        limit: Int
    ) = orderDao.getLocalOrdersByTransferTypeWithLimit(
        type = type,
        limit = limit
    )

    fun insertOrders(vararg orders: Order) = orderDao.insertOrUpdate(*orders)

    fun deleteOrdersByTypeFromRoom(type: Int) = orderDao.deleteAllByTransferType(type)
}