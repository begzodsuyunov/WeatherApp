package com.example.weatherapp.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.weather.WeatherResponseData
import com.example.weatherapp.usecase.GeoUseCase
import com.example.weatherapp.usecase.GraphUseCase
import com.example.weatherapp.usecase.WeatherUseCase
import com.example.weatherapp.viewmodel.MainViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val useCase: GeoUseCase,
    private val weatherUseCase: WeatherUseCase
) : MainViewModel, ViewModel() {

    override val data = MutableLiveData<WeatherResponseData>()

    override val chartScreenLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun getData(country: String) {
        viewModelScope.launch {
            useCase.getData(country)
            weatherUseCase.getWeather().collectLatest {
                data.postValue(it)
            }
        }
    }

    override fun triggerChartScreen() {
        chartScreenLiveData.value = Unit
    }
}