package com.puxiansheng.logic.data.user;

import java.lang.System;

@androidx.room.Database(entities = {com.puxiansheng.logic.bean.User.class}, version = 2, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/puxiansheng/logic/data/user/UserDatabase;", "Landroidx/room/RoomDatabase;", "()V", "userDao", "Lcom/puxiansheng/logic/data/user/UserDao;", "Companion", "logic_release"})
public abstract class UserDatabase extends androidx.room.RoomDatabase {
    private static com.puxiansheng.logic.data.user.UserDatabase userDatabase;
    public static final com.puxiansheng.logic.data.user.UserDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.puxiansheng.logic.data.user.UserDao userDao();
    
    public UserDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/puxiansheng/logic/data/user/UserDatabase$Companion;", "", "()V", "userDatabase", "Lcom/puxiansheng/logic/data/user/UserDatabase;", "createDatabase", "context", "Landroid/content/Context;", "getInstance", "logic_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.puxiansheng.logic.data.user.UserDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final com.puxiansheng.logic.data.user.UserDatabase createDatabase(android.content.Context context) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}