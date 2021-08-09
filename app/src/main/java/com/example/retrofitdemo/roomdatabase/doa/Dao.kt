package com.example.retrofitdemo.roomdatabase.doa

import androidx.room.*
import androidx.room.Dao
import com.example.retrofitdemo.roomdatabase.roomdata.FavouriteData
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(favouriteData: FavouriteData)

    @Delete
    suspend fun deleteFavourite(favouriteData: FavouriteData)

    @Query("SELECT * FROM favourite_table ")
    fun readAllData() : Flow<List<FavouriteData>>
}