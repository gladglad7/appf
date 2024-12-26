package com.example.financeapp.data.model

data class ExpenseCategory(
    val id: Long = 0,
    val name: String,
    val icon: Int? = null,
    val color: Int? = null
)