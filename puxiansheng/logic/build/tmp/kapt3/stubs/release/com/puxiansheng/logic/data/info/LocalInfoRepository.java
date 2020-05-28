package com.puxiansheng.logic.data.info;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\tJ\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\rJ\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\b\u001a\u00020\tJ\u001f\u0010\u0010\u001a\u00020\u00062\u0012\u0010\u0011\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000e0\u0012\"\u00020\u000e\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/puxiansheng/logic/data/info/LocalInfoRepository;", "", "infoDao", "Lcom/puxiansheng/logic/data/info/InfoDao;", "(Lcom/puxiansheng/logic/data/info/InfoDao;)V", "deleteAllFavorInfo", "", "deleteInfoByCategoryFromRoom", "category", "", "deleteInfoByIdFromRoom", "infoId", "getFavorInfoFromRoom", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/InfoItem;", "getInfoByCategoryFromRoom", "insertInfoIntoRoom", "info", "", "([Lcom/puxiansheng/logic/bean/InfoItem;)V", "logic_release"})
public final class LocalInfoRepository {
    private final com.puxiansheng.logic.data.info.InfoDao infoDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getInfoByCategoryFromRoom(int category) {
        return null;
    }
    
    public final void insertInfoIntoRoom(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.InfoItem... info) {
    }
    
    public final void deleteAllFavorInfo() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getFavorInfoFromRoom() {
        return null;
    }
    
    public final void deleteInfoByIdFromRoom(int infoId) {
    }
    
    public final void deleteInfoByCategoryFromRoom(int category) {
    }
    
    public LocalInfoRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.info.InfoDao infoDao) {
        super();
    }
}