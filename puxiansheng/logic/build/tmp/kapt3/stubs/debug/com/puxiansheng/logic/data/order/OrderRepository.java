package com.puxiansheng.logic.data.order;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u001a\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u0014J\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u0014J&\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u00142\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\fJ&\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u00142\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\fJ\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u0014J\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u0014J\"\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020%0$2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0014J\u001a\u0010\'\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020%0$2\u0006\u0010\u0013\u001a\u00020\u0014J&\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u00142\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\fJ&\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u00142\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\fJ\u001a\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u0014J\u0081\u0001\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010-\u001a\u00020\f2\u0006\u0010.\u001a\u00020\f2\u0006\u0010/\u001a\u00020\f2\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0006\u00103\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00142\b\u00104\u001a\u0004\u0018\u00010\u00142\b\u00105\u001a\u0004\u0018\u00010\u00142\b\u00106\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u00107J\u001a\u00108\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000b0\n2\u0006\u0010\u000f\u001a\u00020\fJ\u001a\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\u0006\u0010\u001c\u001a\u00020\u0014J\u0083\u0001\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\b\b\u0002\u0010-\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\f2\b\b\u0002\u0010/\u001a\u00020\f2\b\b\u0002\u00100\u001a\u00020\f2\b\b\u0002\u00102\u001a\u00020\f2\b\b\u0002\u00103\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00142\b\u00104\u001a\u0004\u0018\u00010\u00142\b\u00105\u001a\u0004\u0018\u00010\u00142\b\u00106\u001a\u0004\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010;Jy\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\n2\b\b\u0002\u0010.\u001a\u00020\f2\b\b\u0002\u0010/\u001a\u00020\f2\b\b\u0002\u00100\u001a\u00020\f2\b\b\u0002\u00102\u001a\u00020\f2\b\b\u0002\u00103\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u00142\b\u00104\u001a\u0004\u0018\u00010\u00142\b\u00105\u001a\u0004\u0018\u00010\u00142\b\u00106\u001a\u0004\u0018\u00010\u00142\b\u0010\u001f\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010=J\"\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020?0\u000b0\n2\u0006\u0010@\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fJ\u0012\u0010A\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u000b0\nJ\u0012\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u000b0\nJ\u0012\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0\u000b0\nJ\u001f\u0010E\u001a\u00020\u00122\u0012\u0010F\u001a\n\u0012\u0006\b\u0001\u0012\u00020%0G\"\u00020%\u00a2\u0006\u0002\u0010HJ\"\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\u000b0\n2\u0006\u0010K\u001a\u00020\f2\u0006\u00100\u001a\u00020\fJ\"\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\u000b0\n2\u0006\u0010K\u001a\u00020\f2\u0006\u00100\u001a\u00020\fJv\u0010M\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\u000b0\n2\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010-\u001a\u00020\f2\b\b\u0002\u0010/\u001a\u00020\f2\b\b\u0002\u00101\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\f2\b\b\u0002\u00100\u001a\u00020\f2\b\b\u0002\u0010N\u001a\u00020\f2\b\b\u0002\u0010O\u001a\u00020\f2\b\b\u0002\u0010P\u001a\u00020\f2\b\b\u0002\u0010Q\u001a\u00020\fJ\u00f4\u0001\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0\u000b0\n2\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010-\u001a\u00020\f2\b\b\u0002\u0010.\u001a\u00020\f2\b\b\u0002\u0010/\u001a\u00020\f2\b\b\u0002\u00101\u001a\u00020\f2\b\b\u0002\u0010S\u001a\u00020\f2\b\b\u0002\u00100\u001a\u00020\f2\b\b\u0002\u0010T\u001a\u00020\f2\b\b\u0002\u0010U\u001a\u00020V2\b\b\u0002\u0010W\u001a\u00020V2\b\b\u0002\u0010X\u001a\u00020\u00142\b\b\u0002\u0010Y\u001a\u00020\u00142\b\b\u0002\u0010Z\u001a\u00020\u00142\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\\\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010]\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010^\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006_"}, d2 = {"Lcom/puxiansheng/logic/data/order/OrderRepository;", "", "orderDao", "Lcom/puxiansheng/logic/data/order/OrderDao;", "(Lcom/puxiansheng/logic/data/order/OrderDao;)V", "localOrderRepository", "Lcom/puxiansheng/logic/data/order/LocalOrderRepository;", "remoteOrderRepository", "Lcom/puxiansheng/logic/data/order/RemoteOrderRepository;", "deleteAllHistoryTransferInOrderFromRemote", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "", "deleteAllHistoryTransferOutOrderFromRemote", "deleteFavorTransferInOrderFromRemote", "shopID", "deleteFavorTransferOutOrderFromRemote", "deleteOrdersByTypeFromRoom", "", "type", "", "deleteTransferInOrderFromRemote", "deleteTransferOutOrderFromRemote", "getEditTransferInOrderDetailFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespOrderDetail;", "getEditTransferOutOrderDetailFromRemote", "getFavoriteTransferInOrdersFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespOrders;", "page", "getFavoriteTransferOutOrdersFromRemote", "getHomeRecommendedTransferInOrdersFromRemote", "city", "getHomeRecommendedTransferOutOrdersFromRemote", "getMineTransferInOrdersFromRemote", "getMineTransferOutOrdersFromRemote", "getOrdersByTransferTypeFromRoomWithLimit", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/Order;", "limit", "getOrdersByTypeFromRoom", "getRecommendedTransferInOrdersFromRemote", "getRecommendedTransferOutOrdersFromRemote", "getTransferInOrderDetailFromRemote", "getTransferInOrdersBrowsingHistoryFromRemote", "getTransferInOrdersFromRemote", "title", "industry", "size", "area", "rent", "sortBy", "sortType", "hot", "top", "recommend", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/puxiansheng/util/http/APIRst;", "getTransferOutOrderDetailFromRemote", "getTransferOutOrdersBrowsingHistoryFromRemote", "getTransferOutOrdersFromRemote", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/puxiansheng/util/http/APIRst;", "getTransferSuccessFromRemote", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/puxiansheng/util/http/APIRst;", "getUserLikeShopFromRemote", "Lcom/puxiansheng/logic/bean/HttpRecommendOrder;", "cityId", "getUserProcessingOrder", "Lcom/puxiansheng/logic/bean/http/HttpRespReleaseOrders;", "getUserPublicOrder", "getUserSoldOutOrder", "insertOrders", "orders", "", "([Lcom/puxiansheng/logic/bean/Order;)V", "submitSimpleTransferInOrder", "Lcom/puxiansheng/logic/bean/http/HttpRespSubmitOrder;", "phone", "submitSimpleTransferOutOrder", "submitTransferInOrder", "description", "contactName", "contactPhone", "facility", "submitTransferOutOrder", "fee", "address", "lng", "", "lat", "exclusive", "state", "floor", "images", "environment", "reason", "label", "logic_debug"})
public final class OrderRepository {
    private final com.puxiansheng.logic.data.order.RemoteOrderRepository remoteOrderRepository = null;
    private final com.puxiansheng.logic.data.order.LocalOrderRepository localOrderRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSubmitOrder>> submitSimpleTransferInOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String area) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSubmitOrder>> submitSimpleTransferOutOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String area) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSubmitOrder>> submitTransferOutOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, @org.jetbrains.annotations.NotNull()
    java.lang.String size, @org.jetbrains.annotations.NotNull()
    java.lang.String rent, @org.jetbrains.annotations.NotNull()
    java.lang.String fee, @org.jetbrains.annotations.NotNull()
    java.lang.String area, @org.jetbrains.annotations.NotNull()
    java.lang.String address, double lng, double lat, int exclusive, int state, int floor, @org.jetbrains.annotations.Nullable()
    java.lang.String images, @org.jetbrains.annotations.Nullable()
    java.lang.String contactName, @org.jetbrains.annotations.Nullable()
    java.lang.String contactPhone, @org.jetbrains.annotations.Nullable()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.String environment, @org.jetbrains.annotations.Nullable()
    java.lang.String facility, @org.jetbrains.annotations.Nullable()
    java.lang.String reason, @org.jetbrains.annotations.Nullable()
    java.lang.String label) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSubmitOrder>> submitTransferInOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String size, @org.jetbrains.annotations.NotNull()
    java.lang.String rent, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, @org.jetbrains.annotations.NotNull()
    java.lang.String area, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String contactName, @org.jetbrains.annotations.NotNull()
    java.lang.String contactPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String facility) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getTransferSuccessFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String industry, @org.jetbrains.annotations.NotNull()
    java.lang.String size, @org.jetbrains.annotations.NotNull()
    java.lang.String area, @org.jetbrains.annotations.NotNull()
    java.lang.String sortBy, @org.jetbrains.annotations.NotNull()
    java.lang.String sortType, int page, @org.jetbrains.annotations.Nullable()
    java.lang.Integer hot, @org.jetbrains.annotations.Nullable()
    java.lang.Integer top, @org.jetbrains.annotations.Nullable()
    java.lang.Integer recommend, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getTransferOutOrdersFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, @org.jetbrains.annotations.NotNull()
    java.lang.String size, @org.jetbrains.annotations.NotNull()
    java.lang.String area, @org.jetbrains.annotations.NotNull()
    java.lang.String sortBy, @org.jetbrains.annotations.NotNull()
    java.lang.String sortType, int page, @org.jetbrains.annotations.Nullable()
    java.lang.Integer hot, @org.jetbrains.annotations.Nullable()
    java.lang.Integer top, @org.jetbrains.annotations.Nullable()
    java.lang.Integer recommend, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getTransferInOrdersFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, @org.jetbrains.annotations.NotNull()
    java.lang.String size, @org.jetbrains.annotations.NotNull()
    java.lang.String area, @org.jetbrains.annotations.NotNull()
    java.lang.String rent, @org.jetbrains.annotations.NotNull()
    java.lang.String sortBy, @org.jetbrains.annotations.NotNull()
    java.lang.String sortType, int page, @org.jetbrains.annotations.Nullable()
    java.lang.Integer hot, @org.jetbrains.annotations.Nullable()
    java.lang.Integer top, @org.jetbrains.annotations.Nullable()
    java.lang.Integer recommend, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
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
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getTransferOutOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.HttpRecommendOrder>> getUserLikeShopFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String cityId, @org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getEditTransferOutOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getTransferInOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getEditTransferInOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getMineTransferOutOrdersFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getMineTransferInOrdersFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getRecommendedTransferOutOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getRecommendedTransferInOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getHomeRecommendedTransferOutOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getHomeRecommendedTransferInOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseOrders>> getUserPublicOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseOrders>> getUserProcessingOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseOrders>> getUserSoldOutOrder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteTransferOutOrderFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteTransferInOrderFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteFavorTransferOutOrderFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteFavorTransferInOrderFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteAllHistoryTransferOutOrderFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteAllHistoryTransferInOrderFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getFavoriteTransferOutOrdersFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getFavoriteTransferInOrdersFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getTransferOutOrdersBrowsingHistoryFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getTransferInOrdersBrowsingHistoryFromRemote(int page) {
        return null;
    }
    
    public OrderRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.order.OrderDao orderDao) {
        super();
    }
}