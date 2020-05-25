package com.puxiansheng.logic.data.order;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0006\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004J\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004J\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0013J&\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006J&\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006J&\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006J&\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00050\u0004J\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00050\u0004J&\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006J&\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006J\u0012\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00050\u0004J\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0013J\u0081\u0001\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010\'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010*\u001a\u0004\u0018\u00010\u00132\b\u0010+\u001a\u0004\u0018\u00010\u00132\b\u0010,\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010-J\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00050\u00042\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010\u0012\u001a\u00020\u0013Jw\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010*\u001a\u0004\u0018\u00010\u00132\b\u0010+\u001a\u0004\u0018\u00010\u00132\b\u0010,\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u00101Jo\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00050\u00042\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010*\u001a\u0004\u0018\u00010\u00132\b\u0010+\u001a\u0004\u0018\u00010\u00132\b\u0010,\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u00103J&\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u00050\u00042\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\"\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00050\u00042\u0006\u00108\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006J\"\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00050\u00042\u0006\u00108\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006Jv\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00050\u00042\b\b\u0002\u0010;\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u00062\b\b\u0002\u0010\'\u001a\u00020\u00062\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010&\u001a\u00020\u00062\b\b\u0002\u0010<\u001a\u00020\u00062\b\b\u0002\u0010=\u001a\u00020\u00062\b\b\u0002\u0010>\u001a\u00020\u00062\b\b\u0002\u0010?\u001a\u00020\u0006J\u00f4\u0001\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002070\u00050\u00042\b\b\u0002\u0010;\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u00062\b\b\u0002\u0010$\u001a\u00020\u00062\b\b\u0002\u0010%\u001a\u00020\u00062\b\b\u0002\u0010\'\u001a\u00020\u00062\b\b\u0002\u0010A\u001a\u00020\u00062\b\b\u0002\u0010&\u001a\u00020\u00062\b\b\u0002\u0010B\u001a\u00020\u00062\b\b\u0002\u0010C\u001a\u00020D2\b\b\u0002\u0010E\u001a\u00020D2\b\b\u0002\u0010F\u001a\u00020\u00132\b\b\u0002\u0010G\u001a\u00020\u00132\b\b\u0002\u0010H\u001a\u00020\u00132\n\b\u0002\u0010I\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010<\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010?\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010K\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010L\u001a\u0004\u0018\u00010\u0006\u00a8\u0006M"}, d2 = {"Lcom/puxiansheng/logic/data/order/RemoteOrderRepository;", "", "()V", "deleteFavorTransferInOrderFromRemote", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "", "shopID", "deleteFavorTransferOutOrderFromRemote", "deleteHistroyTransferInOrderFromRemote", "deleteHistroyTransferOutOrderFromRemote", "deleteTransferInOrderFromRemote", "deleteTransferOutOrderFromRemote", "getEditTransferInOrderDetailFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespOrderDetail;", "getEditTransferOutOrderDetailFromRemote", "getFavoriteTransferInOrdersFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespOrders;", "page", "", "getFavoriteTransferOutOrdersFromRemote", "getHomeRecommendedTransferInOrdersFromRemote", "city", "getHomeRecommendedTransferOutOrdersFromRemote", "getMineTransferInOrdersFromRemote", "getMineTransferOutOrdersFromRemote", "getProcessingFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespReleaseOrders;", "getPublicFromRemote", "getRecommendedTransferInOrdersFromRemote", "getRecommendedTransferOutOrdersFromRemote", "getSoldOutFromRemote", "getTransferInOrderDetailFromRemote", "getTransferInOrdersBrowsingHistoryFromRemote", "getTransferInOrdersFromRemote", "title", "industry", "size", "area", "rent", "sortBy", "sortType", "hot", "top", "recommend", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/puxiansheng/util/http/APIRst;", "getTransferOutOrderDetailFromRemote", "getTransferOutOrdersBrowsingHistoryFromRemote", "getTransferOutOrdersFromRemote", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/puxiansheng/util/http/APIRst;", "getTransferSuccessFromRemote", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/puxiansheng/util/http/APIRst;", "getUserLikeShopFromRemote", "Lcom/puxiansheng/logic/bean/HttpRecommendOrder;", "submitSimpleTransferInOrder", "Lcom/puxiansheng/logic/bean/http/HttpRespSubmitOrder;", "phone", "submitSimpleTransferOutOrder", "submitTransferInOrder", "type", "description", "contactName", "contactPhone", "facility", "submitTransferOutOrder", "fee", "address", "lng", "", "lat", "exclusive", "state", "floor", "images", "environment", "reason", "label", "logic_debug"})
public final class RemoteOrderRepository {
    
    /**
     * submit a new simple transfer-in order to remote server.
     * @param phone
     * @param area
     */
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSubmitOrder>> submitSimpleTransferInOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String area) {
        return null;
    }
    
    /**
     * submit a new simple transfer-out order to remote server.
     * @param phone
     * @param area
     */
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSubmitOrder>> submitSimpleTransferOutOrder(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String area) {
        return null;
    }
    
    /**
     * submit a new transfer-out order to remote server.
     */
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
    
    /**
     * submit a new transfer-in order to remote server.
     */
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
    
    /**
     * request a list of transfer-out orders from remote server base on page key.
     */
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
    
    /**
     * request a list of transfer-out orders from remote server base on page key.
     */
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
    
    /**
     * request a list of transfer-in orders from remote server base on page key.
     */
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
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getEditTransferOutOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getTransferOutOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.HttpRecommendOrder>> getUserLikeShopFromRemote(@org.jetbrains.annotations.Nullable()
    java.lang.String city, @org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getEditTransferInOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrderDetail>> getTransferInOrderDetailFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String shopID) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getMineTransferOutOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getMineTransferInOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getRecommendedTransferOutOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getHomeRecommendedTransferOutOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getRecommendedTransferInOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespOrders>> getHomeRecommendedTransferInOrdersFromRemote(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
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
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteHistroyTransferOutOrderFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<java.lang.String>> deleteHistroyTransferInOrderFromRemote() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseOrders>> getPublicFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseOrders>> getProcessingFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseOrders>> getSoldOutFromRemote() {
        return null;
    }
    
    public RemoteOrderRepository() {
        super();
    }
}