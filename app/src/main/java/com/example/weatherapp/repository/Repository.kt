package com.example.weatherapp.repository

import com.example.weatherapp.data.model.forecast.ForecastResponseData
import com.example.weatherapp.data.model.weather.WeatherResponseData
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getData(country: String)
    suspend fun getWeather(): Flow<WeatherResponseData>
    suspend fun getForecast(): Flow<ForecastResponseData>

}