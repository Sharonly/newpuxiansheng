package com.puxiansheng.logic.data.order.source

import androidx.paging.PageKeyedDataSource
import com.puxiansheng.logic.bean.Order
import com.puxiansheng.logic.data.order.RemoteOrderRepository

class RemoteTransferOutOrderDataSource: PageKeyedDataSource<Long, Order>() {
    private val remoteOrderRepository = RemoteOrderRepository()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Order>
    ) {
        //callback.onResult()
        //remoteOrderRepository.requestRemoteTransferOutOrders()
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Order>) {

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Order>) = Unit
}