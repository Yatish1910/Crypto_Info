package com.example.retrofitdemo.repository

import com.example.retrofitdemo.api.RetrofitInstance
import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.SingleCoinData
import com.example.retrofitdemo.model.chart.ChartData
import com.example.retrofitdemo.roomdatabase.doa.Dao
import com.example.retrofitdemo.roomdatabase.roomdata.FavouriteData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository(private val dao: Dao) {
    suspend fun getPost():Response<ArrayList<Post>>{
        return RetrofitInstance.api.getPost()
    }
    suspend fun getSingleCoinData( url:String):Response<SingleCoinData>{
        return RetrofitInstance.api.getSingleCoinData(url)
    }
    suspend fun getChartData(url:String):Response<ChartData>{
        return RetrofitInstance.api.getChartData(url)
    }
    val readAllData: Flow<List<FavouriteData>> = dao.readAllData()
    suspend fun addFavourite(favouriteData: FavouriteData){
        dao.addFavourite(favouriteData)
    }
    suspend fun deleteFavourite(favouriteData: FavouriteData){
        dao.deleteFavourite(favouriteData)
    }
}