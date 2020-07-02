package com.puxiansheng.logic.bean.http;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000W\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0003\b\u00c9\u0001\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u00c1\u0005\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\b\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\b\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0018\u001a\u00020\r\u0012\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001e\u001a\u00020\b\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0005\u0012\b\b\u0002\u0010 \u001a\u00020\u0005\u0012\b\b\u0002\u0010!\u001a\u00020\u0005\u0012\b\b\u0002\u0010\"\u001a\u00020\u0005\u0012\b\b\u0002\u0010#\u001a\u00020\u0005\u0012\b\b\u0002\u0010$\u001a\u00020\u0005\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&\u0012\b\b\u0002\u0010\'\u001a\u00020\u0005\u0012\b\b\u0002\u0010(\u001a\u00020\u0005\u0012\b\b\u0002\u0010)\u001a\u00020\b\u0012\b\b\u0002\u0010*\u001a\u00020\u0005\u0012\b\b\u0002\u0010+\u001a\u00020\u0005\u0012\b\b\u0002\u0010,\u001a\u00020\b\u0012\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b\u0012\u0010\b\u0002\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b\u0012\b\b\u0002\u00100\u001a\u00020\u0005\u0012\b\b\u0002\u00101\u001a\u00020\u0005\u0012\b\b\u0002\u00102\u001a\u00020\u0005\u0012\b\b\u0002\u00103\u001a\u00020\u0005\u0012\b\b\u0002\u00104\u001a\u00020\u0005\u0012\b\b\u0002\u00105\u001a\u00020\u0005\u0012\b\b\u0002\u00106\u001a\u00020\u0005\u0012\n\b\u0002\u00107\u001a\u0004\u0018\u000108\u0012\b\b\u0002\u00109\u001a\u00020\b\u0012\b\b\u0002\u0010:\u001a\u00020\u0005\u0012\b\b\u0002\u0010;\u001a\u00020\u0005\u0012\b\b\u0002\u0010<\u001a\u00020\b\u0012\b\b\u0002\u0010=\u001a\u00020\b\u0012\b\b\u0002\u0010>\u001a\u00020\u0005\u0012\b\b\u0002\u0010?\u001a\u00020\u0005\u0012\b\b\u0002\u0010@\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b\u0012\b\b\u0002\u0010B\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b\u0012\b\b\u0002\u0010D\u001a\u00020\u0005\u0012\b\b\u0002\u0010E\u001a\u00020\b\u0012\b\b\u0002\u0010F\u001a\u00020\u0005\u0012\b\b\u0002\u0010G\u001a\u00020\u0005\u0012\b\b\u0002\u0010H\u001a\u00020\b\u0012\u0010\b\u0002\u0010I\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010\u000b\u00a2\u0006\u0002\u0010KJ\n\u0010\u00d2\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00d3\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d4\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00d5\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00d6\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00d7\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00d8\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00d9\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00da\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00db\u0001\u001a\u00020\rH\u00c6\u0003J\u0012\u0010\u00dc\u0001\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bH\u00c6\u0003J\n\u0010\u00dd\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00de\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00df\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e0\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e1\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e2\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00e3\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e4\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e5\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e6\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e7\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00e8\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00e9\u0001\u001a\u00020\u0005H\u00c6\u0003J\f\u0010\u00ea\u0001\u001a\u0004\u0018\u00010&H\u00c6\u0003J\n\u0010\u00eb\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ec\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ed\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00ee\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ef\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f0\u0001\u001a\u00020\bH\u00c6\u0003J\u0012\u0010\u00f1\u0001\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000bH\u00c6\u0003J\u0012\u0010\u00f2\u0001\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000bH\u00c6\u0003J\n\u0010\u00f3\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00f4\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f5\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f6\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f7\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f8\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00f9\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00fa\u0001\u001a\u00020\u0005H\u00c6\u0003J\f\u0010\u00fb\u0001\u001a\u0004\u0018\u000108H\u00c6\u0003J\n\u0010\u00fc\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00fd\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00fe\u0001\u001a\u00020\bH\u00c6\u0003J\n\u0010\u00ff\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0080\u0002\u001a\u00020\bH\u00c6\u0003J\n\u0010\u0081\u0002\u001a\u00020\bH\u00c6\u0003J\n\u0010\u0082\u0002\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0083\u0002\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u0084\u0002\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u0085\u0002\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bH\u00c6\u0003J\n\u0010\u0086\u0002\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u0087\u0002\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000bH\u00c6\u0003J\n\u0010\u0088\u0002\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u0089\u0002\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000bH\u00c6\u0003J\n\u0010\u008a\u0002\u001a\u00020\bH\u00c6\u0003J\n\u0010\u008b\u0002\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u008c\u0002\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u008d\u0002\u001a\u00020\bH\u00c6\u0003J\u0012\u0010\u008e\u0002\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010\u000bH\u00c6\u0003J\n\u0010\u008f\u0002\u001a\u00020\rH\u00c6\u0003J\n\u0010\u0090\u0002\u001a\u00020\rH\u00c6\u0003J\n\u0010\u0091\u0002\u001a\u00020\bH\u00c6\u0003J\u00c6\u0005\u0010\u0092\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\b2\b\b\u0002\u0010\u0015\u001a\u00020\b2\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\r2\u0010\b\u0002\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u00052\b\b\u0002\u0010\u001b\u001a\u00020\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\b\b\u0002\u0010\'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u00052\b\b\u0002\u0010)\u001a\u00020\b2\b\b\u0002\u0010*\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020\u00052\b\b\u0002\u0010,\u001a\u00020\b2\u0010\b\u0002\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b2\u0010\b\u0002\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b2\b\b\u0002\u00100\u001a\u00020\u00052\b\b\u0002\u00101\u001a\u00020\u00052\b\b\u0002\u00102\u001a\u00020\u00052\b\b\u0002\u00103\u001a\u00020\u00052\b\b\u0002\u00104\u001a\u00020\u00052\b\b\u0002\u00105\u001a\u00020\u00052\b\b\u0002\u00106\u001a\u00020\u00052\n\b\u0002\u00107\u001a\u0004\u0018\u0001082\b\b\u0002\u00109\u001a\u00020\b2\b\b\u0002\u0010:\u001a\u00020\u00052\b\b\u0002\u0010;\u001a\u00020\u00052\b\b\u0002\u0010<\u001a\u00020\b2\b\b\u0002\u0010=\u001a\u00020\b2\b\b\u0002\u0010>\u001a\u00020\u00052\b\b\u0002\u0010?\u001a\u00020\u00052\b\b\u0002\u0010@\u001a\u00020\u00052\u0010\b\u0002\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\b\b\u0002\u0010B\u001a\u00020\u00052\u0010\b\u0002\u0010C\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b2\b\b\u0002\u0010D\u001a\u00020\u00052\b\b\u0002\u0010E\u001a\u00020\b2\b\b\u0002\u0010F\u001a\u00020\u00052\b\b\u0002\u0010G\u001a\u00020\u00052\b\b\u0002\u0010H\u001a\u00020\b2\u0010\b\u0002\u0010I\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010\u000bH\u00c6\u0001J\u0016\u0010\u0093\u0002\u001a\u00030\u0094\u00022\t\u0010\u0095\u0002\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\n\u0010\u0096\u0002\u001a\u00020\bH\u00d6\u0001J\n\u0010\u0097\u0002\u001a\u00020\u0005H\u00d6\u0001R \u0010%\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR&\u0010A\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001e\u0010@\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR&\u0010I\u001a\n\u0012\u0004\u0012\u00020J\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bX\u0010Q\"\u0004\bY\u0010SR\u001e\u0010 \u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010U\"\u0004\b[\u0010WR\u001e\u0010\u001b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010U\"\u0004\b]\u0010WR\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b^\u0010_\"\u0004\b`\u0010aR\u001e\u0010\u0017\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\u001e\u0010D\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bf\u0010U\"\u0004\bg\u0010WR\u001e\u0010;\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bh\u0010U\"\u0004\bi\u0010WR\u001e\u00100\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bj\u0010U\"\u0004\bk\u0010WR\u001e\u00101\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bl\u0010U\"\u0004\bm\u0010WR\u001e\u00102\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bn\u0010U\"\u0004\bo\u0010WR&\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bp\u0010Q\"\u0004\bq\u0010SR\u001e\u00109\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\br\u0010_\"\u0004\bs\u0010aR\u001e\u0010\u0018\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR\u001e\u0010\u001e\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bx\u0010_\"\u0004\by\u0010aR\u001e\u0010:\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bz\u0010U\"\u0004\b{\u0010WR&\u0010C\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b|\u0010Q\"\u0004\b}\u0010SR\u001e\u0010\u001a\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b~\u0010U\"\u0004\b\u007f\u0010WR \u0010B\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0080\u0001\u0010U\"\u0005\b\u0081\u0001\u0010WR(\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010Q\"\u0005\b\u0083\u0001\u0010SR \u0010?\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0084\u0001\u0010U\"\u0005\b\u0085\u0001\u0010WR \u0010<\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0001\u0010_\"\u0005\b\u0087\u0001\u0010aR \u0010#\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0088\u0001\u0010U\"\u0005\b\u0089\u0001\u0010WR \u0010\u001f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008a\u0001\u0010U\"\u0005\b\u008b\u0001\u0010WR \u0010\u001d\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010U\"\u0005\b\u008d\u0001\u0010WR \u0010$\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008e\u0001\u0010U\"\u0005\b\u008f\u0001\u0010WR(\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0090\u0001\u0010Q\"\u0005\b\u0091\u0001\u0010SR \u0010,\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0092\u0001\u0010_\"\u0005\b\u0093\u0001\u0010aR \u0010\u0010\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0094\u0001\u0010U\"\u0005\b\u0095\u0001\u0010WR\u001f\u0010\u0013\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0013\u0010_\"\u0005\b\u0096\u0001\u0010aR\u001f\u0010\u0015\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0015\u0010_\"\u0005\b\u0097\u0001\u0010aR\u001f\u0010\u0014\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0014\u0010_\"\u0005\b\u0098\u0001\u0010aR\u001f\u0010=\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b=\u0010_\"\u0005\b\u0099\u0001\u0010aR\u001f\u0010\u0011\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0011\u0010_\"\u0005\b\u009a\u0001\u0010aR\u001f\u0010H\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\bH\u0010_\"\u0005\b\u009b\u0001\u0010aR\u001f\u0010\u0012\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u0012\u0010_\"\u0005\b\u009c\u0001\u0010aR \u0010G\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009d\u0001\u0010U\"\u0005\b\u009e\u0001\u0010WR \u0010E\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010_\"\u0005\b\u00a0\u0001\u0010aR \u0010F\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a1\u0001\u0010U\"\u0005\b\u00a2\u0001\u0010WR(\u0010-\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a3\u0001\u0010Q\"\u0005\b\u00a4\u0001\u0010SR \u0010\u0016\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a5\u0001\u0010U\"\u0005\b\u00a6\u0001\u0010WR \u0010+\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a7\u0001\u0010U\"\u0005\b\u00a8\u0001\u0010WR \u0010*\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a9\u0001\u0010U\"\u0005\b\u00aa\u0001\u0010WR \u00103\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ab\u0001\u0010U\"\u0005\b\u00ac\u0001\u0010WR \u0010\u000e\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ad\u0001\u0010u\"\u0005\b\u00ae\u0001\u0010wR \u0010\"\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00af\u0001\u0010U\"\u0005\b\u00b0\u0001\u0010WR \u0010\u000f\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b1\u0001\u0010_\"\u0005\b\u00b2\u0001\u0010aR \u0010)\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b3\u0001\u0010_\"\u0005\b\u00b4\u0001\u0010aR \u00104\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b5\u0001\u0010U\"\u0005\b\u00b6\u0001\u0010WR \u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b7\u0001\u0010c\"\u0005\b\u00b8\u0001\u0010eR \u00105\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00b9\u0001\u0010U\"\u0005\b\u00ba\u0001\u0010WR \u00106\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00bb\u0001\u0010U\"\u0005\b\u00bc\u0001\u0010WR \u0010\f\u001a\u00020\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00bd\u0001\u0010u\"\u0005\b\u00be\u0001\u0010wR$\u00107\u001a\u0004\u0018\u0001088\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u00bf\u0001\u0010\u00c0\u0001\"\u0006\b\u00c1\u0001\u0010\u00c2\u0001R \u0010\t\u001a\u00020\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00c3\u0001\u0010_\"\u0005\b\u00c4\u0001\u0010aR \u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00c5\u0001\u0010U\"\u0005\b\u00c6\u0001\u0010WR \u0010\u0006\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00c7\u0001\u0010c\"\u0005\b\u009b\u0001\u0010eR \u0010(\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00c8\u0001\u0010U\"\u0005\b\u00c9\u0001\u0010WR \u0010\'\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ca\u0001\u0010U\"\u0005\b\u00cb\u0001\u0010WR \u0010\u001c\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00cc\u0001\u0010U\"\u0005\b\u00cd\u0001\u0010WR \u0010!\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00ce\u0001\u0010U\"\u0005\b\u00cf\u0001\u0010WR \u0010>\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00d0\u0001\u0010U\"\u0005\b\u00d1\u0001\u0010W\u00a8\u0006\u0098\u0002"}, d2 = {"Lcom/puxiansheng/logic/bean/http/OrderDetailObject;", "", "shopID", "", "title", "", "updateTime", "checkId", "", "status", "images", "", "size", "", "rent", "rentUnitId", "industry", "isTop", "isVip", "isHot", "isRecommend", "isLargeOrder", "largeOrderImg", "city_id", "fee", "formattedIndustry", "formattedFinalIndustry", "categoryStr", "view_acreage_un_prefix", "formattedTransferFee", "floor", "formattedSize", "categoryAcreage", "view_category", "rentName", "formattedRent", "image", "address", "Lcom/puxiansheng/logic/bean/Address;", "viewOpening", "viewCanEmpty", "runningState", "lng", "lat", "includeFacilities", "labelList", "Lcom/puxiansheng/logic/bean/MenuItem;", "facilities", "description", "descriptionUrl", "environment", "reason", "serviceAgentPhone", "shopOwnerName", "shopOwnerPhoneNumbr", "state", "Lcom/puxiansheng/logic/bean/Order$Companion$State;", "favorite", "formattedDate", "day_time", "formattedPageViews", "isSuccess", "view_rent_un_prefix", "formattedLocationNodes", "area_point_str", "areaLis", "formattedFinalLocationNode", "formattedFacilities", "data_type", "jump_type", "jump_view", "jump_param", "isUpdateTime", "articles", "Lcom/puxiansheng/logic/bean/ArticleBean;", "(JLjava/lang/String;JIILjava/util/List;DDILjava/lang/String;IIIIILjava/lang/String;JDLjava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/puxiansheng/logic/bean/Address;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/puxiansheng/logic/bean/Order$Companion$State;ILjava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/util/List;)V", "getAddress", "()Lcom/puxiansheng/logic/bean/Address;", "setAddress", "(Lcom/puxiansheng/logic/bean/Address;)V", "getAreaLis", "()Ljava/util/List;", "setAreaLis", "(Ljava/util/List;)V", "getArea_point_str", "()Ljava/lang/String;", "setArea_point_str", "(Ljava/lang/String;)V", "getArticles", "setArticles", "getCategoryAcreage", "setCategoryAcreage", "getCategoryStr", "setCategoryStr", "getCheckId", "()I", "setCheckId", "(I)V", "getCity_id", "()J", "setCity_id", "(J)V", "getData_type", "setData_type", "getDay_time", "setDay_time", "getDescription", "setDescription", "getDescriptionUrl", "setDescriptionUrl", "getEnvironment", "setEnvironment", "getFacilities", "setFacilities", "getFavorite", "setFavorite", "getFee", "()D", "setFee", "(D)V", "getFloor", "setFloor", "getFormattedDate", "setFormattedDate", "getFormattedFacilities", "setFormattedFacilities", "getFormattedFinalIndustry", "setFormattedFinalIndustry", "getFormattedFinalLocationNode", "setFormattedFinalLocationNode", "getFormattedIndustry", "setFormattedIndustry", "getFormattedLocationNodes", "setFormattedLocationNodes", "getFormattedPageViews", "setFormattedPageViews", "getFormattedRent", "setFormattedRent", "getFormattedSize", "setFormattedSize", "getFormattedTransferFee", "setFormattedTransferFee", "getImage", "setImage", "getImages", "setImages", "getIncludeFacilities", "setIncludeFacilities", "getIndustry", "setIndustry", "setHot", "setLargeOrder", "setRecommend", "setSuccess", "setTop", "setUpdateTime", "setVip", "getJump_param", "setJump_param", "getJump_type", "setJump_type", "getJump_view", "setJump_view", "getLabelList", "setLabelList", "getLargeOrderImg", "setLargeOrderImg", "getLat", "setLat", "getLng", "setLng", "getReason", "setReason", "getRent", "setRent", "getRentName", "setRentName", "getRentUnitId", "setRentUnitId", "getRunningState", "setRunningState", "getServiceAgentPhone", "setServiceAgentPhone", "getShopID", "setShopID", "getShopOwnerName", "setShopOwnerName", "getShopOwnerPhoneNumbr", "setShopOwnerPhoneNumbr", "getSize", "setSize", "getState", "()Lcom/puxiansheng/logic/bean/Order$Companion$State;", "setState", "(Lcom/puxiansheng/logic/bean/Order$Companion$State;)V", "getStatus", "setStatus", "getTitle", "setTitle", "getUpdateTime", "getViewCanEmpty", "setViewCanEmpty", "getViewOpening", "setViewOpening", "getView_acreage_un_prefix", "setView_acreage_un_prefix", "getView_category", "setView_category", "getView_rent_un_prefix", "setView_rent_un_prefix", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component52", "component53", "component54", "component55", "component56", "component57", "component58", "component59", "component6", "component60", "component61", "component62", "component63", "component64", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "logic_debug"})
public final class OrderDetailObject {
    @com.google.gson.annotations.SerializedName(value = "id")
    private long shopID;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "title")
    private java.lang.String title;
    @com.google.gson.annotations.SerializedName(value = "update_time")
    private long updateTime;
    @com.google.gson.annotations.SerializedName(value = "checked_id")
    private int checkId;
    @com.google.gson.annotations.SerializedName(value = "status")
    private int status;
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
    @com.google.gson.annotations.SerializedName(value = "is_vip")
    private int isVip;
    @com.google.gson.annotations.SerializedName(value = "is_hot")
    private int isHot;
    @com.google.gson.annotations.SerializedName(value = "is_recommend")
    private int isRecommend;
    @com.google.gson.annotations.SerializedName(value = "is_large_order")
    private int isLargeOrder;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "large_order_img")
    private java.lang.String largeOrderImg;
    @com.google.gson.annotations.SerializedName(value = "city_id")
    private long city_id;
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
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "lng")
    private java.lang.String lng;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "lat")
    private java.lang.String lat;
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
    @com.google.gson.annotations.SerializedName(value = "view_status")
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
    @com.google.gson.annotations.SerializedName(value = "is_update_time")
    private int isUpdateTime;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "data")
    private java.util.List<com.puxiansheng.logic.bean.ArticleBean> articles;
    
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
    
    public final int getCheckId() {
        return 0;
    }
    
    public final void setCheckId(int p0) {
    }
    
    public final int getStatus() {
        return 0;
    }
    
    public final void setStatus(int p0) {
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
    
    public final int isVip() {
        return 0;
    }
    
    public final void setVip(int p0) {
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
    
    public final int isLargeOrder() {
        return 0;
    }
    
    public final void setLargeOrder(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLargeOrderImg() {
        return null;
    }
    
    public final void setLargeOrderImg(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getCity_id() {
        return 0L;
    }
    
    public final void setCity_id(long p0) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLng() {
        return null;
    }
    
    public final void setLng(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLat() {
        return null;
    }
    
    public final void setLat(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
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
    
    public final int isUpdateTime() {
        return 0;
    }
    
    public final void setUpdateTime(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.ArticleBean> getArticles() {
        return null;
    }
    
    public final void setArticles(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.ArticleBean> p0) {
    }
    
    public OrderDetailObject(long shopID, @org.jetbrains.annotations.NotNull()
    java.lang.String title, long updateTime, int checkId, int status, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> images, double size, double rent, int rentUnitId, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, int isTop, int isVip, int isHot, int isRecommend, int isLargeOrder, @org.jetbrains.annotations.NotNull()
    java.lang.String largeOrderImg, long city_id, double fee, @org.jetbrains.annotations.Nullable()
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
    java.lang.String viewCanEmpty, int runningState, @org.jetbrains.annotations.NotNull()
    java.lang.String lng, @org.jetbrains.annotations.NotNull()
    java.lang.String lat, int includeFacilities, @org.jetbrains.annotations.Nullable()
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
    java.lang.String jump_param, int isUpdateTime, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.ArticleBean> articles) {
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
    
    public final int component4() {
        return 0;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int component13() {
        return 0;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final int component15() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    public final long component17() {
        return 0L;
    }
    
    public final double component18() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component19() {
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
    
    public final int component24() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component25() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component28() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component29() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component30() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Address component31() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component32() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component33() {
        return null;
    }
    
    public final int component34() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component36() {
        return null;
    }
    
    public final int component37() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component38() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component39() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component40() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component41() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component42() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component43() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component44() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component45() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component46() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Order.Companion.State component47() {
        return null;
    }
    
    public final int component48() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component49() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component50() {
        return null;
    }
    
    public final int component51() {
        return 0;
    }
    
    public final int component52() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component53() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component54() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component55() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component56() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component57() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component58() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component59() {
        return null;
    }
    
    public final int component60() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component61() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component62() {
        return null;
    }
    
    public final int component63() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.ArticleBean> component64() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.http.OrderDetailObject copy(long shopID, @org.jetbrains.annotations.NotNull()
    java.lang.String title, long updateTime, int checkId, int status, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> images, double size, double rent, int rentUnitId, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, int isTop, int isVip, int isHot, int isRecommend, int isLargeOrder, @org.jetbrains.annotations.NotNull()
    java.lang.String largeOrderImg, long city_id, double fee, @org.jetbrains.annotations.Nullable()
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
    java.lang.String viewCanEmpty, int runningState, @org.jetbrains.annotations.NotNull()
    java.lang.String lng, @org.jetbrains.annotations.NotNull()
    java.lang.String lat, int includeFacilities, @org.jetbrains.annotations.Nullable()
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
    java.lang.String jump_param, int isUpdateTime, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.ArticleBean> articles) {
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