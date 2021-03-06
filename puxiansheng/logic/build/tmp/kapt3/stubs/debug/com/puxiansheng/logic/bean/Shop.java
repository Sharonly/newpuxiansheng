package com.puxiansheng.logic.bean;

import java.lang.System;

@androidx.room.TypeConverters(value = {com.puxiansheng.logic.bean.converter.MenuListConverter.class, com.puxiansheng.logic.bean.converter.StringListConverter.class, com.puxiansheng.logic.bean.converter.ArtcleListConverter.class})
@kotlinx.android.parcel.Parcelize()
@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0003\b\u00c5\u0001\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00b1\u0004\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0012\u0012\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016\u0012\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0005\u0012\b\b\u0002\u0010 \u001a\u00020\u0012\u0012\b\b\u0002\u0010!\u001a\u00020\u0012\u0012\b\b\u0002\u0010\"\u001a\u00020\u0012\u0012\b\b\u0002\u0010#\u001a\u00020\u0005\u0012\b\b\u0002\u0010$\u001a\u00020\u0005\u0012\b\b\u0002\u0010%\u001a\u00020\u0005\u0012\b\b\u0002\u0010&\u001a\u00020\u0012\u0012\b\b\u0002\u0010\'\u001a\u00020\u0005\u0012\b\b\u0002\u0010(\u001a\u00020\u0005\u0012\b\b\u0002\u0010)\u001a\u00020\u0005\u0012\b\b\u0002\u0010*\u001a\u00020\u0005\u0012\b\b\u0002\u0010+\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016\u0012\b\b\u0002\u0010-\u001a\u00020\u0005\u0012\b\b\u0002\u0010.\u001a\u00020\u0005\u0012\b\b\u0002\u0010/\u001a\u00020\u0005\u0012\b\b\u0002\u00100\u001a\u00020\u0012\u0012\b\b\u0002\u00101\u001a\u00020\u0012\u0012\b\b\u0002\u00102\u001a\u00020\u0012\u0012\b\b\u0002\u00103\u001a\u00020\u0012\u0012\b\b\u0002\u00104\u001a\u00020\u0005\u0012\b\b\u0002\u00105\u001a\u00020\u0005\u0012\b\b\u0002\u00106\u001a\u00020\u0005\u0012\b\b\u0002\u00107\u001a\u00020\u0012\u0012\b\b\u0002\u00108\u001a\u00020\u0005\u0012\b\b\u0002\u00109\u001a\u00020\u0012\u0012\b\b\u0002\u0010:\u001a\u00020\u0005\u0012\b\b\u0002\u0010;\u001a\u00020\u0005\u00a2\u0006\u0002\u0010<J\n\u0010\u00a9\u0001\u001a\u00020\u0003H\u00c6\u0003J\n\u0010\u00aa\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u00ab\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ac\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00ad\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00ae\u0001\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u00af\u0001\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0016H\u00c6\u0003J\n\u0010\u00b0\u0001\u001a\u00020\u0012H\u00c6\u0003J\u0012\u0010\u00b1\u0001\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016H\u00c6\u0003J\u0012\u0010\u00b2\u0001\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016H\u00c6\u0003J\u0012\u0010\u00b3\u0001\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016H\u00c6\u0003J\n\u0010\u00b4\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00b5\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00b6\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00b7\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00b8\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00b9\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00ba\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00bb\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00bc\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00bd\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00be\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00bf\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u00c0\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00c1\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c2\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c3\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c4\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c5\u0001\u001a\u00020\u0005H\u00c6\u0003J\u0012\u0010\u00c6\u0001\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016H\u00c6\u0003J\n\u0010\u00c7\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c8\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00c9\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00ca\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u00cb\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00cc\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00cd\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00ce\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00cf\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d0\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d1\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d2\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00d3\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d4\u0001\u001a\u00020\u0012H\u00c6\u0003J\n\u0010\u00d5\u0001\u001a\u00020\u0007H\u00c6\u0003J\n\u0010\u00d6\u0001\u001a\u00020\u0005H\u00c6\u0003J\n\u0010\u00d7\u0001\u001a\u00020\u0005H\u00c6\u0003J\f\u0010\u00d8\u0001\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\f\u0010\u00d9\u0001\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\f\u0010\u00da\u0001\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\n\u0010\u00db\u0001\u001a\u00020\u0007H\u00c6\u0003J\u00b6\u0004\u0010\u00dc\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u00052\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00122\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00162\u0010\b\u0002\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00162\u0010\b\u0002\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00162\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\u00052\b\b\u0002\u0010\u001e\u001a\u00020\u00052\b\b\u0002\u0010\u001f\u001a\u00020\u00052\b\b\u0002\u0010 \u001a\u00020\u00122\b\b\u0002\u0010!\u001a\u00020\u00122\b\b\u0002\u0010\"\u001a\u00020\u00122\b\b\u0002\u0010#\u001a\u00020\u00052\b\b\u0002\u0010$\u001a\u00020\u00052\b\b\u0002\u0010%\u001a\u00020\u00052\b\b\u0002\u0010&\u001a\u00020\u00122\b\b\u0002\u0010\'\u001a\u00020\u00052\b\b\u0002\u0010(\u001a\u00020\u00052\b\b\u0002\u0010)\u001a\u00020\u00052\b\b\u0002\u0010*\u001a\u00020\u00052\b\b\u0002\u0010+\u001a\u00020\u00052\u0010\b\u0002\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00162\b\b\u0002\u0010-\u001a\u00020\u00052\b\b\u0002\u0010.\u001a\u00020\u00052\b\b\u0002\u0010/\u001a\u00020\u00052\b\b\u0002\u00100\u001a\u00020\u00122\b\b\u0002\u00101\u001a\u00020\u00122\b\b\u0002\u00102\u001a\u00020\u00122\b\b\u0002\u00103\u001a\u00020\u00122\b\b\u0002\u00104\u001a\u00020\u00052\b\b\u0002\u00105\u001a\u00020\u00052\b\b\u0002\u00106\u001a\u00020\u00052\b\b\u0002\u00107\u001a\u00020\u00122\b\b\u0002\u00108\u001a\u00020\u00052\b\b\u0002\u00109\u001a\u00020\u00122\b\b\u0002\u0010:\u001a\u00020\u00052\b\b\u0002\u0010;\u001a\u00020\u0005H\u00c6\u0001J\n\u0010\u00dd\u0001\u001a\u00020\u0012H\u00d6\u0001J\u0017\u0010\u00de\u0001\u001a\u00030\u00df\u00012\n\u0010\u00e0\u0001\u001a\u0005\u0018\u00010\u00e1\u0001H\u00d6\u0003J\n\u0010\u00e2\u0001\u001a\u00020\u0012H\u00d6\u0001J\n\u0010\u00e3\u0001\u001a\u00020\u0005H\u00d6\u0001J\u001e\u0010\u00e4\u0001\u001a\u00030\u00e5\u00012\b\u0010\u00e6\u0001\u001a\u00030\u00e7\u00012\u0007\u0010\u00e8\u0001\u001a\u00020\u0012H\u00d6\u0001R \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R&\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001e\u00105\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001e\u00108\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bI\u0010F\"\u0004\bJ\u0010HR\u001e\u0010\u001c\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010F\"\u0004\bL\u0010HR\u001e\u0010\u001d\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bM\u0010F\"\u0004\bN\u0010HR\u001e\u0010\u001e\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bO\u0010F\"\u0004\bP\u0010HR&\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010B\"\u0004\bR\u0010DR\u001e\u0010\t\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bS\u0010T\"\u0004\bU\u0010VR\u001e\u0010\u0017\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001e\u00106\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b[\u0010F\"\u0004\b\\\u0010HR\u001e\u0010#\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b]\u0010F\"\u0004\b^\u0010HR&\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b_\u0010B\"\u0004\b`\u0010DR\u001e\u0010\'\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\ba\u0010F\"\u0004\bb\u0010HR\u001e\u0010*\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bc\u0010F\"\u0004\bd\u0010HR\u001e\u0010+\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\be\u0010F\"\u0004\bf\u0010HR\u001e\u0010)\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bg\u0010F\"\u0004\bh\u0010HR\u001e\u0010(\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bi\u0010F\"\u0004\bj\u0010HR\u001e\u0010&\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bk\u0010X\"\u0004\bl\u0010ZR\u001e\u0010%\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bm\u0010F\"\u0004\bn\u0010HR\u001e\u0010$\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bo\u0010F\"\u0004\bp\u0010HR\u001e\u0010\"\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bq\u0010X\"\u0004\br\u0010ZR\u001e\u0010\u0014\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bs\u0010F\"\u0004\bt\u0010HR&\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bu\u0010B\"\u0004\bv\u0010DR\u001e\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bw\u0010X\"\u0004\bx\u0010ZR\u001e\u0010\u0010\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\by\u0010F\"\u0004\bz\u0010HR\u001e\u00101\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u0010X\"\u0004\b{\u0010ZR\u001e\u00103\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010X\"\u0004\b|\u0010ZR\u001e\u00102\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010X\"\u0004\b}\u0010ZR\u001e\u0010!\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010X\"\u0004\b~\u0010ZR\u001e\u00100\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010X\"\u0004\b\u007f\u0010ZR\u001f\u00107\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b7\u0010X\"\u0005\b\u0080\u0001\u0010ZR \u0010;\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010F\"\u0005\b\u0082\u0001\u0010HR \u00109\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0083\u0001\u0010X\"\u0005\b\u0084\u0001\u0010ZR \u0010:\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0085\u0001\u0010F\"\u0005\b\u0086\u0001\u0010HR(\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0087\u0001\u0010B\"\u0005\b\u0088\u0001\u0010DR \u00104\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0089\u0001\u0010F\"\u0005\b\u008a\u0001\u0010HR\"\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008b\u0001\u0010F\"\u0005\b\u008c\u0001\u0010HR\"\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008d\u0001\u0010F\"\u0005\b\u008e\u0001\u0010HR \u0010\u000f\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u008f\u0001\u0010T\"\u0005\b\u0090\u0001\u0010VR \u0010\u000e\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0091\u0001\u0010T\"\u0005\b\u0092\u0001\u0010VR \u0010\u001f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0093\u0001\u0010F\"\u0005\b\u0094\u0001\u0010HR \u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0095\u0001\u0010T\"\u0005\b\u0096\u0001\u0010VR \u0010-\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0097\u0001\u0010F\"\u0005\b\u0098\u0001\u0010HR \u0010\u0011\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0099\u0001\u0010X\"\u0005\b\u009a\u0001\u0010ZR\"\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0012\n\u0000\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001\"\u0006\b\u009d\u0001\u0010\u009e\u0001R \u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u009f\u0001\u0010T\"\u0005\b\u00a0\u0001\u0010VR \u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a1\u0001\u0010F\"\u0005\b\u00a2\u0001\u0010HR \u0010 \u001a\u00020\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a3\u0001\u0010X\"\u0005\b\u00a4\u0001\u0010ZR \u0010/\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a5\u0001\u0010F\"\u0005\b\u00a6\u0001\u0010HR \u0010.\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u00a7\u0001\u0010F\"\u0005\b\u00a8\u0001\u0010H\u00a8\u0006\u00e9\u0001"}, d2 = {"Lcom/puxiansheng/logic/bean/Shop;", "Landroid/os/Parcelable;", "shopID", "", "title", "", "size", "", "rent", "fee", "address", "Lcom/puxiansheng/logic/bean/Address;", "lng", "lat", "newLng", "newLat", "industry", "runningState", "", "includeFacilities", "image", "images", "", "floor", "labels", "Lcom/puxiansheng/logic/bean/MenuItem;", "facilities", "allFacilities", "description", "descriptionUrl", "environment", "reason", "transferType", "isSuccess", "formatted_is_success", "formattedDate", "formattedSize", "formattedRent", "formattedPageViews", "formattedFee", "formattedLocationNodes", "formattedIndustry", "formattedFinalIndustry", "formattedFinalLocationNode", "formattedFacilities", "rentView", "viewOpening", "viewCanEmpty", "isTop", "isHot", "isRecommend", "isLargeOrder", "largeOrderImg", "category_acreage", "formattedArea", "isVip", "data_type", "jump_type", "jump_view", "jump_param", "(JLjava/lang/String;DDDLcom/puxiansheng/logic/bean/Address;Ljava/lang/String;Ljava/lang/String;DDLjava/lang/String;IILjava/lang/String;Ljava/util/List;ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAddress", "()Lcom/puxiansheng/logic/bean/Address;", "setAddress", "(Lcom/puxiansheng/logic/bean/Address;)V", "getAllFacilities", "()Ljava/util/List;", "setAllFacilities", "(Ljava/util/List;)V", "getCategory_acreage", "()Ljava/lang/String;", "setCategory_acreage", "(Ljava/lang/String;)V", "getData_type", "setData_type", "getDescription", "setDescription", "getDescriptionUrl", "setDescriptionUrl", "getEnvironment", "setEnvironment", "getFacilities", "setFacilities", "getFee", "()D", "setFee", "(D)V", "getFloor", "()I", "setFloor", "(I)V", "getFormattedArea", "setFormattedArea", "getFormattedDate", "setFormattedDate", "getFormattedFacilities", "setFormattedFacilities", "getFormattedFee", "setFormattedFee", "getFormattedFinalIndustry", "setFormattedFinalIndustry", "getFormattedFinalLocationNode", "setFormattedFinalLocationNode", "getFormattedIndustry", "setFormattedIndustry", "getFormattedLocationNodes", "setFormattedLocationNodes", "getFormattedPageViews", "setFormattedPageViews", "getFormattedRent", "setFormattedRent", "getFormattedSize", "setFormattedSize", "getFormatted_is_success", "setFormatted_is_success", "getImage", "setImage", "getImages", "setImages", "getIncludeFacilities", "setIncludeFacilities", "getIndustry", "setIndustry", "setHot", "setLargeOrder", "setRecommend", "setSuccess", "setTop", "setVip", "getJump_param", "setJump_param", "getJump_type", "setJump_type", "getJump_view", "setJump_view", "getLabels", "setLabels", "getLargeOrderImg", "setLargeOrderImg", "getLat", "setLat", "getLng", "setLng", "getNewLat", "setNewLat", "getNewLng", "setNewLng", "getReason", "setReason", "getRent", "setRent", "getRentView", "setRentView", "getRunningState", "setRunningState", "getShopID", "()J", "setShopID", "(J)V", "getSize", "setSize", "getTitle", "setTitle", "getTransferType", "setTransferType", "getViewCanEmpty", "setViewCanEmpty", "getViewOpening", "setViewOpening", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component41", "component42", "component43", "component44", "component45", "component46", "component47", "component48", "component49", "component5", "component50", "component51", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "logic_debug"})
public final class Shop implements android.os.Parcelable {
    @androidx.room.ColumnInfo(name = "_shop_id")
    private long shopID;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_title")
    private java.lang.String title;
    @androidx.room.ColumnInfo(name = "_size")
    private double size;
    @androidx.room.ColumnInfo(name = "_rent")
    private double rent;
    @androidx.room.ColumnInfo(name = "_fee")
    private double fee;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.Embedded()
    private com.puxiansheng.logic.bean.Address address;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_lng")
    private java.lang.String lng;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_lat")
    private java.lang.String lat;
    @androidx.room.ColumnInfo(name = "_new_lng")
    private double newLng;
    @androidx.room.ColumnInfo(name = "_new_lat")
    private double newLat;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_industry")
    private java.lang.String industry;
    @androidx.room.ColumnInfo(name = "_running_state")
    private int runningState;
    @androidx.room.ColumnInfo(name = "_exclusive")
    private int includeFacilities;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_image")
    private java.lang.String image;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_images")
    private java.util.List<java.lang.String> images;
    @androidx.room.ColumnInfo(name = "_floor")
    private int floor;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_labels")
    private java.util.List<com.puxiansheng.logic.bean.MenuItem> labels;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_facilities")
    private java.util.List<com.puxiansheng.logic.bean.MenuItem> facilities;
    @org.jetbrains.annotations.Nullable()
    @androidx.room.ColumnInfo(name = "_allfacilities")
    private java.util.List<com.puxiansheng.logic.bean.MenuItem> allFacilities;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_description")
    private java.lang.String description;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_description_url")
    private java.lang.String descriptionUrl;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_environment")
    private java.lang.String environment;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_reason")
    private java.lang.String reason;
    @androidx.room.ColumnInfo(name = "_transfer_type")
    private int transferType;
    @androidx.room.ColumnInfo(name = "_is_success")
    private int isSuccess;
    @androidx.room.ColumnInfo(name = "_formatted_is_success")
    private int formatted_is_success;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_date")
    private java.lang.String formattedDate;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_size")
    private java.lang.String formattedSize;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_rent")
    private java.lang.String formattedRent;
    @androidx.room.ColumnInfo(name = "_formatted_page_views")
    private int formattedPageViews;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_fee")
    private java.lang.String formattedFee;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_location_nodes")
    private java.lang.String formattedLocationNodes;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_industry")
    private java.lang.String formattedIndustry;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_final_industry")
    private java.lang.String formattedFinalIndustry;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_final_location_node")
    private java.lang.String formattedFinalLocationNode;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "view_demand_ids")
    private java.util.List<com.puxiansheng.logic.bean.MenuItem> formattedFacilities;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_rent_view")
    private java.lang.String rentView;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_view_opening")
    private java.lang.String viewOpening;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_view_can_empty")
    private java.lang.String viewCanEmpty;
    @androidx.room.ColumnInfo(name = "_is_top")
    private int isTop;
    @androidx.room.ColumnInfo(name = "_is_hot")
    private int isHot;
    @androidx.room.ColumnInfo(name = "_is_recommend")
    private int isRecommend;
    @androidx.room.ColumnInfo(name = "_is_large_order")
    private int isLargeOrder;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_large_order_img")
    private java.lang.String largeOrderImg;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_category_acreage")
    private java.lang.String category_acreage;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_formatted_area")
    private java.lang.String formattedArea;
    @androidx.room.ColumnInfo(name = "_is_vip")
    private int isVip;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_data_type")
    private java.lang.String data_type;
    @androidx.room.ColumnInfo(name = "_jump_type")
    private int jump_type;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_jump_view")
    private java.lang.String jump_view;
    @org.jetbrains.annotations.NotNull()
    @androidx.room.ColumnInfo(name = "_jump_param")
    private java.lang.String jump_param;
    public static final android.os.Parcelable.Creator CREATOR = null;
    
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
    
    public final double getFee() {
        return 0.0;
    }
    
    public final void setFee(double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Address getAddress() {
        return null;
    }
    
    public final void setAddress(@org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Address p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLng() {
        return null;
    }
    
    public final void setLng(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLat() {
        return null;
    }
    
    public final void setLat(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public final double getNewLng() {
        return 0.0;
    }
    
    public final void setNewLng(double p0) {
    }
    
    public final double getNewLat() {
        return 0.0;
    }
    
    public final void setNewLat(double p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getIndustry() {
        return null;
    }
    
    public final void setIndustry(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getRunningState() {
        return 0;
    }
    
    public final void setRunningState(int p0) {
    }
    
    public final int getIncludeFacilities() {
        return 0;
    }
    
    public final void setIncludeFacilities(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImage() {
        return null;
    }
    
    public final void setImage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getImages() {
        return null;
    }
    
    public final void setImages(@org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> p0) {
    }
    
    public final int getFloor() {
        return 0;
    }
    
    public final void setFloor(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> getLabels() {
        return null;
    }
    
    public final void setLabels(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> getFacilities() {
        return null;
    }
    
    public final void setFacilities(@org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> getAllFacilities() {
        return null;
    }
    
    public final void setAllFacilities(@org.jetbrains.annotations.Nullable()
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
    
    public final int getTransferType() {
        return 0;
    }
    
    public final void setTransferType(int p0) {
    }
    
    public final int isSuccess() {
        return 0;
    }
    
    public final void setSuccess(int p0) {
    }
    
    public final int getFormatted_is_success() {
        return 0;
    }
    
    public final void setFormatted_is_success(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedDate() {
        return null;
    }
    
    public final void setFormattedDate(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedSize() {
        return null;
    }
    
    public final void setFormattedSize(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedRent() {
        return null;
    }
    
    public final void setFormattedRent(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getFormattedPageViews() {
        return 0;
    }
    
    public final void setFormattedPageViews(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedFee() {
        return null;
    }
    
    public final void setFormattedFee(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getFormattedIndustry() {
        return null;
    }
    
    public final void setFormattedIndustry(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedFinalIndustry() {
        return null;
    }
    
    public final void setFormattedFinalIndustry(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
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
    public final java.lang.String getRentView() {
        return null;
    }
    
    public final void setRentView(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategory_acreage() {
        return null;
    }
    
    public final void setCategory_acreage(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormattedArea() {
        return null;
    }
    
    public final void setFormattedArea(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int isVip() {
        return 0;
    }
    
    public final void setVip(int p0) {
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
    
    public Shop(long shopID, @org.jetbrains.annotations.NotNull()
    java.lang.String title, double size, double rent, double fee, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Address address, @org.jetbrains.annotations.Nullable()
    java.lang.String lng, @org.jetbrains.annotations.Nullable()
    java.lang.String lat, double newLng, double newLat, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, int runningState, int includeFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String image, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> images, int floor, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> labels, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> facilities, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> allFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String descriptionUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String environment, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, int transferType, int isSuccess, int formatted_is_success, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedSize, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedRent, int formattedPageViews, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFee, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedLocationNodes, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalLocationNode, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> formattedFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String rentView, @org.jetbrains.annotations.NotNull()
    java.lang.String viewOpening, @org.jetbrains.annotations.NotNull()
    java.lang.String viewCanEmpty, int isTop, int isHot, int isRecommend, int isLargeOrder, @org.jetbrains.annotations.NotNull()
    java.lang.String largeOrderImg, @org.jetbrains.annotations.NotNull()
    java.lang.String category_acreage, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedArea, int isVip, @org.jetbrains.annotations.NotNull()
    java.lang.String data_type, int jump_type, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_view, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_param) {
        super();
    }
    
    public Shop() {
        super();
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.puxiansheng.logic.bean.Address component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int component13() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component15() {
        return null;
    }
    
    public final int component16() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component17() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component18() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component19() {
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
    
    public final int component25() {
        return 0;
    }
    
    public final int component26() {
        return 0;
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
    
    public final int component30() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component31() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component34() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component35() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<com.puxiansheng.logic.bean.MenuItem> component36() {
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
    
    public final int component40() {
        return 0;
    }
    
    public final int component41() {
        return 0;
    }
    
    public final int component42() {
        return 0;
    }
    
    public final int component43() {
        return 0;
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
    
    public final int component47() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component48() {
        return null;
    }
    
    public final int component49() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component50() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component51() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.logic.bean.Shop copy(long shopID, @org.jetbrains.annotations.NotNull()
    java.lang.String title, double size, double rent, double fee, @org.jetbrains.annotations.Nullable()
    com.puxiansheng.logic.bean.Address address, @org.jetbrains.annotations.Nullable()
    java.lang.String lng, @org.jetbrains.annotations.Nullable()
    java.lang.String lat, double newLng, double newLat, @org.jetbrains.annotations.NotNull()
    java.lang.String industry, int runningState, int includeFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String image, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> images, int floor, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> labels, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> facilities, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> allFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String descriptionUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String environment, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, int transferType, int isSuccess, int formatted_is_success, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedDate, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedSize, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedRent, int formattedPageViews, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFee, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedLocationNodes, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalIndustry, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedFinalLocationNode, @org.jetbrains.annotations.Nullable()
    java.util.List<com.puxiansheng.logic.bean.MenuItem> formattedFacilities, @org.jetbrains.annotations.NotNull()
    java.lang.String rentView, @org.jetbrains.annotations.NotNull()
    java.lang.String viewOpening, @org.jetbrains.annotations.NotNull()
    java.lang.String viewCanEmpty, int isTop, int isHot, int isRecommend, int isLargeOrder, @org.jetbrains.annotations.NotNull()
    java.lang.String largeOrderImg, @org.jetbrains.annotations.NotNull()
    java.lang.String category_acreage, @org.jetbrains.annotations.NotNull()
    java.lang.String formattedArea, int isVip, @org.jetbrains.annotations.NotNull()
    java.lang.String data_type, int jump_type, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_view, @org.jetbrains.annotations.NotNull()
    java.lang.String jump_param) {
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