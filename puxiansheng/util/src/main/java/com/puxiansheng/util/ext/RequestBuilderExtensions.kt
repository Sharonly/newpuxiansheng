package com.puxiansheng.util.ext

import com.puxiansheng.util.http.METHOD
import okhttp3.FormBody
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.Request.Builder

fun Builder.createRequest(
    url: String,
    headerMap: Map<String, String> = mapOf(),
    fieldMap: Map<String, String> = mapOf(),
    parts: List<MultipartBody.Part> = listOf(),
    method: METHOD
): Request = when (method) {
    METHOD.GET -> get(url = url, fieldMap = fieldMap, headerMap = headerMap)
    METHOD.POST -> {
        if (parts.isNotEmpty()) {
            multiParts(url = url, fieldMap = fieldMap, parts = parts, headerMap = headerMap)
        } else {
            post(url = url, fieldMap = fieldMap, headerMap = headerMap)
        }
    }
}

fun Builder.get(
    url: String,
    fieldMap: Map<String, String> = mapOf(),
    headerMap: Map<String, String> = mapOf()
): Request = this.apply {
    if (headerMap.isNotEmpty()) headerMap.forEach { addHeader(it.key, it.value) }
    val sb = StringBuilder().append(url)
    if (fieldMap.isNotEmpty()) {
        sb.append("?")
        fieldMap.forEach { sb.append("${it.key}=${it.value}&") }
    }
    url(sb.substring(0, sb.lastIndex))
}.build()

fun Builder.post(
    url: String,
    fieldMap: Map<String, String> = mapOf(),
    headerMap: Map<String, String> = mapOf()
): Request = this.let {
    FormBody.Builder().apply {
        if (headerMap.isNotEmpty()) headerMap.forEach { addHeader(it.key, it.value) }
        if (fieldMap.isNotEmpty()) fieldMap.forEach { add(it.key, it.value) }
    }.let { post(it.build()) }
}.let { url(url) }.build()


fun Builder.multiParts(
    url: String,
    fieldMap: Map<String, String> = mapOf(),
    parts: List<MultipartBody.Part> = listOf(),
    headerMap: Map<String, String> = mapOf()
): Request = this.let {
    MultipartBody.Builder().apply {
        setType(MultipartBody.FORM)
        if (headerMap.isNotEmpty()) headerMap.forEach { addHeader(it.key, it.value) }
        if (parts.isNotEmpty()) parts.forEach { addPart(it) }
        if (fieldMap.isNotEmpty()) fieldMap.forEach { addFormDataPart(it.key, it.value) }
    }.let { post(it.build()) }
}.let { url(url) }.build()