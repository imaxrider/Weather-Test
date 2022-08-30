package com.imax.testapplication.model

import com.google.gson.annotations.SerializedName

data class CityModel(
    @SerializedName("coord")
    var coord: CoordModel?,
    @SerializedName("country")
    var country: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("population")
    var population: Int?,
    @SerializedName("sunrise")
    var sunrise: Int?,
    @SerializedName("sunset")
    var sunset: Int?,
    @SerializedName("timezone")
    var timezone: Int?
)
