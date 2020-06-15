package com.puxiansheng.logic.data.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007J\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0004J\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00042\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011J,\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007J\u0012\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u000b0\u0004J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000b0\u0004J\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u000b0\u0004J\u0012\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u000b0\u0004J<\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00072\b\u0010\u001f\u001a\u0004\u0018\u00010\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0007J\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u0007J\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010$\u001a\u00020\u0007J\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0004J\u001e\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0013\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\"\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u000b0\u00042\u0006\u0010)\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011J\u0012\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020(0\u000b0\u0004J\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u000b0\u0004J2\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00042\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007J\"\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00042\u0006\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u00020\u0007J\u001a\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002030\u000b0\u00042\u0006\u00104\u001a\u000205JJ\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00042\u0006\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u00072\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00072\u0006\u0010<\u001a\u00020\u0011\u00a8\u0006="}, d2 = {"Lcom/puxiansheng/logic/data/user/RemoteUserRepository;", "", "()V", "bindMobileNumber", "Lcom/puxiansheng/util/http/APIRst;", "Lokhttp3/Response;", "phone", "", "code", "id", "deleteHistorySearch", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespEmpty;", "favorite", "Lcom/puxiansheng/logic/bean/http/HttpRespFavorite;", "objectID", "type", "", "forgetPassword", "userAccount", "password", "newPassword", "getReleaseCountFromRemote", "Lcom/puxiansheng/logic/bean/http/HttpRespReleaseCountData;", "getRequestList", "Lcom/puxiansheng/logic/bean/http/HttpRespRequest;", "getRequestType", "Lcom/puxiansheng/logic/bean/http/HttpRespMenuDate;", "getUserCallBack", "login", "userPassword", "verificationCode", "wechatCode", "loginByPass", "loginByPhoneNum", "loginByWeChat", "weChatCode", "logout", "register", "requireHistorySearch", "Lcom/puxiansheng/logic/bean/HttpSearchObject;", "userId", "requireRecommendSearch", "requireRemoteUserInfo", "Lcom/puxiansheng/logic/bean/http/HttpUserInfo;", "resetPassword", "newPasswordAgain", "submitSuggestion", "content", "cate", "submitUserIcon", "Lcom/puxiansheng/logic/bean/http/HttpRespIconUpload;", "file", "Ljava/io/File;", "submitUserInfo", "actualName", "sex", "nickName", "headerImg", "address", "cityId", "logic_debug"})
public final class RemoteUserRepository {
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> login(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String userPassword, @org.jetbrains.annotations.Nullable()
    java.lang.String newPassword, @org.jetbrains.annotations.Nullable()
    java.lang.String verificationCode, @org.jetbrains.annotations.Nullable()
    java.lang.String wechatCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> register(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String code) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> forgetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> loginByPass(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String userPassword) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> loginByPhoneNum(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String code) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> loginByWeChat(@org.jetbrains.annotations.NotNull()
    java.lang.String weChatCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> logout() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> resetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String newPasswordAgain, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpUserInfo>> requireRemoteUserInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.logic.bean.http.HttpRespFavorite> favorite(@org.jetbrains.annotations.NotNull()
    java.lang.String objectID, int type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> submitUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String actualName, @org.jetbrains.annotations.NotNull()
    java.lang.String sex, @org.jetbrains.annotations.NotNull()
    java.lang.String nickName, @org.jetbrains.annotations.Nullable()
    java.lang.String headerImg, @org.jetbrains.annotations.Nullable()
    java.lang.String address, int cityId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespIconUpload>> submitUserIcon(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> submitSuggestion(@org.jetbrains.annotations.NotNull()
    java.lang.String content, @org.jetbrains.annotations.NotNull()
    java.lang.String cate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespMenuDate>> getRequestType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespRequest>> getRequestList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespRequest>> getUserCallBack() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> bindMobileNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.HttpSearchObject>> requireHistorySearch(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, int type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> deleteHistorySearch() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.HttpSearchObject>> requireRecommendSearch() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespReleaseCountData>> getReleaseCountFromRemote() {
        return null;
    }
    
    public RemoteUserRepository() {
        super();
    }
}