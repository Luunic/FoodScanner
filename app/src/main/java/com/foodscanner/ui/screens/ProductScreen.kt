package com.foodscanner.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.data.HealthScoreCalculator
import com.foodscanner.data.Product
import com.foodscanner.ui.components.productscreen.HealthScoreCard
import com.foodscanner.ui.components.productscreen.ProductImage
import com.foodscanner.ui.components.productscreen.ProductName
import com.foodscanner.ui.components.productscreen.AllergenAlert
import com.foodscanner.ui.components.productscreen.IngredientList
import com.foodscanner.ui.components.productscreen.NutrimentCircles
import com.foodscanner.ui.components.productscreen.RedirectProductText
import com.foodscanner.ui.theme.FoodScannerTheme
import com.foodscanner.service.allergenTranslations
import com.foodscanner.service.labelTranslations
import com.foodscanner.ui.components.productscreen.ProductLabelChipData
import com.foodscanner.ui.components.productscreen.ProductLabelChips
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.foodscanner.ui.components.utility.animations.SlideInFromBottom

@Composable
fun ProductScreen(
    currentProduct: Product?,
    selectedAllergens: List<String>,
    onGoToScanPageClick: () -> Unit,
    onFavoriteClick: (Product?) -> Unit,
    isFavorite: Boolean
) {
    //animation state
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    Box (
        modifier = Modifier.fillMaxSize()
    ) {
        //only show page when there is a current/last scanned product
        if (currentProduct == null) {
            RedirectProductText(onButtonClick = onGoToScanPageClick)
        }
        else {
            //language
            val configuration = LocalConfiguration.current
            val languageCode = configuration.locales[0].language
            val allergenTranslationMap = allergenTranslations[languageCode]
                ?: allergenTranslations["en"]
                ?: emptyMap()
            val labelTranslationMap = labelTranslations[languageCode]
                ?: labelTranslations["en"]
                ?: emptyMap()

            //ingredients
            val productIngredients = currentProduct
                .getIngredients()
                .orEmpty()

            //labels
            val productLabels = currentProduct
                .getLabels()
                .orEmpty()
                .filter { it.isNotBlank() }
            val displayLabels = productLabels.map { labelKey ->
                ProductLabelChipData(
                    key = labelKey,
                    text = labelTranslationMap[labelKey] ?: labelKey
                )
            }

            //allergens
            val productAllergens = currentProduct
                .getAllergens()
                .orEmpty()
                .filter { it.isNotBlank() }
            val visibleAllergens = if (selectedAllergens.isEmpty()) {
                productAllergens
            } else {
                productAllergens.filter { productAllergen ->
                    selectedAllergens.any { selectedKey ->
                        val translatedSelectedAllergen = allergenTranslationMap[selectedKey] ?: selectedKey

                        productAllergen.equals(selectedKey, ignoreCase = true) ||
                        productAllergen.equals(translatedSelectedAllergen, ignoreCase = true)
                    }
                }
            }
            val displayAllergens = visibleAllergens.map { allergen ->
                allergenTranslationMap[allergen] ?: allergen
            }


            //page layout
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
                        imageUrl = currentProduct.getImageUrl(),
                        visible = startAnimation
                    )
                }

                item {
                    SlideInFromBottom(
                        visible = startAnimation,
                        delayMillis = 80
                    ) {
                        ProductName(
                            productName = currentProduct.getName(),
                            currentProduct = currentProduct,
                            onFavoriteClick = onFavoriteClick,
                            isFavorite = isFavorite
                        )
                    }
                }

                item {
                    HealthScoreCard(
                        healthscorevalue = HealthScoreCalculator.calculate(currentProduct),
                        visible = startAnimation,
                        delayMillis = 160
                    )
                }

                if (displayLabels.isNotEmpty()) {
                    item {
                        SlideInFromBottom(
                            visible = startAnimation,
                            delayMillis = 240
                        ) {
                            ProductLabelChips(
                                labels = displayLabels,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                item {
                    NutrimentCircles(
                        calories = currentProduct.getNutriments()?.getEnergyKcals(),
                        carbs = currentProduct.getNutriments()?.getCarbohydrates(),
                        protein = currentProduct.getNutriments()?.getProteins(),
                        fat = currentProduct.getNutriments()?.getFat(),
                        fiber = currentProduct.getNutriments()?.getFiber(),
                        salt = currentProduct.getNutriments()?.getSalt(),
                        sugar = currentProduct.getNutriments()?.getSugars(),
                        visible = startAnimation,
                        delayMillis = 320
                    )
                }

                if (displayAllergens.isNotEmpty()) {
                    item {
                        SlideInFromBottom(
                            visible = startAnimation,
                            delayMillis = 400
                        ) {
                            AllergenAlert(
                                allergens = displayAllergens
                            )
                        }
                    }
                }

                if (productIngredients.isNotEmpty()) {
                    item {
                        IngredientList(
                            ingredients = productIngredients,
                            visible = startAnimation,
                            delayMillis = 480
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(104.dp))
                }
            }
        }
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
            ProductScreen(
                currentProduct = Product(null,null,null,null,null,null,null,null,null,null,null, null),
                selectedAllergens = emptyList(),
                onGoToScanPageClick = {},
                isFavorite = true,
                onFavoriteClick = {}
            )
        }
    }
}