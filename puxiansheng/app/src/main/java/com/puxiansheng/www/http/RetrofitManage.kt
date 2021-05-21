package com.puxiansheng.www.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManage {

    private val TIME_OUT=60*1000L
//     val BASE_URL1 = "http://live_api.51wpu.net.cn/"
   // private val BASE_URL2 = "https://api3.51wpu.com.cn/"


   private const val API_ADDRESS = "https://api3.51wpu.com.cn/"//测试
    private const val STORAGE_ADDRESS = "https://api3.51wpu.com.cn/"

//    private const val API_ADDRESS = "https://api3.puxiansheng.com/" //正式环境
//    private const val STORAGE_ADDRESS = "https://api3.puxiansheng.com/"//正式环境

    fun getApiService():ApiService{
        val manager=Retrofit.Builder()
            .baseUrl(API_ADDRESS)
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return manager.create(ApiService::class.java)
    }


    fun getApiServiceX():ApiService{
        val manager=Retrofit.Builder()
            .baseUrl(API_ADDRESS)
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return manager.create(ApiService::class.java)
    }


    private fun getOkhttpClient():OkHttpClient{
        return OkHttpClient().newBuilder()
            .readTimeout(TIME_OUT,TimeUnit.MILLISECONDS)
            .writeTimeout(TIME_OUT,TimeUnit.MILLISECONDS)
            .connectTimeout(TIME_OUT,TimeUnit.MILLISECONDS)
            .addInterceptor(HttpInterceptor())
            .build()
    }
}