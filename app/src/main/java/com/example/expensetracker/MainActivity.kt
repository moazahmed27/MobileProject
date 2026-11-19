package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.expensetracker.ui.ExpenseViewModel
import com.example.expensetracker.ui.detail.ExpenseDetailActivity
import com.example.expensetracker.ui.screens.ExpenseScreen

class MainActivity : ComponentActivity() {
    private val viewModel: ExpenseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val expenses by viewModel.expenses.collectAsStateWithLifecycle()
                ExpenseScreen(
                    expenses = expenses,
                    onAdd = viewModel::add,
                    onDelete = viewModel::delete,
                    // Explicit Intent -> XML activity
                    onOpen = { expense ->
                        startActivity(
                            Intent(this, ExpenseDetailActivity::class.java)
                                .putExtra(ExpenseDetailActivity.EXTRA_ID, expense.id)
                        )
                    }
                )
            }
        }
    }
}
