package com.puxiansheng.logic.data.user

import android.util.Log
import com.puxiansheng.logic.BuildConfig
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.api.API.DELETE_HISTORY_SEARCH
import com.puxiansheng.logic.api.API.DO_FAVORITE
import com.puxiansheng.logic.api.API.DO_FORGET_PASSWORD
import com.puxiansheng.logic.api.API.DO_LOGIN
import com.puxiansheng.logic.api.API.DO_LOGIN_BY_PASS
import com.puxiansheng.logic.api.API.DO_LOGIN_BY_PHONE
import com.puxiansheng.logic.api.API.DO_LOGIN_BY_WECHAT
import com.puxiansheng.logic.api.API.DO_LOGOUT
import com.puxiansheng.logic.api.API.DO_REGISTER
import com.puxiansheng.logic.api.API.DO_RESET_PASSWORD
import com.puxiansheng.logic.api.API.GET_HISTORY_SEARCH
import com.puxiansheng.logic.api.API.GET_RECOMMEND_SEARCH
import com.puxiansheng.logic.api.API.GET_REQUEST_LIST
import com.puxiansheng.logic.api.API.GET_REQUEST_TYPE
import com.puxiansheng.logic.api.API.GET_USER_CALLBACK
import com.puxiansheng.logic.api.API.GET_USER_INFO
import com.puxiansheng.logic.api.API.call
import com.puxiansheng.logic.api.API.callForJson
import com.puxiansheng.logic.api.API.callAny
import com.puxiansheng.logic.api.API.sign
import com.puxiansheng.logic.bean.HttpSearchObject
import com.puxiansheng.logic.bean.http.*
import com.puxiansheng.util.http.*

class RemoteUserRepository {
    fun login(
        userAccount: String,
        userPassword: String?,
        newPassword: String?,
        verificationCode: String?,
        wechatCode: String?
    ) = buildRequest(
        url = DO_LOGIN, fieldMap = mutableMapOf(
            "name" to userAccount,
            "password" to (userPassword ?: ""),
            "code" to (verificationCode ?: ""),
            "new_password" to (newPassword ?: ""),
            "wechat_code" to (wechatCode ?: "")
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }


    fun register(
        userAccount: String,
        code: String?
    ) = buildRequest(
        url = DO_REGISTER, fieldMap = mutableMapOf(
            "name" to userAccount,
            "code" to (code ?: "")
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }


    fun forgetPassword(
        userAccount: String,
        code: String,
        password: String,
        newPassword: String
    ) = buildRequest(
        url = DO_FORGET_PASSWORD, fieldMap = mutableMapOf(
            "name" to userAccount,
            "code" to code,
            "password" to password,
            "new_password" to newPassword
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }

    fun loginByPass(
        userAccount: String,
        userPassword: String?
    ) = buildRequest(
        url = DO_LOGIN_BY_PASS, fieldMap = mutableMapOf(
            "name" to userAccount,
            "password" to (userPassword ?: "")
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }


    fun loginByPhoneNum(
        userAccount: String,
        code: String?
    ) = buildRequest(
        url = DO_LOGIN_BY_PHONE, fieldMap = mutableMapOf(
            "name" to userAccount,
            "code" to (code ?: "")
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }

    fun loginByWeChat(
        weChatCode: String
    ) = buildRequest(
        url = DO_LOGIN_BY_WECHAT, fieldMap = mutableMapOf(
            "wechat_code" to (weChatCode ?: "")
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        Log.d("---login--","DO_LOGIN_BY_WECHAT it = "+it)
        callForJson(it)
    }

    fun logout(

    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = DO_LOGOUT,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        call(it)
    }

    fun resetPassword(
        originalPassword: String,
        newPassword: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = DO_RESET_PASSWORD,
        fieldMap = mutableMapOf(
            "password" to originalPassword,
            "new_password" to newPassword
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        call(it)
    }

    fun requireRemoteUserInfo(
    ): APIRst<APIResp<HttpUserInfo>> = buildRequest(
        url = GET_USER_INFO,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        Log.d("---login--", "userinfo = " + it)
        call(it)
    }

    fun favorite(
        objectID: String,
        type: Int
    ): APIRst<HttpRespFavorite> = buildRequest(
        url = DO_FAVORITE,
        fieldMap = mutableMapOf(
            "id" to objectID,
            "type" to type.toString()
        ).also {
            it["sign"] = sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        callAny(it)
    }

    fun submitUserInfo(
        actualName: String,
        sex: String,
        nickName: String,
        headerImg: String? = null,
        address: String? = null,
        cityId: Int
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.SAVE_USER_INFO,
        fieldMap = mutableMapOf(
            "actual_name" to actualName,
            "sex" to sex,
            "nick_name" to nickName,
            "city_id" to cityId.toString()
        ).also { map ->
            headerImg?.let {
                if (it != "") {
                    map["header_img"] = headerImg
                }
            }
            map["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = map, method = "POST")
        }
    ).let {
        call(it)
    }

    fun submitSuggestion(content: String): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.SUBMIT_SUGGESTION,
        fieldMap = mutableMapOf("content" to content).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        call(it)
    }

    fun getRequestType(): APIRst<APIResp<HttpRespMenuDate>> = buildRequest(
        url = GET_REQUEST_TYPE,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        Log.d("---request--", " getRequestType it = " + it)
        call(it)
    }

    fun getRequestList(): APIRst<APIResp<HttpRespRequest>> = buildRequest(
        url = GET_REQUEST_LIST,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        Log.d("---request--", " getRequestList = " + it)
        call(it)
    }

    fun getUserCallBack(): APIRst<APIResp<HttpRespRequest>> = buildRequest(
        url = GET_USER_CALLBACK,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        }, method = METHOD.GET
    ).let {
        call(it)
    }


    fun bindMobileNumber(
        phone: String,
        code: String,
        id: String
    ) = buildRequest(
        url = API.DO_BIND_MOBILE_NUMBER,
        fieldMap = mutableMapOf(
            "phone" to phone,
            "code" to code,
            "id" to id
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }


    fun requireHistorySearch(
        userId: String, type: Int
    ): APIRst<APIResp<HttpSearchObject>> = buildRequest(
        url = GET_HISTORY_SEARCH,
        fieldMap = mutableMapOf(
            "user_id" to userId,
            "type" to type.toString()
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        call(it)
    }


    fun deleteHistorySearch(
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = DELETE_HISTORY_SEARCH,
        fieldMap = mutableMapOf<String, String>(
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        call(it)
    }


    fun requireRecommendSearch(
    ): APIRst<APIResp<HttpSearchObject>> = buildRequest(
        url = GET_RECOMMEND_SEARCH,
        fieldMap = mutableMapOf<String, String>(
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {

        call(it)
    }


    fun getReleaseCountFromRemote(): APIRst<APIResp<HttpRespReleaseCountData>> =
        buildRequest(
            url = API.GET_USER_RELEASE_COUNT,
            fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = API.sign(
                    signatureToken = API.currentSignatureToken,
                    fieldMap = it,
                    method = "GET"
                )
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }

}