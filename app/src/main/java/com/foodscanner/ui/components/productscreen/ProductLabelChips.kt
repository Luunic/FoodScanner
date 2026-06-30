package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.NoFood
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ProductLabelChipData(
    val key: String,
    val text: String
)

//map Icons
private val labelIconMap: Map<String, ImageVector> = mapOf(
    "en:vegan" to Icons.Default.Eco,
    "en:vegetarian" to Icons.Default.Grass,
    "en:organic" to Icons.Default.Eco,
    "en:fair-trade" to Icons.Default.Favorite,
    "en:halal" to Icons.Default.Verified,
    "en:kosher" to Icons.Default.Verified,

    "en:gluten-free" to Icons.Default.NoFood,
    "en:lactose-free" to Icons.Default.NoFood,
    "en:no-added-sugar" to Icons.Default.CheckCircle,
    "en:no-added-salt" to Icons.Default.CheckCircle,

    "en:no-palm-oil" to Icons.Default.Eco,
    "en:palm-oil-free" to Icons.Default.Eco,

    "en:low-fat" to Icons.Default.CheckCircle,
    "en:low-sugar" to Icons.Default.CheckCircle,
    "en:high-protein" to Icons.Default.FitnessCenter,
    "en:source-of-fiber" to Icons.Default.Restaurant,
    "en:high-fiber" to Icons.Default.Restaurant,

    "en:non-gmo" to Icons.Default.Verified,
    "en:gmo-free" to Icons.Default.Verified,
    "en:free-range" to Icons.Default.Grass,
    "en:sustainably-sourced" to Icons.Default.Eco,
    "en:rainforest-alliance" to Icons.Default.Public,

    "en:made-in-eu" to Icons.Default.LocationOn,
    "en:local" to Icons.Default.LocationOn,
    "en:made-in-germany" to Icons.Default.LocationOn,

    "en:nutriscore" to Icons.Default.Verified,
    "en:ecoscore" to Icons.Default.Eco
)

@Composable
fun ProductLabelChips(
    labels: List<ProductLabelChipData>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        labels.forEach { label ->
            val icon = labelIconMap[label.key] ?: Icons.Default.CheckCircle

            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFAFEEEC).copy(alpha = 0.35f))
                    .border(
                        width = 1.dp,
                        color = Color(0xFF246565).copy(alpha = 0.25f),
                        shape = RoundedCornerShape(50)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFF246565),
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = label.text,
                    fontSize = 13.sp,
                    color = Color(0xFF246565)
                )
            }
        }
    }
}