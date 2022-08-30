package com.imax.testapplication.model

import com.google.gson.annotations.SerializedName


data class ForeCastModel(
    @SerializedName("clouds")
    var clouds: CloudsModel?,
    @SerializedName("dt")
    var dt: Long?,
    @SerializedName("dt_txt")
    var dtTxt: String?,
    @SerializedName("main")
    var main: MainModel?,
    @SerializedName("pop")
    var pop: Double?,
    @SerializedName("rain")
    var rain: RainModel?,
    @SerializedName("sys")
    var sys: SysModel?,
    @SerializedName("visibility")
    var visibility: Int?,
    @SerializedName("weather")
    var weather: List<WeatherModel?>?,
    @SerializedName("wind")
    var wind: WindModel?,
    @SerializedName("units")
    var units: String?
)
