package com.example.weatherapp.data.remote

import com.example.weatherapp.data.model.geo.GeoResponseData
import com.example.weatherapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {
    @GET("direct")
    suspend fun getData(
        @Query("q") country:String = "Tashkent",
        @Query("appid") apiKey:String = API_KEY
    ): Response<GeoResponseData>
}