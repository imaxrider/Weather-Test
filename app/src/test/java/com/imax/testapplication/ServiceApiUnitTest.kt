package com.imax.testapplication

import com.imax.testapplication.config.AppConfig
import com.imax.testapplication.constant.UnitsType
import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse
import com.imax.testapplication.service.ServiceBuilder
import org.junit.Assert
import org.junit.Test
import retrofit2.Call

class ServiceApiUnitTest {

    @Test
    fun getWeather(){
        val request = ServiceBuilder.apiService()
        val call: Call<WeatherResponse> = request!!.getWeather(
            "Bangkok", AppConfig.APP_ID, UnitsType.metric.name)

        val response = call.execute().body()!!
        println(response)
        Assert.assertEquals(200, response.cod)

    }

    @Test
    fun getForeCast(){
        val request = ServiceBuilder.apiService()
        val call: Call<ForecastResponse> = request!!.getForeCast(
            13.736717,100.523186, AppConfig.APP_ID, UnitsType.metric.name)

        val response = call.execute().body()!!
        println(response)
        Assert.assertEquals(200, response.cod)

    }
}