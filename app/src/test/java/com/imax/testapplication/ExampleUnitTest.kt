package com.imax.testapplication

import com.imax.testapplication.business.AppImpl


import org.junit.Assert.*

import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun checkUnits() {
        val units = AppImpl.checkUnits("metric")
        assertEquals(0x00B0.toChar() + "C", units)
    }

}