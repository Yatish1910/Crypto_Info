package com.example.retrofitdemo

import android.util.Log
import androidx.lifecycle.*
import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.SingleCoinData
import com.example.retrofitdemo.model.chart.ChartData
import com.example.retrofitdemo.repository.Repository
import com.example.retrofitdemo.roomdatabase.roomdata.FavouriteData
import com.example.retrofitdemo.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
class MainViewModel(private val repository:Repository):ViewModel() {
    val myResponse : MutableLiveData<Response<ArrayList<Post>>> = MutableLiveData()
    val singleCoinData:MutableLiveData<Response<SingleCoinData>> = MutableLiveData()
    var url:String? = null
    val chartData : MutableLiveData<Response<ChartData>> = MutableLiveData()
    val favouriteCoins : LiveData<List<FavouriteData>> = repository.readAllData.asLiveData()

    fun insertFavouriteCoin(favouriteData: FavouriteData){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavourite(favouriteData)
        }
    }
    fun deleteFavouriteData(favouriteData: FavouriteData){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavourite(favouriteData)
        }
    }
    fun getPost(){
        viewModelScope.launch {
            val response: Response<ArrayList<Post>> = repository.getPost()
            myResponse.value = response
        }
    }
    fun getSingleCoinData(){
        viewModelScope.launch {
            val response: Response<SingleCoinData> = repository.getSingleCoinData(
                Constants.SINGLE_COIN_DATA_PREFIX + url + Constants.SINGLE_COIN_DATA_SUFFIX
            )
            singleCoinData.value = response
        }
    }
    fun getChartData(day:Int){
        if(day == 0){
            val max = "max"
            viewModelScope.launch {

                val response: Response<ChartData> = repository.getChartData(
                    Constants.CHART_DATA_PREFIX + url + Constants.CHART_DATA_SUFFIX + max
                )
                chartData.value = response
            }
        }else{
            viewModelScope.launch {

                val response: Response<ChartData> = repository.getChartData(
                    Constants.CHART_DATA_PREFIX + url + Constants.CHART_DATA_SUFFIX + day
                )
                chartData.value = response
                Log.d("Url Single",Constants.CHART_DATA_PREFIX + url + Constants.CHART_DATA_SUFFIX + day.toString())
            }
        }
    }

}