package com.puxiansheng.logic.data.user;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/puxiansheng/logic/data/user/UserDao;", "", "insert", "", "user", "Lcom/puxiansheng/logic/bean/User;", "(Lcom/puxiansheng/logic/bean/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isUserExist", "userAccount", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestLastUser", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logic_debug"})
public abstract interface UserDao {
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.puxiansheng.logic.bean.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from user_table where _account = :userAccount")
    public abstract java.lang.Object isUserExist(@org.jetbrains.annotations.NotNull()
    java.lang.String userAccount, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.puxiansheng.logic.bean.User> p1);
    
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Query(value = "select * from user_table order by _login_timestamp desc limit 1")
    public abstract java.lang.Object requestLastUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.puxiansheng.logic.bean.User> p0);
}