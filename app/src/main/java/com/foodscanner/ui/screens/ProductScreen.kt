package com.foodscanner.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R
import com.foodscanner.ui.components.productscreen.HealthScoreCard
import com.foodscanner.ui.components.productscreen.ProductImage
import com.foodscanner.ui.components.productscreen.ProductName
import com.foodscanner.ui.components.utility.VitalScanFooter
import com.foodscanner.ui.components.utility.VitalScanHeader
import com.foodscanner.ui.components.utility.customShadow
import com.foodscanner.ui.theme.FoodScannerTheme
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import com.foodscanner.ui.components.productscreen.AllergenAlert
import com.foodscanner.ui.components.productscreen.IngredientList
import com.foodscanner.ui.components.productscreen.NutrimentCircles
import com.foodscanner.ui.components.productscreen.NutrimentLegendRow
import kotlin.math.roundToInt

@Composable
fun ProductScreen() {
    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(
                start = 26.dp,
                end = 26.dp,
                top = 24.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(64.dp))
            }
            item {
                ProductImage(
                    imageUrl = ""
                    // Example: https://images.openfoodfacts.org/images/products/400/151/811/7316/front_de.23.400.jpg
                )
            }
            item {
                ProductName(
                    productName = "Harvest Morning Granola"
                )
            }
            item {
                HealthScoreCard(
                    healthscorevalue = 90,
                    choicerating = "Excellent Choice"
                )
            }
            item {
                NutrimentCircles(
                    calories = 321.8,
                    carbs = 36.0,
                    protein = 24.0,
                    fat = 11.0,
                    fiber = 10.0,
                    salt = 8.0,
                    sugar = 5.0,
                )
            }
            item {
                AllergenAlert(
                    allergens = listOf(
                        "Almonds",
                        "Peanuts"
                    )
                )
            }
            item {
                IngredientList(
                    ingredients = listOf(
                        "Whole grain oats",
                        "Almonds",
                        "Honey",
                        "Sunflower oil",
                        "Sea salt"
                    )
                )
            }
            item {
                Spacer(modifier = Modifier.height(104.dp))
            }
        }

        VitalScanHeader (
            modifier = Modifier.align(Alignment.TopCenter)
        )
        VitalScanFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            onScanClick = {},
            onProductClick = {},
            onHistoryClick = {},
            onFavoritesClick = {},
        )
    }
}




@Preview
@Composable
fun ProductScreenPreview() {
    FoodScannerTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF9F9F9)
        ){
            ProductScreen()
        }
    }
}