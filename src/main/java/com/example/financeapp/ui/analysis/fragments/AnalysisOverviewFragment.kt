package com.example.financeapp.ui.analysis.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.financeapp.ui.analysis.model.AnalysisData

class AnalysisOverviewFragment : Fragment() {
    private var analysisData: List<AnalysisData> = emptyList()

    companion object {
        fun newInstance(data: List<AnalysisData>) = AnalysisOverviewFragment().apply {
            analysisData = data
        }
    }
}