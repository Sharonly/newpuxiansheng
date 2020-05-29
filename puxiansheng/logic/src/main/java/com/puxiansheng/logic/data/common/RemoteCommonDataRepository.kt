package com.puxiansheng.logic.data.common

import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespServiceLink
import com.puxiansheng.logic.bean.http.HttpRespStatisticsData
import com.puxiansheng.logic.bean.http.HttpRespUrlLink
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteCommonDataRepository {
    fun getStatisticsDataFromRemote(): APIRst<APIResp<HttpRespStatisticsData>> =
        buildRequest(
            url = API.GET_HOME_PAGE_STATISTICS_DATA,
            fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }



    fun getServiceLinkFromRemote(): APIRst<APIResp<HttpRespServiceLink>> =
        buildRequest(
            url = API.GET_SERVICE_LINK,
            fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }


    fun getConfigUrl(name:String) : APIRst<APIResp<HttpRespUrlLink>> =
        buildRequest(
            url = API.GET_CONFIG_URL,
            fieldMap = mutableMapOf("name" to name).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }
}