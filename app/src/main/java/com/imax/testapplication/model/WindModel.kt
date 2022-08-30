package com.imax.testapplication.model

import com.google.gson.annotations.SerializedName

data class WindModel(
    @SerializedName("deg")
    var deg: Int?,
    @SerializedName("gust")
    var gust: Double?,
    @SerializedName("speed")
    var speed: Double?
)
