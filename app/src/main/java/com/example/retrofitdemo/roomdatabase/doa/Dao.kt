package com.example.retrofitdemo.roomdatabase.doa

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.retrofitdemo.roomdatabase.roomdata.FavouriteData

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(favouriteData: FavouriteData)

    @Delete
    suspend fun deleteFavourite(favouriteData: FavouriteData)

    @Query("SELECT * FROM favourite_table ORDER BY id ASC")
    fun readAllData() :LiveData<ArrayList<FavouriteData>>
}