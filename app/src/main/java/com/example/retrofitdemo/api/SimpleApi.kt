package com.example.retrofitdemo.api

import com.example.retrofitdemo.model.Post
import com.example.retrofitdemo.model.SingleCoinData
import com.example.retrofitdemo.model.chart.ChartData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SimpleApi {
    @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=250&page=1&sparkline=false&price_change_percentage=%201h%2C%2024h%2C%207d%2C%2014d%2C%2030d%2C%20200d%2C%201y")
    suspend fun getPost():Response<ArrayList<Post>>

    @GET
    suspend fun getSingleCoinData(@Url url:String):Response<SingleCoinData>

    @GET
    suspend fun getChartData(@Url url:String) :Response<ChartData>
}