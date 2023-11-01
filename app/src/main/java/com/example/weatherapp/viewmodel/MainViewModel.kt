package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.model.weather.WeatherResponseData

interface MainViewModel {

    val data: LiveData<WeatherResponseData>
    val chartScreenLiveData: LiveData<Unit>

    fun getData(country: String)
    fun triggerChartScreen()
}