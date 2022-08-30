package com.imax.testapplication.service

import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("data/2.5/weather")
    fun getWeather(@Query("q") city: String,
                   @Query("APPID") appId: String,
                   @Query("units") units: String
    ): Call<WeatherResponse>

    @POST("data/2.5/forecast")
    fun getForeCast(@Query("lat") lat: Double,
                    @Query("lon") lon: Double,
                    @Query("APPID") appId: String,
                    @Query("units") units: String
    ): Call<ForecastResponse>

}