package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.forecast.ForecastResponseData
import com.example.weatherapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat:String,
        @Query("lon") lon:String,
        @Query("appid") appId:String = API_KEY
    ): Response<ForecastResponseData>
}