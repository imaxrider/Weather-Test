package com.imax.testapplication.util

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    val DT_FORMAT = "EEE, d-MMM HH:mm"
    val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"
    var sdf = SimpleDateFormat(DT_FORMAT)
    var sdfDt = SimpleDateFormat(DATETIME_FORMAT)
    val LOCAL_DEFAULT = Locale.ENGLISH

    fun changeFormat(dt: String): String? {
        val date = sdfDt.parse(dt) as Date
        sdf.applyPattern(DT_FORMAT)
        return sdf.format(date)
    }
}