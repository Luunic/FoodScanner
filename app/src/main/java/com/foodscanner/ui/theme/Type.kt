package com.foodscanner.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import com.foodscanner.R


private val AppFontFamily = FontFamily(

    Font(
        R.font.plusjakartasans_extralight,
        FontWeight.ExtraLight
    ),
    Font(
        R.font.plusjakartasans_light,
        FontWeight.Light
    ),
    Font(
        R.font.plusjakartasans_regular,
        FontWeight.Normal
    ),
    Font(
        R.font.plusjakartasans_medium,
        FontWeight.Medium
    ),
    Font(
        R.font.plusjakartasans_semibold,
        FontWeight.SemiBold
    ),
    Font(
        R.font.plusjakartasans_bold,
        FontWeight.Bold
    ),
    Font(
        R.font.plusjakartasans_extrabold,
        FontWeight.ExtraBold
    ),

    Font(
        R.font.plusjakartasans_extralightitalic,
        FontWeight.ExtraLight,
        FontStyle.Italic
    ),
    Font(
        R.font.plusjakartasans_lightitalic,
        FontWeight.Light,
        FontStyle.Italic
    ),
    Font(
        R.font.plusjakartasans_italic,
        FontWeight.Normal,
        FontStyle.Italic
    ),
    Font(
        R.font.plusjakartasans_mediumitalic,
        FontWeight.Medium,
        FontStyle.Italic
    ),
    Font(
        R.font.plusjakartasans_semibolditalic,
        FontWeight.SemiBold,
        FontStyle.Italic
    ),
    Font(
        R.font.plusjakartasans_bolditalic,
        FontWeight.Bold,
        FontStyle.Italic
    ),
    Font(
        R.font.plusjakartasans_extrabolditalic,
        FontWeight.ExtraBold,
        FontStyle.Italic
    )
)

val Typography = Typography(
    displayLarge = TextStyle(fontFamily = AppFontFamily),
    displayMedium = TextStyle(fontFamily = AppFontFamily),
    displaySmall = TextStyle(fontFamily = AppFontFamily),

    headlineLarge = TextStyle(fontFamily = AppFontFamily),
    headlineMedium = TextStyle(fontFamily = AppFontFamily),
    headlineSmall = TextStyle(fontFamily = AppFontFamily),

    titleLarge = TextStyle(fontFamily = AppFontFamily),
    titleMedium = TextStyle(fontFamily = AppFontFamily),
    titleSmall = TextStyle(fontFamily = AppFontFamily),

    bodyLarge = TextStyle(fontFamily = AppFontFamily),
    bodyMedium = TextStyle(fontFamily = AppFontFamily),
    bodySmall = TextStyle(fontFamily = AppFontFamily),

    labelLarge = TextStyle(fontFamily = AppFontFamily),
    labelMedium = TextStyle(fontFamily = AppFontFamily),
    labelSmall = TextStyle(fontFamily = AppFontFamily)
)




//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = AppFontFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
//
//)

