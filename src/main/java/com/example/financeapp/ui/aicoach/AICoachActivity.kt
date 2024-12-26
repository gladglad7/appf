package com.example.financeapp.ui.aicoach

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.databinding.ActivityAiCoachBinding
import com.example.financeapp.ui.aicoach.adapter.EducationContentAdapter
import com.example.financeapp.ui.aicoach.adapter.RecommendationsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AICoachActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAiCoachBinding
    private val viewModel: AICoachViewModel by viewModels()
    private val recommendationsAdapter = RecommendationsAdapter()
    private val educationContentAdapter = EducationContentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAiCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerViews() {
        binding.recyclerViewRecommendations.apply {
            layoutManager = LinearLayoutManager(this@AICoachActivity)
            adapter = recommendationsAdapter
        }

        binding.recyclerViewEducation.apply {
            layoutManager = LinearLayoutManager(this@AICoachActivity)
            adapter = educationContentAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.recommendations.observe(this) { recommendations ->
            recommendationsAdapter.submitList(recommendations)
        }

        viewModel.educationContent.observe(this) { content ->
            educationContentAdapter.submitList(content)
        }
    }
}