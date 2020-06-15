package com.puxiansheng.logic.data.device;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0012\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\'J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'\u00a8\u0006\u000b"}, d2 = {"Lcom/puxiansheng/logic/data/device/DeviceDao;", "", "delete", "", "device", "Lcom/puxiansheng/logic/bean/Device;", "id", "", "get", "Landroidx/lifecycle/LiveData;", "insertOrUpdate", "logic_release"})
public abstract interface DeviceDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertOrUpdate(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.Device device);
    
    @androidx.room.Query(value = "delete from table_device where _id = :id")
    public abstract void delete(int id);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.Device device);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_device where _id = 1")
    public abstract androidx.lifecycle.LiveData<com.puxiansheng.logic.bean.Device> get();
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
}