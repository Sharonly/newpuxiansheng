package com.puxiansheng.logic.data.system

import com.puxiansheng.logic.api.API
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface SystemApiService {
    @FormUrlEncoded
    @POST("API.REQUIRE_TOKEN")
    suspend fun requireSignatureToken(@FieldMap fieldMap: Map<String, String>): Call<ResponseBody>
}