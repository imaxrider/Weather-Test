package com.imax.testapplication.model.response
import com.google.gson.annotations.SerializedName
import com.imax.testapplication.model.*


data class WeatherResponse(
    @SerializedName("message")
    var message: String?,
    @SerializedName("base")
    var base: String?,
    @SerializedName("clouds")
    var clouds: CloudsModel?,
    @SerializedName("cod")
    var cod: Int?,
    @SerializedName("coord")
    var coord: CoordModel?,
    @SerializedName("dt")
    var dt: Int?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("main")
    var main: MainModel?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("sys")
    var sys: SysModel?,
    @SerializedName("timezone")
    var timezone: Int?,
    @SerializedName("visibility")
    var visibility: Int?,
    @SerializedName("weather")
    var weather: List<WeatherModel?>?,
    @SerializedName("wind")
    var wind: WindModel?
)

{
    constructor() : this(null,null,null,null,null
        ,null,null,null,null,null,null
        ,null, null,null)
}
