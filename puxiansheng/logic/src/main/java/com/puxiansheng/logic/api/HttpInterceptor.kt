package com.puxiansheng.logic.api

import android.util.Log
import androidx.work.impl.utils.LiveDataUtils
import com.google.gson.Gson
import com.puxiansheng.logic.util.LiveDataBus
import com.puxiansheng.util.http.APIResp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject

class HttpInterceptor(
    private var signatureToken: String,
    private var authToken: String,
    private var cityId: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
//        Log.d("----token"," it.url = "+it.url+"   Authorization = "+authToken+"   signatureToken ="+signatureToken+"  cityId = "+cityId)
        Request.Builder()
            .url(it.url)
            .method(it.method, it.body)
            .addHeader("Authorization", authToken)
            .addHeader("SignToken", signatureToken)
            .addHeader("cityId", cityId)


    }.let {
        val resp = chain.proceed(it.build())
        val originalBody = resp.body?.string()

        originalBody?.let { body ->
//            val fromJson = Gson().fromJson(originalBody, APIResp::class.java)
//
//            val list= intArrayOf(1008,1009,1010,1011,1012,1004)
//            if (list.indexOf(fromJson.code)!=-1){
//                //退出登录,退回登录页，并清空token
//                API.setAuthToken("")
//                API.logoutSignal.postValue(JSONObject(body).optInt("code", fromJson.code))
//                return@let
//            }


            API.logoutSignal.postValue(JSONObject(body).optInt("code", -99))
//            Log.d("----77"," JSONObject(body).optInt = "+JSONObject(body).optInt("code", -99))
//            Log.d("----77"," JSONObject(body)= "+JSONObject(body))
            API.getToken.postValue(JSONObject(body).optInt("code", -99))

        }
        resp.newBuilder().body(originalBody?.toResponseBody(resp.body?.contentType())).build()
    }

    fun setSignatureToken(token: String) {
        signatureToken = token
    }

    fun setAuthToken(token: String) {
        authToken = token
    }
    fun setCityId(id: String) {
        cityId = id
    }

}