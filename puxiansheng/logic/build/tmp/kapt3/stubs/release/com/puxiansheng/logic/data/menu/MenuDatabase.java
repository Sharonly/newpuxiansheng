package com.puxiansheng.logic.data.menu;

import java.lang.System;

@androidx.room.Database(entities = {com.puxiansheng.logic.bean.MenuItem.class}, version = 3, exportSchema = false)
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/puxiansheng/logic/data/menu/MenuDatabase;", "Landroidx/room/RoomDatabase;", "()V", "menuDao", "Lcom/puxiansheng/logic/data/menu/MenuDao;", "Companion", "logic_release"})
public abstract class MenuDatabase extends androidx.room.RoomDatabase {
    private static com.puxiansheng.logic.data.menu.MenuDatabase menuDatabase;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_2_3 = null;
    public static final com.puxiansheng.logic.data.menu.MenuDatabase.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.puxiansheng.logic.data.menu.MenuDao menuDao();
    
    public MenuDatabase() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/puxiansheng/logic/data/menu/MenuDatabase$Companion;", "", "()V", "MIGRATION_2_3", "Landroidx/room/migration/Migration;", "getMIGRATION_2_3", "()Landroidx/room/migration/Migration;", "menuDatabase", "Lcom/puxiansheng/logic/data/menu/MenuDatabase;", "createDatabase", "context", "Landroid/content/Context;", "getInstance", "logic_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.puxiansheng.logic.data.menu.MenuDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
        
        private final com.puxiansheng.logic.data.menu.MenuDatabase createDatabase(android.content.Context context) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_2_3() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}