package com.imax.testapplication.business

import com.imax.testapplication.constant.ApiResponseCode
import com.imax.testapplication.constant.HttpResponseCode
import com.imax.testapplication.constant.UnitsType

object AppImpl {
    fun httpResponseCode(code: Int?): Boolean {
        return try {
            code == HttpResponseCode.SUCCESS.code

        }catch (ex: Exception){
            ex.printStackTrace()
            false
        }
    }
    fun apiResponseCode(code: Int?): Boolean {
        return try {
            code == ApiResponseCode.SUCCESS.code

        }catch (ex: Exception){
            ex.printStackTrace()
            false
        }
    }

    fun checkUnits(units:String) : String{
        if (units == UnitsType.metric.name){
          return  0x00B0.toChar() + "C"
        }
        return  0x00B0.toChar() + "F"
    }
}