package com.puxiansheng.logic.data.system

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.api.API.API_APP_ID
import com.puxiansheng.logic.api.API.API_SECRET
import com.puxiansheng.logic.api.API.API_VERSION
import com.puxiansheng.logic.api.API.GET_IMAGE_CODE
import com.puxiansheng.logic.api.API.GET_VERIFICATION_CODE
import com.puxiansheng.logic.api.API.GET_TOKEN
import com.puxiansheng.logic.api.API.GET_SYSTEM_CONFIG
import com.puxiansheng.logic.api.API.call
import com.puxiansheng.logic.api.API.callForJson
import com.puxiansheng.logic.api.API.sign
import com.puxiansheng.logic.api.API.signNew
import com.puxiansheng.logic.bean.SignatureToken
import com.puxiansheng.logic.bean.Device
import com.puxiansheng.logic.bean.http.HttpRespEmpty
import com.puxiansheng.logic.bean.http.HttpRespImageCode
import com.puxiansheng.logic.bean.http.HttpRespSystemConfig
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteSystemRepository {
    /**
     * require signature token from remote server for common http request signature.
     * */
//    fun requestSignatureToken(
//        device: Device, registrationId: String? = null
//    ): APIRst<APIResp<SignatureToken>> = buildRequest(
//        url = GET_TOKEN,
//        fieldMap = mutableMapOf(
//            "appid" to API_APP_ID,
//            "secret" to API_SECRET,
//            "channel" to device.manufacturer,
//            "device_no" to device.uid,
//            "version" to API_VERSION
//        ).also { map ->
//            registrationId?.let {
//                if (it != "") {
//                    map["registration_id"] = it
//                }
//            }
//            Log.d("---device--", " device = " + device+" registrationId = "+registrationId)
//            map["sign"] = sign(signatureToken = null, fieldMap = map, method = "POST")
//        }
//    ).let {
//        call(it)
//    }

    fun requestSignatureToken(
        device: Device, registrationId: String? = null
    ): APIRst<APIResp<SignatureToken>> = buildRequest(
        url = GET_TOKEN,
        fieldMap = mutableMapOf(
            "appid" to API_APP_ID,
            "secret" to API_SECRET,
            "channel" to device.manufacturer,
            "device_no" to device.uid,
            "version" to API_VERSION
        ).also { map ->
            registrationId?.let {
                if (it != "") {
                    map["registration_id"] = it
                }
            }
            Log.d("---device--", " device = " + device+" registrationId = "+registrationId)
            map["sign"] = signNew(signatureToken = null, fieldMap = map, method = "POST")
        }
    ).let {
        call(it)
    }

    fun requestVerificationCode(
        phoneNumber: String,
        key: String,
        code: String,
        type: String
    ) = buildRequest(
        url = GET_VERIFICATION_CODE,
        fieldMap = mutableMapOf(
            "phone" to phoneNumber,
            "captcha_key" to key,
            "captcha_code" to code,
            "type" to type
        ).also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "POST")
        }
    ).let {
        callForJson(it)
    }

    fun requestImageCode(): APIRst<APIResp<HttpRespImageCode>> = buildRequest(
        url = GET_IMAGE_CODE,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        call(it)
    }

    fun requestRemoteSystemConfig(): APIRst<APIResp<HttpRespSystemConfig>> = buildRequest(
        url = GET_SYSTEM_CONFIG,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] = sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        call(it)
    }
}