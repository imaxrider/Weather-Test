package com.imax.testapplication.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.imax.testapplication.R
import com.imax.testapplication.business.AppImpl
import com.imax.testapplication.constant.UnitsType
import com.imax.testapplication.databinding.FragmentWeatherBinding
import com.imax.testapplication.ui.viewmodel.WeatherViewModel
import com.imax.testapplication.util.SharedPrefUtils

class WeatherFragment : Fragment() {
    private  val TAG = "WeatherFragment"

    private lateinit var contexts: Context
    private lateinit var sharedPrefUtils: SharedPrefUtils

    private val weatherViewModel: WeatherViewModel by activityViewModels()

    private var binding: FragmentWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        contexts = requireActivity().applicationContext

        main()
        return binding!!.root
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
            binding?.weather = weather
            
//            weatherViewModel.getWeather(weather.name!!, UnitsType.metric.name).observe(requireActivity()){
//                Log.i(TAG,"weatherViewModel $it")
//            }
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }
    private fun checkUpdateUnits(){
        weatherViewModel.getUpdateUnits.observe(requireActivity()){
            binding!!.txvUnits.text = AppImpl.checkUnits(it)
        }
    }
}