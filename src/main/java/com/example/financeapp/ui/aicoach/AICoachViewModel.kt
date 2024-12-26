package com.example.financeapp.ui.aicoach

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AICoachViewModel @Inject constructor() : ViewModel() {
    private val _recommendations = MutableLiveData<List<String>>()
    val recommendations: LiveData<List<String>> = _recommendations

    private val _educationContent = MutableLiveData<List<String>>()
    val educationContent: LiveData<List<String>> = _educationContent

    init {
        loadRecommendations()
        loadEducationContent()
    }

    private fun loadRecommendations() {
        // TODO: Implement actual recommendations loading
        _recommendations.value = listOf(
            "Aylık bütçe planı oluşturun",
            "Acil durum fonu oluşturun",
            "Gereksiz harcamaları azaltın"
        )
    }

    private fun loadEducationContent() {
        // TODO: Implement actual education content loading
        _educationContent.value = listOf(
            "Bütçe Yönetimi 101",
            "Yatırım Temelleri",
            "Tasarruf İpuçları"
        )
    }
}