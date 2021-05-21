package com.puxiansheng.www.http

import androidx.lifecycle.LiveData
import com.puxiansheng.util.http.ApiBaseResponse
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

class LiveDataCallAdapter<T>(private val responseType: Type) : CallAdapter<ApiBaseResponse<T>, LiveData<ApiBaseResponse<T>>> {
    override fun adapt(call: Call<ApiBaseResponse<T>>): LiveData<ApiBaseResponse<T>> {
        return object : LiveData<ApiBaseResponse<T>>() {
            private val started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {//确保执行一次
                    call.enqueue(object : Callback<ApiBaseResponse<T>> {
                        override fun onFailure(call: Call<ApiBaseResponse<T>>, t: Throwable) {
                            val apiBaseResponse=ApiBaseResponse<T>(-1,"网络异常:request fail:${t.message}",null)
                            postValue(apiBaseResponse)
                        }

                        override fun onResponse(call: Call<ApiBaseResponse<T>>, response: Response<ApiBaseResponse<T>>) {
                            if (response.isSuccessful&&response.code()==200){
                                postValue(response.body())
                            }else{
                                val apiBaseResponse=ApiBaseResponse<T>(response.code(),"网络异常: ${response.code()}",null)
                                postValue(apiBaseResponse)
                            }
                        }
                    })
                }
            }
        }
    }

    override fun responseType() = responseType
}


class LiveDataCallAdapterFactory : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val responseType: Type

        if (CallAdapter.Factory.getRawType(returnType) != LiveData::class.java) {
            throw IllegalStateException("return type must be parameterized")
        }
        val observableType = CallAdapter.Factory.getParameterUpperBound(0, returnType as ParameterizedType)
        val rawObservableType = CallAdapter.Factory.getRawType(observableType)
        responseType = if (rawObservableType == Response::class.java) {
            if (observableType !is ParameterizedType) {
                throw IllegalArgumentException("Response must be parameterized")
            }
            CallAdapter.Factory.getParameterUpperBound(0, observableType)
        } else {
            observableType
        }
        return LiveDataCallAdapter<Type>(responseType)
    }
}