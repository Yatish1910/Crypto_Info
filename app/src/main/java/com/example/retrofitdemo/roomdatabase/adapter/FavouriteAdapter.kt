package com.example.retrofitdemo.roomdatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitdemo.R
import com.example.retrofitdemo.roomdatabase.roomdata.FavouriteData

class FavouriteAdapter(private val favouriteCoinData: List<FavouriteData>): RecyclerView.Adapter<FavouriteAdapter.FavouriteListViewHolder>() {
    class FavouriteListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val favoriteCoinImage = itemView.findViewById<ImageView>(R.id.favourite_coin_image)!!
        val favouriteCoinName = itemView.findViewById<TextView>(R.id.favourite_coin_name)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteListViewHolder {
        return FavouriteListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favourite_list, parent, false))
    }

    override fun onBindViewHolder(holder: FavouriteListViewHolder, position: Int) {
        val currentPosition = favouriteCoinData[position]
        Glide.with(holder.itemView.context)
            .load(currentPosition.image)
            .into(holder.favoriteCoinImage)
        holder.favouriteCoinName.text = currentPosition.name
    }

    override fun getItemCount(): Int {
        return favouriteCoinData.size
    }
}