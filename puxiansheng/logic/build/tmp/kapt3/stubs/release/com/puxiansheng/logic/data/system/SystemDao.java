package com.puxiansheng.logic.data.system;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/puxiansheng/logic/data/system/SystemDao;", "", "getLocalSystemConfig", "Lcom/puxiansheng/logic/bean/SystemConfig;", "insertOrUpdateLocalSystemConfig", "", "systemConfig", "logic_release"})
public abstract interface SystemDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertOrUpdateLocalSystemConfig(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.SystemConfig systemConfig);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from table_sys_cfg where _id = 1")
    public abstract com.puxiansheng.logic.bean.SystemConfig getLocalSystemConfig();
}