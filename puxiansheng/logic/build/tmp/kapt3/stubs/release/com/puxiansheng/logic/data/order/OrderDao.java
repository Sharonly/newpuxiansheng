package com.puxiansheng.logic.data.order;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u0005H\'J\u001c\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\'J$\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H\'J!\u0010\r\u001a\u00020\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000f\"\u00020\bH\'\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/puxiansheng/logic/data/order/OrderDao;", "", "deleteAllByTransferType", "", "type", "", "getLocalOrdersByOrderType", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/Order;", "orderType", "getLocalOrdersByTransferType", "getLocalOrdersByTransferTypeWithLimit", "limit", "insertOrUpdate", "order", "", "([Lcom/puxiansheng/logic/bean/Order;)V", "logic_release"})
public abstract interface OrderDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertOrUpdate(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.Order... order);
    
    @androidx.room.Query(value = "delete from table_orders where _transfer_type = :type")
    public abstract void deleteAllByTransferType(int type);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_orders where _transfer_type = :type")
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.Order> getLocalOrdersByTransferType(int type);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_orders where _transfer_type = :type limit :limit")
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.Order> getLocalOrdersByTransferTypeWithLimit(int type, int limit);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_orders where _order_type = :orderType")
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.Order> getLocalOrdersByOrderType(int orderType);
}