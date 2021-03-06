package com.puxiansheng.logic.data.business

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.BusinessBean
import com.puxiansheng.logic.bean.http.HttpRespBusinessList
import com.puxiansheng.logic.bean.http.HttpRespEmpty
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteBusinessRepository {

    fun requestBusinessList(title:String,page: Int): APIRst<APIResp<HttpRespBusinessList>> =
        buildRequest(
            url = API.GET_JOIN_LIST, fieldMap = mutableMapOf(
                "page" to page.toString(),
                "title" to title
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }


    fun requestBusinessDetail(id: String): APIRst<APIResp<BusinessBean>> =
        buildRequest(
            url = API.GET_JOIN_DETAIL, fieldMap = mutableMapOf(
                "id" to id
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }


    fun submitBusinessUserInfo(
        id:String,
        name: String,
        phone: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.SUBMIT_JOIN_INFO,
        fieldMap = mutableMapOf(
            "id" to id,
            "name" to name,
            "phone" to phone
        ).also {
            it["sign"] =
                API.sign(
                    signatureToken = API.currentSignatureToken,
                    fieldMap = it,
                    method = "POST"
                )
        }
    ).let {
        API.call(it)
    }

}