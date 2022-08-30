package com.imax.testapplication.service

import com.imax.testapplication.config.AppConfig
import com.imax.testapplication.util.UnsafeOkHttpUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    fun  apiService(): ApiService? {

        val okHttpClient = UnsafeOkHttpUtils.getUnsafeOkHttpClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConfig.API_ENDPOINT)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

}