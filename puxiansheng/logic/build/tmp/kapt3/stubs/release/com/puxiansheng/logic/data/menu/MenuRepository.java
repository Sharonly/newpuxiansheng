package com.puxiansheng.logic.data.menu;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J%\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\r0\f\"\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J!\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J)\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u001b2\u0006\u0010\u001e\u001a\u00020\u001fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006$"}, d2 = {"Lcom/puxiansheng/logic/data/menu/MenuRepository;", "", "menuDao", "Lcom/puxiansheng/logic/data/menu/MenuDao;", "(Lcom/puxiansheng/logic/data/menu/MenuDao;)V", "localMenuRepository", "Lcom/puxiansheng/logic/data/menu/LocalMenuRepository;", "remoteMenuRepository", "Lcom/puxiansheng/logic/data/menu/RemoteMenuRepository;", "insertOrUpdate", "", "menuItem", "", "Lcom/puxiansheng/logic/bean/MenuItem;", "([Lcom/puxiansheng/logic/bean/MenuItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestAllMenu", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMenuByParentID", "parentID", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestMenuByType", "type", "requestMenuByTypeAndParentID", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestRemoteIndustrySelectiveData", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespMenuDate;", "signatureToken", "", "requestRemotePropertySelectiveData", "requestRemoteRentSelectiveData", "requestRemoteRentUnitSelectiveData", "requestRemoteSizeSelectiveData", "logic_release"})
public final class MenuRepository {
    private final com.puxiansheng.logic.data.menu.RemoteMenuRepository remoteMenuRepository = null;
    private final com.puxiansheng.logic.data.menu.LocalMenuRepository localMenuRepository = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestAllMenu(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p0) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertOrUpdate(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.MenuItem[] menuItem, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestMenuByParentID(int parentID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestMenuByType(int type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestMenuByTypeAndParentID(int type, int parentID, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.puxiansheng.logic.bean.MenuItem>> p2) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespMenuDate>> requestRemoteIndustrySelectiveData(@org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespMenuDate>> requestRemoteSizeSelectiveData(@org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespMenuDate>> requestRemoteRentUnitSelectiveData(@org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespMenuDate>> requestRemoteRentSelectiveData(@org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespMenuDate>> requestRemotePropertySelectiveData(@org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken) {
        return null;
    }
    
    public MenuRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.menu.MenuDao menuDao) {
        super();
    }
}