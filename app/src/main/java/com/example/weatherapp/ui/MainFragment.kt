package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentGraphBinding
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.viewmodel.MainViewModel
import com.example.weatherapp.viewmodel.impl.MainViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.chartScreenLiveData.observe(this){
            findNavController().navigate(R.id.action_mainFragment_to_graphFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnSearch.setOnClickListener {
            val country = viewBinding.countryInput.text.toString()
            viewModel.getData(country.toLowerCase())
        }

        viewModel.data.observe(viewLifecycleOwner){
            viewBinding.apply {
                name.text = it.name
                mainHumidity.text = "Humidity: ${it.main.humidity}"
                mainTemp.text = "Temp: ${it.main.temp} Kelvin"
                weatherDescription.text = "Sky: ${it.weather[0].description}"
                windSpeed.text = "Wind Speed: ${it.wind.speed}m/s"
            }
        }

        viewBinding.graph.setOnClickListener {
            viewModel.triggerChartScreen()
        }

    }
}