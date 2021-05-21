package com.puxiansheng.util.http

data class ApiBaseResponse<T>(
    var code:Int,
    var msg: String,
    var data:T?=null
)