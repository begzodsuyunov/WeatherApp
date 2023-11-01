package com.example.weatherapp.repository.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.forecast.ForecastResponseData
import com.example.weatherapp.data.model.weather.WeatherResponseData
import com.example.weatherapp.data.remote.ForecastApi
import com.example.weatherapp.data.remote.GeoApi
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val geoApi: GeoApi,
    private val weatherApi: WeatherApi,
    private val foreCastApi: ForecastApi
) : Repository, ViewModel() {

    private lateinit var lat: String
    private lateinit var lon: String

    override suspend fun getData(country: String) {
        val response = geoApi.getData(country)
        Log.d("oooo","in repository getData() working")
        if (response.isSuccessful) {
            Log.d("oooo","in repository getData(), response is successful")
            response.body()?.get(0)?.let {
                lat = it.lat.toString()
                lon = it.lon.toString()
            }
        }

    }

    override suspend fun getWeather(): Flow<WeatherResponseData> {
        val responseFlow = flow<WeatherResponseData> {
            val response = weatherApi.getWeatherData(lat, lon)
            if (response.isSuccessful) {
                Log.d("oooo","in repository getWeather(), response is successful")

                response.body()?.let {
                    emit(it)
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch {  }
        return responseFlow
    }

    override suspend fun getForecast(): Flow<ForecastResponseData> {
        val responseFlow = flow<ForecastResponseData> {
            val response = foreCastApi.getForecast(lat, lon)
            if (response.isSuccessful) {
                Log.d("oooo","in repository getForecast(), response is successful")
                response.body()?.let {
                    emit(it)
                }
            } else {
                Log.d("oooo","in repository getForecast(), response is unsuccessful")
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                Log.d("oooo","in repository getForecast() --- catch ---, ${it.message}\n ${it.cause}\n$it")

            }
        return responseFlow
    }
}