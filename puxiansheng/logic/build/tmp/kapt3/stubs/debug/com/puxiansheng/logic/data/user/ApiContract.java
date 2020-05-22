package com.puxiansheng.logic.data.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\b\b\u0001\u0010\n\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\u0007H&J$\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00040\u00032\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H&\u00a8\u0006\u000f"}, d2 = {"Lcom/puxiansheng/logic/data/user/ApiContract;", "", "login", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/User;", "userAccount", "", "userPassword", "signatureToken", "verificationCode", "wechatCode", "requireRemoteUserInfo", "Lcom/puxiansheng/logic/bean/http/HttpRespUserInfo;", "authToken", "logic_debug"})
public abstract interface ApiContract {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.User>> login(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.NotNull()
    java.lang.String userPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.Nullable()
    java.lang.String verificationCode, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.Nullable()
    java.lang.String wechatCode);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespUserInfo>> requireRemoteUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String authToken, @org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken);
}