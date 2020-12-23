package com.puxiansheng.util.http

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.puxiansheng.util.BuildConfig
import com.puxiansheng.util.ext.createRequest
import com.puxiansheng.util.ext.get
import com.puxiansheng.util.ext.multiParts
import com.puxiansheng.util.ext.post
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import kotlin.reflect.KClass


/**
 * simple http methods enum class.
 * */
enum class METHOD {
    GET, POST
}

fun callWithTypes(
    req: Request,
    client: OkHttpClient,
    typeMap: Map<Int, KClass<Any>>
): Any? {

    return null
}

fun concertWithTypes(
    resp: Response,
    typeMap: Map<Int, KClass<Any>>
): Any? {

    return null
}

/**
 * fetch remote data by calling the http request and returns the original http response.
 * */
fun callForJson(
    req: Request,
    client: OkHttpClient
) =
    runCatching {
        if (BuildConfig.DEBUG) println(req)
        client.newCall(req).execute()
    }.fold(onSuccess = {
        APIRst.Success(it)
    }, onFailure = {
        if (BuildConfig.DEBUG) println("onFailure it =$it")
        APIRst.Error(it)
    })

/**
 * fetch remote data by calling the http request and returns the results.
 * @param T style_input_bottom_line parcelable type def.
 * @param req the http request.
 * @param client the okhttp client.
 * */
inline fun <reified T> call(
    req: Request,
    client: OkHttpClient
): APIRst<APIResp<T>> =
    runCatching {
        if (BuildConfig.DEBUG) println(req)
        client.newCall(req).execute()
    }.fold(onSuccess = {
        convert(it)
    }, onFailure = {
        if (BuildConfig.DEBUG) println("onFailure $it")
        APIRst.Error(it)
    })

/**
 * convert style_input_bottom_line http response body to style_input_bottom_line typed data class.
 * @param T parcelable type def.
 * */
inline fun <reified T> convert(
    resp: Response
): APIRst<APIResp<T>> = runCatching {
    if (BuildConfig.DEBUG) println(resp)
    resp.body?.let {
        val data = it.string()
        if (BuildConfig.DEBUG) printJson("ResponseBody$data")
//      printJson("---videoX ResponseBody$data")
        try {
            APIResp.fromJson<T>(data)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) println(e)
            null
        }
    }
}.fold(onSuccess = {
    it?.let { APIRst.Success(it) } ?: APIRst.Error(Exception("Null Response"))
}, onFailure = {
    if (BuildConfig.DEBUG) print("onFailure == = ")
    APIRst.Error(it)
})

inline fun <reified T> callAny(
    req: Request,
    client: OkHttpClient
): APIRst<T> =
    runCatching {
        if (BuildConfig.DEBUG) println(req)
        client.newCall(req).execute()
    }.fold(onSuccess = {
        convertAny(it)
    }, onFailure = {
        if (BuildConfig.DEBUG) println(it)
        APIRst.Error(it)
    })

inline fun <reified T> convertAny(
    resp: Response
): APIRst<T> = runCatching {
    if (BuildConfig.DEBUG) println(resp)
    resp.body?.let {
        val data = it.string()
        if (BuildConfig.DEBUG) printJson("ResponseBody$data")
        try {
            Gson().fromJson<T>(data, object : TypeToken<T>() {}.type)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG) println(e)
            null
        }
    }
}.fold(onSuccess = {
    it?.let { APIRst.Success(it) } ?: APIRst.Error(Exception("Null Response"))
}, onFailure = {
    if (BuildConfig.DEBUG) print(it)
    APIRst.Error(it)
})

fun printJson(json: String) {
    val maxLogSize = 1000
    for (i in 0..json.length / maxLogSize) {
        val start = i * maxLogSize
        var end = (i + 1) * maxLogSize
        end = if (end > json.length) json.length else end

        println(json.substring(start, end))
    }
}

fun buildRequest(
    url: String,
    headerMap: Map<String, String> = mapOf(),
    fieldMap: Map<String, String> = mapOf(),
    parts: List<MultipartBody.Part> = listOf(),
    method: METHOD = METHOD.POST
): Request = Request.Builder().createRequest(
    url = url,
    headerMap = headerMap,
    fieldMap = fieldMap,
    parts = parts,
    method = method

)

/**
 * return basic http get request by the given params.
 * @param url the http address url.
 * @param fieldMap http body params map.
 * @param headerMap http header params map.
 * */
fun buildGetReq(
    url: String,
    fieldMap: Map<String, String> = mapOf(),
    headerMap: Map<String, String> = mapOf()
): Request {
    return Request.Builder().get(url = url, fieldMap = fieldMap, headerMap = headerMap)
}

/**
 * return basic http post request by the given params.
 * @param url the http address url.
 * @param fieldMap http body params map.
 * @param headerMap http header params map.
 * */
fun buildPostReq(
    url: String,
    fieldMap: Map<String, String> = mapOf(),
    headerMap: Map<String, String> = mapOf()
): Request {
    return Request.Builder().post(url = url, fieldMap = fieldMap, headerMap = headerMap)
}

/**
 * return basic http mutilpart request by the given params.
 * @param url the http address url.
 * @param fieldMap http body params map.
 * @param headerMap http header params map.
 * */
fun buildMultiPartReq(
    url: String,
    fieldMap: Map<String, String> = mapOf(),
    parts: List<MultipartBody.Part> = listOf(),
    headerMap: Map<String, String> = mapOf()
): Request {
    return Request.Builder()
        .multiParts(url = url, fieldMap = fieldMap, parts = parts, headerMap = headerMap)
}

/**
 * encode style_input_bottom_line http request.
 * */
fun encode(req: Request) {

}

/**
 * decode the http response.
 * */
fun decode(resp: Response) {

}