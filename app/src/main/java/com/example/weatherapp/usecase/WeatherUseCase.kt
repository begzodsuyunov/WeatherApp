package com.example.weatherapp.usecase

import com.example.weatherapp.data.model.weather.WeatherResponseData
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {
    suspend fun getWeather(): Flow<WeatherResponseData>

}