package com.example.weatherapp.di

import com.example.weatherapp.repository.Repository
import com.example.weatherapp.repository.impl.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun repositoryBind(impl: RepositoryImpl) : Repository
}