package com.example.financeapp.ui.analysis

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.databinding.ActivityAnalysisBinding
import com.example.financeapp.ui.analysis.adapter.AnalysisPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnalysisActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalysisBinding
    private val viewModel: AnalysisViewModel by viewModels()
    private val pagerAdapter by lazy { AnalysisPagerAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        observeData()
    }

    private fun setupViews() {
        binding.viewPager.apply {
            adapter = pagerAdapter
            offscreenPageLimit = 2
            isUserInputEnabled = true
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Genel Bakış"
                1 -> "Detaylı Analiz"
                2 -> "Grafikler"
                else -> ""
            }
        }.attach()
    }

    private fun observeData() {
        viewModel.expenseData.observe(this) { expenseList ->
            pagerAdapter.updateData(expenseList)
        }
    }
}