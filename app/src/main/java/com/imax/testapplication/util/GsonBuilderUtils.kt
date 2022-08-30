package com.imax.testapplication.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object GsonBuilderUtils {
    var gsonBuilder = GsonBuilder()
    var builder = gsonBuilder.create()!!

    fun <T> getData(json: String?, clazz: Class<T>?): T {
        return Gson().fromJson(json, clazz) }

}