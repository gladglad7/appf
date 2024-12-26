package com.example.financeapp.ui.analysis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.databinding.ActivityAnalysisDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalysisDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalysisDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}