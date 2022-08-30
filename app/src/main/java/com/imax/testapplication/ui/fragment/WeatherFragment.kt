package com.imax.testapplication.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.imax.testapplication.R
import com.imax.testapplication.business.AppImpl
import com.imax.testapplication.ui.viewmodel.WeatherViewModel
import com.imax.testapplication.util.CollectionUtils
import com.imax.testapplication.util.SharedPrefUtils


class WeatherFragment : Fragment() {
    private  val TAG = "WeatherFragment"
    private lateinit var viewFragment: View
    private lateinit var contexts: Context
    private lateinit var sharedPrefUtils: SharedPrefUtils
    private lateinit var txvTem: TextView
    private lateinit var txvUnits: TextView
    private lateinit var txvHumidity: TextView
    private lateinit var txvWeather: TextView

    private val weatherViewModel: WeatherViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewFragment = inflater.inflate(R.layout.fragment_weather, container, false)
        contexts = requireActivity().applicationContext
        txvTem = viewFragment.findViewById(R.id.txvTem)
        txvUnits = viewFragment.findViewById(R.id.txvUnits)
        txvHumidity = viewFragment.findViewById(R.id.txvHumidity)
        txvWeather = viewFragment.findViewById(R.id.txvWeather)
        main()
        return viewFragment
    }
    private fun main(){
        sharedPrefUtils = SharedPrefUtils(contexts).getInstance()!!

        getData()
        checkUpdateUnits()
    }
    @SuppressLint("SetTextI18n")
    private fun getData(){
        try {
            val weather = sharedPrefUtils.getWeather()
            Log.i(TAG,"$weather")
            if (!CollectionUtils.isEmpty(weather)){
                txvTem.text = "${weather.main!!.temp}"
            }

            if (!CollectionUtils.isEmpty(weather.weather)){
                txvWeather.text = weather.weather!![0]?.main
            }
            txvHumidity.text = "Humidity  ${weather.main?.humidity}%"
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }
    private fun checkUpdateUnits(){
        weatherViewModel.getUpdateUnits.observe(requireActivity()){
            txvUnits.text = AppImpl.checkUnits(it)
        }
    }
}