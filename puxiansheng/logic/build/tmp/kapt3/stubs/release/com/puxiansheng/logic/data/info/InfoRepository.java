package com.puxiansheng.logic.data.info;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fJ\u000e\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0014J\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\r0\f2\u0006\u0010\u0018\u001a\u00020\u0014J\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001b0\u001aJ\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\r0\f2\u0006\u0010\u0018\u001a\u00020\u0014J:\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\r0\f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00142\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0010J\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\r0\fJ\u001f\u0010%\u001a\u00020\n2\u0012\u0010&\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001b0\'\"\u00020\u001b\u00a2\u0006\u0002\u0010(R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/puxiansheng/logic/data/info/InfoRepository;", "", "infoDao", "Lcom/puxiansheng/logic/data/info/InfoDao;", "(Lcom/puxiansheng/logic/data/info/InfoDao;)V", "localInfoRepository", "Lcom/puxiansheng/logic/data/info/LocalInfoRepository;", "remoteInfoRepository", "Lcom/puxiansheng/logic/data/info/RemoteInfoRepository;", "deleteAllInfoFromRoom", "", "deleteFavorInfoFromRemote", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespEmpty;", "infoId", "", "deleteHistoryInfoFromRemote", "deleteInfoByCategoryFromRoom", "category", "", "deleteInfoByIdFromRoom", "getFavorInfoFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespFavorInfoList;", "page", "getFavorInfoFromRoom", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/InfoItem;", "getHistoryInfoFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespHistoryInfoList;", "getInfoByCategoryFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespInfoList;", "city", "title", "getInfoByCategoryFromRoom", "getInfoCategoriesFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespInfoCategory;", "insertInfoIntoRoom", "info", "", "([Lcom/puxiansheng/logic/bean/InfoItem;)V", "logic_release"})
public final class InfoRepository {
    private final com.puxiansheng.logic.data.info.LocalInfoRepository localInfoRepository = null;
    private final com.puxiansheng.logic.data.info.RemoteInfoRepository remoteInfoRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespInfoCategory>> getInfoCategoriesFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespInfoList>> getInfoByCategoryFromRemote(int category, int page, @org.jetbrains.annotations.Nullable()
    java.lang.String city, @org.jetbrains.annotations.Nullable()
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getInfoByCategoryFromRoom(int category) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespFavorInfoList>> getFavorInfoFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespHistoryInfoList>> getHistoryInfoFromRemote(int page) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> deleteHistoryInfoFromRemote() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> deleteFavorInfoFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String infoId) {
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