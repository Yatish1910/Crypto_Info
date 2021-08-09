package com.example.retrofitdemo.roomdatabase.roomdata

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_table")
data class FavouriteData(
    @PrimaryKey
    val coinId: String,
    val name: String,
    val image: String
)