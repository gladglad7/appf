package com.example.financeapp.ui.transactions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.financeapp.databinding.ActivityTransactionDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}