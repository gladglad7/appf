package com.example.financeapp.data.model

import java.util.Date

data class Transaction(
    val id: Long = 0,
    val amount: Double,
    val category: String,
    val description: String,
    val type: String, // "EXPENSE" veya "INCOME"
    val date: Date,
    val note: String
)