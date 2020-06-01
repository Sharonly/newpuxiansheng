package com.puxiansheng.logic.data.message

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.*
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteMessageRepository {

    fun getMessageCategoriesFromRemote(): APIRst<APIResp<HttpRespInfoCategory>> = buildRequest(
        url = API.GET_MESSAGE_CATEGORY,
        fieldMap = mutableMapOf<String, String>().also {
            it["sign"] =
                API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
        },
        method = METHOD.GET
    ).let {
        API.call(it)
    }

    fun getMessageByCategoryFromRemote(
        category: Int,
        page: Int,
        city: String? = null,title: String? = null
    ): APIRst<APIResp<HttpRespMessageList>> =
        buildRequest(
            url = API.GET_MESSAGE_LIST,
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

    fun getMessagedDetailFromRemote(
        messageId: String
    ): APIRst<APIResp<MessageListObject>> =
        buildRequest(
            url = API.GET_MESSAGE_DETAIL,
            fieldMap = mutableMapOf(
                "id" to messageId
            ).also { map ->
                map["sign"] = API.sign(
                    signatureToken = API.currentSignatureToken,
                    fieldMap = map,
                    method = "GET"
                )
            },
            method = METHOD.GET
        ).let {
            Log.d("---message--","detail it = "+it)
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


    fun deleteAllHistroyInfoFromRemote(
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_HISTORY_ORDER,
        fieldMap = mutableMapOf(
            "type" to "2"
        ).also { map ->
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

    fun deleteFavorInfoFromRemote(
        infoId: String
    ): APIRst<APIResp<HttpRespEmpty>> = buildRequest(
        url = API.DELETE_FAVOR_ORDER,
        fieldMap = mutableMapOf(
            "id" to infoId,"type" to "2"
        ).also { map ->
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

}