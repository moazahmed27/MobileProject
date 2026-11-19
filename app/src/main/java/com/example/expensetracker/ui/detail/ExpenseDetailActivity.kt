package com.example.expensetracker.ui.detail

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.expensetracker.R
import com.example.expensetracker.data.AppDatabase
import com.example.expensetracker.data.Categories
import com.example.expensetracker.data.Expense
import com.example.expensetracker.data.ExpenseRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

/** XML-based screen (activity_expense_detail.xml): edit, share (implicit Intent) and delete. */
class ExpenseDetailActivity : AppCompatActivity() {

    private val repository by lazy { ExpenseRepository(AppDatabase.get(this).expenseDao()) }
    private var current: Expense? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_detail)

        val etTitle = findViewById<TextInputEditText>(R.id.etTitle)
        val etAmount = findViewById<TextInputEditText>(R.id.etAmount)
        val etNote = findViewById<TextInputEditText>(R.id.etNote)
        val spinner = findViewById<Spinner>(R.id.spCategory).apply {
            adapter = ArrayAdapter(
                this@ExpenseDetailActivity,
                android.R.layout.simple_spinner_dropdown_item,
                Categories.all
            )
        }

        val id = intent.getIntExtra(EXTRA_ID, -1)

        lifecycleScope.launch {
            current = repository.getById(id)
            current?.let {
                etTitle.setText(it.title)
                etAmount.setText(it.amount.toString())
                etNote.setText(it.note)
                spinner.setSelection(Categories.all.indexOf(it.category).coerceAtLeast(0))
            } ?: finish()
        }

        findViewById<MaterialButton>(R.id.btnSave).setOnClickListener {
            val expense = current ?: return@setOnClickListener
            val title = etTitle.text?.toString().orEmpty().trim()
            val amount = etAmount.text?.toString()?.toDoubleOrNull()
            when {
                title.isEmpty() -> etTitle.error = "Title is required"
                amount == null || amount <= 0 -> etAmount.error = "Enter a valid amount"
                else -> lifecycleScope.launch {
                    repository.update(
                        expense.copy(
                            title = title,
                            amount = amount,
                            category = spinner.selectedItem as String,
                            note = etNote.text?.toString().orEmpty().trim()
                        )
                    )
                    Toast.makeText(this@ExpenseDetailActivity, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        // Implicit Intent -> share sheet
        findViewById<MaterialButton>(R.id.btnShare).setOnClickListener {
            val expense = current ?: return@setOnClickListener
            val send = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT,
                    "${expense.title} - %.2f EGP (${expense.category})".format(expense.amount)
                )
            }
            startActivity(Intent.createChooser(send, "Share expense"))
        }

        findViewById<MaterialButton>(R.id.btnDelete).setOnClickListener {
            val expense = current ?: return@setOnClickListener
            lifecycleScope.launch {
                repository.delete(expense)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_ID = "expense_id"
    }
}
