package com.imax.testapplication

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.imax.testapplication.config.AppConfig
import com.imax.testapplication.constant.UnitsType
import com.imax.testapplication.model.response.WeatherResponse
import com.imax.testapplication.service.ServiceBuilder

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Call

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.imax.testapplication", appContext.packageName)
    }


}