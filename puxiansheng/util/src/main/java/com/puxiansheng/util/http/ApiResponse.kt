package com.puxiansheng.util.http

sealed class ApiResponse <out T>{
    data class Success <out T> (val data: T) : ApiResponse<T>()

    data class Error(val error: APIError) : ApiResponse<APIError>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}

val ApiResponse<*>.succeeded
    get() = this is ApiResponse.Success && data != null
