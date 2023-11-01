package com.example.weatherapp.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.forecast.ForecastResponseData
import com.example.weatherapp.usecase.GeoUseCase
import com.example.weatherapp.usecase.GraphUseCase
import com.example.weatherapp.viewmodel.GraphViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GraphViewModelImpl @Inject constructor(
    private val graphUseCase: GraphUseCase,
    private val geoUseCase: GeoUseCase
): GraphViewModel, ViewModel() {


    override val chartLiveData: MutableLiveData<ForecastResponseData> = MutableLiveData()

    override fun getData(country: String) {
        viewModelScope.launch{
            geoUseCase.getData(country)
            graphUseCase.getForecast().collectLatest {
                chartLiveData.value = it
            }
        }
    }
}