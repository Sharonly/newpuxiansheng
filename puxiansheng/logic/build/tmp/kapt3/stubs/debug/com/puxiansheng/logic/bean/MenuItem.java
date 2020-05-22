package com.puxiansheng.logic.bean;

import java.lang.System;

@kotlinx.android.parcel.Parcelize()
@androidx.room.Entity(tableName = "table_menu", indices = {@androidx.room.Index(unique = true, value = {"_menu_id", "_type"})})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b+\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001:\u0001>BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\b\u00a2\u0006\u0002\u0010\rJ\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\bH\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\bH\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003JY\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\bH\u00c6\u0001J\t\u00102\u001a\u00020\u0005H\u00d6\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106H\u00d6\u0003J\t\u00107\u001a\u00020\u0005H\u00d6\u0001J\t\u00108\u001a\u00020\bH\u00d6\u0001J\u0019\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0005H\u00d6\u0001R\u001e\u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001e\u0010\u000b\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R$\u0010\u0016\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR\u001e\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u000f\"\u0004\b\"\u0010\u0011R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010$\"\u0004\b(\u0010&\u00a8\u0006?"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem;", "Landroid/os/Parcelable;", "menuID", "", "type", "", "parentID", "text", "", "color", "value", "icon_enable", "icon_disable", "(JIJLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getColor", "()Ljava/lang/String;", "setColor", "(Ljava/lang/String;)V", "getIcon_disable", "setIcon_disable", "getIcon_enable", "setIcon_enable", "itemID", "itemID$annotations", "()V", "getItemID", "()J", "setItemID", "(J)V", "getMenuID", "setMenuID", "getParentID", "setParentID", "getText", "setText", "getType", "()I", "setType", "(I)V", "getValue", "setValue", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "TYPE", "logic_debug"})
public final class MenuItem implements android.os.Parcelable {
    @androidx.room.ColumnInfo(name = "_id")
    @androidx.room.PrimaryKey(autoGenerate = true)
    private long itemID;
    @androidx.room.ColumnInfo(name = "_menu_id")
    @com.google.gson.annotations.SerializedName(value = "id")
    private long menuID;
    @androidx.room.ColumnInfo(name = "_type")
    private int type;
    @androidx.room.ColumnInfo(name = "_parent_id")
    @com.google.gson.annotations.SerializedName(value = "pid")
    private long parentID;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_text")
    @com.google.gson.annotations.SerializedName(value = "name")
    private java.lang.String text;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_color")
    @com.google.gson.annotations.SerializedName(value = "color")
    private java.lang.String color;
    @androidx.room.ColumnInfo(name = "_value")
    @com.google.gson.annotations.SerializedName(value = "value")
    private int value;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_icon_enable")
    @com.google.gson.annotations.SerializedName(value = "icon")
    private java.lang.String icon_enable;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_icon_disable")
    @com.google.gson.annotations.SerializedName(value = "colorless_icon")
    private java.lang.String icon_disable;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @kotlinx.android.parcel.IgnoredOnParcel()
    public static void itemID$annotations() {
    }
    
    public final long getItemID() {
        return 0L;
    }
    
    public final void setItemID(long p0) {
    }
    
    public final long getMenuID() {
        return 0L;
    }
    
    public final void setMenuID(long p0) {
    }
    
    public final int getType() {
        return 0;
    }
    
    public final void setType(int p0) {
    }
    
    public final long getParentID() {
        return 0L;
    }
    
    public final void setParentID(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getText() {
        return null;
    }
    
    public final void setText(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getColor() {
        return null;
    }
    
    public final void setColor(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getValue() {
        return 0;
    }
    
    public final void setValue(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIcon_enable() {
        return null;
    }
    
    public final void setIcon_enable(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIcon_disable() {
        return null;
    }
    
    public final void setIcon_disable(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public MenuItem(long menuID, int type, long parentID, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String color, int value, @org.jetbrains.annotations.NotNull()
    java.lang.String icon_enable, @org.jetbrains.annotations.NotNull()
    java.lang.String icon_disable) {
        super();
    }
    
    public MenuItem() {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.MenuItem copy(long menuID, int type, long parentID, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String color, int value, @org.jetbrains.annotations.NotNull()
    java.lang.String icon_enable, @org.jetbrains.annotations.NotNull()
    java.lang.String icon_disable) {
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
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.Object createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem$TYPE;", "", "(Ljava/lang/String;I)V", "value", "", "INDUSTRY", "SIZE", "RENT_UNIT", "RENT", "PROPERTY", "logic_debug"})
    public static enum TYPE {
        /*public static final*/ INDUSTRY /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem$TYPE$INDUSTRY;", "Lcom/puxiansheng/logic/bean/MenuItem$TYPE;", "value", "", "logic_debug"}) INDUSTRY(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            INDUSTRY() {
                super();
            }
        } */,
        /*public static final*/ SIZE /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem$TYPE$SIZE;", "Lcom/puxiansheng/logic/bean/MenuItem$TYPE;", "value", "", "logic_debug"}) SIZE(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            SIZE() {
                super();
            }
        } */,
        /*public static final*/ RENT_UNIT /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem$TYPE$RENT_UNIT;", "Lcom/puxiansheng/logic/bean/MenuItem$TYPE;", "value", "", "logic_debug"}) RENT_UNIT(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            RENT_UNIT() {
                super();
            }
        } */,
        /*public static final*/ RENT /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem$TYPE$RENT;", "Lcom/puxiansheng/logic/bean/MenuItem$TYPE;", "value", "", "logic_debug"}) RENT(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            RENT() {
                super();
            }
        } */,
        /*public static final*/ PROPERTY /* = new @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u00c6\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016\u00a8\u0006\u0004"}, d2 = {"Lcom/puxiansheng/logic/bean/MenuItem$TYPE$PROPERTY;", "Lcom/puxiansheng/logic/bean/MenuItem$TYPE;", "value", "", "logic_debug"}) PROPERTY(){
            
            @java.lang.Override()
            public int value() {
                return 0;
            }
            
            PROPERTY() {
                super();
            }
        } */;
        
        public abstract int value();
        
        TYPE() {
        }
    }
}