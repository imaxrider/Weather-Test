package com.imax.testapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.imax.testapplication.business.AppImpl
import com.imax.testapplication.config.AppConfig
import com.imax.testapplication.model.Mapper.ErrorMapper
import com.imax.testapplication.model.response.WeatherResponse
import com.imax.testapplication.service.ServiceBuilder
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    private var updateUnits = MutableLiveData("")

    val getUpdateUnits: LiveData<String> = updateUnits

    fun updateUnits(result: String) {
        updateUnits.value = result
    }

    //Call Service
    private var weatherMutableLiveData = MutableLiveData<WeatherResponse?>()

    fun weather(city: String, selectTem: String){
        callWeather(city, selectTem)
    }

    private fun callWeather(city: String, selectTem: String) {
        val call = ServiceBuilder.apiService()?.getWeather(city,AppConfig.APP_ID, selectTem)
        call?.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>) {
                if (AppImpl.httpResponseCode(response.code())){
                    weatherMutableLiveData.value = response.body()
                }else{
                    weatherMutableLiveData.value = ErrorMapper.weatherMap(response.code(),response.message())
                }
            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                weatherMutableLiveData.value = ErrorMapper.weatherMap(0, t.message!!)
            }
        })
    }

    fun getWeather(city: String, selectTem: String): MutableLiveData<WeatherResponse?> {
        callWeather(city, selectTem)
        return this.weatherMutableLiveData
    }

    private fun subscribeData(): Observer<WeatherResponse?> {
        return object : Observer<WeatherResponse?> {
            fun onSubscribe(d: Disposable?) {}
            fun onNext(weather: WeatherResponse?) {
                weatherMutableLiveData.value = weather!!
            }

            fun onError(e: Throwable?) {}
            fun onComplete() {}
            override fun onChanged(t: WeatherResponse?) {

            }
        }
    }

}