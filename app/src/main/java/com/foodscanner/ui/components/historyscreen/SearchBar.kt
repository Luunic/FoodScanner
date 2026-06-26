package com.foodscanner.ui.components.historyscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.ui.components.utility.customShadow

@Composable
fun HistorySearchBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchTextChange: (String) -> Unit = {}
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            ),
        placeholder = {
            Text(
                text = stringResource(com.foodscanner.R.string.history_search),
                fontSize = 16.sp,
                color = Color(0xFF6F7978)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(com.foodscanner.R.drawable.search_24dp_ffffff_fill0_wght400_grad0_opsz24),
                contentDescription = "Search",
                tint = Color(0xFF6F7978),
                modifier = Modifier.size(22.dp)
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color(0xFF1A1C1C),
            unfocusedTextColor = Color(0xFF1A1C1C),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            cursorColor = Color(0xFF246565),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
    )
}