package com.jb.testretrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    val URL = "https://jsonplaceholder.typicode.com/"
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttp())//add OkHttpClient
            .build()
    }

    fun getOkHttp(): OkHttpClient {
        val token = "RestfulApiToken"
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor { chain ->
            val apiRequest = chain.request()
            ////新增token到Header
            apiRequest.newBuilder().addHeader("Authorization","Bearer" + token)
            val apiResponse = chain.proceed(apiRequest)
            //處理Http error code
            when(apiResponse.code()){
                200 -> Log.d("test","success")
                404 -> Log.d("test","fail")
            }
            apiResponse
        }
        return okHttpBuilder.build()
    }
}