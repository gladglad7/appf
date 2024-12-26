package com.example.financeapp.ui.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financeapp.ui.analysis.fragments.ExpenseAnalysisFragment
import com.example.financeapp.ui.analysis.model.ExpenseData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor() : ViewModel() {
    private val _expenseData = MutableLiveData<List<ExpenseData>>()
    val expenseData: LiveData<List<ExpenseData>> = _expenseData

    init {
        loadExpenseData(ExpenseAnalysisFragment.TimeRange.MONTH) // Varsayılan olarak aylık veri
    }

    fun loadExpenseData(timeRange: ExpenseAnalysisFragment.TimeRange) {
        val mockData = when (timeRange) {
            ExpenseAnalysisFragment.TimeRange.WEEK -> createWeeklyMockData()
            ExpenseAnalysisFragment.TimeRange.MONTH -> createMonthlyMockData()
            ExpenseAnalysisFragment.TimeRange.YEAR -> createYearlyMockData()
        }
        _expenseData.value = mockData
    }

    private fun createWeeklyMockData(): List<ExpenseData> {
        return listOf(
            ExpenseData(100.0, "Yiyecek", System.currentTimeMillis()),
            ExpenseData(50.0, "Ulaşım", System.currentTimeMillis()),
            ExpenseData(200.0, "Alışveriş", System.currentTimeMillis())
        )
    }

    private fun createMonthlyMockData(): List<ExpenseData> {
        return listOf(
            ExpenseData(500.0, "Kira", System.currentTimeMillis()),
            ExpenseData(300.0, "Market", System.currentTimeMillis()),
            ExpenseData(150.0, "Faturalar", System.currentTimeMillis())
        )
    }

    private fun createYearlyMockData(): List<ExpenseData> {
        return listOf(
            ExpenseData(6000.0, "Kira", System.currentTimeMillis()),
            ExpenseData(3600.0, "Market", System.currentTimeMillis()),
            ExpenseData(1800.0, "Faturalar", System.currentTimeMillis())
        )
    }
}