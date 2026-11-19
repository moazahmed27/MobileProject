package com.example.expensetracker.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.AppDatabase
import com.example.expensetracker.data.Expense
import com.example.expensetracker.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ExpenseViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = ExpenseRepository(AppDatabase.get(app).expenseDao())

    val expenses: StateFlow<List<Expense>> = repository.observeAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun add(title: String, amount: Double, category: String, note: String) = viewModelScope.launch {
        repository.insert(
            Expense(
                title = title.trim(),
                amount = amount,
                category = category,
                note = note.trim()
            )
        )
    }

    fun delete(expense: Expense) = viewModelScope.launch {
        repository.delete(expense)
    }
}
