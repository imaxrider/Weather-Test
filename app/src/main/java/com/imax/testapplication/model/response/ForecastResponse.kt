package com.imax.testapplication.model.response
import com.google.gson.annotations.SerializedName
import com.imax.testapplication.model.*


data class ForecastResponse(
    @SerializedName("city")
    var city: CityModel?,
    @SerializedName("cnt")
    var cnt: Int?,
    @SerializedName("cod")
    var cod: Int?,
    @SerializedName("list")
    var list: ArrayList<ForeCastModel?>?,
    @SerializedName("message")
    var message: String?
)
{
    constructor() : this(null,null,null,null,null)
}


