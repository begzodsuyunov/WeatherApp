package com.example.weatherapp.di

import com.example.weatherapp.data.remote.ForecastApi
import com.example.weatherapp.data.remote.GeoApi
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.utils.BASE_URL_GEO
import com.example.weatherapp.utils.BASE_URL_WEATHER
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


    @Provides
    @Singleton
    fun client(loggingInterceptor: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideWeatherRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_WEATHER)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @[Provides Singleton Named("Geo")]
    fun provideGeoRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GEO)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


    @Provides
    fun provideGeoApi(@Named("Geo") retrofit: Retrofit) = retrofit.create(GeoApi::class.java)

    @Provides
    fun provideWeatherApi(retrofit: Retrofit) = retrofit.create(WeatherApi::class.java)

    @Provides
    fun provideForeCastApi(retrofit: Retrofit) = retrofit.create(ForecastApi::class.java)

}