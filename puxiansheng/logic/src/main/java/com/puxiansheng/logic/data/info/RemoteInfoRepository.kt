package com.puxiansheng.logic.data.info

import android.icu.text.CaseMap
import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespHistoryInfoList
import com.puxiansheng.logic.bean.http.HttpRespInfoCategory
import com.puxiansheng.logic.bean.http.HttpRespInfoList
import com.puxiansheng.logic.bean.http.HttpRespOrders
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteInfoRepository {

    fun getInfoCategoriesFromRemote(): APIRst<APIResp<HttpRespInfoCategory>> = buildRequest(
        url = API.GET_INFO_CATEGORY,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getInfoByCategoryFromRemote(
        category: Int,
        page: Int,
        city: String? = null,title: String? = null
    ): APIRst<APIResp<HttpRespInfoList>> =
        buildRequest(
            url = API.GET_INFO_LIST,
            fieldMap = mutableMapOf(
                "page" to page.toString(),
                "cate" to category.toString()
            ).also { map ->
                city?.let {
                    map["city"] = it
                }
                title?.let {
                    map["title"] = it
                }

                map["sign"] = API.sign(
                    signatureToken = API.currentSignatureToken,
                    fieldMap = map,
                    method = "GET"
                )
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }

    fun getFavoriteInfoFromRemote(
        page: Int
    ): APIRst<APIResp<HttpRespInfoList>> = buildRequest(
        url = API.GET_ARTICLE_FAVORITE,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also {
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


    fun getHistoryInfoFromRemote(
        page: Int
    ): APIRst<APIResp<HttpRespHistoryInfoList>> = buildRequest(
        url = API.GET_ARTICLE_HISTORY,
        fieldMap = mutableMapOf(
            "page" to page.toString()
        ).also {
            it["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = it,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        Log.d("---history"," getHistoryInfoFromRemote it= "+it)
        API.call(it)
    }


}