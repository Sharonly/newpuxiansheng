package com.puxiansheng.logic.data.menu;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ!\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J)\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\f2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/puxiansheng/logic/data/menu/LocalMenuRepository;", "", "menuDao", "Lcom/puxiansheng/logic/data/menu/MenuDao;", "(Lcom/puxiansheng/logic/data/menu/MenuDao;)V", "insertOrUpdate", "", "menuItem", "", "Lcom/puxiansheng/logic/bean/MenuItem;", "([Lcom/puxiansheng/logic/bean/MenuItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestAllMenu", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMenuByParentID", "parentID", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMenuByType", "type", "requestMenuByTypeAndParentID", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logic_release"})
public final class LocalMenuRepository {
    private final com.puxiansheng.logic.data.menu.MenuDao menuDao = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertOrUpdate(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.MenuItem[] menuItem, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestAllMenu(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestMenuByType(int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestMenuByParentID(int parentID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestMenuByTypeAndParentID(int type, int parentID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p2) {
        return null;
    }
    
    public LocalMenuRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.menu.MenuDao menuDao) {
        super();
    }
}