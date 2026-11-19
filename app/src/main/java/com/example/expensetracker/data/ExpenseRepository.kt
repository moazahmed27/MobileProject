package com.example.expensetracker.data

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {
    fun observeAll(): Flow<List<Expense>> = dao.observeAll()

    suspend fun getById(id: Int): Expense? = dao.getById(id)

    suspend fun insert(expense: Expense) = dao.insert(expense)

    suspend fun update(expense: Expense) = dao.update(expense)

    suspend fun delete(expense: Expense) = dao.delete(expense)
}
