package com.example.retrofitdemo.repository

import com.example.retrofitdemo.api.RetrofitInstance
import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.SingleCoinData
import com.example.retrofitdemo.model.chart.ChartData
import retrofit2.Response

 object  Repository {
    suspend fun getPost():Response<ArrayList<Post>>{
        return RetrofitInstance.api.getPost()
    }
    suspend fun getSingleCoinData( url:String):Response<SingleCoinData>{
        return RetrofitInstance.api.getSingleCoinData(url)
    }
    suspend fun getChartData(url:String):Response<ChartData>{
        return RetrofitInstance.api.getChartData(url)
    }
}