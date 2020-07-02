package com.puxiansheng.logic.util;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0004\f\r\u000e\u000fB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0005J*\u0010\u0007\u001a\n\u0012\u0004\u0012\u0002H\t\u0018\u00010\u0006\"\u0004\b\u0000\u0010\t2\u0006\u0010\b\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u000bR \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/puxiansheng/logic/util/LiveDataBus;", "", "()V", "bus", "", "", "Lcom/puxiansheng/logic/util/LiveDataBus$BusMutableLiveData;", "with", "key", "T", "type", "Ljava/lang/Class;", "BusMutableLiveData", "Companion", "ObserverWrapper", "SingletonHolder", "logic_debug"})
public final class LiveDataBus {
    
    /**
     * 创建一个map来装载订阅者
     */
    private final java.util.Map<java.lang.String, com.puxiansheng.logic.util.LiveDataBus.BusMutableLiveData<java.lang.Object>> bus = null;
    public static final com.puxiansheng.logic.util.LiveDataBus.Companion Companion = null;
    
    /**
     * 外部调用 （提供给订阅者的方法）
     */
    @org.jetbrains.annotations.Nullable()
    public final synchronized <T extends java.lang.Object>com.puxiansheng.logic.util.LiveDataBus.BusMutableLiveData<T> with(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.Class<T> type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.util.LiveDataBus.BusMutableLiveData<java.lang.Object> with(@org.jetbrains.annotations.NotNull()
    java.lang.String key) {
        return null;
    }
    
    private LiveDataBus() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c2\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/puxiansheng/logic/util/LiveDataBus$SingletonHolder;", "", "()V", "DEFAULT_BUS", "Lcom/puxiansheng/logic/util/LiveDataBus;", "getDEFAULT_BUS", "()Lcom/puxiansheng/logic/util/LiveDataBus;", "logic_debug"})
    static final class SingletonHolder {
        @org.jetbrains.annotations.NotNull()
        private static final com.puxiansheng.logic.util.LiveDataBus DEFAULT_BUS = null;
        public static final com.puxiansheng.logic.util.LiveDataBus.SingletonHolder INSTANCE = null;
        
        @org.jetbrains.annotations.NotNull()
        public final com.puxiansheng.logic.util.LiveDataBus getDEFAULT_BUS() {
            return null;
        }
        
        private SingletonHolder() {
            super();
        }
    }
    
    /**
     * 重写MutableLiveData 在observe方法中进行Hook
     */
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0002J \u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0016J\u0018\u0010\r\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00028\u00000\u0006H\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\"\u0010\u0004\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/puxiansheng/logic/util/LiveDataBus$BusMutableLiveData;", "T", "Landroidx/lifecycle/MutableLiveData;", "()V", "observerMap", "Ljava/util/HashMap;", "Landroidx/lifecycle/Observer;", "hook", "", "observer", "observe", "owner", "Landroidx/lifecycle/LifecycleOwner;", "observeForever", "removeObserver", "removeObservers", "logic_debug"})
    public static final class BusMutableLiveData<T extends java.lang.Object> extends androidx.lifecycle.MutableLiveData<T> {
        private final java.util.HashMap<androidx.lifecycle.Observer<?>, androidx.lifecycle.Observer<?>> observerMap = null;
        
        @java.lang.Override()
        public void observe(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.LifecycleOwner owner, @org.jetbrains.annotations.NotNull()
        androidx.lifecycle.Observer<? super T> observer) {
        }
        
        @java.lang.Override()
        public void observeForever(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.Observer<? super T> observer) {
        }
        
        @java.lang.Override()
        public void removeObserver(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.Observer<? super T> observer) {
        }
        
        @java.lang.Override()
        public void removeObservers(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.LifecycleOwner owner) {
        }
        
        /**
         * hook方法
         */
        private final void hook(androidx.lifecycle.Observer<? super T> observer) {
        }
        
        public BusMutableLiveData() {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00018\u0000H\u0016\u00a2\u0006\u0002\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/puxiansheng/logic/util/LiveDataBus$ObserverWrapper;", "T", "Landroidx/lifecycle/Observer;", "observer", "(Landroidx/lifecycle/Observer;)V", "isCallOnObserve", "", "()Z", "onChanged", "", "t", "(Ljava/lang/Object;)V", "logic_debug"})
    static final class ObserverWrapper<T extends java.lang.Object> implements androidx.lifecycle.Observer<T> {
        private final androidx.lifecycle.Observer<T> observer = null;
        
        private final boolean isCallOnObserve() {
            return false;
        }
        
        @java.lang.Override()
        public void onChanged(@org.jetbrains.annotations.Nullable()
        T t) {
        }
        
        public ObserverWrapper(@org.jetbrains.annotations.Nullable()
        androidx.lifecycle.Observer<T> observer) {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/puxiansheng/logic/util/LiveDataBus$Companion;", "", "()V", "get", "Lcom/puxiansheng/logic/util/LiveDataBus;", "logic_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final com.puxiansheng.logic.util.LiveDataBus get() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}