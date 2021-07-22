package com.example.retrofitdemo.model

data class Post(
    val current_price: String,
    val id: String,
    val image: String,
    //val market_cap: String,
    val market_cap_rank: String,
    //val max_supply: String?= null,
    val name: String,
)
