package com.example.retrofitdemo.roomdatabase.roomdata

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favourite_table")
data class FavouriteData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: String
): Parcelable