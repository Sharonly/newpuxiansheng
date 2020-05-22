package com.puxiansheng.logic.data.user

import androidx.annotation.Nullable
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.logic.bean.User
import com.puxiansheng.logic.bean.http.HttpRespUserInfo
import com.puxiansheng.util.http.APIResp

interface ApiContract {
    fun login(
        userAccount: String,
        userPassword: String,
        signatureToken: String,
        @Nullable verificationCode: String,
        @Nullable wechatCode: String
    ): APIRst<APIResp<User>>

    fun requireRemoteUserInfo(
        authToken: String,
        signatureToken: String
    ): APIRst<APIResp<HttpRespUserInfo>>

}