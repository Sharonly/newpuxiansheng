package com.puxiansheng.logic.data.system;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J-\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0001\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\t"}, d2 = {"Lcom/puxiansheng/logic/data/system/SystemApiService;", "", "requireSignatureToken", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "fieldMap", "", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logic_release"})
public abstract interface SystemApiService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "API.REQUIRE_TOKEN")
    @retrofit2.http.FormUrlEncoded()
    public abstract java.lang.Object requireSignatureToken(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.FieldMap()
    java.util.Map<java.lang.String, java.lang.String> fieldMap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Call<okhttp3.ResponseBody>> p1);
}