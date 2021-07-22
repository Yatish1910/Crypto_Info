package com.example.retrofitdemo.util

class Constants {
    companion object{
        const val BASE_URL = "https://api.coingecko.com/"
        const val SINGLE_COIN_DATA_PREFIX = "api/v3/coins/"
        const val SINGLE_COIN_DATA_SUFFIX = "?tickers=false&market_data=true&community_data=true&developer_data=false&sparkline=true"
        const val CHART_DATA_PREFIX = "api/v3/coins/"
        const val CHART_DATA_SUFFIX = "/market_chart?vs_currency=usd&days="
    }
}