package com.example.kotlinstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ItemShortCommentBinding

class RecyclerViewAdapter(val commentList : ArrayList<CommentData>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.ViewHolder {
        val binding = ItemShortCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        val item = commentList[position]
        val listener = View.OnClickListener {
            Toast.makeText(it.context,"추천 클릭",Toast.LENGTH_SHORT).show()
        }
        holder.bind(listener, item)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    class ViewHolder(val binding: ItemShortCommentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(listener : View.OnClickListener, item : CommentData){
            binding.userImage.setImageResource(item.userImage)
            binding.idName.text = item.userId
            binding.minute.text = item.date
            binding.ratingStar.rating = item.rating.toFloat()
            binding.shortComment.text = item.comment
            binding.recomNum.text = item.thumbUpCount.toString()

            var recommendation = binding.recommendation
            recommendation.setOnClickListener(listener)
        }
    }

}