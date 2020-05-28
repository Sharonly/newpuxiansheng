package com.puxiansheng.logic.data.order.source;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0004J*\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\fH\u0016J*\u0010\r\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\fH\u0016J*\u0010\u000e\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/puxiansheng/logic/data/order/source/RemoteTransferOutOrderDataSource;", "Landroidx/paging/PageKeyedDataSource;", "", "Lcom/puxiansheng/logic/bean/Order;", "()V", "remoteOrderRepository", "Lcom/puxiansheng/logic/data/order/RemoteOrderRepository;", "loadAfter", "", "params", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "callback", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "logic_release"})
public final class RemoteTransferOutOrderDataSource extends androidx.paging.PageKeyedDataSource<java.lang.Long, com.puxiansheng.logic.bean.Order> {
    private final com.puxiansheng.logic.data.order.RemoteOrderRepository remoteOrderRepository = null;
    
    @java.lang.Override()
    public void loadInitial(@org.jetbrains.annotations.NotNull()
    androidx.paging.PageKeyedDataSource.LoadInitialParams<java.lang.Long> params, @org.jetbrains.annotations.NotNull()
    androidx.paging.PageKeyedDataSource.LoadInitialCallback<java.lang.Long, com.puxiansheng.logic.bean.Order> callback) {
    }
    
    @java.lang.Override()
    public void loadAfter(@org.jetbrains.annotations.NotNull()
    androidx.paging.PageKeyedDataSource.LoadParams<java.lang.Long> params, @org.jetbrains.annotations.NotNull()
    androidx.paging.PageKeyedDataSource.LoadCallback<java.lang.Long, com.puxiansheng.logic.bean.Order> callback) {
    }
    
    @java.lang.Override()
    public void loadBefore(@org.jetbrains.annotations.NotNull()
    androidx.paging.PageKeyedDataSource.LoadParams<java.lang.Long> params, @org.jetbrains.annotations.NotNull()
    androidx.paging.PageKeyedDataSource.LoadCallback<java.lang.Long, com.puxiansheng.logic.bean.Order> callback) {
    }
    
    public RemoteTransferOutOrderDataSource() {
        super();
    }
}