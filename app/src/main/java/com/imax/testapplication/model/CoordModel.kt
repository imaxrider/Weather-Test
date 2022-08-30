package com.imax.testapplication.model

import com.google.gson.annotations.SerializedName

data class CoordModel(
    @SerializedName("lat")
    var lat: Double?,
    @SerializedName("lon")
    var lon: Double?
)