package com.imax.testapplication.model

import com.google.gson.annotations.SerializedName

data class SysModel(
    @SerializedName("country")
    var country: String?,
    @SerializedName("sunrise")
    var sunrise: Int?,
    @SerializedName("sunset")
    var sunset: Int?,
    @SerializedName("pod")
    var pod: String?
)
