package com.example.kotlinstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ItemCommentBinding

class CommentRecyclerViewAdapter(val commentList: ArrayList<CommentData>)
    : RecyclerView.Adapter<CommentRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentRecyclerViewAdapter.ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentRecyclerViewAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CommentRecyclerViewAdapter.ViewHolder,
        position: Int
    ) {
        val item = commentList[position]
        val listener = View.OnClickListener {it->
            Toast.makeText(it.context, "추천 클릭", Toast.LENGTH_SHORT).show()
        }
        holder.bind(listener, item)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class ViewHolder(val binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, item: CommentData) {
            binding.userImage.setImageResource(item.userImage)
            binding.userId.text = item.userId
            binding.date.text = item.date
            binding.ratingBar.rating = item.rating.toFloat()
            binding.comment.text = item.comment
            binding.thumbUpCount.text = item.thumbUpCount.toString()
            binding.thumbUp.setOnClickListener(listener)
        }
    }
}