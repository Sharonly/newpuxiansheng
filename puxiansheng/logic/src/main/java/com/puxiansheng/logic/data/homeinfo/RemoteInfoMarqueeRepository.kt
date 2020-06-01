package com.puxiansheng.logic.data.homeinfo

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespBannerImages
import com.puxiansheng.logic.bean.http.HttpRespMarqueeInfos
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteInfoMarqueeRepository {
    fun requestRemoteMarqueeInfos(page: String): APIRst<APIResp<HttpRespMarqueeInfos>> =
        buildRequest(
            url = API.GET_HEADLINE, fieldMap = mutableMapOf(
                "page" to page
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }
}