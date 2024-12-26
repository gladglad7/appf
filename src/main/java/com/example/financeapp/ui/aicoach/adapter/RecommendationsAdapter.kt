package com.example.financeapp.ui.aicoach.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.databinding.ItemRecommendationBinding

class RecommendationsAdapter : RecyclerView.Adapter<RecommendationsAdapter.ViewHolder>() {
    private var recommendations = listOf<String>()

    class ViewHolder(private val binding: ItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendation: String) {
            binding.textRecommendation.text = recommendation
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recommendations[position])
    }

    override fun getItemCount() = recommendations.size

    fun submitList(newList: List<String>) {
        recommendations = newList
        notifyDataSetChanged()
    }
}