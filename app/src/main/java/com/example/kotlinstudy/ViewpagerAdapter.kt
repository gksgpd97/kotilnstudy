package com.example.kotlinstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ItemMovieListBinding
import com.example.kotlinstudy.databinding.ItemShortCommentBinding

class ViewpagerAdapter(val movieList : ArrayList<MovieListData>) : RecyclerView.Adapter<ViewpagerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewpagerAdapter.ViewHolder {
        val binding = ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewpagerAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewpagerAdapter.ViewHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(val binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : MovieListData){
            binding.imageMovie.setImageResource(item.movieImage)
            binding.textMovieName.text = item.movieTitle
        }
    }

}