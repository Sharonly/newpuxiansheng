package com.puxiansheng.logic.data.order;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\"\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0007\u001a\u00020\bJ\u001f\u0010\u000e\u001a\u00020\u00062\u0012\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0010\"\u00020\u000b\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/puxiansheng/logic/data/order/LocalOrderRepository;", "", "orderDao", "Lcom/puxiansheng/logic/data/order/OrderDao;", "(Lcom/puxiansheng/logic/data/order/OrderDao;)V", "deleteOrdersByTypeFromRoom", "", "type", "", "getOrdersByTransferTypeFromRoomWithLimit", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/Order;", "limit", "getOrdersByTypeFromRoom", "insertOrders", "orders", "", "([Lcom/puxiansheng/logic/bean/Order;)V", "logic_release"})
public final class LocalOrderRepository {
    private final com.puxiansheng.logic.data.order.OrderDao orderDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.Order> getOrdersByTypeFromRoom(int type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.Order> getOrdersByTransferTypeFromRoomWithLimit(int type, int limit) {
        return null;
    }
    
    public final void insertOrders(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.Order... orders) {
    }
    
    public final void deleteOrdersByTypeFromRoom(int type) {
    }
    
    public LocalOrderRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.order.OrderDao orderDao) {
        super();
    }
}