package com.puxiansheng.logic.data.menu

import com.puxiansheng.logic.api.API.GET_MULTI_CITY_AREA_DATA
import com.puxiansheng.logic.api.API.GET_REMOTE_INDUSTRY_DATA
import com.puxiansheng.logic.api.API.GET_REMOTE_NEW_AREA_DATA
import com.puxiansheng.logic.api.API.GET_REMOTE_PROPERTY_DATA
import com.puxiansheng.logic.api.API.GET_REMOTE_RENT_DATA
import com.puxiansheng.logic.api.API.GET_REMOTE_RENT_UNIT_DATA
import com.puxiansheng.logic.api.API.GET_REMOTE_SIZE_DATA
import com.puxiansheng.logic.api.API.sign
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.logic.api.API.call
import com.puxiansheng.logic.bean.http.HttpRespAreaObject
import com.puxiansheng.logic.bean.http.HttpRespMenuDate
import com.puxiansheng.util.http.buildRequest

class RemoteMenuRepository {
    fun requestRemoteIndustrySelectiveData(signatureToken: String): APIRst<APIResp<HttpRespMenuDate>> =
        buildRequest(
            url = GET_REMOTE_INDUSTRY_DATA, fieldMap = mutableMapOf(
                "tree" to "0",
                "id" to "0"
            ).also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemoteIndustrySelectiveById(signatureToken: String,id:Int): APIRst<APIResp<HttpRespMenuDate>> =
        buildRequest(
            url = GET_REMOTE_INDUSTRY_DATA, fieldMap = mutableMapOf(
                "tree" to "0",
                "id" to id.toString()
            ).also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }



    fun requestRemoteMultiAreaSelectiveData(tree:String,ids:String,signatureToken: String): APIRst<APIResp<HttpRespAreaObject>> =
        buildRequest(
            url = GET_MULTI_CITY_AREA_DATA, fieldMap = mutableMapOf(
                "tree" to tree,
                "ids" to ids
            ).also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemoteNewAreaSelectiveData(tree:String,signatureToken: String): APIRst<APIResp<HttpRespAreaObject>> =
        buildRequest(
            url = GET_REMOTE_NEW_AREA_DATA, fieldMap = mutableMapOf(
                "tree" to tree,
                "id" to "0"
            ).also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemoteNewAreaById(signatureToken: String,id:Int): APIRst<APIResp<HttpRespAreaObject>> =
        buildRequest(
            url = GET_REMOTE_NEW_AREA_DATA, fieldMap = mutableMapOf(
                "tree" to "0",
                "id" to id.toString()
            ).also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemoteSizeSelectiveData(signatureToken: String): APIRst<APIResp<HttpRespMenuDate>> =
        buildRequest(
            url = GET_REMOTE_SIZE_DATA, fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemoteRentUnitSelectiveData(signatureToken: String): APIRst<APIResp<HttpRespMenuDate>> =
        buildRequest(
            url = GET_REMOTE_RENT_UNIT_DATA,
            fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemoteRentSelectiveData(signatureToken: String): APIRst<APIResp<HttpRespMenuDate>> =
        buildRequest(
            url = GET_REMOTE_RENT_DATA, fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }

    fun requestRemotePropertySelectiveData(signatureToken: String): APIRst<APIResp<HttpRespMenuDate>> =
        buildRequest(
            url = GET_REMOTE_PROPERTY_DATA, fieldMap = mutableMapOf<String, String>().also {
                it["sign"] = sign(signatureToken = signatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            call(it)
        }
}