package com.imax.testapplication.service

import android.util.Log
import com.imax.testapplication.business.AppImpl
import com.imax.testapplication.config.AppConfig

import com.imax.testapplication.model.Mapper.ErrorMapper
import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestApiService {

    private val TAG: String = this::class.java.simpleName

    fun getWeather(city: String,selectTem:String,result: (WeatherResponse) -> Unit) {
        val call = ServiceBuilder.apiService()?.getWeather(city, AppConfig.APP_ID,selectTem)
        call?.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (AppImpl.httpResponseCode(response.code())){
                    result(response.body()!!)
                }else{
                    result(ErrorMapper.weatherMap(response.code(),response.message()))
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG,"getWeather ${t.message}")
                result(ErrorMapper.weatherMap(0, t.message!!))
            }
        })
    }


    fun getForeCast(weatherResponse: WeatherResponse,selectTem:String,result: (ForecastResponse) -> Unit) {
        val call = ServiceBuilder.apiService()?.getForeCast(weatherResponse.coord?.lat!!
            ,weatherResponse.coord?.lon!!, AppConfig.APP_ID,selectTem)
        call?.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                if (AppImpl.httpResponseCode(response.code())){
                    result(response.body()!!)
                }else{
                    result(ErrorMapper.foreCastMap(response.code(),response.message()))
                }
            }
            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.e(TAG,"getForeCast ${t.message}")
                result(ErrorMapper.foreCastMap(0, t.message!!))
            }
        })
    }
}