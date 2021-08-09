package com.example.retrofitdemo.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.data.Adapter
import com.example.retrofitdemo.mViewModel
import com.example.retrofitdemo.model.Post

class Home : Fragment(),Adapter.OnItemClick {

    var data:ArrayList<Post> = ArrayList()
    //private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        mViewModel.getPost()
        mViewModel.myResponse.observe(viewLifecycleOwner, {
            if(it.isSuccessful){
                 data = it.body()!!
                recyclerView.adapter = Adapter(data,this)
            }else{
                Log.d("Response","Error 404")
            }
        })
        return view
    }

    override fun onItemClick(position: Int) {
        mViewModel.url = data[position].id
        findNavController().navigate(R.id.action_home2_to_chart)
        Toast.makeText(context,"Item Clicked at position $position ",Toast.LENGTH_SHORT).show()
    }


}