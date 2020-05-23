package com.puxiansheng.logic.data.business

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespBusinessList
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteBusinessRepository {

    fun requestBusinessList(page: Int): APIRst<APIResp<HttpRespBusinessList>> =
        buildRequest(
            url = API.GET_JOIN_LIST, fieldMap = mutableMapOf(
                "page" to page.toString()
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("---business--"," it = "+it)
            API.call(it)
        }
}