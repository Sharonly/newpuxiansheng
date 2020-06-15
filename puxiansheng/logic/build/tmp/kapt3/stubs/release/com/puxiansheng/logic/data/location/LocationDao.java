package com.puxiansheng.logic.data.location;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0003H&\u00a8\u0006\u0007"}, d2 = {"Lcom/puxiansheng/logic/data/location/LocationDao;", "", "requestLocalCitiesByProvince", "", "requestLocalCityByID", "requestLocalProvinceByID", "requestLocalProvinces", "logic_release"})
public abstract interface LocationDao {
    
    public abstract void requestLocalCityByID();
    
    public abstract void requestLocalProvinceByID();
    
    public abstract void requestLocalProvinces();
    
    public abstract void requestLocalCitiesByProvince();
}