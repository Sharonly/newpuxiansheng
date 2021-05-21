package com.puxiansheng.logic.data.project

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.*
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteProjectRepository {

    fun requestHomeProject(): APIRst<APIResp<ProjectListObject>> =
        buildRequest(
            url = API.GET_NEW_HOME_PROJECT, fieldMap = mutableMapOf( "page" to "1").also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("HOME_project","it = "+it)
            API.call(it)
        }

    fun requestProjectList( industry: String,
                            city: String?,
                            view_count: String, page: Int): APIRst<APIResp<ProjectListObject>> =
        buildRequest(
            url = API.GET_NEW_PROJECT_LIST, fieldMap = mutableMapOf(
                "industry_path" to industry,
                "view_count" to view_count,
                "page" to page.toString()
            ).also {
                    map ->
                city?.let {
                    map["city"] = city
                }
                map["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = map, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("LIST_project","it = "+it)
            API.call(it)
        }


    fun getProjectDetailFromRemote(
        shopID: String
    ): APIRst<APIResp<HttpRespProjectDetail>> = buildRequest(
        url = API.GET_PROJECT_DETAIL,
        fieldMap = mutableMapOf(
            "id" to shopID
        ).also { map ->
            map["sign"] = API.sign(
                signatureToken = API.currentSignatureToken,
                fieldMap = map,
                method = "GET"
            )
        },
        method = METHOD.GET
    ).let {
        Log.d("LIST_project Detail","it = "+it)
        API.call(it)
    }

    fun requestFavorProject(page: Int): APIRst<APIResp<ProjectListObject>> =
        buildRequest(
            url = API.GET_FAVOR_PROJECT, fieldMap = mutableMapOf( "page" to page.toString()).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("favor_project","it = "+it)
            API.call(it)
        }

}