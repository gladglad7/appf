package com.example.financeapp.ui.analysis.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.databinding.ItemExpenseAnalysisBinding
import com.example.financeapp.ui.analysis.model.ExpenseData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailedAnalysisAdapter : ListAdapter<ExpenseData, DetailedAnalysisAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExpenseAnalysisBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemExpenseAnalysisBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(expense: ExpenseData) {
            binding.apply {
                expenseCategory.text = expense.category
                expenseAmount.text = String.format(Locale.getDefault(), "%.2f ₺", expense.amount)
                expenseDate.text = formatDate(expense.date)
            }
        }

        private fun formatDate(timestamp: Long): String {
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExpenseData>() {
            override fun areItemsTheSame(oldItem: ExpenseData, newItem: ExpenseData): Boolean {
                return oldItem.date == newItem.date && oldItem.category == newItem.category
            }

            override fun areContentsTheSame(oldItem: ExpenseData, newItem: ExpenseData): Boolean {
                return oldItem == newItem
            }
        }
    }
}