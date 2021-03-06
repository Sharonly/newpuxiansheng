package com.puxiansheng.logic.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b{\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J,\u0010\u0090\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0092\u00010\u0091\u0001\"\u0007\b\u0000\u0010\u0093\u0001\u0018\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0086\bJ%\u0010\u0096\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0091\u0001\"\u0007\b\u0000\u0010\u0093\u0001\u0018\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0086\bJ\u0019\u0010\u0097\u0001\u001a\n\u0012\u0005\u0012\u00030\u0098\u00010\u0091\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001J;\u0010\u0099\u0001\u001a\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u0003H\u0093\u00010\u0092\u00010\u0091\u0001\"\f\b\u0000\u0010\u0093\u0001\u0018\u0001*\u00030\u009a\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u00012\b\u0010\u009b\u0001\u001a\u00030\u0089\u0001H\u0086\bJ\u0011\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020\u0004J\u0011\u0010\u009f\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u00a0\u0001\u001a\u00020\u0004J\u0011\u0010\u00a1\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u009e\u0001\u001a\u00020\u0004J1\u0010\u00a2\u0001\u001a\u00020\u00042\t\u0010\u00a3\u0001\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u00a4\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00a5\u00012\u0007\u0010\u00a6\u0001\u001a\u00020\u0004J1\u0010\u00a7\u0001\u001a\u00020\u00042\t\u0010\u00a3\u0001\u001a\u0004\u0018\u00010\u00042\u0014\u0010\u00a4\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00a5\u00012\u0007\u0010\u00a6\u0001\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\'\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010>\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010J\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010K\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010L\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010M\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010P\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010[\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\\\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010_\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010`\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010a\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010c\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010h\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010k\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010m\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010o\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010s\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010u\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010v\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010w\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010x\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u001a\u0010y\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R\u001b\u0010~\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000f\n\u0000\u001a\u0004\b\u007f\u0010{\"\u0005\b\u0080\u0001\u0010}R\u001d\u0010\u0081\u0001\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u0010\n\u0000\u001a\u0005\b\u0082\u0001\u0010{\"\u0005\b\u0083\u0001\u0010}R\u001b\u0010\u0084\u0001\u001a\t\u0012\u0004\u0012\u00020\t0\u0085\u0001\u00a2\u0006\n\n\u0000\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0015\u0010\u0088\u0001\u001a\u00030\u0089\u0001\u00a2\u0006\n\n\u0000\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0010\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u008e\u0001\u001a\t\u0012\u0004\u0012\u00020\t0\u0085\u0001\u00a2\u0006\n\n\u0000\u001a\u0006\b\u008f\u0001\u0010\u0087\u0001\u00a8\u0006\u00a8\u0001"}, d2 = {"Lcom/puxiansheng/logic/api/API;", "", "()V", "API_ADDRESS", "", "API_APP_ID", "API_SECRET", "API_VERSION", "CODE_AUTO_CODE_EMPTY", "", "CODE_AUTO_CODE_ERROR", "CODE_AUTO_CODE_EXPIRED", "CODE_AUTO_CODE_INVALID", "CODE_BAND_MOBILE_NUMBER", "CODE_BANNED_USER", "CODE_BANNED_VERSION", "CODE_EMPTY_SIGNATURE_TOKEN", "CODE_ERROR_ACCOUNT_OR_PWD", "CODE_ERROR_AUTH_TOKEN", "CODE_ERROR_PARAMS", "CODE_ERROR_SIGNATURE_TOKEN", "CODE_ERROR_UNKNOWN", "CODE_REQUIRE_LOGIN", "CODE_SERVER_ERROR", "CODE_SUCCESS", "CODE_UNKNOWN_SIGNATURE_TOKEN", "CODE_USER_NOT_EXIST", "CODE_VERSION_NOT_EXIST", "DELETE_FAVOR_ORDER", "DELETE_HISTORY_ORDER", "DELETE_HISTORY_SEARCH", "DELETE_TRANSFER_IN_ORDER", "DELETE_TRANSFER_OUT_ORDER", "DO_BIND_MOBILE_NUMBER", "DO_FAVORITE", "DO_FORGET_PASSWORD", "DO_LOGIN", "DO_LOGIN_BY_PASS", "DO_LOGIN_BY_PHONE", "DO_LOGIN_BY_WECHAT", "DO_LOGOUT", "DO_REGISTER", "DO_RESET_PASSWORD", "DO_UPLOAD_IMAGE", "GET_ADVERT", "GET_AREA_BY_CITY_ID", "GET_ARTICLE_FAVORITE", "GET_ARTICLE_HISTORY", "GET_CONFIG_URL", "GET_CURRENT_LOCATION", "GET_EDIT_TRANSFER_IN_ORDER", "GET_EDIT_TRANSFER_OUT_ORDER", "GET_FAVORITE", "GET_HEADLINE", "GET_HISTORY", "GET_HISTORY_SEARCH", "GET_HOME_MENU", "GET_HOME_PAGE_STATISTICS_DATA", "GET_IMAGE", "GET_IMAGES", "GET_IMAGE_CODE", "GET_INFO_CATEGORY", "GET_INFO_LIST", "GET_JOIN_DETAIL", "GET_JOIN_LIST", "GET_MESSAGE_CATEGORY", "GET_MESSAGE_DETAIL", "GET_MESSAGE_LIST", "GET_MINE_TRANSFER_IN_ORDERS", "GET_MINE_TRANSFER_OUT_ORDERS", "GET_RECOMMEND_SEARCH", "GET_RECOMMEND_TRANSFER_IN_ORDER", "GET_RECOMMEND_TRANSFER_OUT_ORDER", "GET_REMOTE_INDUSTRY_DATA", "GET_REMOTE_PROPERTY_DATA", "GET_REMOTE_RENT_DATA", "GET_REMOTE_RENT_UNIT_DATA", "GET_REMOTE_SIZE_DATA", "GET_REMOTE_TRANSFER_IN_ORDER", "GET_REMOTE_TRANSFER_IN_ORDERS", "GET_REMOTE_TRANSFER_OUT_ORDER", "GET_REMOTE_TRANSFER_OUT_ORDERS", "GET_REMOTE_TRANSFER_SUCCESS_ORDERS", "GET_REQUEST_LIST", "GET_REQUEST_TYPE", "GET_SAVE_TRANSFER_OUT_ORDER", "GET_SERVICE_LINK", "GET_SUPPORTED_CITIES", "GET_SYSTEM_CONFIG", "GET_TOKEN", "GET_USER_CALLBACK", "GET_USER_INFO", "GET_USER_LIKE_SHOP", "GET_USER_PROCESSING", "GET_USER_PULISHED", "GET_USER_RELEASE_COUNT", "GET_USER_SOLD_OUT", "GET_VERIFICATION_CODE", "LOGIN_ACTUL_NAME", "LOGIN_ACTUL_PHONE", "LOGIN_NICK_NAME", "LOGIN_USER_ICON", "LOGIN_USER_ID", "LOGIN_USER_PHONE", "LOGIN_USER_STATE", "LOGIN_USER_TOKEN", "REFRESH_SHOP", "SAVE_USER_ICON", "SAVE_USER_INFO", "STORAGE_ADDRESS", "SUBMIT_ADVERT", "SUBMIT_JOIN_INFO", "SUBMIT_SIMPLE_TRANSFER_IN_ORDER", "SUBMIT_SIMPLE_TRANSFER_OUT_ORDER", "SUBMIT_SUGGESTION", "SUBMIT_TRANSFER_IN_ORDER", "SUBMIT_TRANSFER_OUT_ORDER", "USER_CITY_ID", "USER_CITY_NAME", "USER_SEX", "WEIXIN_APP_ID", "cityid", "getCityid", "()Ljava/lang/String;", "setCityid", "(Ljava/lang/String;)V", "currentAuthToken", "getCurrentAuthToken", "setCurrentAuthToken", "currentSignatureToken", "getCurrentSignatureToken", "setCurrentSignatureToken", "getToken", "Landroidx/lifecycle/MutableLiveData;", "getGetToken", "()Landroidx/lifecycle/MutableLiveData;", "httpClient", "Lokhttp3/OkHttpClient;", "getHttpClient", "()Lokhttp3/OkHttpClient;", "interceptor", "Lcom/puxiansheng/logic/api/HttpInterceptor;", "logoutSignal", "getLogoutSignal", "call", "Lcom/puxiansheng/util/http/APIRst;", "Lcom/puxiansheng/util/http/APIResp;", "T", "request", "Lokhttp3/Request;", "callAny", "callForJson", "Lokhttp3/Response;", "callWithNewClient", "Landroid/os/Parcelable;", "client", "setAuthToken", "", "token", "setCityId", "id", "setSignatureToken", "sign", "signatureToken", "fieldMap", "", "method", "signNew", "logic_debug"})
public final class API {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_EMPTY_SIGNATURE_TOKEN = 1000;
    public static final int CODE_ERROR_SIGNATURE_TOKEN = 1001;
    public static final int CODE_ERROR_PARAMS = 1002;
    public static final int CODE_UNKNOWN_SIGNATURE_TOKEN = 1003;
    public static final int CODE_ERROR_AUTH_TOKEN = 1004;
    public static final int CODE_USER_NOT_EXIST = 1005;
    public static final int CODE_ERROR_ACCOUNT_OR_PWD = 1006;
    public static final int CODE_BANNED_USER = 1007;
    public static final int CODE_AUTO_CODE_EMPTY = 1008;
    public static final int CODE_AUTO_CODE_ERROR = 1009;
    public static final int CODE_AUTO_CODE_EXPIRED = 1010;
    public static final int CODE_AUTO_CODE_INVALID = 1011;
    public static final int CODE_REQUIRE_LOGIN = 1012;
    public static final int CODE_SERVER_ERROR = 5000;
    public static final int CODE_BAND_MOBILE_NUMBER = 4012;
    public static final int CODE_VERSION_NOT_EXIST = 4031;
    public static final int CODE_BANNED_VERSION = 4032;
    public static final int CODE_ERROR_UNKNOWN = 9999;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String API_APP_ID = "cee34b0e9989df19";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String API_SECRET = "6385dab0cee34b0e9989df190522d449";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String API_VERSION = "332";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String WEIXIN_APP_ID = "wxe5266f2fb1236eee";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_USER_ID = "user_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_USER_TOKEN = "user_token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_USER_PHONE = "user_phone";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_ACTUL_PHONE = "actual_phone";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_USER_ICON = "user_icon";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_NICK_NAME = "nick_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_SEX = "user_sex";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_ACTUL_NAME = "actual_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LOGIN_USER_STATE = "user_login_state";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_CITY_ID = "city_id";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String USER_CITY_NAME = "city_name";
    private static final java.lang.String API_ADDRESS = "https://api3.puxiansheng.com/";
    private static final java.lang.String STORAGE_ADDRESS = "https://api3.puxiansheng.com/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_LOGIN = "https://api3.puxiansheng.com/api/login.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_LOGIN_BY_PASS = "https://api3.puxiansheng.com/api/user_login.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_LOGIN_BY_PHONE = "https://api3.puxiansheng.com/api/sms_login.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_LOGIN_BY_WECHAT = "https://api3.puxiansheng.com/api/wechat_login.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_REGISTER = "https://api3.puxiansheng.com/api/register.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_FORGET_PASSWORD = "https://api3.puxiansheng.com/api/user_change_pwd.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_LOGOUT = "https://api3.puxiansheng.com/api/login_out.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_BIND_MOBILE_NUMBER = "https://api3.puxiansheng.com/api/auth/bind_phone.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_RESET_PASSWORD = "https://api3.puxiansheng.com/api/user_change/edit_pwd.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_FAVORITE = "https://api3.puxiansheng.com/api/set_collect.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_INFO = "https://api3.puxiansheng.com/api/get_userinfo.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_SYSTEM_CONFIG = "https://api3.puxiansheng.com/api/config.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_TOKEN = "https://api3.puxiansheng.com/api/get_token.token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_VERIFICATION_CODE = "https://api3.puxiansheng.com/api/send_msg.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_IMAGE_CODE = "https://api3.puxiansheng.com/api/get_captcha.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SAVE_USER_INFO = "https://api3.puxiansheng.com/api/user_edit_save.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SAVE_USER_ICON = "https://api3.puxiansheng.com/api/upload/header_img.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_HOME_MENU = "https://api3.puxiansheng.com/api/home_menu.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_JOIN_LIST = "https://api3.puxiansheng.com/api/join/get_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_JOIN_DETAIL = "https://api3.puxiansheng.com/api/join/get_info.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_JOIN_INFO = "https://api3.puxiansheng.com/api/join/submit.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_HISTORY_SEARCH = "https://api3.puxiansheng.com/api/history_search.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_HISTORY_SEARCH = "https://api3.puxiansheng.com/api/history/search/del.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_RECOMMEND_SEARCH = "https://api3.puxiansheng.com/api/transfer/hot/search.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_IMAGES = "https://api3.puxiansheng.com/api/get_images.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_IMAGE = "https://api3.puxiansheng.com/api/get_images_info.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_HEADLINE = "https://api3.puxiansheng.com/api/get_headlines.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_ADVERT = "https://api3.puxiansheng.com/api/ad/get_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_ADVERT = "https://api3.puxiansheng.com/api/ad/ad_report.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_LIKE_SHOP = "https://api3.puxiansheng.com/api/transfer/get_user_like.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_INFO_CATEGORY = "https://api3.puxiansheng.com/api/article/get_cate_new.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_INFO_LIST = "https://api3.puxiansheng.com/api/article/get_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_MESSAGE_CATEGORY = "https://api3.puxiansheng.com/api/notice/get_cate_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_MESSAGE_LIST = "https://api3.puxiansheng.com/api/notice/get_msg_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_MESSAGE_DETAIL = "https://api3.puxiansheng.com/api/notice/get_info.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_AREA_BY_CITY_ID = "https://api3.puxiansheng.com/api/area/list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_SUPPORTED_CITIES = "https://api3.puxiansheng.com/api/area/get_open.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_CURRENT_LOCATION = "https://api3.puxiansheng.com/api/area/position.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DO_UPLOAD_IMAGE = "https://api3.puxiansheng.com/api/upload/transfer_img.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_SIMPLE_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/transfer/fast_transfer.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_SIMPLE_TRANSFER_IN_ORDER = "https://api3.puxiansheng.com/api/find/fast_find.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/transfer/shop_submit.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_TRANSFER_IN_ORDER = "https://api3.puxiansheng.com/api/find/shop_submit.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_TRANSFER_OUT_ORDERS = "https://api3.puxiansheng.com/api/transfer/get_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_TRANSFER_IN_ORDERS = "https://api3.puxiansheng.com/api/find/get_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_TRANSFER_SUCCESS_ORDERS = "https://api3.puxiansheng.com/api/transfer/get_success.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/transfer_shop/info.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_TRANSFER_IN_ORDER = "https://api3.puxiansheng.com/api/find_shop/info.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_MINE_TRANSFER_OUT_ORDERS = "https://api3.puxiansheng.com/api/user/get_transfer_issue.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_MINE_TRANSFER_IN_ORDERS = "https://api3.puxiansheng.com/api/user/get_find_shop_issue.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_TRANSFER_IN_ORDER = "https://api3.puxiansheng.com/api/user/del_find.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/user/del_transfer.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_FAVOR_ORDER = "https://api3.puxiansheng.com/api/user/collect_null.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String REFRESH_SHOP = "https://api3.puxiansheng.com/api/user/update_shop_time.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DELETE_HISTORY_ORDER = "https://api3.puxiansheng.com/api/new/clear_view_log.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_EDIT_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/transfer_shop/edit_echo.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_EDIT_TRANSFER_IN_ORDER = "https://api3.puxiansheng.com/api/find_shop/edit_echo.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_SAVE_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/transfer/shop_submit_echo.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_RECOMMEND_TRANSFER_OUT_ORDER = "https://api3.puxiansheng.com/api/transfer/get_new_recommend.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_RECOMMEND_TRANSFER_IN_ORDER = "https://api3.puxiansheng.com/api/find/get_new_recommend.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SUBMIT_SUGGESTION = "https://api3.puxiansheng.com/api/feedback_submit.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REQUEST_TYPE = "https://api3.puxiansheng.com/api/get_feedback_cate.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REQUEST_LIST = "https://api3.puxiansheng.com/api/get_problems.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_CALLBACK = "https://api3.puxiansheng.com/api/my_feedback.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_FAVORITE = "https://api3.puxiansheng.com/api/user/collect_log.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_HISTORY = "https://api3.puxiansheng.com/api/user/view_log.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_ARTICLE_HISTORY = "https://api3.puxiansheng.com/api/article/get_view_list";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_ARTICLE_FAVORITE = "https://api3.puxiansheng.com/api/article/get_collect_list.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_PULISHED = "https://api3.puxiansheng.com/api/user/get_release_shop.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_SOLD_OUT = "https://api3.puxiansheng.com/api/user/get_stop_shop.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_PROCESSING = "https://api3.puxiansheng.com/api/user/get_verify_shop.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_INDUSTRY_DATA = "https://api3.puxiansheng.com/api/shop/get_industry.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_SIZE_DATA = "https://api3.puxiansheng.com/api/shop/get_acreage.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_RENT_UNIT_DATA = "https://api3.puxiansheng.com/api/shop/get_ret_unit.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_RENT_DATA = "https://api3.puxiansheng.com/api/shop/get_rent.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_REMOTE_PROPERTY_DATA = "https://api3.puxiansheng.com/api/shop/get_property.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_HOME_PAGE_STATISTICS_DATA = "https://api3.puxiansheng.com/api/shop/statistics.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_USER_RELEASE_COUNT = "https://api3.puxiansheng.com/api/user/release/count.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_SERVICE_LINK = "https://api3.puxiansheng.com/api/config/kf_url.html";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String GET_CONFIG_URL = "https://api3.puxiansheng.com/api/config.html";
    private static final com.puxiansheng.logic.api.HttpInterceptor interceptor = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.lifecycle.MutableLiveData<java.lang.Integer> logoutSignal = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.lifecycle.MutableLiveData<java.lang.Integer> getToken = null;
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient httpClient = null;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String currentSignatureToken;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String currentAuthToken;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String cityid;
    public static final com.puxiansheng.logic.api.API INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getLogoutSignal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getGetToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient getHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentSignatureToken() {
        return null;
    }
    
    public final void setCurrentSignatureToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentAuthToken() {
        return null;
    }
    
    public final void setCurrentAuthToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCityid() {
        return null;
    }
    
    public final void setCityid(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final void setSignatureToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void setAuthToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token) {
    }
    
    public final void setCityId(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.puxiansheng.util.http.APIRst<okhttp3.Response> callForJson(@org.jetbrains.annotations.NotNull()
    okhttp3.Request request) {
        return null;
    }
    
    /**
     * gen signed token for every http request as param in any http request body.
     * @param signatureToken given by remote server.
     * @param fieldMap which the params containing in the http request.
     * @param method simple string, please input "POST" or "GET" .etc as uppercase.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sign(@org.jetbrains.annotations.Nullable()
    java.lang.String signatureToken, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> fieldMap, @org.jetbrains.annotations.NotNull()
    java.lang.String method) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String signNew(@org.jetbrains.annotations.Nullable()
    java.lang.String signatureToken, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> fieldMap, @org.jetbrains.annotations.NotNull()
    java.lang.String method) {
        return null;
    }
    
    private API() {
        super();
    }
}