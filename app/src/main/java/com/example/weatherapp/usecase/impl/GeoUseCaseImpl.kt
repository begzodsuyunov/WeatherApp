package com.example.weatherapp.usecase.impl

import com.example.weatherapp.repository.Repository
import com.example.weatherapp.usecase.GeoUseCase
import javax.inject.Inject


class GeoUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GeoUseCase{
    override suspend fun getData(country: String) {
        repository.getData(country)
    }
}