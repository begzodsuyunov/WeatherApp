package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import com.example.weatherapp.data.model.forecast.ForecastResponseData

interface GraphViewModel {

    val chartLiveData: LiveData<ForecastResponseData>
    fun getData(country:String)

}