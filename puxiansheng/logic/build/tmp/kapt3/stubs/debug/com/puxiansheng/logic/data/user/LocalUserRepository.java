package com.puxiansheng.logic.data.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0013\u0010\n\u001a\u0004\u0018\u00010\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/puxiansheng/logic/data/user/LocalUserRepository;", "", "userDao", "Lcom/puxiansheng/logic/data/user/UserDao;", "(Lcom/puxiansheng/logic/data/user/UserDao;)V", "insertUser", "", "user", "Lcom/puxiansheng/logic/bean/User;", "(Lcom/puxiansheng/logic/bean/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestLastUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logic_debug"})
public final class LocalUserRepository {
    private final com.puxiansheng.logic.data.user.UserDao userDao = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertUser(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object requestLastUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.puxiansheng.logic.bean.User> p0) {
        return null;
    }
    
    public LocalUserRepository(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.data.user.UserDao userDao) {
        super();
    }
}