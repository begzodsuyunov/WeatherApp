package com.example.weatherapp.usecase.impl

import com.example.weatherapp.data.model.weather.WeatherResponseData
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.usecase.WeatherUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class WeatherUseCaseImpl @Inject constructor(
    private val repository: Repository
) : WeatherUseCase {
    override suspend fun getWeather(): Flow<WeatherResponseData> {
        return repository.getWeather()
    }
}