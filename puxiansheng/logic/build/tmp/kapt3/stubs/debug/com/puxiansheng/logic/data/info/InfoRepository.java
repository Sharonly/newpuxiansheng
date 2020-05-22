package com.puxiansheng.logic.data.info;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\rJ\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00112\u0006\u0010\u0014\u001a\u00020\rJ\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00170\u0016J\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00120\u00112\u0006\u0010\u0014\u001a\u00020\rJ.\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00112\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\r2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001cJ\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u00120\u0011J\u001f\u0010 \u001a\u00020\n2\u0012\u0010!\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\"\"\u00020\u0017\u00a2\u0006\u0002\u0010#R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/puxiansheng/logic/data/info/InfoRepository;", "", "infoDao", "Lcom/puxiansheng/logic/data/info/InfoDao;", "(Lcom/puxiansheng/logic/data/info/InfoDao;)V", "localInfoRepository", "Lcom/puxiansheng/logic/data/info/LocalInfoRepository;", "remoteInfoRepository", "Lcom/puxiansheng/logic/data/info/RemoteInfoRepository;", "deleteAllInfoFromRoom", "", "deleteInfoByCategoryFromRoom", "category", "", "deleteInfoByIdFromRoom", "infoId", "getFavorInfoFromRemote", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespInfoList;", "page", "getFavorInfoFromRoom", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/InfoItem;", "getHistoryInfoFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespHistoryInfoList;", "getInfoByCategoryFromRemote", "city", "", "getInfoByCategoryFromRoom", "getInfoCategoriesFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespInfoCategory;", "insertInfoIntoRoom", "info", "", "([Lcom/puxiansheng/logic/bean/InfoItem;)V", "logic_debug"})
public final class InfoRepository {
    private final com.puxiansheng.logic.data.info.LocalInfoRepository localInfoRepository = null;
    private final com.puxiansheng.logic.data.info.RemoteInfoRepository remoteInfoRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespInfoCategory>> getInfoCategoriesFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespInfoList>> getInfoByCategoryFromRemote(int category, int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getInfoByCategoryFromRoom(int category) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespInfoList>> getFavorInfoFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespHistoryInfoList>> getHistoryInfoFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getFavorInfoFromRoom() {
        return null;
    }
    
    public final void deleteInfoByCategoryFromRoom(int category) {
    }
    
    public final void deleteInfoByIdFromRoom(int infoId) {
    }
    
    public final void insertInfoIntoRoom(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.InfoItem... info) {
    }
    
    public final void deleteAllInfoFromRoom() {
    }
    
    public InfoRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.info.InfoDao infoDao) {
        super();
    }
}