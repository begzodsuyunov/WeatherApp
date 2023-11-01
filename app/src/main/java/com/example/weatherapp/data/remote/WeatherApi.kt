package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.weather.WeatherResponseData
import com.example.weatherapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherData(
        @Query("lat") lat: String = "41.3123363",
        @Query("lon") lon: String = "69.2787079",
        @Query("appid") appid: String = API_KEY
    ): Response<WeatherResponseData>

}