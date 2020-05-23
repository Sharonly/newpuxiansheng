package com.puxiansheng.logic.bean;

import java.lang.System;

@androidx.room.Entity(tableName = "table_business")
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0087\b\u0018\u0000 <2\u00020\u0001:\u0001<B\u008d\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0012J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0006H\u00c6\u0003J\t\u0010*\u001a\u00020\u0006H\u00c6\u0003J\t\u0010+\u001a\u00020\u0006H\u00c6\u0003J\t\u0010,\u001a\u00020\u0006H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0006H\u00c6\u0003J\t\u0010/\u001a\u00020\u0006H\u00c6\u0003J\t\u00100\u001a\u00020\u0006H\u00c6\u0003J\t\u00101\u001a\u00020\u0006H\u00c6\u0003J\u0016\u00102\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010 J\t\u00103\u001a\u00020\u0006H\u00c6\u0003J\t\u00104\u001a\u00020\u0006H\u00c6\u0003J\u0098\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0006H\u00c6\u0001\u00a2\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\u0003H\u00d6\u0001J\t\u0010;\u001a\u00020\u0006H\u00d6\u0001R\u0016\u0010\b\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\t\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0016\u0010\f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0016\u0010\r\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0016\u0010\u000e\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u001eR(\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0016\u0010\u000f\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014R\u0016\u0010\u0010\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0014R\u0016\u0010\u0011\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0014R\u0016\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0014\u00a8\u0006="}, d2 = {"Lcom/puxiansheng/logic/bean/BusinessBean;", "", "shopID", "", "id", "name", "", "trades", "contact_phone", "investment", "shop_Keywords", "", "large_img", "large_img_alt", "large_img_title", "thumb_img", "thumb_img_alt", "thumb_img_title", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContact_phone", "()Ljava/lang/String;", "getId", "()I", "getInvestment", "getLarge_img", "getLarge_img_alt", "getLarge_img_title", "getName", "getShopID", "setShopID", "(I)V", "getShop_Keywords", "()[Ljava/lang/String;", "setShop_Keywords", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "getThumb_img", "getThumb_img_alt", "getThumb_img_title", "getTrades", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/puxiansheng/logic/bean/BusinessBean;", "equals", "", "other", "hashCode", "toString", "Companion", "logic_debug"})
public final class BusinessBean {
    @androidx.room.ColumnInfo(name = "_shop_id")
    @androidx.room.PrimaryKey(autoGenerate = true)
    private int shopID;
    @androidx.room.ColumnInfo(name = "_id")
    @com.google.gson.annotations.SerializedName(value = "id")
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_name")
    @com.google.gson.annotations.SerializedName(value = "name")
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_trades")
    @com.google.gson.annotations.SerializedName(value = "trades")
    private final java.lang.String trades = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_contact_phone")
    @com.google.gson.annotations.SerializedName(value = "contact_phone")
    private final java.lang.String contact_phone = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_investment")
    @com.google.gson.annotations.SerializedName(value = "investment")
    private final java.lang.String investment = null;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_item_keywords")
    @com.google.gson.annotations.SerializedName(value = "keywords")
    private java.lang.String[] shop_Keywords;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_large_img")
    @com.google.gson.annotations.SerializedName(value = "large_img")
    private final java.lang.String large_img = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_large_img_alt")
    @com.google.gson.annotations.SerializedName(value = "large_img_alt")
    private final java.lang.String large_img_alt = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_large_img_title")
    @com.google.gson.annotations.SerializedName(value = "large_img_title")
    private final java.lang.String large_img_title = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_thumb_img")
    @com.google.gson.annotations.SerializedName(value = "thumb_img")
    private final java.lang.String thumb_img = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_thumb_img_alt")
    @com.google.gson.annotations.SerializedName(value = "thumb_img_alt")
    private final java.lang.String thumb_img_alt = null;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_thumb_img_title")
    @com.google.gson.annotations.SerializedName(value = "thumb_img_title")
    private final java.lang.String thumb_img_title = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.puxiansheng.logic.bean.BusinessBean> DIFF = null;
    public static final com.puxiansheng.logic.bean.BusinessBean.Companion Companion = null;
    
    public final int getShopID() {
        return 0;
    }
    
    public final void setShopID(int p0) {
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTrades() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContact_phone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInvestment() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String[] getShop_Keywords() {
        return null;
    }
    
    public final void setShop_Keywords(@org.jetbrains.annotations.Nullable()
    java.lang.String[] p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLarge_img() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLarge_img_alt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLarge_img_title() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThumb_img() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThumb_img_alt() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThumb_img_title() {
        return null;
    }
    
    public BusinessBean(int shopID, int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String trades, @org.jetbrains.annotations.NotNull()
    java.lang.String contact_phone, @org.jetbrains.annotations.NotNull()
    java.lang.String investment, @org.jetbrains.annotations.Nullable()
    java.lang.String[] shop_Keywords, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_title, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_title) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String[] component7() {
        return null;
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
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
    public final com.puxiansheng.logic.bean.BusinessBean copy(int shopID, int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String trades, @org.jetbrains.annotations.NotNull()
    java.lang.String contact_phone, @org.jetbrains.annotations.NotNull()
    java.lang.String investment, @org.jetbrains.annotations.Nullable()
    java.lang.String[] shop_Keywords, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_title, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_title) {
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
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"Lcom/puxiansheng/logic/bean/BusinessBean$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/puxiansheng/logic/bean/BusinessBean;", "getDIFF", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "logic_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.puxiansheng.logic.bean.BusinessBean> getDIFF() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}