package com.puxiansheng.logic.data.business;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fJ\u001f\u0010\u000f\u001a\u00020\n2\u0012\u0010\u0010\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0011\"\u00020\u000e\u00a2\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u00142\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0019J\u001a\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00150\u00142\u0006\u0010\u001b\u001a\u00020\u0019J*\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u00150\u00142\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/puxiansheng/logic/data/business/BusinessRepository;", "", "businessDao", "Lcom/puxiansheng/logic/data/business/BusinessDao;", "(Lcom/puxiansheng/logic/data/business/BusinessDao;)V", "localBusinessRepository", "Lcom/puxiansheng/logic/data/business/LocalBusinessRepository;", "remoteRepository", "Lcom/puxiansheng/logic/data/business/RemoteBusinessRepository;", "deleteBusinessFromRoom", "", "getBusinessFromRoom", "Landroidx/paging/DataSource$Factory;", "", "Lcom/puxiansheng/logic/bean/BusinessBean;", "insertBusinessInfo", "business", "", "([Lcom/puxiansheng/logic/bean/BusinessBean;)V", "requestRemoteBusiness", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespBusinessList;", "page", "title", "", "requestRemoteBusinessDetail", "id", "submitBusinessUserInfo", "Lcom/puxiansheng/logic/bean/http/HttpRespEmpty;", "name", "phone", "logic_release"})
public final class BusinessRepository {
    private final com.puxiansheng.logic.data.business.RemoteBusinessRepository remoteRepository = null;
    private final com.puxiansheng.logic.data.business.LocalBusinessRepository localBusinessRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespBusinessList>> requestRemoteBusiness(int page, @org.jetbrains.annotations.NotNull()
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.BusinessBean>> requestRemoteBusinessDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.BusinessBean> getBusinessFromRoom() {
        return null;
    }
    
    public final void deleteBusinessFromRoom() {
    }
    
    public final void insertBusinessInfo(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.BusinessBean... business) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> submitBusinessUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String phone) {
        return null;
    }
    
    public BusinessRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.business.BusinessDao businessDao) {
        super();
    }
}