package com.puxiansheng.logic.data.location

import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.HttpRespCurrentLocation
import com.puxiansheng.logic.bean.http.HttpRespLocationNodes
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteLocationRepository {
    fun requestRemoteCitiesByParentID(parentID: String): APIRst<APIResp<HttpRespLocationNodes>> =
        buildRequest(
            url = API.GET_AREA_BY_CITY_ID,
            fieldMap = mutableMapOf(
                "parent_id" to parentID
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

    fun getRemoteSupportedCities(): APIRst<APIResp<HttpRespLocationNodes>> =
        buildRequest(
            url = API.GET_NEW_SUPPORTED_CITIES,
            fieldMap = mutableMapOf<String, String>().also {
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

    fun getCurrentLocationFromRemote(
        location: String? = null
    ): APIRst<APIResp<HttpRespCurrentLocation>> =
        buildRequest(
            url = API.GET_CURRENT_LOCATION,
            fieldMap = mutableMapOf<String, String>().also { map ->
                location?.let {
                    map["name"] = location
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
}