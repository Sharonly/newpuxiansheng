package com.puxiansheng.logic.bean;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.puxiansheng.logic.bean.converter.LocationNodeListConverter.class})
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\nH\u00c6\u0003J;\u0010 \u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\t\u0010!\u001a\u00020\bH\u00d6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u00d6\u0003J\t\u0010&\u001a\u00020\bH\u00d6\u0001J\t\u0010\'\u001a\u00020\u0006H\u00d6\u0001J\u0019\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\bH\u00d6\u0001R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R&\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b\u00a8\u0006-"}, d2 = {"Lcom/puxiansheng/logic/bean/Address;", "Landroid/os/Parcelable;", "locationNodes", "", "Lcom/puxiansheng/logic/bean/LocationNode;", "addressDetail", "", "postCode", "", "coordinates", "Lcom/puxiansheng/logic/bean/Coordinates;", "(Ljava/util/List;Ljava/lang/String;ILcom/puxiansheng/logic/bean/Coordinates;)V", "getAddressDetail", "()Ljava/lang/String;", "setAddressDetail", "(Ljava/lang/String;)V", "getCoordinates", "()Lcom/puxiansheng/logic/bean/Coordinates;", "setCoordinates", "(Lcom/puxiansheng/logic/bean/Coordinates;)V", "getLocationNodes", "()Ljava/util/List;", "setLocationNodes", "(Ljava/util/List;)V", "getPostCode", "()I", "setPostCode", "(I)V", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "logic_debug"})
public final class Address implements android.os.Parcelable {
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_location_nodes")
    @com.google.gson.annotations.SerializedName(value = "location_nodes")
    private java.util.List<com.puxiansheng.logic.bean.LocationNode> locationNodes;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_address_description")
    @com.google.gson.annotations.SerializedName(value = "address")
    private java.lang.String addressDetail;
    @androidx.room.ColumnInfo(name = "_post_code")
    @com.google.gson.annotations.SerializedName(value = "post_code")
    private int postCode;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Embedded()
    @com.google.gson.annotations.SerializedName(value = "coordinates")
    private com.puxiansheng.logic.bean.Coordinates coordinates;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.LocationNode> getLocationNodes() {
        return null;
    }
    
    public final void setLocationNodes(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.LocationNode> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAddressDetail() {
        return null;
    }
    
    public final void setAddressDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getPostCode() {
        return 0;
    }
    
    public final void setPostCode(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Coordinates getCoordinates() {
        return null;
    }
    
    public final void setCoordinates(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Coordinates p0) {
    }
    
    public Address(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.LocationNode> locationNodes, @org.jetbrains.annotations.NotNull()
    java.lang.String addressDetail, int postCode, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Coordinates coordinates) {
        super();
    }
    
    public Address() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.LocationNode> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Coordinates component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.Address copy(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.LocationNode> locationNodes, @org.jetbrains.annotations.NotNull()
    java.lang.String addressDetail, int postCode, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Coordinates coordinates) {
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
}