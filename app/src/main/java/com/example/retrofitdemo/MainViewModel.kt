package com.example.retrofitdemo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.SingleCoinData
import com.example.retrofitdemo.model.chart.ChartData
import com.example.retrofitdemo.repository.Repository
import com.example.retrofitdemo.util.Constants
import kotlinx.coroutines.launch
import retrofit2.Response
 class MainViewModel:ViewModel() {
    val myResponse : MutableLiveData<Response<ArrayList<Post>>> = MutableLiveData()
    val singleCoinData:MutableLiveData<Response<SingleCoinData>> = MutableLiveData()
    var url:String? = null
    val chartData : MutableLiveData<Response<ChartData>> = MutableLiveData()
    fun getPost(){
        viewModelScope.launch {
            val response: Response<ArrayList<Post>> = Repository.getPost()
            myResponse.value = response
        }
    }

    fun getSingleCoinData(){
        viewModelScope.launch {
            val response: Response<SingleCoinData> = Repository.getSingleCoinData(
                Constants.SINGLE_COIN_DATA_PREFIX + url + Constants.SINGLE_COIN_DATA_SUFFIX
            )
            singleCoinData.value = response
        }
    }
    fun getChartData(day:Int){
        if(day == 0){
            val max = "max"
            viewModelScope.launch {

                val response: Response<ChartData> = Repository.getChartData(
                    Constants.CHART_DATA_PREFIX + url + Constants.CHART_DATA_SUFFIX + max
                )
                chartData.value = response
            }
        }else{
            viewModelScope.launch {

                val response: Response<ChartData> = Repository.getChartData(
                    Constants.CHART_DATA_PREFIX + url + Constants.CHART_DATA_SUFFIX + day
                )
                chartData.value = response
                Log.d("urlurl",Constants.CHART_DATA_PREFIX + url + Constants.CHART_DATA_SUFFIX + day.toString())
            }
        }
    }
}