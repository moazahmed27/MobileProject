package com.example.expensetracker.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensetracker.data.Expense

@Composable
fun ExpenseCard(
    expense: Expense,
    onDelete: (Expense) -> Unit,
    onOpen: (Expense) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen(expense) }
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(expense.title, style = MaterialTheme.typography.titleMedium)
                Text(expense.category, style = MaterialTheme.typography.labelMedium)
                if (expense.note.isNotBlank()) {
                    Text(expense.note, style = MaterialTheme.typography.bodySmall)
                }
            }
            Text(
                text = "%.2f EGP".format(expense.amount),
                style = MaterialTheme.typography.titleSmall
            )
            IconButton(onClick = { onDelete(expense) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
