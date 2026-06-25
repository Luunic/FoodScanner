package com.foodscanner.ui.screens


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
                ProductImage()
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
                    calories = 321.0,
                    carbs = 25.0,
                    protein = 25.0,
                    fat = 25.0
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


@Composable
fun NutrimentCircles(
    modifier: Modifier = Modifier,
    calories: Double?,
    carbs: Double?,
    protein: Double?,
    fat: Double?

) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        var carbsangle: Float = 0.0F
        if (carbs != null) {
            carbsangle = (carbs/100 * 360f ).toFloat()
        }
        var proteinangle: Float = 0.0F
        if (protein != null) {
            proteinangle = (protein/100 * 360f ).toFloat()
        }
        var fatangle: Float = 0.0F
        if (fat != null) {
            fatangle = (fat/100 * 360f ).toFloat()
        }


        Card(
            modifier = modifier
                .weight(1f)
                .height(250.dp)
                .customShadow(
                    color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                    blurRadius = 20.dp,
                    offsetY = 8.dp
                ),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MACRONUTRIENTS",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.4.sp,
                    color = Color(0xFF3F4948)
                )

                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier.size(96.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val strokeWidth = 8.dp.toPx()
                        val diameter = size.minDimension - strokeWidth
                        val topLeft = Offset(strokeWidth / 2, strokeWidth / 2)
                        val arcSize = Size(diameter, diameter)


                        //Background Circle
                        drawArc(
                            color = Color(0xFFF4F3F3),
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        )

                        //Fat Circle
                        drawArc(
                            color = Color(0xFF93D2D0),
                            startAngle = proteinangle + carbsangle - 90f,
                            sweepAngle = fatangle,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        )

                        //Protein Circle
                        drawArc(
                            color = Color(0xFF407E7D),
                            startAngle = carbsangle - 90f,
                            sweepAngle = fatangle,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        )

                        //Carb Circle
                        drawArc(
                            color = Color(0xFF246565),
                            startAngle = -90f,
                            sweepAngle = carbsangle,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        )

                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$calories",
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1C1C)
                        )

                        Text(
                            text = "KCAL",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.8.sp,
                            color = Color(0xFF3F4948)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    NutrimentLegendRow(
                        color = Color(0xFF246565),
                        label = "Carbs",
                        value = "$carbs g"
                    )

                    NutrimentLegendRow(
                        color = Color(0xFF407E7D),
                        label = "Protein",
                        value = "$protein g"
                    )

                    NutrimentLegendRow(
                        color = Color(0xFF93D2D0),
                        label = "Fat",
                        value = "$fat g"
                    )
                }
            }
        }
        Card(
            modifier = modifier
                .weight(1f)
                .height(250.dp)
                .customShadow(
                    color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                    blurRadius = 20.dp,
                    offsetY = 8.dp
                ),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

        }
    }
}

@Composable
fun NutrimentLegendRow(
    color: Color,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(color, CircleShape)
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                text = label,
                fontSize = 11.sp,
                color = Color(0xFF3F4948)
            )
        }

        Text(
            text = value,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A1C1C)
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