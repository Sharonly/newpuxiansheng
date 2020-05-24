package com.puxiansheng.logic.data.image

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespBannerImages
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteImageRepository {

    fun requestRemoteImages(position: String): APIRst<APIResp<HttpRespBannerImages>> =
        buildRequest(
            url = API.GET_IMAGES, fieldMap = mutableMapOf(
                "position" to position
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("---img--","  it = "+it)
            API.call(it)
        }
}