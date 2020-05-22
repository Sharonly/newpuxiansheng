package com.puxiansheng.util.http

sealed class APIRst <out R>{
    data class Success <out T> (val data: T) : APIRst<T>()
    data class Error(val exception: Throwable) : APIRst<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val APIRst<*>.succeeded
    get() = this is APIRst.Success && data != null
