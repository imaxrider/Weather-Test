package com.imax.testapplication.model.Mapper

import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse

object ErrorMapper {
    fun weatherMap(code : Int,message : String) : WeatherResponse{
        val response = WeatherResponse()
        response.cod = code
        response.message = message
        return response
    }

    fun foreCastMap(code : Int,message : String) : ForecastResponse{
        val response = ForecastResponse()
        response.cod = code
        response.message = message
        return response
    }
}