package com.puxiansheng.logic.data.image

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.logic.bean.http.*
import com.puxiansheng.util.http.APIResp
import com.puxiansheng.util.http.APIRst
import com.puxiansheng.util.http.METHOD
import com.puxiansheng.util.http.buildRequest

class RemoteImageRepository {

    fun requestMenuImages(): APIRst<APIResp<HttpRespBannerImages>> =
        buildRequest(
            url = API.GET_HOME_MENU, fieldMap = mutableMapOf<String,String>().also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("homeMenu","it = "+it)
            API.call(it)
        }


    fun requestNewMenuImages(): APIRst<APIResp<HttpRespBannerImages>> =
        buildRequest(
            url = API.GET_NEW_HOME_MENU, fieldMap = mutableMapOf<String,String>().also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }

    fun requestHomeVideo(): APIRst<APIResp<RecommendSuccessVideoList>> =
        buildRequest(
            url = API.GET_NEW_HOME_VIDEO, fieldMap = mutableMapOf<String,String>().also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("HOME_video","it = "+it)
            API.call(it)
        }

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


    fun requestRemoteImages(position: String): APIRst<APIResp<HttpRespBannerImages>> =
        buildRequest(
            url = API.GET_IMAGES, fieldMap = mutableMapOf(
                "position" to position
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }


    fun requestRemoteBanner(position: String): APIRst<APIResp<HttpRespBanner>> =
        buildRequest(
            url = API.GET_IMAGE, fieldMap = mutableMapOf(
                "position" to position
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            API.call(it)
        }

    fun requestAdvertImages(position: String): APIRst<APIResp<HttpRespBannerImages>> =
        buildRequest(
            url = API.GET_ADVERT, fieldMap = mutableMapOf(
                "position" to position
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("---Advert--","it = "+it)
            API.call(it)
        }


    fun submitAdvertImages(position: String): APIRst<APIResp<HttpRespEmpty>> =
        buildRequest(
            url = API.SUBMIT_ADVERT, fieldMap = mutableMapOf(
                "position" to position
            ).also {
                it["sign"] = API.sign(signatureToken = API.currentSignatureToken, fieldMap = it, method = "GET")
            },
            method = METHOD.GET
        ).let {
            Log.d("---Advert--","it = "+it)
            API.call(it)
        }

}