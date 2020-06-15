package com.puxiansheng.logic.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003J\u000e\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0003J\u000e\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/puxiansheng/logic/api/HttpInterceptor;", "Lokhttp3/Interceptor;", "signatureToken", "", "authToken", "cityId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "setAuthToken", "", "token", "setCityId", "id", "setSignatureToken", "logic_release"})
public final class HttpInterceptor implements okhttp3.Interceptor {
    private java.lang.String signatureToken;
    private java.lang.String authToken;
    private java.lang.String cityId;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public okhttp3.Response intercept(@org.jetbrains.annotations.NotNull()
    okhttp3.Interceptor.Chain chain) {
        return null;
    }
    
    public final void setSignatureToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void setAuthToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void setCityId(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    public HttpInterceptor(@org.jetbrains.annotations.NotNull()
    java.lang.String signatureToken, @org.jetbrains.annotations.NotNull()
    java.lang.String authToken, @org.jetbrains.annotations.NotNull()
    java.lang.String cityId) {
        super();
    }
}