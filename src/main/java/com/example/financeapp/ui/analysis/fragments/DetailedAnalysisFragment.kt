package com.example.financeapp.ui.analysis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.financeapp.databinding.FragmentDetailedAnalysisBinding
import com.example.financeapp.ui.analysis.AnalysisViewModel
import com.example.financeapp.ui.analysis.adapter.DetailedAnalysisAdapter
import com.example.financeapp.ui.analysis.model.ExpenseData
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class DetailedAnalysisFragment : Fragment() {
    private var _binding: FragmentDetailedAnalysisBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalysisViewModel by viewModels()
    private val adapter = DetailedAnalysisAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailedAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
    }

    private fun setupViews() {
        binding.detailedAnalysisList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DetailedAnalysisFragment.adapter
        }
    }

    private fun observeData() {
        viewModel.expenseData.observe(viewLifecycleOwner) { expenseList ->
            updateUI(expenseList)
        }
    }

    private fun updateUI(expenseList: List<ExpenseData>) {
        binding.apply {
            adapter.submitList(expenseList)

            val totalExpense = expenseList.sumOf { it.amount }
            totalExpenseValue.text = String.format(Locale.getDefault(), "%.2f ₺", totalExpense)

            val categoryAnalysis = expenseList.groupBy { it.category }
                .mapValues { it.value.sumOf { expense -> expense.amount } }

            val highestCategory = categoryAnalysis.maxByOrNull { it.value }
            highestCategoryValue.text = highestCategory?.key ?: "-"
            highestAmountValue.text = highestCategory?.value?.let {
                String.format(Locale.getDefault(), "%.2f ₺", it)
            } ?: "0.00 ₺"

            val averageExpense = if (expenseList.isNotEmpty()) {
                totalExpense / expenseList.size
            } else 0.0
            averageExpenseValue.text = String.format(Locale.getDefault(), "%.2f ₺", averageExpense)
        }
    }

    fun updateDetailedData(expenses: List<ExpenseData>) {
        if (isAdded && _binding != null) {
            updateUI(expenses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): DetailedAnalysisFragment {
            return DetailedAnalysisFragment().apply {
                arguments = Bundle()
            }
        }
    }
}