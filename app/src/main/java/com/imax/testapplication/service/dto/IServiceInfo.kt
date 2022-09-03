package com.imax.testapplication.service.dto

import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse

interface IServiceInfo {

    fun weather(weatherResponse: WeatherResponse?)
    fun error(t: Throwable?)

//    fun setWeather(weatherResponse: WeatherResponse?)
//
//    fun setForecast(forecastResponse: ForecastResponse?)

}
