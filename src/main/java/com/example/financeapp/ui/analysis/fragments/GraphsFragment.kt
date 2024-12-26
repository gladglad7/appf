package com.example.financeapp.ui.analysis.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financeapp.databinding.FragmentGraphsBinding
import com.example.financeapp.ui.analysis.AnalysisViewModel
import com.example.financeapp.ui.analysis.model.ExpenseData
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class GraphsFragment : Fragment() {
    private var _binding: FragmentGraphsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AnalysisViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCharts()
        observeData()
    }

    private fun setupCharts() {
        binding.pieChart.apply {
            description.isEnabled = false
            setDrawEntryLabels(true)
            legend.apply {
                isEnabled = true
                orientation = Legend.LegendOrientation.VERTICAL
                verticalAlignment = Legend.LegendVerticalAlignment.CENTER
                horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
                setDrawInside(false)
            }
            isDrawHoleEnabled = true
            holeRadius = 40f
            transparentCircleRadius = 45f
            setEntryLabelTextSize(12f)
            setEntryLabelColor(android.graphics.Color.BLACK)
        }
    }

    private fun observeData() {
        viewModel.expenseData.observe(viewLifecycleOwner) { expenses ->
            updateCharts(expenses)
        }
    }

    private fun updateCharts(expenses: List<ExpenseData>) {
        val categoryData = expenses.groupBy { it.category }
            .mapValues { it.value.sumOf { expense -> expense.amount } }

        val entries = categoryData.map { (category, amount) ->
            PieEntry(amount.toFloat(), category)
        }

        val dataSet = PieDataSet(entries, "Harcama Kategorileri").apply {
            colors = listOf(
                android.graphics.Color.rgb(255, 99, 71),  // Kırmızımsı
                android.graphics.Color.rgb(75, 192, 192), // Turkuaz
                android.graphics.Color.rgb(54, 162, 235), // Mavi
                android.graphics.Color.rgb(153, 102, 255),// Mor
                android.graphics.Color.rgb(255, 159, 64), // Turuncu
                android.graphics.Color.rgb(255, 205, 86), // Sarı
                android.graphics.Color.rgb(75, 192, 192), // Yeşilimsi
                android.graphics.Color.rgb(201, 203, 207) // Gri
            )
            valueTextSize = 14f
            valueTextColor = android.graphics.Color.BLACK
            valueLinePart1Length = 0.4f
            valueLinePart2Length = 0.4f
            valueLineColor = android.graphics.Color.GRAY
            yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
        }

        val pieData = PieData(dataSet).apply {
            setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return String.format(Locale.getDefault(), "%.1f ₺", value)
                }
            })
        }

        binding.pieChart.apply {
            data = pieData
            highlightValues(null)
            invalidate()
        }
    }

    fun updateGraphData(expenses: List<ExpenseData>) {
        if (isAdded && _binding != null) {
            updateCharts(expenses)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): GraphsFragment {
            return GraphsFragment().apply {
                arguments = Bundle()
            }
        }
    }
}