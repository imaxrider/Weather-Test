package com.imax.testapplication.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.imax.testapplication.R
import com.imax.testapplication.databinding.FragmentForeCastBinding
import com.imax.testapplication.model.ForeCastModel
import com.imax.testapplication.ui.adapter.ForeCastAdapter
import com.imax.testapplication.ui.viewmodel.WeatherViewModel
import com.imax.testapplication.util.CollectionUtils
import com.imax.testapplication.util.SharedPrefUtils


class ForeCastFragment : Fragment() {
    private  val TAG = "ForeCastFragment"
    lateinit var contexts: Context
    private lateinit var sharedPrefUtils: SharedPrefUtils
    private val weatherViewModel: WeatherViewModel by activityViewModels()
    var foreCastList: ArrayList<ForeCastModel?>? = ArrayList()
    lateinit var adapter: ForeCastAdapter

    private lateinit var binding: FragmentForeCastBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fore_cast, container, false)
        contexts = requireActivity().applicationContext

        init()
        return binding.root
    }

    private fun init(){
        sharedPrefUtils = SharedPrefUtils(contexts).getInstance()!!
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.isNestedScrollingEnabled = false
        binding.recyclerView.layoutManager = LinearLayoutManager(
            contexts, LinearLayoutManager.VERTICAL, false)

        getData()
    }
    private fun getData(){
        try {
            val foreCast = sharedPrefUtils.getForecast()
            Log.i(TAG,"$foreCast")

            if (!CollectionUtils.isEmpty(foreCast.list)){
                foreCastList = foreCast.list
                adapter = ForeCastAdapter(foreCastList,contexts)
                binding.recyclerView.adapter = adapter
            }

            checkUpdateUnits()
        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun checkUpdateUnits(){
        weatherViewModel.getUpdateUnits.observe(requireActivity()){
            for ((i) in foreCastList!!.withIndex()){
                foreCastList!![i]?.units = it
            }
            adapter.notifyDataSetChanged()
        }
    }
}