package com.example.retrofitdemo.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitdemo.R
import com.example.retrofitdemo.model.Post

class Adapter(private val data: ArrayList<Post>,private val onItemClick: OnItemClick): RecyclerView.Adapter<Adapter.ListViewHolder>() {
    class ListViewHolder(itemView: View,onItemClick: OnItemClick) :RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener {
                onItemClick.onItemClick(adapterPosition)
            }
        }

        val image = itemView.findViewById<ImageView>(R.id.coin_image)!!
        val names = itemView.findViewById<TextView>(R.id.coin_name)!!
        val price = itemView.findViewById<TextView>(R.id.coin_price)!!
        val capRank = itemView.findViewById<TextView>(R.id.coin_market_value)!!
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListViewHolder(view,onItemClick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = data[position]
        holder.names.text = currentItem.name
        Glide
            .with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.image)
        holder.price.text = "$" + currentItem.current_price
        holder.capRank.text = currentItem.market_cap_rank
    }

    override fun getItemCount(): Int {
        return data.size
    }
    interface OnItemClick{
        fun onItemClick( position: Int)
    }
}