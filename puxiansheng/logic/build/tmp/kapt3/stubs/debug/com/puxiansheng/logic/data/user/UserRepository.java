package com.puxiansheng.logic.data.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rJ\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\nJ\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0019\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\rJ\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ<\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0019\u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\r2\b\u0010\"\u001a\u0004\u0018\u00010\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\r2\b\u0010#\u001a\u0004\u0018\u00010\rJ\u001e\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0019\u001a\u00020\r2\b\u0010!\u001a\u0004\u0018\u00010\rJ\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0019\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\rJ\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010#\u001a\u00020\rJ\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\nJ\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0019\u001a\u00020\r2\b\u0010\u001a\u001a\u0004\u0018\u00010\rJ\u0013\u0010)\u001a\u0004\u0018\u00010\u001eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J\"\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u00110\n2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010-\u001a\u00020\rJ\u0012\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020,0\u00110\nJ\u0012\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\u00110\nJ\"\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\n2\u0006\u00102\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\rJ\u001a\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\n2\u0006\u00104\u001a\u00020\rJF\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\n2\u0006\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\r2\u0006\u00108\u001a\u00020\r2\b\u00109\u001a\u0004\u0018\u00010\r2\b\u0010:\u001a\u0004\u0018\u00010\r2\u0006\u0010;\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006<"}, d2 = {"Lcom/puxiansheng/logic/data/user/UserRepository;", "", "userDao", "Lcom/puxiansheng/logic/data/user/UserDao;", "(Lcom/puxiansheng/logic/data/user/UserDao;)V", "localUserRepository", "Lcom/puxiansheng/logic/data/user/LocalUserRepository;", "remoteUserRepository", "Lcom/puxiansheng/logic/data/user/RemoteUserRepository;", "bindMobileNumber", "Lcom/puxiansheng/util/http/APIRst;", "Lokhttp3/Response;", "phone", "", "code", "id", "deleteHistorySearch", "Lcom/puxiansheng/util/http/APIResp;", "Lcom/puxiansheng/logic/bean/http/HttpRespEmpty;", "favorite", "Lcom/puxiansheng/logic/bean/http/HttpRespFavorite;", "objectID", "type", "", "forgetPassword", "userAccount", "verificationCode", "insertUser", "", "user", "Lcom/puxiansheng/logic/bean/User;", "(Lcom/puxiansheng/logic/bean/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "userPassword", "newPassword", "wechatCode", "loginByPass", "loginByPhoneNum", "loginByWeChat", "logout", "register", "requestLastLocalUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requireHistorySearch", "Lcom/puxiansheng/logic/bean/HttpSearchObject;", "userId", "requireRecommendSearch", "requireRemoteUserInfo", "Lcom/puxiansheng/logic/bean/http/HttpUserInfo;", "resetPassword", "originalPassword", "submitSuggestion", "content", "submitUserInfo", "nickName", "sex", "actulName", "iconImg", "address", "cityId", "logic_debug"})
public final class UserRepository {
    private final com.puxiansheng.logic.data.user.LocalUserRepository localUserRepository = null;
    private final com.puxiansheng.logic.data.user.RemoteUserRepository remoteUserRepository = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUser(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestLastLocalUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.puxiansheng.logic.bean.User> p0) {
        return null;
    }
    
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
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> loginByPass(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String userPassword) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> loginByPhoneNum(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String verificationCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> loginByWeChat(@org.jetbrains.annotations.NotNull()
    java.lang.String wechatCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> register(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String verificationCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> forgetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.Nullable()
    java.lang.String verificationCode) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> logout() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> resetPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String originalPassword, @org.jetbrains.annotations.NotNull()
    java.lang.String newPassword) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> submitSuggestion(@org.jetbrains.annotations.NotNull()
    java.lang.String content) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpUserInfo>> requireRemoteUserInfo() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.http.HttpRespEmpty>> submitUserInfo(@org.jetbrains.annotations.NotNull()
    java.lang.String nickName, @org.jetbrains.annotations.NotNull()
    java.lang.String sex, @org.jetbrains.annotations.NotNull()
    java.lang.String actulName, @org.jetbrains.annotations.Nullable()
    java.lang.String iconImg, @org.jetbrains.annotations.Nullable()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String cityId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.logic.bean.http.HttpRespFavorite> favorite(@org.jetbrains.annotations.NotNull()
    java.lang.String objectID, int type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<com.puxiansheng.util.http.APIResp<com.puxiansheng.logic.bean.HttpSearchObject>> requireHistorySearch(int type, @org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
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
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> bindMobileNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String phone, @org.jetbrains.annotations.NotNull()
    java.lang.String code, @org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public UserRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.user.UserDao userDao) {
        super();
    }
}