package com.example.weatherapp.di

import com.example.weatherapp.usecase.GeoUseCase
import com.example.weatherapp.usecase.GraphUseCase
import com.example.weatherapp.usecase.WeatherUseCase
import com.example.weatherapp.usecase.impl.GeoUseCaseImpl
import com.example.weatherapp.usecase.impl.GraphUseCaseImpl
import com.example.weatherapp.usecase.impl.WeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun weatherUseCaseBind(impl: WeatherUseCaseImpl): WeatherUseCase

    @Binds
    fun geoUseCaseBind(impl: GeoUseCaseImpl): GeoUseCase

    @Binds
    fun graphUseCaseBind(impl: GraphUseCaseImpl): GraphUseCase

}