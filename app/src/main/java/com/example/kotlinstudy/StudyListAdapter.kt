package com.example.kotlinstudy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinstudy.databinding.ItemStudyListBinding

class StudyListAdapter(val studyListData: ArrayList<StudyListData>)
    : RecyclerView.Adapter<StudyListAdapter.CustomViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudyListAdapter.CustomViewHolder {
        val binding = ItemStudyListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudyListAdapter.CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudyListAdapter.CustomViewHolder, position: Int) {
        val item = studyListData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return studyListData.size
    }

    class CustomViewHolder(val binding: ItemStudyListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item:StudyListData){
            binding.name.text = item.name
            binding.age.text = item.age.toString()
            binding.job.text = item.job
        }
    }
}