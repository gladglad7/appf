package com.example.financeapp.ui.aicoach.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.databinding.ItemEducationContentBinding

class EducationContentAdapter : RecyclerView.Adapter<EducationContentAdapter.ViewHolder>() {
    private var contents = listOf<String>()

    class ViewHolder(private val binding: ItemEducationContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(content: String) {
            binding.textContent.text = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEducationContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contents[position])
    }

    override fun getItemCount() = contents.size

    fun submitList(newList: List<String>) {
        contents = newList
        notifyDataSetChanged()
    }
}