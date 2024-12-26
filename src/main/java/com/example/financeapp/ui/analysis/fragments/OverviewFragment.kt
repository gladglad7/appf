package com.example.financeapp.ui.analysis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financeapp.databinding.FragmentOverviewBinding
import com.example.financeapp.ui.analysis.AnalysisViewModel
import com.example.financeapp.ui.analysis.model.ExpenseData
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class OverviewFragment : Fragment() {
    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalysisViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel.expenseData.observe(viewLifecycleOwner) { expenses ->
            updateOverview(expenses)
        }
    }

    private fun updateOverview(expenses: List<ExpenseData>) {
        val totalExpense = expenses.sumOf { it.amount }
        binding.totalExpenseValue.text = String.format(Locale.getDefault(), "%.2f ₺", totalExpense)

        // Diğer genel bakış istatistikleri...
    }

    fun updateOverviewData(expenses: List<ExpenseData>) {
        if (isAdded && _binding != null) {
            updateOverview(expenses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = OverviewFragment()
    }
}