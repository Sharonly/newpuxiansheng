package com.puxiansheng.logic.bean;

import java.lang.System;

@androidx.room.Entity(tableName = "table_orders", indices = {@androidx.room.Index(unique = true, value = {"_shop_id", "_transfer_type"})})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0002>?BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\nH\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\fH\u00c6\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003JY\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010;\u001a\u00020\u0003H\u00d6\u0001J\t\u0010<\u001a\u00020=H\u00d6\u0001R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\u00158\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013R \u0010\r\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR \u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R \u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/\u00a8\u0006@"}, d2 = {"Lcom/puxiansheng/logic/bean/Order;", "", "orderType", "", "favorite", "shop", "Lcom/puxiansheng/logic/bean/Shop;", "shopOwner", "Lcom/puxiansheng/logic/bean/User;", "state", "Lcom/puxiansheng/logic/bean/Order$Companion$State;", "status", "Lcom/puxiansheng/logic/bean/Order$Companion$orderStatus;", "serviceAgent", "Lcom/puxiansheng/logic/bean/ServiceAgent;", "(IILcom/puxiansheng/logic/bean/Shop;Lcom/puxiansheng/logic/bean/User;Lcom/puxiansheng/logic/bean/Order$Companion$State;Lcom/puxiansheng/logic/bean/Order$Companion$orderStatus;Lcom/puxiansheng/logic/bean/ServiceAgent;)V", "getFavorite", "()I", "setFavorite", "(I)V", "orderID", "", "getOrderID", "()J", "setOrderID", "(J)V", "getOrderType", "setOrderType", "getServiceAgent", "()Lcom/puxiansheng/logic/bean/ServiceAgent;", "setServiceAgent", "(Lcom/puxiansheng/logic/bean/ServiceAgent;)V", "getShop", "()Lcom/puxiansheng/logic/bean/Shop;", "setShop", "(Lcom/puxiansheng/logic/bean/Shop;)V", "getShopOwner", "()Lcom/puxiansheng/logic/bean/User;", "setShopOwner", "(Lcom/puxiansheng/logic/bean/User;)V", "getState", "()Lcom/puxiansheng/logic/bean/Order$Companion$State;", "setState", "(Lcom/puxiansheng/logic/bean/Order$Companion$State;)V", "getStatus", "()Lcom/puxiansheng/logic/bean/Order$Companion$orderStatus;", "setStatus", "(Lcom/puxiansheng/logic/bean/Order$Companion$orderStatus;)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "Type", "logic_debug"})
public final class Order {
    @androidx.room.ColumnInfo(name = "_order_id")
    @androidx.room.PrimaryKey(autoGenerate = true)
    private long orderID;
    @androidx.room.ColumnInfo(name = "_order_type")
    private int orderType;
    @androidx.room.ColumnInfo(name = "_favorite")
    private int favorite;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Embedded()
    private com.puxiansheng.logic.bean.Shop shop;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Embedded()
    private com.puxiansheng.logic.bean.User shopOwner;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Embedded()
    private com.puxiansheng.logic.bean.Order.Companion.State state;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Embedded()
    private com.puxiansheng.logic.bean.Order.Companion.orderStatus status;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Ignore()
    private com.puxiansheng.logic.bean.ServiceAgent serviceAgent;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TRANSFER_TYPE = "TRANSFER_TYPE";
    public static final int TRANSFER_TYPE_OUT = 0;
    public static final int TRANSFER_TYPE_IN = 1;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.puxiansheng.logic.bean.Order> DIFF = null;
    public static final com.puxiansheng.logic.bean.Order.Companion Companion = null;
    
    public final long getOrderID() {
        return 0L;
    }
    
    public final void setOrderID(long p0) {
    }
    
    public final int getOrderType() {
        return 0;
    }
    
    public final void setOrderType(int p0) {
    }
    
    public final int getFavorite() {
        return 0;
    }
    
    public final void setFavorite(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Shop getShop() {
        return null;
    }
    
    public final void setShop(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Shop p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.User getShopOwner() {
        return null;
    }
    
    public final void setShopOwner(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.User p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.State getState() {
        return null;
    }
    
    public final void setState(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.State p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.orderStatus getStatus() {
        return null;
    }
    
    public final void setStatus(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.orderStatus p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.ServiceAgent getServiceAgent() {
        return null;
    }
    
    public final void setServiceAgent(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.ServiceAgent p0) {
    }
    
    public Order(int orderType, int favorite, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Shop shop, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.User shopOwner, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.State state, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.orderStatus status, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.ServiceAgent serviceAgent) {
        super();
    }
    
    public Order() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Shop component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.User component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.State component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.orderStatus component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.ServiceAgent component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.Order copy(int orderType, int favorite, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Shop shop, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.User shopOwner, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.State state, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.orderStatus status, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.ServiceAgent serviceAgent) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011\u00a8\u0006\u0012"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type;", "", "(Ljava/lang/String;I)V", "value", "", "TRANSFER_OUT", "TRANSFER_IN", "TRANSFER_OUT_PRIVATE", "TRANSFER_IN_PRIVATE", "TRANSFER_OUT_RECOMMEND", "TRANSFER_IN_RECOMMEND", "TRANSFER_OUT_FAVORITE", "TRANSFER_IN_FAVORITE", "TRANSFER_OUT_HISTORY", "TRANSFER_IN_HISTORY", "RECOMMEND_INFO", "USER_PUBLIC_ORDER", "EMPTY", "logic_debug"})
    public static enum Type {
        /*public static final*/ TRANSFER_OUT /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_OUT;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_OUT(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_OUT() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_IN /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_IN;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_IN(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_IN() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_OUT_PRIVATE /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_OUT_PRIVATE;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_OUT_PRIVATE(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_OUT_PRIVATE() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_IN_PRIVATE /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_IN_PRIVATE;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_IN_PRIVATE(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_IN_PRIVATE() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_OUT_RECOMMEND /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_OUT_RECOMMEND;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_OUT_RECOMMEND(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_OUT_RECOMMEND() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_IN_RECOMMEND /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_IN_RECOMMEND;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_IN_RECOMMEND(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_IN_RECOMMEND() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_OUT_FAVORITE /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_OUT_FAVORITE;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_OUT_FAVORITE(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_OUT_FAVORITE() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_IN_FAVORITE /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_IN_FAVORITE;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_IN_FAVORITE(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_IN_FAVORITE() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_OUT_HISTORY /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_OUT_HISTORY;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_OUT_HISTORY(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_OUT_HISTORY() {
                super();
            }
        } */,
        /*public static final*/ TRANSFER_IN_HISTORY /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$TRANSFER_IN_HISTORY;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) TRANSFER_IN_HISTORY(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            TRANSFER_IN_HISTORY() {
                super();
            }
        } */,
        /*public static final*/ RECOMMEND_INFO /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$RECOMMEND_INFO;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) RECOMMEND_INFO(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            RECOMMEND_INFO() {
                super();
            }
        } */,
        /*public static final*/ USER_PUBLIC_ORDER /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$USER_PUBLIC_ORDER;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) USER_PUBLIC_ORDER(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            USER_PUBLIC_ORDER() {
                super();
            }
        } */,
        /*public static final*/ EMPTY /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Type$EMPTY;", "Lcom/puxiansheng/logic/bean/Order$Type;", "value", "", "logic_debug"}) EMPTY(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            EMPTY() {
                super();
            }
        } */;
        
        public abstract int value();
        
        Type() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/puxiansheng/logic/bean/Order;", "getDIFF", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "TRANSFER_TYPE", "", "TRANSFER_TYPE_IN", "", "TRANSFER_TYPE_OUT", "State", "orderStatus", "logic_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.puxiansheng.logic.bean.Order> getDIFF() {
            return null;
        }
        
        private Companion() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Companion$State;", "", "text", "", "color", "(Ljava/lang/String;Ljava/lang/String;)V", "getColor", "()Ljava/lang/String;", "getText", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "logic_debug"})
        public static final class State {
            @org.jetbrains.annotations.NotNull()
            @androidx.room.ColumnInfo(name = "_state_text")
            @com.google.gson.annotations.SerializedName(value = "text")
            private final java.lang.String text = null;
            @org.jetbrains.annotations.NotNull()
            @androidx.room.ColumnInfo(name = "_state_color")
            @com.google.gson.annotations.SerializedName(value = "color")
            private final java.lang.String color = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getText() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getColor() {
                return null;
            }
            
            public State(@org.jetbrains.annotations.NotNull()
            java.lang.String text, @org.jetbrains.annotations.NotNull()
            java.lang.String color) {
                super();
            }
            
            public State() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.puxiansheng.logic.bean.Order.Companion.State copy(@org.jetbrains.annotations.NotNull()
            java.lang.String text, @org.jetbrains.annotations.NotNull()
            java.lang.String color) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001J\t\u0010\u0011\u001a\u00020\u0003H\u00d6\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/puxiansheng/logic/bean/Order$Companion$orderStatus;", "", "text", "", "color", "(Ljava/lang/String;Ljava/lang/String;)V", "getColor", "()Ljava/lang/String;", "getText", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "logic_debug"})
        public static final class orderStatus {
            @org.jetbrains.annotations.NotNull()
            @androidx.room.ColumnInfo(name = "_status_name")
            @com.google.gson.annotations.SerializedName(value = "name")
            private final java.lang.String text = null;
            @org.jetbrains.annotations.NotNull()
            @androidx.room.ColumnInfo(name = "_status_color")
            @com.google.gson.annotations.SerializedName(value = "color")
            private final java.lang.String color = null;
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getText() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getColor() {
                return null;
            }
            
            public orderStatus(@org.jetbrains.annotations.NotNull()
            java.lang.String text, @org.jetbrains.annotations.NotNull()
            java.lang.String color) {
                super();
            }
            
            public orderStatus() {
                super();
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.puxiansheng.logic.bean.Order.Companion.orderStatus copy(@org.jetbrains.annotations.NotNull()
            java.lang.String text, @org.jetbrains.annotations.NotNull()
            java.lang.String color) {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            @java.lang.Override()
            public java.lang.String toString() {
                return null;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object p0) {
                return false;
            }
        }
    }
}