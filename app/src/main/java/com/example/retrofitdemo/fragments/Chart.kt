package com.example.retrofitdemo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.retrofitdemo.MainViewModel
import com.example.retrofitdemo.R
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.google.android.material.button.MaterialButtonToggleGroup


class Chart : Fragment() {
    private lateinit var vie: View
    private lateinit var id:String
    private val viewModel: MainViewModel by activityViewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vie = inflater.inflate(R.layout.fragment_chart, container, false)
        val name = vie.findViewById<TextView>(R.id.single_coin_name_id)
        val image = vie.findViewById<ImageView>(R.id.single_coin_image_id)
        val price = vie.findViewById<TextView>(R.id.single_coin_price_id)
        viewModel.getSingleCoinData()
        viewModel.singleCoinData.observe(viewLifecycleOwner, {
            if(it.isSuccessful){
                Glide
                    .with(requireContext())
                    .load(it.body()?.image?.large)
                    .into(image)
                name.text = it.body()?.name.toString()
                id = it.body()?.name.toString()
                price.text = "$" + it.body()?.market_data?.current_price?.usd
            }else{
                Log.d("Single Coin Response","404")
            }
        }

            )
        val groupChartButton = vie.findViewById<MaterialButtonToggleGroup>(R.id.button_group)
        groupChartButton.addOnButtonCheckedListener{to, checkedId, isChecked ->
               if (isChecked){
                   when (checkedId){
                       R.id.day -> showOneDayChart(1)
                       R.id.week -> showOneWeekChart(7)
                       R.id.month -> showOneMonthChart(14)
                       R.id.year -> showOneYearChart(365)
                       R.id.all -> showAllChart(0)
                   }
               }
        }
        val chartDataArray:ArrayList<Double> = ArrayList()
        viewModel.chartData.observe(viewLifecycleOwner,{
            if(it.isSuccessful){
                for(element in it.body()?.prices!!){
                    chartDataArray.add(
                        element[1]
                    )
                }
                Log.d("Chart",chartDataArray.toString())
                showChart(chartDataArray)
            }else{
                Log.d("Error",chartDataArray.toString())
            }
        }
        )

        return vie

    }

    private fun showChart(chartDataArray: ArrayList<Double>) {
        val aaChartView = vie.findViewById<AAChartView>(R.id.chat_id)
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Line)
            .backgroundColor("#FFFFFF00")
            .dataLabelsEnabled(false)
            .series(
                arrayOf(
                    AASeriesElement()
                        .name(id)
                        .data(chartDataArray.toArray())
                )
            )
        aaChartView.aa_drawChartWithChartModel(aaChartModel)

    }


    private fun showAllChart(i: Int) {
        viewModel.getChartData(i)
    }

    private fun showOneYearChart(i: Int) {
        viewModel.getChartData(i)
    }

    private fun showOneMonthChart(i: Int) {
        viewModel.getChartData(i)
    }

    private fun showOneWeekChart(i: Int) {
        viewModel.getChartData(i)
    }

    private fun showOneDayChart(i: Int) {
        viewModel.getChartData(i)
    }


}