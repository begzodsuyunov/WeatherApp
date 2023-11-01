package com.example.weatherapp.usecase

interface GeoUseCase {
    suspend fun getData(country: String)

}