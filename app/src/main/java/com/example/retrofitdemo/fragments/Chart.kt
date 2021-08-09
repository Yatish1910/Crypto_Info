package com.example.retrofitdemo.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.retrofitdemo.R
import com.example.retrofitdemo.mViewModel
import com.example.retrofitdemo.roomdatabase.roomdata.FavouriteData
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.google.android.material.button.MaterialButtonToggleGroup
import kotlinx.coroutines.launch


class Chart : Fragment() {
    private var favouriteImage:String? = null
    private var favouriteName:String? = null
    private var favouriteId:String? = null
    private lateinit var mView: View
    private lateinit var id:String
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_chart, container, false)
        val name = mView.findViewById<TextView>(R.id.single_coin_name_id)
        val image = mView.findViewById<ImageView>(R.id.single_coin_image_id)
        val price = mView.findViewById<TextView>(R.id.single_coin_price_id)
        mViewModel.getSingleCoinData()
        mViewModel.singleCoinData.observe(viewLifecycleOwner, {
            if(it.isSuccessful){
                favouriteImage = it.body()?.image?.large.toString()
                favouriteId = it.body()?.id.toString()
                Glide
                    .with(requireContext())
                    .load(it.body()?.image?.large)
                    .into(image)
                name.text = it.body()?.name.toString()
                favouriteName = it.body()?.name.toString()
                id = it.body()?.name.toString()
                price.text = "$" + it.body()?.market_data?.current_price?.usd
            }else{
                Log.d("Single Coin Response","404")
            }
        })
        val groupChartButton = mView.findViewById<MaterialButtonToggleGroup>(R.id.button_group)
        groupChartButton.addOnButtonCheckedListener{_, checkedId, isChecked ->
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
        mViewModel.chartData.observe(viewLifecycleOwner,{
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

        val favoriteImage = mView.findViewById<ImageView>(R.id.add_to_favorite)
        favoriteImage.setOnClickListener {
            val data = FavouriteData(favouriteId!!,favouriteName!!,favouriteImage!!)
            if(favoriteImage.tag != "ON"){
                lifecycleScope.launch {
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_color_filled)
                    mViewModel.insertFavouriteCoin(data)
                    favoriteImage.tag = "ON"
                }
            }
            else{
                lifecycleScope.launch {
                    Toast.makeText(requireContext(),"Deleted", Toast.LENGTH_LONG).show()
                    favoriteImage.tag = "OFF"
                    mViewModel.deleteFavouriteData(data)
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_empty_color)
                }
            }
        }
        mViewModel.favouriteCoins.observe(viewLifecycleOwner, {
            Log.d("Samrth",it.toString())
            it.forEach {
                if(it.name == favouriteName){
                    Toast.makeText(requireContext(),"present",Toast.LENGTH_LONG).show()
                    favoriteImage.tag = "ON"
                    favoriteImage.setImageResource(R.drawable.ic_baseline_favorite_color_filled)
                }
            }
        })
        return mView

    }


    private fun showChart(chartDataArray: ArrayList<Double>) {
        val aaChartView = mView.findViewById<AAChartView>(R.id.chat_id)
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
        mViewModel.getChartData(i)
    }

    private fun showOneYearChart(i: Int) {
        mViewModel.getChartData(i)
    }

    private fun showOneMonthChart(i: Int) {
        mViewModel.getChartData(i)
    }

    private fun showOneWeekChart(i: Int) {
        mViewModel.getChartData(i)
    }

    private fun showOneDayChart(i: Int) {
        mViewModel.getChartData(i)
    }


}