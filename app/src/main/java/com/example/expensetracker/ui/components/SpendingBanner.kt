package com.example.expensetracker.ui.components

import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.expensetracker.R
import com.example.expensetracker.data.Categories

/** XML layout (view_banner.xml) hosted inside Compose via AndroidView. */
@Composable
fun SpendingBanner(total: Double) {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.view_banner, null)
        },
        update = { view ->
            view.findViewById<TextView>(R.id.bannerTotal).text = "%.2f EGP".format(total)
            view.findViewById<TextView>(R.id.bannerBudget).text =
                "Monthly budget: %.0f EGP".format(Categories.MONTHLY_BUDGET)
            view.findViewById<ProgressBar>(R.id.bannerProgress).apply {
                max = Categories.MONTHLY_BUDGET.toInt()
                progress = total.toInt().coerceAtMost(max)
            }
        }
    )
}
