package com.example.weatherapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.weatherapp.R
import com.example.weatherapp.data.model.forecast.ForecastResponseData
import com.example.weatherapp.databinding.FragmentGraphBinding
import com.example.weatherapp.viewmodel.GraphViewModel
import com.example.weatherapp.viewmodel.impl.GraphViewModelImpl
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class GraphFragment : Fragment(R.layout.fragment_graph) {

    private val viewBinding: FragmentGraphBinding by viewBinding(FragmentGraphBinding::bind)
    private val viewModel: GraphViewModel by viewModels<GraphViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.btnSearch.setOnClickListener {
            val country = viewBinding.countryInput.text.toString()
            viewModel.getData(country.lowercase(Locale.getDefault()))
        }

        viewModel.chartLiveData.observe(viewLifecycleOwner) {

            val lineDataSet = LineDataSet(loadGraphXYvalues(it), "Weather data 1")
            /*      this is to draw multiple lines since we want only one, we ain't need it
                    val dataSets:ArrayList<ILineDataSet> = arrayListOf(lineDataSet)*/
            val lineData = LineData(lineDataSet)
            viewBinding.lineChart.data = lineData
            //to update graph
            viewBinding.lineChart.invalidate()
        }


    }
    private fun loadGraphXYvalues(forecast: ForecastResponseData): ArrayList<Entry> {
        val arraylistXY: ArrayList<Entry> = ArrayList()
        val sdf = SimpleDateFormat("dd")
        val currentTimeInLong = Calendar.getInstance().time.time
        val currentDateInFloat = sdf.format(currentTimeInLong).toFloat()

        for (i in 0 .. 5) {
            val time = forecast.list[i].dt * 1000L
            val date = sdf.format(Date(time)).toFloat()
            val temp = (forecast.list[i].main.temp - 273.15).toInt().toFloat()
            arraylistXY.add(Entry((i+12).toFloat(), temp))
        }

        return arraylistXY
    }
}