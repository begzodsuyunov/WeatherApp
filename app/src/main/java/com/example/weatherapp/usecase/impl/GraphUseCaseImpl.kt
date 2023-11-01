package com.example.weatherapp.usecase.impl

import com.example.weatherapp.data.model.forecast.ForecastResponseData
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.usecase.GraphUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GraphUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GraphUseCase {
    override suspend fun getForecast(): Flow<ForecastResponseData> {
        return repository.getForecast()
    }
}