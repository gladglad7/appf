package com.example.financeapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financeapp.R
import com.example.financeapp.data.model.FinancialData
import com.example.financeapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
    }

    private fun setupViews() {
        binding.apply {
            tvBalance.text = getString(R.string.balance_label)
            tvIncome.text = getString(R.string.income_label)
            tvExpense.text = getString(R.string.expense_label)
        }
    }

    private fun observeData() {
        viewModel.financialData.observe(viewLifecycleOwner) { data ->
            updateUI(data)
        }
    }

    private fun updateUI(data: FinancialData) {
        binding.apply {
            tvBalanceAmount.text = String.format(Locale.getDefault(), "%.2f ₺", data.balance)
            tvIncomeAmount.text = String.format(Locale.getDefault(), "%.2f ₺", data.totalIncome)
            tvExpenseAmount.text = String.format(Locale.getDefault(), "%.2f ₺", data.totalExpense)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}