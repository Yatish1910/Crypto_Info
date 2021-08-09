package com.example.retrofitdemo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.retrofitdemo.repository.Repository
import com.example.retrofitdemo.roomdatabase.favouritedatabase.FavouriteDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var mViewModel: MainViewModel
@SuppressLint("StaticFieldLeak")
lateinit var navHostFragment: NavHostFragment
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        bottomNavigationView.setupWithNavController(navHostFragment.navController)
        model()
    }

    private fun model() {
        val localeData = FavouriteDatabase.getDataBase(this).favouriteDao()
        val repository  = Repository(localeData)
        mViewModel = ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
    }
}