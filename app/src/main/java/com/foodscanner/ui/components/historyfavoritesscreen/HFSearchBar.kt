package com.foodscanner.ui.components.historyfavoritesscreen

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R
import com.foodscanner.ui.components.utility.customShadow
import com.foodscanner.ui.components.utility.animations.rememberBottomSlideAnimValues

@Composable
fun HistorySearchBar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    searchBarText: String,
    onSearchTextChange: (String) -> Unit = {},
    visible: Boolean = true,
    delayMillis: Int = 0
) {
    //fly in animation
    val anim = rememberBottomSlideAnimValues(
        visible = visible,
        delayMillis = delayMillis
    )

    Box (
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .customShadow(
                color = Color(70, 101, 101, (255 * anim.shadowAlpha.value).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            )
    ) {
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .graphicsLayer {
                    this.alpha = anim.alpha.value
                    this.translationY = anim.translationY.value
                },
            placeholder = {
                Text(
                    text = searchBarText,
                    fontSize = 16.sp,
                    color = Color(0xFF6F7978)
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search_24dp_ffffff_fill0_wght400_grad0_opsz24),
                    contentDescription = "Search",
                    tint = Color(0xFF6F7978),
                    modifier = Modifier.size(22.dp)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(32.dp),
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
}