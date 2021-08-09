package com.example.retrofitdemo.fragments.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.mViewModel
import com.example.retrofitdemo.roomdatabase.adapter.FavouriteAdapter


class Favourite : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favourite, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.favourite_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        mViewModel.favouriteCoins.observe(viewLifecycleOwner, {
            recyclerView.adapter = FavouriteAdapter(it)
        })
//        binding.favtoggleButton.setOnClickListener {
//            val element = FavouriteEntity(
//                coinDetailResponse!!.id,
//                coinDetailResponse!!.name,
//                coinDetailResponse!!.marketData.current_price.usd.toString(),
//                coinDetailResponse!!.imageLink.url,
//                coinDetailResponse!!.marketData.priceChangePercentage24h.toString()
//            )
//
//            if (binding.favtoggleButton.tag != "ON") {
//                binding.favtoggleButton.setImageResource(R.drawable.ic_baseline_favorite_24)
//                viewModelShared.addToFavourites(element)
//                binding.favtoggleButton.tag = "ON"
//            } else {
//                binding.favtoggleButton.tag = "OFF"
//                viewModelShared.removeCoinFromFavourite(element)
//                binding.favtoggleButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//            }
//        }
//
//        viewModelShared.allFavouriteCoin.value?.forEach {
//            if (it.coinId == coinId) {
//                binding.favtoggleButton.setImageResource(R.drawable.ic_baseline_favorite_24)
//                binding.favtoggleButton.tag = "ON"
//            }
//        }'
        return view
    }


}