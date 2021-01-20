package com.puxiansheng.logic.data.homeinfo

import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespMarqueeInfos
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteInfoMarqueeRepository {
    fun requestRemoteMarqueeInfos(city:String): APIRst<APIResp<HttpRespMarqueeInfos>> =
        buildRequest(
            url = API.GET_NEW_PXS_HEADLINE, fieldMap =
            mutableMapOf(
                "city" to city
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
//            Log.d("---pxs_headline", " it = "+it)
            API.call(it)
        }


}