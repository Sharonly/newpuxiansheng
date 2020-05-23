package com.puxiansheng.logic.data.business;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0014\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\'J!\u0010\b\u001a\u00020\u00032\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\n\"\u00020\u0007H\'\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2 = {"Lcom/puxiansheng/logic/data/business/BusinessDao;", "", "deleteAllFromRoom", "", "getBusinessInfoFromRoom", "Landroidx/paging/DataSource$Factory;", "", "Lcom/puxiansheng/logic/bean/BusinessBean;", "insertOrUpdateIntoRoom", "info", "", "([Lcom/puxiansheng/logic/bean/BusinessBean;)V", "logic_debug"})
public abstract interface BusinessDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertOrUpdateIntoRoom(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.BusinessBean... info);
    
    @androidx.room.Query(value = "delete from table_business")
    public abstract void deleteAllFromRoom();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_business")
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.BusinessBean> getBusinessInfoFromRoom();
}