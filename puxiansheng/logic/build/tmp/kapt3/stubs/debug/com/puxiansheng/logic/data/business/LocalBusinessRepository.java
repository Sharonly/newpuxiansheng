package com.puxiansheng.logic.data.business;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bJ\u001f\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\r\"\u00020\n\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/puxiansheng/logic/data/business/LocalBusinessRepository;", "", "businessDao", "Lcom/puxiansheng/logic/data/business/BusinessDao;", "(Lcom/puxiansheng/logic/data/business/BusinessDao;)V", "deleteAllBusiness", "", "getBusinessInfoFromRoom", "Landroidx/paging/DataSource$Factory;", "", "Lcom/puxiansheng/logic/bean/BusinessBean;", "insertBusinessInfoIntoRoom", "info", "", "([Lcom/puxiansheng/logic/bean/BusinessBean;)V", "logic_debug"})
public final class LocalBusinessRepository {
    private com.puxiansheng.logic.data.business.BusinessDao businessDao;
    
    public final void insertBusinessInfoIntoRoom(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.BusinessBean... info) {
    }
    
    public final void deleteAllBusiness() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.BusinessBean> getBusinessInfoFromRoom() {
        return null;
    }
    
    public LocalBusinessRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.business.BusinessDao businessDao) {
        super();
    }
}