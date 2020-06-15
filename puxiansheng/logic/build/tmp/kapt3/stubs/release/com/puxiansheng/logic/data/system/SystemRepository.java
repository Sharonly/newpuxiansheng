package com.puxiansheng.logic.data.system;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000eJ\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u000f0\u000eJ,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u0016J\"\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000f0\u000e2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/puxiansheng/logic/data/system/SystemRepository;", "", "systemDao", "Lcom/puxiansheng/logic/data/system/SystemDao;", "(Lcom/puxiansheng/logic/data/system/SystemDao;)V", "localSystemConfigRepository", "Lcom/puxiansheng/logic/data/system/LocalSystemRepository;", "remoteSystemConfigRepository", "Lcom/puxiansheng/logic/data/system/RemoteSystemRepository;", "insertOrUpdateSystemConfig", "", "config", "Lcom/puxiansheng/logic/bean/SystemConfig;", "requestImageCode", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespImageCode;", "requestRemoteSystemConfig", "Lcom/puxiansheng/logic/bean/http/HttpRespSystemConfig;", "requestVerificationCode", "Lokhttp3/Response;", "phoneNumber", "", "key", "code", "type", "requireSignatureToken", "Lcom/puxiansheng/logic/bean/SignatureToken;", "device", "Lcom/puxiansheng/logic/bean/Device;", "registrationId", "logic_release"})
public final class SystemRepository {
    private final com.puxiansheng.logic.data.system.LocalSystemRepository localSystemConfigRepository = null;
    private final com.puxiansheng.logic.data.system.RemoteSystemRepository remoteSystemConfigRepository = null;
    private final com.puxiansheng.logic.data.system.SystemDao systemDao = null;
    
    public final void insertOrUpdateSystemConfig(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.SystemConfig config) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.SignatureToken>> requireSignatureToken(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.Device device, @org.jetbrains.annotations.NotNull()
    java.lang.String registrationId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> requestVerificationCode(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespSystemConfig>> requestRemoteSystemConfig() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespImageCode>> requestImageCode() {
        return null;
    }
    
    public SystemRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.system.SystemDao systemDao) {
        super();
    }
}