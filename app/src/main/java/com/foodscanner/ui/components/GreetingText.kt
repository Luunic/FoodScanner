package com.foodscanner.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.sp
import com.foodscanner.R

// Creates Text above Button
@Composable
fun GreetingText (
    username: String,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text (
            text = stringResource(R.string.greeting_name, username),
            color = Color(0xFF407E7D),
            fontWeight = Bold,
            fontSize = 48.sp
        )
        Text (
            text = stringResource(R.string.ready_to_check),
            color = Color(0xFF3f4948).copy(alpha = 0.8f),
            fontSize = 18.sp
        )
    }
}