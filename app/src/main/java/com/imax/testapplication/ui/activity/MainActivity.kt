package com.imax.testapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.imax.testapplication.R
import com.imax.testapplication.business.AppImpl
import com.imax.testapplication.compoment.Alert
import com.imax.testapplication.compoment.Loading
import com.imax.testapplication.config.IntentConfig
import com.imax.testapplication.config.SharedConfig
import com.imax.testapplication.constant.UnitsType
import com.imax.testapplication.databinding.ActivityMainBinding
import com.imax.testapplication.model.response.ForecastResponse
import com.imax.testapplication.model.response.WeatherResponse
import com.imax.testapplication.service.RequestApiService
import com.imax.testapplication.service.dto.IServiceInfo
import com.imax.testapplication.util.GsonBuilderUtils
import com.imax.testapplication.util.SharedPrefUtils

class MainActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.simpleName

    private lateinit var sharedPrefUtils: SharedPrefUtils
    private var selectTem = UnitsType.metric
    private var requestApiService = RequestApiService()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        init()
        itemOnClick()
    }

    private fun init() {

        sharedPrefUtils = SharedPrefUtils(this).getInstance()!!

    }

    private fun itemOnClick(){
        binding.btnSearch.setOnClickListener {
            Loading.progressDialogShow(this)
            requestApiService.getWeather(binding.edtSearch.text.toString()
                ,selectTem.name,::getWeatherResult)

        }

        binding.radioGroupTem.setOnCheckedChangeListener { _, selectRadio ->
            when (selectRadio) {
                R.id.radioCelsius -> {
                    selectTem = UnitsType.metric
                }
                R.id.radioFahrenheit -> {
                    selectTem = UnitsType.imperial
                }
            }
        }
    }

    private fun getWeatherResult(response: WeatherResponse){
        try {
            if (AppImpl.apiResponseCode(response.cod)){
                sharedPrefUtils.saveString(SharedConfig.WEATHER, GsonBuilderUtils.builder.toJson(response))
                requestApiService.getForeCast(response,selectTem.name,::getForeCastResult)
            }else{
                errorCallback(response.message!!)
            }
        }catch (ex : Exception){
            errorCallback(ex.message!!)
        }
    }


    private fun getForeCastResult(response: ForecastResponse){
        try {
            if (AppImpl.apiResponseCode(response.cod)){
                sharedPrefUtils.saveString(SharedConfig.FORECAST, GsonBuilderUtils.builder.toJson(response))
                getServiceSuccess()
            }else{
                errorCallback(response.message!!)
            }
        }catch (ex:Exception){
            errorCallback(ex.message!!)
        }
    }


    private fun getServiceSuccess(){
        val intent = Intent(applicationContext, TabMenuActivity::class.java)
        intent.putExtra(IntentConfig.TEMPERATURE, selectTem.name)
        startActivity(intent)
        Loading.progressDialogDismiss()
    }

    private fun errorCallback(message: String) {
        Loading.progressDialogDismiss()
        val alert = Alert.isErrorDialog(this, message)
        alert.show()
    }
}