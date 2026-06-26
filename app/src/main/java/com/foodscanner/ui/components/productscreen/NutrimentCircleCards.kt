package com.foodscanner.ui.components.productscreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.foodscanner.R
import com.foodscanner.ui.components.utility.customShadow
import kotlin.math.roundToInt

@Composable
fun NutrimentCircles(
    modifier: Modifier = Modifier,
    calories: Double?,
    carbs: Double?,
    protein: Double?,
    fat: Double?,
    fiber: Double?,
    salt: Double?,
    sugar: Double?,

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

        var fiberangle: Float = 0.0F
        if (fiber != null) {
            fiberangle = (fiber/50 * 360f ).toFloat()
        }

        var saltangle: Float = 0.0F
        if (salt != null) {
            saltangle = (salt/50 * 360f ).toFloat()
        }

        var sugarangle: Float = 0.0F
        if (sugar != null) {
            sugarangle = (sugar/50 * 360f ).toFloat()
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
                    text = stringResource(R.string.macros),
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
                            color = Color(0xFF4F9896),
                            startAngle = carbsangle - 90f,
                            sweepAngle = proteinangle,
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
                            text = "${calories?.roundToInt()}",
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
                        label = stringResource(R.string.carbs),
                        value = "${carbs?.roundToInt()} g"
                    )

                    NutrimentLegendRow(
                        color = Color(0xFF4F9896),
                        label = stringResource(R.string.protein),
                        value = "${protein?.roundToInt()} g"
                    )

                    NutrimentLegendRow(
                        color = Color(0xFF93D2D0),
                        label = stringResource(R.string.fat),
                        value = "${fat?.roundToInt()} g"
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.nutrients),
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

                        //Sugar Circle
                        drawArc(
                            color = Color(0xFFA7D8F0),
                            startAngle = fiberangle + saltangle - 90f,
                            sweepAngle = sugarangle,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        )

                        //Salt Circle
                        drawArc(
                            color = Color(0xFF6BAED6),
                            startAngle = fiberangle - 90f,
                            sweepAngle = saltangle,
                            useCenter = false,
                            topLeft = topLeft,
                            size = arcSize,
                            style = Stroke(
                                width = strokeWidth,
                                cap = StrokeCap.Round
                            )
                        )

                        //Fiber Circle
                        drawArc(
                            color = Color(0xFF3A7CA5),
                            startAngle = -90f,
                            sweepAngle = fiberangle,
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
                            text = "NaN",
                            fontSize = 24.sp,
                            lineHeight = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1C1C)
                        )

                        Text(
                            text = "NaN",
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
                        color = Color(0xFF3A7CA5),
                        label = stringResource(R.string.fiber),
                        value = "${fiber?.roundToInt()} g"
                    )

                    NutrimentLegendRow(
                        color = Color(0xFF6BAED6),
                        label = stringResource(R.string.salt),
                        value = "${salt?.roundToInt()} g"
                    )

                    NutrimentLegendRow(
                        color = Color(0xFFA7D8F0),
                        label = stringResource(R.string.sugar),
                        value = "${sugar?.roundToInt()} g"
                    )
                }
            }
        }
    }
}