package com.example.weatherapp.usecase

import com.example.weatherapp.data.model.forecast.ForecastResponseData
import kotlinx.coroutines.flow.Flow

interface GraphUseCase {

    suspend fun getForecast(): Flow<ForecastResponseData>

}