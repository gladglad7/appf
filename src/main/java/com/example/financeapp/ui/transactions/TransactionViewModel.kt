package com.example.financeapp.ui.transactions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financeapp.data.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor() : ViewModel() {
    private val _transactionData = MutableLiveData<Transaction>()
    val transactionData: LiveData<Transaction> = _transactionData

    private var currentTransaction = Transaction(
        id = 0,
        amount = 0.0,
        category = "",
        description = "", // Not alanını description olarak kullanacağız
        type = "EXPENSE",
        date = Date(),
        note = ""
    )

    fun setTransactionType(isExpense: Boolean) {
        currentTransaction = currentTransaction.copy(
            type = if (isExpense) "EXPENSE" else "INCOME"
        )
        _transactionData.value = currentTransaction
    }

    fun setAmount(amount: Double) {
        currentTransaction = currentTransaction.copy(amount = amount)
        _transactionData.value = currentTransaction
    }

    fun setCategory(category: String) {
        currentTransaction = currentTransaction.copy(category = category)
        _transactionData.value = currentTransaction
    }

    fun setNote(note: String) {
        currentTransaction = currentTransaction.copy(
            note = note,
            description = note // Not alanını description olarak da kullanıyoruz
        )
        _transactionData.value = currentTransaction
    }

    fun saveTransaction() {
        // TODO: Repository üzerinden kaydetme işlemi yapılacak
    }
}