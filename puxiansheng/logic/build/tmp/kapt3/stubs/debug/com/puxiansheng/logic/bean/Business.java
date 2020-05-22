package com.puxiansheng.logic.bean;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\u00a5\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00c6\u0001J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\u0005H\u00d6\u0001J\t\u0010;\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0011\u0010\u0010\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016\u00a8\u0006<"}, d2 = {"Lcom/puxiansheng/logic/bean/Business;", "", "contact_phone", "", "id", "", "investment", "jump_param", "jump_type", "jump_view", "keywords", "", "large_img", "large_img_alt", "large_img_title", "name", "thumb_img", "thumb_img_alt", "thumb_img_title", "trades", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getContact_phone", "()Ljava/lang/String;", "getId", "()I", "getInvestment", "getJump_param", "getJump_type", "getJump_view", "getKeywords", "()Ljava/util/List;", "getLarge_img", "getLarge_img_alt", "getLarge_img_title", "getName", "getThumb_img", "getThumb_img_alt", "getThumb_img_title", "getTrades", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "logic_debug"})
public final class Business {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contact_phone = null;
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String investment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String jump_param = null;
    private final int jump_type = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String jump_view = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> keywords = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String large_img = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String large_img_alt = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String large_img_title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thumb_img = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thumb_img_alt = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thumb_img_title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String trades = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContact_phone() {
        return null;
    }
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getInvestment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJump_param() {
        return null;
    }
    
    public final int getJump_type() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJump_view() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getKeywords() {
        return null;
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
    public final java.lang.String getName() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTrades() {
        return null;
    }
    
    public Business(@org.jetbrains.annotations.NotNull()
    java.lang.String contact_phone, int id, @org.jetbrains.annotations.NotNull()
    java.lang.String investment, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_param, int jump_type, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_view, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keywords, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_title, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_title, @org.jetbrains.annotations.NotNull()
    java.lang.String trades) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
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
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
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
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.Business copy(@org.jetbrains.annotations.NotNull()
    java.lang.String contact_phone, int id, @org.jetbrains.annotations.NotNull()
    java.lang.String investment, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_param, int jump_type, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_view, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keywords, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String large_img_title, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_alt, @org.jetbrains.annotations.NotNull()
    java.lang.String thumb_img_title, @org.jetbrains.annotations.NotNull()
    java.lang.String trades) {
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