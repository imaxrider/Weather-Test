package com.imax.testapplication.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel:ViewModel() {
    private var updateUnits = MutableLiveData("")

    val getUpdateUnits: LiveData<String> = updateUnits

    fun updateUnits(result: String){
        updateUnits.value = result
    }
}