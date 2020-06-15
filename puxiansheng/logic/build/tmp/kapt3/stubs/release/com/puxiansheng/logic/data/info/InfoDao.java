package com.puxiansheng.logic.data.info;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0006H\'J\u0014\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\nH\'J\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0005\u001a\u00020\u0006H\'J!\u0010\r\u001a\u00020\u00032\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000b0\u000f\"\u00020\u000bH\'\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/puxiansheng/logic/data/info/InfoDao;", "", "deleteAllFromRoom", "", "deleteByCategoryFromRoom", "category", "", "deleteByIdFromRoom", "infoId", "getFavorInfoFromRoom", "Landroidx/paging/DataSource$Factory;", "Lcom/puxiansheng/logic/bean/InfoItem;", "getInfoByCategoryFromRoom", "insertOrUpdateIntoRoom", "info", "", "([Lcom/puxiansheng/logic/bean/InfoItem;)V", "logic_release"})
public abstract interface InfoDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertOrUpdateIntoRoom(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.InfoItem... info);
    
    @androidx.room.Query(value = "delete from table_info")
    public abstract void deleteAllFromRoom();
    
    @androidx.room.Query(value = "delete from table_info where _category = :category")
    public abstract void deleteByCategoryFromRoom(int category);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_info where _category = :category")
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getInfoByCategoryFromRoom(int category);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_info")
    public abstract androidx.paging.DataSource.Factory<java.lang.Integer, com.puxiansheng.logic.bean.InfoItem> getFavorInfoFromRoom();
    
    @androidx.room.Query(value = "delete from table_info where _info_id = :infoId")
    public abstract void deleteByIdFromRoom(int infoId);
}