package com.example.financeapp.ui.analysis.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.financeapp.ui.analysis.fragments.DetailedAnalysisFragment
import com.example.financeapp.ui.analysis.fragments.GraphsFragment
import com.example.financeapp.ui.analysis.fragments.OverviewFragment
import com.example.financeapp.ui.analysis.model.ExpenseData

class AnalysisPagerAdapter(
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {

    private var currentData: List<ExpenseData> = emptyList()

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OverviewFragment.newInstance().apply {
                if (currentData.isNotEmpty()) updateOverviewData(currentData)
            }
            1 -> DetailedAnalysisFragment.newInstance().apply {
                if (currentData.isNotEmpty()) updateDetailedData(currentData)
            }
            2 -> GraphsFragment.newInstance().apply {
                if (currentData.isNotEmpty()) updateGraphData(currentData)
            }
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }

    fun updateData(newData: List<ExpenseData>) {
        currentData = newData
        notifyItemRangeChanged(0, itemCount)
    }
}