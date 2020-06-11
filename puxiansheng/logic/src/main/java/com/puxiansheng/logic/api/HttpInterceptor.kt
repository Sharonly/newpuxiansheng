package com.puxiansheng.logic.api

import android.util.Log
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
//        Log.d("----token"," it.url = "+it.url+"   Authorization = "+authToken+"   signatureToken ="+signatureToken)
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
            API.logoutSignal.postValue(JSONObject(body).optInt("code", -99))
//            Log.d("----77"," JSONObject(body).optInt = "+JSONObject(body).optInt("code", -99))
//            Log.d("----77"," JSONObject(body)= "+JSONObject(body))
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