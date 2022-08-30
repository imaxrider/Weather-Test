package com.imax.testapplication.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.imax.testapplication.R
import com.imax.testapplication.config.IntentConfig
import com.imax.testapplication.constant.PageType
import com.imax.testapplication.ui.fragment.ForeCastFragment
import com.imax.testapplication.ui.fragment.WeatherFragment
import com.imax.testapplication.ui.viewmodel.WeatherViewModel
import com.imax.testapplication.util.CollectionUtils
import com.imax.testapplication.util.SharedPrefUtils

class TabMenuActivity : AppCompatActivity() {
    private val TAG: String = this::class.java.simpleName
    private var sectionsPagerAdapter: SectionsPagerAdapter? = null
    private val fragments = ArrayList<Fragment>()
    private val pageTitle = ArrayList<String>()

    private val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) }
    private val viewPager: ViewPager by lazy { findViewById(R.id.viewPager) }
    private val tabLayout: TabLayout by lazy { findViewById(R.id.tabLayout) }
    private val txvCity: TextView by lazy { findViewById(R.id.txvCity) }

    lateinit var sharedPrefUtils: SharedPrefUtils
    lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_menu)

        main()
        itemOnClick()
    }
        private fun main(){
            weatherViewModel = ViewModelProvider(this)[WeatherViewModel::class.java]
            sharedPrefUtils = SharedPrefUtils(this).getInstance()!!
            setSupportActionBar(toolbar)
            mapFragmentPermission()
            supportActionBar!!.setDisplayShowTitleEnabled(false)

            sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

            viewPager.adapter = sectionsPagerAdapter
            viewPager.offscreenPageLimit = pageTitle.size

            tabLayout.setupWithViewPager(viewPager)

            selectWeather()

            viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            tabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewPager))

            getData()
        }

    private fun itemOnClick(){
        toolbar.setNavigationOnClickListener {
            finish()
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int, positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
            }

            override fun onPageSelected(position: Int) {
                selectMenuAction(position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    private fun getData(){
        try {

            val weather = sharedPrefUtils.getWeather()
            if (!CollectionUtils.isEmpty(weather)){
                txvCity.text = weather.name
            }

            val units =  intent.getStringExtra(IntentConfig.TEMPERATURE)
            weatherViewModel.updateUnits(units!!)

        }catch (ex : Exception){
            ex.printStackTrace()
        }
    }

    fun selectMenuAction(position : Int){
        try {
            when (pageTitle[position]) {
                PageType.WEATHER.name -> {
                    selectWeather()
                }
                PageType.FORECAST.name -> {
                    selectWeather()

                }
            }

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
    private fun selectWeather() {
        for (i in pageTitle.indices) {
            if (pageTitle[i] == PageType.WEATHER.name) {
                tabLayout.getTabAt(i)!!.setIcon(R.drawable.ic_cloud)
            }
            if (pageTitle[i] == PageType.FORECAST.name) {
                tabLayout.getTabAt(i)!!.setIcon(R.drawable.ic_cloud_circle)
            }

        }
    }


    private fun mapFragmentPermission() {
        fragments.add(WeatherFragment())
        pageTitle.add(PageType.WEATHER.name)

        fragments.add(ForeCastFragment())
        pageTitle.add(PageType.FORECAST.name)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!) {
        override fun getItem(position: Int): Fragment {

            return fragments[position]
        }

        override fun getCount(): Int {

            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {

            return pageTitle[position]
        }
    }
}