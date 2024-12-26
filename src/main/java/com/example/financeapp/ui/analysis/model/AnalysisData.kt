package com.example.financeapp.ui.analysis.model

data class AnalysisData(
    val title: String,
    val value: Double,
    val category: String,
    val date: Long = System.currentTimeMillis()
)