package com.puxiansheng.logic.bean;

import java.lang.System;

@androidx.room.Entity(tableName = "table_sys_cfg")
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u000bH\u00c6\u0003JO\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00d6\u0001J\t\u0010#\u001a\u00020\u000bH\u00d6\u0001R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\u00a8\u0006$"}, d2 = {"Lcom/puxiansheng/logic/bean/SystemConfig;", "", "id", "", "maxUploadFileLength", "", "maxUploadFile", "networkConnectReadTimeout", "networkConnectWriteTimeout", "networkConnectTimeout", "defaultUserIcon", "", "(IJIIIILjava/lang/String;)V", "getDefaultUserIcon", "()Ljava/lang/String;", "getId", "()I", "getMaxUploadFile", "getMaxUploadFileLength", "()J", "getNetworkConnectReadTimeout", "getNetworkConnectTimeout", "getNetworkConnectWriteTimeout", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "logic_release"})
public final class SystemConfig {
    @androidx.room.ColumnInfo(name = "_id")
    @androidx.room.PrimaryKey(autoGenerate = false)
    private final int id = 0;
    @androidx.room.ColumnInfo(name = "_max_upload_file_size")
    @com.google.gson.annotations.SerializedName(value = "max_file_length")
    private final long maxUploadFileLength = 0L;
    @androidx.room.ColumnInfo(name = "_max_upload_file")
    @com.google.gson.annotations.SerializedName(value = "max_upload")
    private final int maxUploadFile = 0;
    @androidx.room.ColumnInfo(name = "_network_connect_read_timeout")
    @com.google.gson.annotations.SerializedName(value = "conn_read_time_out")
    private final int networkConnectReadTimeout = 0;
    @androidx.room.ColumnInfo(name = "_network_connect_write_timeout")
    @com.google.gson.annotations.SerializedName(value = "conn_write_time_out")
    private final int networkConnectWriteTimeout = 0;
    @androidx.room.ColumnInfo(name = "_network_connect_timeout")
    @com.google.gson.annotations.SerializedName(value = "conn_time_out")
    private final int networkConnectTimeout = 0;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_def_user_icon")
    @com.google.gson.annotations.SerializedName(value = "default_header_img")
    private final java.lang.String defaultUserIcon = null;
    
    public final int getId() {
        return 0;
    }
    
    public final long getMaxUploadFileLength() {
        return 0L;
    }
    
    public final int getMaxUploadFile() {
        return 0;
    }
    
    public final int getNetworkConnectReadTimeout() {
        return 0;
    }
    
    public final int getNetworkConnectWriteTimeout() {
        return 0;
    }
    
    public final int getNetworkConnectTimeout() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDefaultUserIcon() {
        return null;
    }
    
    public SystemConfig(int id, long maxUploadFileLength, int maxUploadFile, int networkConnectReadTimeout, int networkConnectWriteTimeout, int networkConnectTimeout, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultUserIcon) {
        super();
    }
    
    public SystemConfig() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.SystemConfig copy(int id, long maxUploadFileLength, int maxUploadFile, int networkConnectReadTimeout, int networkConnectWriteTimeout, int networkConnectTimeout, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultUserIcon) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}