package com.puxiansheng.www.http

import android.util.Log
import com.puxiansheng.logic.api.API
import com.puxiansheng.www.BuildConfig
import com.puxiansheng.www.tools.DevicesUtils
import com.puxiansheng.www.tools.SpUtils
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import java.lang.Exception

class HttpInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.request().let {
        Request.Builder()
            .url(it.url)
            .method(it.method, it.body)
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", SpUtils.getInstance().getAuthorization())
            .addHeader("SignToken", SpUtils.getInstance().getSigntoken())
            .addHeader("cityId", SpUtils.getInstance().getCityId())
    }.let {

        val resp = chain.proceed(it.build())
        val originalBody = resp.body?.string()

        if (BuildConfig.DEBUG) {
            val startTime = System.currentTimeMillis()
            println("----------------Start----------------")
            val params = StringBuilder()
            if ("POST" ==it.build().method) {
                if (it.build().body is FormBody) {
                    val body =it.build().body as FormBody?
                    body?.let {
                        for (i in 0 until it.size) {
                            params.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",")
                        }
                        params.delete(params.length - 1, params.length)
                    }
                }
            }
            println(
                "Params:{$params}\n" +
                        "URL:${it.build().url}\n" +
                        "Method:${it.build().method}\n" +
                        "Content-Length:${resp.body?.contentLength()}\n" +
                        "Content-Type:${resp.body?.contentType()}\n" +
                        "Header:${it.build().header("token")}\n" +
                        "ResponseBody:${originalBody}\n" +
                        "------------End----耗时:${System.currentTimeMillis() - startTime}毫秒------------"
            )
        }

        originalBody?.let { body ->
            try {
                val code = JSONObject(body).optInt("code", -99)
              //  println("----code = "+code)
                if (code == 1001 || code == 1008 || code == 1009 || code == 1010 || code == 1011|| code == 500) {
                    getNewSignToken()
                    val newRequests = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", SpUtils.getInstance().getAuthorization())
                        .addHeader("SignToken", SpUtils.getInstance().getSigntoken())
                        .addHeader("cityId", SpUtils.getInstance().getCityId())
                        .build()
                    return chain.proceed(newRequests)
                }else{
                    return@let
                }
//                if (code==1012){
//                    SpUtils.getInstance().saveUserId("")
//                    val i=Intent(BaseApplication.getAppContext(),LoginActivity::class.java)
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
//                    BaseApplication.getAppContext().startActivity(i)
//                    return@let
//                }
            }catch (e:Exception){
                Log.e("json解析异常",e.toString())
            }
        }
        resp.newBuilder().body(originalBody?.toResponseBody(resp.body?.contentType())).build()
    }


    /**
     * 同步获取新token
     */
    private fun getNewSignToken() {
        val parames = HashMap<String, String>()
        parames["appid"] = API.API_APP_ID
        parames["secret"] = API.API_SECRET
        parames["channel"] = DevicesUtils.DEVICE_CHANNEL
        parames["device_no"] = DevicesUtils.getIMEI()
        parames["version"] = DevicesUtils.getAppVersionCode().toString()
        parames["sign"] = DevicesUtils.signNew(
            signatureToken = SpUtils.getInstance().getSigntoken(),
            fieldMap = parames,
            method = "POST"
        )
        val signCall = RetrofitManage.getApiService().getSignToken(parames)
        val signBody = signCall.execute().body()
        Log.d("获取新签名token--->", signBody.toString())
        if (signBody?.data?.token!=null){
            signBody?.data!!.token?.let { SpUtils.getInstance().saveSigntoken(it) }
        }

    }





}