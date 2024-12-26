package com.example.financeapp.ui.transactions

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.financeapp.R
import com.example.financeapp.databinding.ActivityTransactionBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionBinding
    private val viewModel: TransactionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()
        observeData()
    }

    private fun setupViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val categories = resources.getStringArray(R.array.expense_categories)
        binding.categoryInput.editText?.setText(categories.firstOrNull() ?: "")
    }

    private fun setupListeners() {
        binding.apply {
            transactionTypeGroup.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    val isExpenseTransaction = checkedId == R.id.expenseButton
                    viewModel.setTransactionType(isExpenseTransaction)
                }
            }

            amountInput.editText?.addTextChangedListener { text ->
                text?.toString()?.toDoubleOrNull()?.let { amount ->
                    viewModel.setAmount(amount)
                }
            }

            categoryInput.editText?.addTextChangedListener { text ->
                text?.toString()?.let { category ->
                    viewModel.setCategory(category)
                }
            }

            noteInput.editText?.addTextChangedListener { text ->
                text?.toString()?.let { noteText ->
                    viewModel.setNote(noteText)
                }
            }

            saveButton.setOnClickListener {
                viewModel.saveTransaction()
                finish()
            }
        }
    }

    private fun observeData() {
        viewModel.transactionData.observe(this) { transaction ->
            binding.apply {
                amountInput.editText?.setText(
                    String.format(Locale.getDefault(), "%.2f", transaction.amount)
                )
                categoryInput.editText?.setText(transaction.category)
                noteInput.editText?.setText(transaction.note)
                transactionTypeGroup.check(
                    if (transaction.type == "EXPENSE") R.id.expenseButton else R.id.incomeButton
                )
            }
        }
    }
}