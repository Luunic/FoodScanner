package com.foodscanner.ui.components.historyscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.foodscanner.R
import com.foodscanner.data.Product
import com.foodscanner.ui.components.utility.customShadow

@Composable
fun HistoryProductCard(
    product: Product?,
    modifier: Modifier = Modifier,
    onCardClick: (Product?) -> Unit = {}
) {
    val cardShape = RoundedCornerShape(32.dp)
    val nutriScore = product?.getNutriScore()?.uppercase() ?: "?"
    val gradeText = stringResource(R.string.rating, nutriScore)

    Card(
        shape = cardShape,
        modifier = modifier
            .fillMaxWidth()
            .customShadow(
                color = Color(70, 101, 101, (255 * 0.22f).toInt()),
                blurRadius = 20.dp,
                offsetY = 8.dp
            )
            .clip(cardShape)
            .clickable {
                onCardClick(product)
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF4F3F3)),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(product?.getImageUrl())
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.product_placeholder),
                    error = painterResource(R.drawable.product_placeholder),
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        AutoResizeProductName(
                            productName = product?.getName() ?: stringResource(R.string.unknownp),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar_today_24dp_ffffff_fill0_wght400_grad0_opsz24),
                                contentDescription = "Date",
                                tint = Color(0xFF6F7978),
                                modifier = Modifier.size(14.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = product?.getDate() ?: stringResource(R.string.unknownd),
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF3F4948)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFAFEEEC).copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = gradeText,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF00504F)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.chevron_right_24dp_ffffff_fill0_wght400_grad0_opsz24),
                contentDescription = "Open product",
                tint = Color(0xFFBFC8C7),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun AutoResizeProductName(
    productName: String,
    modifier: Modifier = Modifier
) {
    val longestWordLength = productName
        .split(" ", "-", "_", "/")
        .maxOfOrNull { word -> word.length }
        ?: 0

    val fontSize = when {
        longestWordLength <= 10 -> 20.sp
        longestWordLength <= 14 -> 18.sp
        longestWordLength <= 18 -> 16.sp
        else -> 16.sp
    }

    Text(
        text = productName,
        modifier = modifier,
        fontSize = fontSize,
        lineHeight = (fontSize.value + 8).sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFF1A1C1C)
    )
}


@Composable
fun HistoryProductCardTest(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFF4F3F3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.product_placeholder),
                    contentDescription = "Product image",
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        AutoResizeProductName(
                            productName = "Organic Almond Milk",
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar_today_24dp_ffffff_fill0_wght400_grad0_opsz24),
                                contentDescription = "Date", // Datum
                                tint = Color(0xFF6F7978),
                                modifier = Modifier.size(14.dp)
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Today, 10:24 AM", // Heute, 10:24 Uhr
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color(0xFF3F4948)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFAFEEEC).copy(alpha = 0.3f),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "GRADE A", // Bewertung A
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 0.5.sp,
                            color = Color(0xFF00504F)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(id = R.drawable.chevron_right_24dp_ffffff_fill0_wght400_grad0_opsz24),
                contentDescription = "Open product", // Produkt öffnen
                tint = Color(0xFFBFC8C7),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}