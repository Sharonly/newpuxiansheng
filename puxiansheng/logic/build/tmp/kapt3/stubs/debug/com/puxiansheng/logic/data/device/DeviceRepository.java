package com.puxiansheng.logic.data.device;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fJ\b\u0010\r\u001a\u00020\bH\u0002J\u0006\u0010\u000e\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/puxiansheng/logic/data/device/DeviceRepository;", "", "deviceDao", "Lcom/puxiansheng/logic/data/device/DeviceDao;", "(Lcom/puxiansheng/logic/data/device/DeviceDao;)V", "deleteDevice", "", "device", "Lcom/puxiansheng/logic/bean/Device;", "uid", "", "getDevice", "Landroidx/lifecycle/LiveData;", "getLocalDevice", "requireDevice", "logic_debug"})
public final class DeviceRepository {
    private final com.puxiansheng.logic.data.device.DeviceDao deviceDao = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.puxiansheng.logic.bean.Device> getDevice() {
        return null;
    }
    
    public final void requireDevice() {
    }
    
    public final void deleteDevice(int uid) {
    }
    
    public final void deleteDevice(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.Device device) {
    }
    
    private final com.puxiansheng.logic.bean.Device getLocalDevice() {
        return null;
    }
    
    public DeviceRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.device.DeviceDao deviceDao) {
        super();
    }
}