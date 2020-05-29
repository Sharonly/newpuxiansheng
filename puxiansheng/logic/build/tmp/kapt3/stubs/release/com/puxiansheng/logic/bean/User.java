package com.puxiansheng.logic.bean;

import java.lang.System;

@kotlinx.android.parcel.Parcelize()
@androidx.room.Entity(tableName = "user_table", indices = {@androidx.room.Index(unique = true, value = {"_user_contact_phone"})})
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b3\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\b\u0018\u0000 c2\u00020\u0001:\u0001cB\u00a5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0015J\t\u0010G\u001a\u00020\u0003H\u00c6\u0003J\t\u0010H\u001a\u00020\u0003H\u00c6\u0003J\t\u0010I\u001a\u00020\u0007H\u00c6\u0003J\t\u0010J\u001a\u00020\u0003H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\t\u0010L\u001a\u00020\u0003H\u00c6\u0003J\t\u0010M\u001a\u00020\u0003H\u00c6\u0003J\t\u0010N\u001a\u00020\u0003H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\t\u0010P\u001a\u00020\u0003H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0007H\u00c6\u0003J\t\u0010R\u001a\u00020\u0003H\u00c6\u0003J\t\u0010S\u001a\u00020\nH\u00c6\u0003J\t\u0010T\u001a\u00020\u0007H\u00c6\u0003J\t\u0010U\u001a\u00020\u0003H\u00c6\u0003J\t\u0010V\u001a\u00020\u0003H\u00c6\u0003J\u00a9\u0001\u0010W\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\u0014\u001a\u00020\u0003H\u00c6\u0001J\t\u0010X\u001a\u00020\u0007H\u00d6\u0001J\u0013\u0010Y\u001a\u00020\'2\b\u0010Z\u001a\u0004\u0018\u00010[H\u00d6\u0003J\t\u0010\\\u001a\u00020\u0007H\u00d6\u0001J\t\u0010]\u001a\u00020\u0003H\u00d6\u0001J\u0019\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020\u0007H\u00d6\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\f\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001e\u0010\u000f\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010\u0010\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u001e\u0010\u000e\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u001e\u0010\b\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R&\u0010(\u001a\u00020\'2\u0006\u0010&\u001a\u00020\'8F@FX\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001e\u0010\u000b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001d\"\u0004\b-\u0010\u001fR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0017\"\u0004\b3\u0010\u0019R\u001e\u0010\u0012\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0017\"\u0004\b5\u0010\u0019R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0017\"\u0004\b7\u0010\u0019R\u001e\u0010\u0011\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0017\"\u0004\b9\u0010\u0019R$\u0010:\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0014\n\u0000\u0012\u0004\b;\u0010<\u001a\u0004\b=\u0010\u001d\"\u0004\b>\u0010\u001fR\u001e\u0010\r\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u0017\"\u0004\b@\u0010\u0019R\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u001d\"\u0004\bB\u0010\u001fR\u001e\u0010\u0013\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0017\"\u0004\bD\u0010\u0019R\u001e\u0010\u0014\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\u0017\"\u0004\bF\u0010\u0019\u00a8\u0006d"}, d2 = {"Lcom/puxiansheng/logic/bean/User;", "Landroid/os/Parcelable;", "account", "", "token", "nickname", "userSex", "", "icon", "loginTimestamp", "", "loginState", "actualName", "userPhoneNumber", "cityPathId", "cityId", "cityName", "userCityPath", "password", "verificationCode", "wechatCode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;JILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "setAccount", "(Ljava/lang/String;)V", "getActualName", "setActualName", "getCityId", "()I", "setCityId", "(I)V", "getCityName", "setCityName", "getCityPathId", "setCityPathId", "getIcon", "setIcon", "<anonymous parameter 0>", "", "isLogin", "()Z", "setLogin", "(Z)V", "getLoginState", "setLoginState", "getLoginTimestamp", "()J", "setLoginTimestamp", "(J)V", "getNickname", "setNickname", "getPassword", "setPassword", "getToken", "setToken", "getUserCityPath", "setUserCityPath", "userID", "userID$annotations", "()V", "getUserID", "setUserID", "getUserPhoneNumber", "setUserPhoneNumber", "getUserSex", "setUserSex", "getVerificationCode", "setVerificationCode", "getWechatCode", "setWechatCode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "logic_release"})
public final class User implements android.os.Parcelable {
    @androidx.room.ColumnInfo(name = "_user_id")
    @androidx.room.PrimaryKey(autoGenerate = true)
    private int userID;
    @androidx.room.Ignore()
    private boolean isLogin;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_account")
    private java.lang.String account;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_token")
    @com.google.gson.annotations.SerializedName(value = "token")
    private java.lang.String token;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_nickname")
    @com.google.gson.annotations.SerializedName(value = "name")
    private java.lang.String nickname;
    @androidx.room.ColumnInfo(name = "_sex")
    @com.google.gson.annotations.SerializedName(value = "sex")
    private int userSex;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_icon")
    @com.google.gson.annotations.SerializedName(value = "header_img")
    private java.lang.String icon;
    @androidx.room.ColumnInfo(name = "_login_timestamp")
    private long loginTimestamp;
    @androidx.room.ColumnInfo(name = "_login_state")
    private int loginState;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_user_contact_name")
    @com.google.gson.annotations.SerializedName(value = "actual_name")
    private java.lang.String actualName;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_user_contact_phone")
    @com.google.gson.annotations.SerializedName(value = "phone")
    private java.lang.String userPhoneNumber;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_city_path_id")
    @com.google.gson.annotations.SerializedName(value = "city_path_id")
    private java.lang.String cityPathId;
    @com.google.gson.annotations.SerializedName(value = "city_id")
    private int cityId;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_view_path_city")
    @com.google.gson.annotations.SerializedName(value = "view_path_city")
    private java.lang.String cityName;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_city_path_id")
    private java.lang.String userCityPath;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Ignore()
    private java.lang.String password;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Ignore()
    private java.lang.String verificationCode;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Ignore()
    private java.lang.String wechatCode;
    public static final com.puxiansheng.logic.bean.User.Companion Companion = null;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
    @kotlinx.android.parcel.IgnoredOnParcel()
    public static void userID$annotations() {
    }
    
    public final int getUserID() {
        return 0;
    }
    
    public final void setUserID(int p0) {
    }
    
    public final boolean isLogin() {
        return false;
    }
    
    public final void setLogin(boolean $noName_0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccount() {
        return null;
    }
    
    public final void setAccount(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final void setToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNickname() {
        return null;
    }
    
    public final void setNickname(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getUserSex() {
        return 0;
    }
    
    public final void setUserSex(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIcon() {
        return null;
    }
    
    public final void setIcon(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getLoginTimestamp() {
        return 0L;
    }
    
    public final void setLoginTimestamp(long p0) {
    }
    
    public final int getLoginState() {
        return 0;
    }
    
    public final void setLoginState(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getActualName() {
        return null;
    }
    
    public final void setActualName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserPhoneNumber() {
        return null;
    }
    
    public final void setUserPhoneNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCityPathId() {
        return null;
    }
    
    public final void setCityPathId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getCityId() {
        return 0;
    }
    
    public final void setCityId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCityName() {
        return null;
    }
    
    public final void setCityName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserCityPath() {
        return null;
    }
    
    public final void setUserCityPath(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPassword() {
        return null;
    }
    
    public final void setPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getVerificationCode() {
        return null;
    }
    
    public final void setVerificationCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWechatCode() {
        return null;
    }
    
    public final void setWechatCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public User(@org.jetbrains.annotations.NotNull()
    java.lang.String account, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String nickname, int userSex, @org.jetbrains.annotations.NotNull()
    java.lang.String icon, long loginTimestamp, int loginState, @org.jetbrains.annotations.NotNull()
    java.lang.String actualName, @org.jetbrains.annotations.NotNull()
    java.lang.String userPhoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String cityPathId, int cityId, @org.jetbrains.annotations.NotNull()
    java.lang.String cityName, @org.jetbrains.annotations.NotNull()
    java.lang.String userCityPath, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String verificationCode, @org.jetbrains.annotations.NotNull()
    java.lang.String wechatCode) {
        super();
    }
    
    public User() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.User copy(@org.jetbrains.annotations.NotNull()
    java.lang.String account, @org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    java.lang.String nickname, int userSex, @org.jetbrains.annotations.NotNull()
    java.lang.String icon, long loginTimestamp, int loginState, @org.jetbrains.annotations.NotNull()
    java.lang.String actualName, @org.jetbrains.annotations.NotNull()
    java.lang.String userPhoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String cityPathId, int cityId, @org.jetbrains.annotations.NotNull()
    java.lang.String cityName, @org.jetbrains.annotations.NotNull()
    java.lang.String userCityPath, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String verificationCode, @org.jetbrains.annotations.NotNull()
    java.lang.String wechatCode) {
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
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/puxiansheng/logic/bean/User$Companion;", "", "()V", "fromJson", "Lcom/puxiansheng/logic/bean/User;", "json", "", "logic_release"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.puxiansheng.logic.bean.User fromJson(@org.jetbrains.annotations.NotNull()
        java.lang.String json) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}