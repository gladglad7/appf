package com.example.financeapp.ui.analysis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financeapp.R
import com.example.financeapp.databinding.FragmentExpenseAnalysisBinding
import com.example.financeapp.ui.analysis.AnalysisViewModel
import com.example.financeapp.ui.analysis.model.ExpenseData
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExpenseAnalysisFragment : Fragment() {
    private var _binding: FragmentExpenseAnalysisBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalysisViewModel by viewModels()
    private var expenseData: List<ExpenseData> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpenseAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        observeData()
        setupChipGroup()
    }

    private fun setupChipGroup() {
        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                R.id.chipWeek -> viewModel.loadExpenseData(TimeRange.WEEK)
                R.id.chipMonth -> viewModel.loadExpenseData(TimeRange.MONTH)
                R.id.chipYear -> viewModel.loadExpenseData(TimeRange.YEAR)
            }
        }
    }

    private fun setupCharts() {
        with(binding) {
            lineChart.apply {
                description.isEnabled = false
                setTouchEnabled(true)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)
            }

            pieChart.apply {
                description.isEnabled = false
                isDrawHoleEnabled = true
                setHoleColor(android.R.color.transparent)
                setTransparentCircleAlpha(0)
                holeRadius = 58f
                setDrawCenterText(true)
                centerText = getString(R.string.expenses_distribution)
                animateY(1400)
            }
        }
    }

    private fun observeData() {
        viewModel.expenseData.observe(viewLifecycleOwner) { data ->
            expenseData = data
            updateCharts(data)
        }
    }

    private fun updateCharts(data: List<ExpenseData>) {
        updateLineChart(data)
        updatePieChart(data)
    }

    private fun updateLineChart(data: List<ExpenseData>) {
        val entries = data.mapIndexed { index, expenseData ->
            Entry(index.toFloat(), expenseData.amount.toFloat())
        }

        val dataSet = LineDataSet(entries, getString(R.string.expenses_over_time)).apply {
            setDrawFilled(true)
            setDrawValues(false)
            colors = ColorTemplate.MATERIAL_COLORS.toList()
        }

        binding.lineChart.data = LineData(dataSet)
        binding.lineChart.invalidate()
    }

    private fun updatePieChart(data: List<ExpenseData>) {
        val entries = data.map { expenseData ->
            PieEntry(expenseData.amount.toFloat(), expenseData.category)
        }

        val dataSet = PieDataSet(entries, getString(R.string.expense_categories)).apply {
            colors = ColorTemplate.MATERIAL_COLORS.toList()
            setDrawValues(true)
        }

        binding.pieChart.data = PieData(dataSet)
        binding.pieChart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class TimeRange {
        WEEK, MONTH, YEAR
    }

    companion object {
        @JvmStatic
        fun newInstance(): ExpenseAnalysisFragment {
            return ExpenseAnalysisFragment().apply {
                arguments = Bundle()
            }
        }
    }
}