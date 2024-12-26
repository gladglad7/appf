package com.example.financeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financeapp.data.model.ExpenseCategory
import com.example.financeapp.data.model.FinancialData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _financialData = MutableLiveData<FinancialData>()
    val financialData: LiveData<FinancialData> = _financialData

    init {
        loadFinancialData()
    }

    private fun loadFinancialData() {
        // Örnek veri
        val data = FinancialData(
            totalIncome = 5000.0,
            totalExpense = 3000.0,
            balance = 2000.0,
            categories = listOf(
                ExpenseCategory(name = "Market"),
                ExpenseCategory(name = "Ulaşım"),
                ExpenseCategory(name = "Sağlık")
            )
        )
        _financialData.value = data
    }
}