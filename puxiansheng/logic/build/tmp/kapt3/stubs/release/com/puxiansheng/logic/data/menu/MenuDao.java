package com.puxiansheng.logic.data.menu;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ!\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ)\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J!\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ%\u0010\u0015\u001a\u00020\u00032\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u0017\"\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/puxiansheng/logic/data/menu/MenuDao;", "", "deleteAllFromRoom", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMenuByTypeFromRoom", "type", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMenuFromRoom", "", "Lcom/puxiansheng/logic/bean/MenuItem;", "getMenuByIDFromRoom", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMenuByParentIDFromRoom", "parentID", "getMenuByTypeAndParentIDFromRoom", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMenuByTypeFromRoom", "insertOrUpdateIntoRoom", "menu", "", "([Lcom/puxiansheng/logic/bean/MenuItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logic_release"})
public abstract interface MenuDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insertOrUpdateIntoRoom(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.MenuItem[] menu, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "delete from table_menu where _type = :type")
    public abstract java.lang.Object deleteMenuByTypeFromRoom(int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "delete from table_menu")
    public abstract java.lang.Object deleteAllFromRoom(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p0);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from table_menu where _type = :type")
    public abstract java.lang.Object getMenuByTypeFromRoom(int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from table_menu where _parent_id = :parentID")
    public abstract java.lang.Object getMenuByParentIDFromRoom(int parentID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from table_menu where _type = :type and _parent_id = :parentID")
    public abstract java.lang.Object getMenuByTypeAndParentIDFromRoom(int type, int parentID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p2);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from table_menu")
    public abstract java.lang.Object getAllMenuFromRoom(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p0);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from table_menu where _menu_id = :id")
    public abstract java.lang.Object getMenuByIDFromRoom(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.puxiansheng.logic.bean.MenuItem> p1);
}