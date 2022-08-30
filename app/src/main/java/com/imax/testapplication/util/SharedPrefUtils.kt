package com.imax.testapplication.util

import android.content.Context
import android.content.SharedPreferences
import com.imax.testapplication.config.SharedConfig.FORECAST
import com.imax.testapplication.config.SharedConfig.SHARED_DATA
import com.imax.testapplication.config.SharedConfig.WEATHER
import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse


class SharedPrefUtils(val context: Context) {
    private var instance: SharedPrefUtils? = null

    fun getInstance(): SharedPrefUtils? {
        if (instance == null) instance = SharedPrefUtils(context)
        return instance
    }

    private fun getSharedPref(): SharedPreferences {
        return context.getSharedPreferences(SHARED_DATA, Context.MODE_PRIVATE)
    }

    fun saveString(KEY_NAME: String, data: String) {
        val editor: SharedPreferences.Editor = getSharedPref().edit()
        editor.putString(KEY_NAME,data)
        editor.apply()
    }

    fun saveInt(KEY_NAME: String, value: Int) {
        val editor: SharedPreferences.Editor = getSharedPref().edit()
        editor.putInt(KEY_NAME, value)
        editor.apply()
    }

    fun saveBoolean(KEY_NAME: String, status: Boolean) {
        val editor: SharedPreferences.Editor = getSharedPref().edit()
        editor.putBoolean(KEY_NAME, status)
        editor.apply()
    }

    fun getWeather(): WeatherResponse {
        val data = getSharedPref().getString(WEATHER, null)
        return GsonBuilderUtils.getData(data, WeatherResponse::class.java)
    }

    fun getForecast(): ForecastResponse {
        val data = getSharedPref().getString(FORECAST, null)
        return GsonBuilderUtils.getData(data, ForecastResponse::class.java)
    }


}