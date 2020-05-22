package com.puxiansheng.logic.bean.http;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000W\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0003\b\u00b7\u0001\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00fb\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\r\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\n\u0012\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\r\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 \u0012\b\b\u0002\u0010!\u001a\u00020\u0005\u0012\b\b\u0002\u0010\"\u001a\u00020\u0005\u0012\b\b\u0002\u0010#\u001a\u00020\r\u0012\b\b\u0002\u0010$\u001a\u00020\n\u0012\b\b\u0002\u0010%\u001a\u00020\n\u0012\b\b\u0002\u0010&\u001a\u00020\r\u0012\u0010\b\u0002\u0010\'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b\u0012\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b\u0012\b\b\u0002\u0010*\u001a\u00020\u0005\u0012\b\b\u0002\u0010+\u001a\u00020\u0005\u0012\b\b\u0002\u0010,\u001a\u00020\u0005\u0012\b\b\u0002\u0010-\u001a\u00020\u0005\u0012\b\b\u0002\u0010.\u001a\u00020\u0005\u0012\b\b\u0002\u0010/\u001a\u00020\u0005\u0012\b\b\u0002\u00100\u001a\u00020\u0005\u0012\n\b\u0002\u00101\u001a\u0004\u0018\u000102\u0012\b\b\u0002\u00103\u001a\u00020\r\u0012\b\b\u0002\u00104\u001a\u00020\u0005\u0012\b\b\u0002\u00105\u001a\u00020\u0005\u0012\b\b\u0002\u00106\u001a\u00020\r\u0012\b\b\u0002\u00107\u001a\u00020\r\u0012\b\b\u0002\u00108\u001a\u00020\u0005\u0012\b\b\u0002\u00109\u001a\u00020\u0005\u0012\b\b\u0002\u0010:\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b\u0012\b\b\u0002\u0010<\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b\u0012\b\b\u0002\u0010>\u001a\u00020\u0005\u0012\b\b\u0002\u0010?\u001a\u00020\r\u0012\b\b\u0002\u0010@\u001a\u00020\u0005\u0012\b\b\u0002\u0010A\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010B\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010\b\u00a2\u0006\u0002\u0010DJ\n\u0010\u00c0\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00c1\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00c2\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00c3\u0001\u001a\u00020\nH\u00c6\u0003J\u0012\u0010\u00c4\u0001\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH\u00c6\u0003J\n\u0010\u00c5\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c6\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c7\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c8\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c9\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00ca\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00cb\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00cc\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00cd\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ce\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00cf\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d0\u0001\u001a\u00020\u0005H\u00c6\u0003J\f\u0010\u00d1\u0001\u001a\u0004\u0018\u00010 H\u00c6\u0003J\n\u0010\u00d2\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d3\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d4\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00d5\u0001\u001a\u00020\nH\u00c6\u0003J\n\u0010\u00d6\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d7\u0001\u001a\u00020\nH\u00c6\u0003J\n\u0010\u00d8\u0001\u001a\u00020\rH\u00c6\u0003J\u0012\u0010\u00d9\u0001\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\bH\u00c6\u0003J\u0012\u0010\u00da\u0001\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\bH\u00c6\u0003J\n\u0010\u00db\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00dc\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00dd\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00de\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00df\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e0\u0001\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u00e1\u0001\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH\u00c6\u0003J\n\u0010\u00e2\u0001\u001a\u00020\u0005H\u00c6\u0003J\f\u0010\u00e3\u0001\u001a\u0004\u0018\u000102H\u00c6\u0003J\n\u0010\u00e4\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00e5\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e6\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e7\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00e8\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00e9\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ea\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00eb\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ec\u0001\u001a\u00020\nH\u00c6\u0003J\u0012\u0010\u00ed\u0001\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bH\u00c6\u0003J\n\u0010\u00ee\u0001\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u00ef\u0001\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\bH\u00c6\u0003J\n\u0010\u00f0\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f1\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00f2\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f3\u0001\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u00f4\u0001\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010\bH\u00c6\u0003J\n\u0010\u00f5\u0001\u001a\u00020\nH\u00c6\u0003J\n\u0010\u00f6\u0001\u001a\u00020\rH\u00c6\u0003J\n\u0010\u00f7\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f8\u0001\u001a\u00020\rH\u00c6\u0003J\u0080\u0005\u0010\u00f9\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\n2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00052\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\b\b\u0002\u0010\u0018\u001a\u00020\r2\b\b\u0002\u0010\u0019\u001a\u00020\u00052\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u00052\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\r2\b\b\u0002\u0010$\u001a\u00020\n2\b\b\u0002\u0010%\u001a\u00020\n2\b\b\u0002\u0010&\u001a\u00020\r2\u0010\b\u0002\u0010\'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b2\u0010\b\u0002\u0010)\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b2\b\b\u0002\u0010*\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020\u00052\b\b\u0002\u0010,\u001a\u00020\u00052\b\b\u0002\u0010-\u001a\u00020\u00052\b\b\u0002\u0010.\u001a\u00020\u00052\b\b\u0002\u0010/\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020\u00052\n\b\u0002\u00101\u001a\u0004\u0018\u0001022\b\b\u0002\u00103\u001a\u00020\r2\b\b\u0002\u00104\u001a\u00020\u00052\b\b\u0002\u00105\u001a\u00020\u00052\b\b\u0002\u00106\u001a\u00020\r2\b\b\u0002\u00107\u001a\u00020\r2\b\b\u0002\u00108\u001a\u00020\u00052\b\b\u0002\u00109\u001a\u00020\u00052\b\b\u0002\u0010:\u001a\u00020\u00052\u0010\b\u0002\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\b\b\u0002\u0010<\u001a\u00020\u00052\u0010\b\u0002\u0010=\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b2\b\b\u0002\u0010>\u001a\u00020\u00052\b\b\u0002\u0010?\u001a\u00020\r2\b\b\u0002\u0010@\u001a\u00020\u00052\b\b\u0002\u0010A\u001a\u00020\u00052\u0010\b\u0002\u0010B\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010\bH\u00c6\u0001J\u0016\u0010\u00fa\u0001\u001a\u00030\u00fb\u00012\t\u0010\u00fc\u0001\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u00fd\u0001\u001a\u00020\rH\u00d6\u0001J\n\u0010\u00fe\u0001\u001a\u00020\u0005H\u00d6\u0001R \u0010\u001f\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR&\u0010;\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001e\u0010:\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001e\u0010\u001a\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010N\"\u0004\bR\u0010PR\u001e\u0010\u0015\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010N\"\u0004\bT\u0010PR\u001e\u0010B\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010\b8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\bU\u0010JR\u001e\u0010>\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bV\u0010N\"\u0004\bW\u0010PR\u001e\u00105\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010N\"\u0004\bY\u0010PR\u001e\u0010*\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010N\"\u0004\b[\u0010PR\u001e\u0010+\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010N\"\u0004\b]\u0010PR\u001e\u0010,\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b^\u0010N\"\u0004\b_\u0010PR&\u0010)\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b`\u0010J\"\u0004\ba\u0010LR\u001e\u00103\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u001e\u0010\u0012\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u001e\u0010\u0018\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bj\u0010c\"\u0004\bk\u0010eR\u001e\u00104\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bl\u0010N\"\u0004\bm\u0010PR&\u0010=\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bn\u0010J\"\u0004\bo\u0010LR\u001e\u0010\u0014\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bp\u0010N\"\u0004\bq\u0010PR\u001e\u0010<\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\br\u0010N\"\u0004\bs\u0010PR&\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bt\u0010J\"\u0004\bu\u0010LR\u001e\u00109\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bv\u0010N\"\u0004\bw\u0010PR\u001e\u00106\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bx\u0010c\"\u0004\by\u0010eR\u001e\u0010\u001d\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bz\u0010N\"\u0004\b{\u0010PR\u001e\u0010\u0019\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b|\u0010N\"\u0004\b}\u0010PR\u001e\u0010\u0017\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b~\u0010N\"\u0004\b\u007f\u0010PR \u0010\u001e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010N\"\u0005\b\u0081\u0001\u0010PR(\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010J\"\u0005\b\u0083\u0001\u0010LR \u0010&\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010c\"\u0005\b\u0085\u0001\u0010eR \u0010\u000e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0001\u0010N\"\u0005\b\u0087\u0001\u0010PR\u001f\u0010\u0010\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0010\u0010c\"\u0005\b\u0088\u0001\u0010eR\u001f\u0010\u0011\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0011\u0010c\"\u0005\b\u0089\u0001\u0010eR\u001f\u00107\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b7\u0010c\"\u0005\b\u008a\u0001\u0010eR\u001f\u0010\u000f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u000f\u0010c\"\u0005\b\u008b\u0001\u0010eR \u0010A\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010N\"\u0005\b\u008d\u0001\u0010PR \u0010?\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010c\"\u0005\b\u008f\u0001\u0010eR \u0010@\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u0010N\"\u0005\b\u0091\u0001\u0010PR(\u0010\'\u001a\n\u0012\u0004\u0012\u00020(\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0001\u0010J\"\u0005\b\u0093\u0001\u0010LR \u0010%\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010g\"\u0005\b\u0095\u0001\u0010iR \u0010$\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0096\u0001\u0010g\"\u0005\b\u0097\u0001\u0010iR \u0010-\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0098\u0001\u0010N\"\u0005\b\u0099\u0001\u0010PR \u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009a\u0001\u0010g\"\u0005\b\u009b\u0001\u0010iR \u0010\u001c\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009c\u0001\u0010N\"\u0005\b\u009d\u0001\u0010PR \u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009e\u0001\u0010c\"\u0005\b\u009f\u0001\u0010eR \u0010#\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a0\u0001\u0010c\"\u0005\b\u00a1\u0001\u0010eR \u0010.\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a2\u0001\u0010N\"\u0005\b\u00a3\u0001\u0010PR\"\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00a4\u0001\u0010\u00a5\u0001\"\u0006\b\u00a6\u0001\u0010\u00a7\u0001R \u0010/\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a8\u0001\u0010N\"\u0005\b\u00a9\u0001\u0010PR \u00100\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00aa\u0001\u0010N\"\u0005\b\u00ab\u0001\u0010PR \u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ac\u0001\u0010g\"\u0005\b\u00ad\u0001\u0010iR$\u00101\u001a\u0004\u0018\u0001028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00ae\u0001\u0010\u00af\u0001\"\u0006\b\u00b0\u0001\u0010\u00b1\u0001R \u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b2\u0001\u0010N\"\u0005\b\u00b3\u0001\u0010PR\"\u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00b4\u0001\u0010\u00a5\u0001\"\u0006\b\u00b5\u0001\u0010\u00a7\u0001R \u0010\"\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b6\u0001\u0010N\"\u0005\b\u00b7\u0001\u0010PR \u0010!\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b8\u0001\u0010N\"\u0005\b\u00b9\u0001\u0010PR \u0010\u0016\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ba\u0001\u0010N\"\u0005\b\u00bb\u0001\u0010PR \u0010\u001b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00bc\u0001\u0010N\"\u0005\b\u00bd\u0001\u0010PR \u00108\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00be\u0001\u0010N\"\u0005\b\u00bf\u0001\u0010P\u00a8\u0006\u00ff\u0001"}, d2 = {"Lcom/puxiansheng/logic/bean/http/OrderDetailObject;", "", "shopID", "", "title", "", "updateTime", "images", "", "size", "", "rent", "rentUnitId", "", "industry", "isTop", "isHot", "isRecommend", "fee", "formattedIndustry", "formattedFinalIndustry", "categoryStr", "view_acreage_un_prefix", "formattedTransferFee", "floor", "formattedSize", "categoryAcreage", "view_category", "rentName", "formattedRent", "image", "address", "Lcom/puxiansheng/logic/bean/Address;", "viewOpening", "viewCanEmpty", "runningState", "lng", "lat", "includeFacilities", "labelList", "Lcom/puxiansheng/logic/bean/MenuItem;", "facilities", "description", "descriptionUrl", "environment", "reason", "serviceAgentPhone", "shopOwnerName", "shopOwnerPhoneNumbr", "state", "Lcom/puxiansheng/logic/bean/Order$Companion$State;", "favorite", "formattedDate", "day_time", "formattedPageViews", "isSuccess", "view_rent_un_prefix", "formattedLocationNodes", "area_point_str", "areaLis", "formattedFinalLocationNode", "formattedFacilities", "data_type", "jump_type", "jump_view", "jump_param", "data", "Lcom/puxiansheng/logic/bean/http/ArticleObject;", "(JLjava/lang/String;JLjava/util/List;DDILjava/lang/String;IIIDLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/puxiansheng/logic/bean/Address;Ljava/lang/String;Ljava/lang/String;IDDILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/puxiansheng/logic/bean/Order$Companion$State;ILjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getAddress", "()Lcom/puxiansheng/logic/bean/Address;", "setAddress", "(Lcom/puxiansheng/logic/bean/Address;)V", "getAreaLis", "()Ljava/util/List;", "setAreaLis", "(Ljava/util/List;)V", "getArea_point_str", "()Ljava/lang/String;", "setArea_point_str", "(Ljava/lang/String;)V", "getCategoryAcreage", "setCategoryAcreage", "getCategoryStr", "setCategoryStr", "getData", "getData_type", "setData_type", "getDay_time", "setDay_time", "getDescription", "setDescription", "getDescriptionUrl", "setDescriptionUrl", "getEnvironment", "setEnvironment", "getFacilities", "setFacilities", "getFavorite", "()I", "setFavorite", "(I)V", "getFee", "()D", "setFee", "(D)V", "getFloor", "setFloor", "getFormattedDate", "setFormattedDate", "getFormattedFacilities", "setFormattedFacilities", "getFormattedFinalIndustry", "setFormattedFinalIndustry", "getFormattedFinalLocationNode", "setFormattedFinalLocationNode", "getFormattedIndustry", "setFormattedIndustry", "getFormattedLocationNodes", "setFormattedLocationNodes", "getFormattedPageViews", "setFormattedPageViews", "getFormattedRent", "setFormattedRent", "getFormattedSize", "setFormattedSize", "getFormattedTransferFee", "setFormattedTransferFee", "getImage", "setImage", "getImages", "setImages", "getIncludeFacilities", "setIncludeFacilities", "getIndustry", "setIndustry", "setHot", "setRecommend", "setSuccess", "setTop", "getJump_param", "setJump_param", "getJump_type", "setJump_type", "getJump_view", "setJump_view", "getLabelList", "setLabelList", "getLat", "setLat", "getLng", "setLng", "getReason", "setReason", "getRent", "setRent", "getRentName", "setRentName", "getRentUnitId", "setRentUnitId", "getRunningState", "setRunningState", "getServiceAgentPhone", "setServiceAgentPhone", "getShopID", "()J", "setShopID", "(J)V", "getShopOwnerName", "setShopOwnerName", "getShopOwnerPhoneNumbr", "setShopOwnerPhoneNumbr", "getSize", "setSize", "getState", "()Lcom/puxiansheng/logic/bean/Order$Companion$State;", "setState", "(Lcom/puxiansheng/logic/bean/Order$Companion$State;)V", "getTitle", "setTitle", "getUpdateTime", "setUpdateTime", "getViewCanEmpty", "setViewCanEmpty", "getViewOpening", "setViewOpening", "getView_acreage_un_prefix", "setView_acreage_un_prefix", "getView_category", "setView_category", "getView_rent_un_prefix", "setView_rent_un_prefix", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "logic_debug"})
public final class OrderDetailObject {
    @com.google.gson.annotations.SerializedName(value = "id")
    private long shopID;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "title")
    private java.lang.String title;
    @com.google.gson.annotations.SerializedName(value = "update_time")
    private long updateTime;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "img_ids")
    private java.util.List<java.lang.String> images;
    @com.google.gson.annotations.SerializedName(value = "acreage")
    private double size;
    @com.google.gson.annotations.SerializedName(value = "rent")
    private double rent;
    @com.google.gson.annotations.SerializedName(value = "rent_unit_id")
    private int rentUnitId;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "category_path_id")
    private java.lang.String industry;
    @com.google.gson.annotations.SerializedName(value = "is_top")
    private int isTop;
    @com.google.gson.annotations.SerializedName(value = "is_hot")
    private int isHot;
    @com.google.gson.annotations.SerializedName(value = "is_recommend")
    private int isRecommend;
    @com.google.gson.annotations.SerializedName(value = "transfer_fee")
    private double fee;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "category")
    private java.util.List<java.lang.String> formattedIndustry;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "category_end")
    private java.lang.String formattedFinalIndustry;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "category_str")
    private java.lang.String categoryStr;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_acreage_un_prefix")
    private java.lang.String view_acreage_un_prefix;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_transfer_fee")
    private java.lang.String formattedTransferFee;
    @com.google.gson.annotations.SerializedName(value = "floor")
    private int floor;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_acreage")
    private java.lang.String formattedSize;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "category_acreage")
    private java.lang.String categoryAcreage;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_category")
    private java.lang.String view_category;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "rent_name")
    private java.lang.String rentName;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_rent")
    private java.lang.String formattedRent;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "thum_img")
    private java.lang.String image;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "area_obj")
    private com.puxiansheng.logic.bean.Address address;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_opening")
    private java.lang.String viewOpening;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_can_empty")
    private java.lang.String viewCanEmpty;
    @com.google.gson.annotations.SerializedName(value = "is_opening")
    private int runningState;
    @com.google.gson.annotations.SerializedName(value = "lng")
    private double lng;
    @com.google.gson.annotations.SerializedName(value = "lat")
    private double lat;
    @com.google.gson.annotations.SerializedName(value = "can_empty")
    private int includeFacilities;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "labels_list")
    private transient java.util.List<com.puxiansheng.logic.bean.MenuItem> labelList;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "demand_ids")
    private java.util.List<com.puxiansheng.logic.bean.MenuItem> facilities;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "content")
    private java.lang.String description;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "content_url")
    private java.lang.String descriptionUrl;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "support_description")
    private java.lang.String environment;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "transfer_reason")
    private java.lang.String reason;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "kf_phone")
    private java.lang.String serviceAgentPhone;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "contact_name")
    private java.lang.String shopOwnerName;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "contact_phone")
    private java.lang.String shopOwnerPhoneNumbr;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "checked")
    private com.puxiansheng.logic.bean.Order.Companion.State state;
    @com.google.gson.annotations.SerializedName(value = "collect")
    private int favorite;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_time")
    private java.lang.String formattedDate;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "day_time")
    private java.lang.String day_time;
    @com.google.gson.annotations.SerializedName(value = "view_count")
    private int formattedPageViews;
    @com.google.gson.annotations.SerializedName(value = "is_success")
    private int isSuccess;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "view_rent_un_prefix")
    private java.lang.String view_rent_un_prefix;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "area_str")
    private java.lang.String formattedLocationNodes;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "area_point_str")
    private java.lang.String area_point_str;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "area_lis")
    private java.util.List<java.lang.String> areaLis;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "area_end")
    private java.lang.String formattedFinalLocationNode;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "view_demand_ids")
    private java.util.List<com.puxiansheng.logic.bean.MenuItem> formattedFacilities;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "data_type")
    private java.lang.String data_type;
    @com.google.gson.annotations.SerializedName(value = "jump_type")
    private int jump_type;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "jump_view")
    private java.lang.String jump_view;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "jump_param")
    private java.lang.String jump_param;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "data")
    private final java.util.List<com.puxiansheng.logic.bean.http.ArticleObject> data = null;
    
    public final long getShopID() {
        return 0L;
    }
    
    public final void setShopID(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    public final void setTitle(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getUpdateTime() {
        return 0L;
    }
    
    public final void setUpdateTime(long p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getImages() {
        return null;
    }
    
    public final void setImages(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> p0) {
    }
    
    public final double getSize() {
        return 0.0;
    }
    
    public final void setSize(double p0) {
    }
    
    public final double getRent() {
        return 0.0;
    }
    
    public final void setRent(double p0) {
    }
    
    public final int getRentUnitId() {
        return 0;
    }
    
    public final void setRentUnitId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIndustry() {
        return null;
    }
    
    public final void setIndustry(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int isTop() {
        return 0;
    }
    
    public final void setTop(int p0) {
    }
    
    public final int isHot() {
        return 0;
    }
    
    public final void setHot(int p0) {
    }
    
    public final int isRecommend() {
        return 0;
    }
    
    public final void setRecommend(int p0) {
    }
    
    public final double getFee() {
        return 0.0;
    }
    
    public final void setFee(double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getFormattedIndustry() {
        return null;
    }
    
    public final void setFormattedIndustry(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedFinalIndustry() {
        return null;
    }
    
    public final void setFormattedFinalIndustry(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategoryStr() {
        return null;
    }
    
    public final void setCategoryStr(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getView_acreage_un_prefix() {
        return null;
    }
    
    public final void setView_acreage_un_prefix(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedTransferFee() {
        return null;
    }
    
    public final void setFormattedTransferFee(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getFloor() {
        return 0;
    }
    
    public final void setFloor(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedSize() {
        return null;
    }
    
    public final void setFormattedSize(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategoryAcreage() {
        return null;
    }
    
    public final void setCategoryAcreage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getView_category() {
        return null;
    }
    
    public final void setView_category(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRentName() {
        return null;
    }
    
    public final void setRentName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedRent() {
        return null;
    }
    
    public final void setFormattedRent(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImage() {
        return null;
    }
    
    public final void setImage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Address getAddress() {
        return null;
    }
    
    public final void setAddress(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Address p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getViewOpening() {
        return null;
    }
    
    public final void setViewOpening(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getViewCanEmpty() {
        return null;
    }
    
    public final void setViewCanEmpty(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getRunningState() {
        return 0;
    }
    
    public final void setRunningState(int p0) {
    }
    
    public final double getLng() {
        return 0.0;
    }
    
    public final void setLng(double p0) {
    }
    
    public final double getLat() {
        return 0.0;
    }
    
    public final void setLat(double p0) {
    }
    
    public final int getIncludeFacilities() {
        return 0;
    }
    
    public final void setIncludeFacilities(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> getLabelList() {
        return null;
    }
    
    public final void setLabelList(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> getFacilities() {
        return null;
    }
    
    public final void setFacilities(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final void setDescription(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescriptionUrl() {
        return null;
    }
    
    public final void setDescriptionUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEnvironment() {
        return null;
    }
    
    public final void setEnvironment(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReason() {
        return null;
    }
    
    public final void setReason(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getServiceAgentPhone() {
        return null;
    }
    
    public final void setServiceAgentPhone(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getShopOwnerName() {
        return null;
    }
    
    public final void setShopOwnerName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getShopOwnerPhoneNumbr() {
        return null;
    }
    
    public final void setShopOwnerPhoneNumbr(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.State getState() {
        return null;
    }
    
    public final void setState(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.State p0) {
    }
    
    public final int getFavorite() {
        return 0;
    }
    
    public final void setFavorite(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedDate() {
        return null;
    }
    
    public final void setFormattedDate(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDay_time() {
        return null;
    }
    
    public final void setDay_time(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getFormattedPageViews() {
        return 0;
    }
    
    public final void setFormattedPageViews(int p0) {
    }
    
    public final int isSuccess() {
        return 0;
    }
    
    public final void setSuccess(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getView_rent_un_prefix() {
        return null;
    }
    
    public final void setView_rent_un_prefix(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedLocationNodes() {
        return null;
    }
    
    public final void setFormattedLocationNodes(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArea_point_str() {
        return null;
    }
    
    public final void setArea_point_str(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getAreaLis() {
        return null;
    }
    
    public final void setAreaLis(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedFinalLocationNode() {
        return null;
    }
    
    public final void setFormattedFinalLocationNode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> getFormattedFacilities() {
        return null;
    }
    
    public final void setFormattedFacilities(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getData_type() {
        return null;
    }
    
    public final void setData_type(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getJump_type() {
        return 0;
    }
    
    public final void setJump_type(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJump_view() {
        return null;
    }
    
    public final void setJump_view(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJump_param() {
        return null;
    }
    
    public final void setJump_param(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.http.ArticleObject> getData() {
        return null;
    }
    
    public OrderDetailObject(long shopID, @org.jetbrains.annotations.NotNull()
    java.lang.String title, long updateTime, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> images, double size, double rent, int rentUnitId, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, int isTop, int isHot, int isRecommend, double fee, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> formattedIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryStr, @org.jetbrains.annotations.NotNull()
    java.lang.String view_acreage_un_prefix, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedTransferFee, int floor, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedSize, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryAcreage, @org.jetbrains.annotations.NotNull()
    java.lang.String view_category, @org.jetbrains.annotations.NotNull()
    java.lang.String rentName, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedRent, @org.jetbrains.annotations.NotNull()
    java.lang.String image, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Address address, @org.jetbrains.annotations.NotNull()
    java.lang.String viewOpening, @org.jetbrains.annotations.NotNull()
    java.lang.String viewCanEmpty, int runningState, double lng, double lat, int includeFacilities, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> labelList, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> facilities, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String descriptionUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String environment, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceAgentPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String shopOwnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String shopOwnerPhoneNumbr, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.State state, int favorite, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String day_time, int formattedPageViews, int isSuccess, @org.jetbrains.annotations.NotNull()
    java.lang.String view_rent_un_prefix, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedLocationNodes, @org.jetbrains.annotations.NotNull()
    java.lang.String area_point_str, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> areaLis, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalLocationNode, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> formattedFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String data_type, int jump_type, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_view, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_param, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.http.ArticleObject> data) {
        super();
    }
    
    public OrderDetailObject() {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final int component9() {
        return 0;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component13() {
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
    public final java.lang.String component17() {
        return null;
    }
    
    public final int component18() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component20() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component21() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component22() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component23() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component24() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Address component25() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component26() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component27() {
        return null;
    }
    
    public final int component28() {
        return 0;
    }
    
    public final double component29() {
        return 0.0;
    }
    
    public final double component30() {
        return 0.0;
    }
    
    public final int component31() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component32() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component33() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component36() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component37() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component38() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component39() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component40() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.State component41() {
        return null;
    }
    
    public final int component42() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component43() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component44() {
        return null;
    }
    
    public final int component45() {
        return 0;
    }
    
    public final int component46() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component47() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component48() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component49() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component50() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component51() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component52() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component53() {
        return null;
    }
    
    public final int component54() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component55() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component56() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.http.ArticleObject> component57() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.http.OrderDetailObject copy(long shopID, @org.jetbrains.annotations.NotNull()
    java.lang.String title, long updateTime, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> images, double size, double rent, int rentUnitId, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, int isTop, int isHot, int isRecommend, double fee, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> formattedIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryStr, @org.jetbrains.annotations.NotNull()
    java.lang.String view_acreage_un_prefix, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedTransferFee, int floor, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedSize, @org.jetbrains.annotations.NotNull()
    java.lang.String categoryAcreage, @org.jetbrains.annotations.NotNull()
    java.lang.String view_category, @org.jetbrains.annotations.NotNull()
    java.lang.String rentName, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedRent, @org.jetbrains.annotations.NotNull()
    java.lang.String image, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Address address, @org.jetbrains.annotations.NotNull()
    java.lang.String viewOpening, @org.jetbrains.annotations.NotNull()
    java.lang.String viewCanEmpty, int runningState, double lng, double lat, int includeFacilities, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> labelList, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> facilities, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String descriptionUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String environment, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, @org.jetbrains.annotations.NotNull()
    java.lang.String serviceAgentPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String shopOwnerName, @org.jetbrains.annotations.NotNull()
    java.lang.String shopOwnerPhoneNumbr, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Order.Companion.State state, int favorite, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String day_time, int formattedPageViews, int isSuccess, @org.jetbrains.annotations.NotNull()
    java.lang.String view_rent_un_prefix, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedLocationNodes, @org.jetbrains.annotations.NotNull()
    java.lang.String area_point_str, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> areaLis, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalLocationNode, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> formattedFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String data_type, int jump_type, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_view, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_param, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.http.ArticleObject> data) {
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