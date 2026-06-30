package com.foodscanner.ui.components.historyfavoritesscreen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.foodscanner.R
import com.foodscanner.data.Product

@Composable
fun DeleteMessage(
    showMessage: Boolean,
    onDismiss: () -> Unit,
    onConfirmDelete: () -> Unit,
    deleteTitle: String,
    deleteQuestion: String
) {
    if (showMessage) {
        AlertDialog(
            onDismissRequest = onDismiss,
            containerColor = Color.White,
            titleContentColor = Color(0xFF1A1C1C),
            textContentColor = Color(0xFF1A1C1C),
            title = {
                Text(text = deleteTitle)
            },
            text = {
                Text(
                    text = deleteQuestion
                )
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmDelete
                ) {
                    Text(text = stringResource(R.string.delete))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text(text = stringResource(R.string.cancel))
                }
            }
        )
    }
}