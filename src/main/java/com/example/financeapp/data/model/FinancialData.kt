package com.example.financeapp.data.model

data class FinancialData(
    val totalIncome: Double = 0.0,
    val totalExpense: Double = 0.0,
    val balance: Double = 0.0,
    val categories: List<ExpenseCategory> = emptyList(),
    val transactions: List<Transaction> = emptyList()
)