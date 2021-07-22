package com.example.retrofitdemo.roomdatabase.favouritedatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitdemo.roomdatabase.doa.Dao

@Database(entities = [FavouriteDatabase::class], version = 2, exportSchema = false)
 abstract class FavouriteDatabase: RoomDatabase() {
     abstract fun favouriteDao(): Dao
     companion object{
         @Volatile
         private var INSTANCE: FavouriteDatabase? = null
     }
    fun getDataBase(context: Context): FavouriteDatabase{
        val tempINSTANCE = INSTANCE
        if (tempINSTANCE!=null){
            return tempINSTANCE
        }
        synchronized(this)
        {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                FavouriteDatabase::class.java,
                "favourite_database"
            ).build()
            INSTANCE=instance
            return instance
        }
    }
}