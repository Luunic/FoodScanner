package com.foodscanner.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.data.Product
import com.foodscanner.ui.components.productscreen.HealthScoreCard
import com.foodscanner.ui.components.productscreen.ProductImage
import com.foodscanner.ui.components.productscreen.ProductName
import com.foodscanner.ui.components.productscreen.AllergenAlert
import com.foodscanner.ui.components.productscreen.IngredientList
import com.foodscanner.ui.components.productscreen.NutrimentCircles
import com.foodscanner.ui.components.productscreen.RedirectProductText
import com.foodscanner.ui.theme.FoodScannerTheme

@Composable
fun ProductScreen(
    currentProduct: Product?,
    onGoToScanPageClick:() -> Unit,

) {
    Box (
        modifier = Modifier.fillMaxSize()
    ) {

        if (currentProduct == null) {
            RedirectProductText(onButtonClick = onGoToScanPageClick)
        }
        else {

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
                    imageUrl = currentProduct?.getImageUrl()
                    // Example: https://images.openfoodfacts.org/images/products/400/151/811/7316/front_de.23.400.jpg
                )
            }
            item {
                ProductName(
                    productName = currentProduct?.getName()
                )
            }
            item {
                HealthScoreCard(
                    healthscorevalue = currentProduct?.calculateHealthScore()
                )
            }
            item {
                NutrimentCircles(
                    calories = currentProduct?.getNutriments()?.getEnergyKcals(),
                    carbs = currentProduct?.getNutriments()?.getCarbohydrates(),
                    protein = currentProduct?.getNutriments()?.getProteins(),
                    fat = currentProduct?.getNutriments()?.getFat(),
                    fiber = currentProduct?.getNutriments()?.getFiber(),
                    salt = currentProduct?.getNutriments()?.getSalt(),
                    sugar = currentProduct?.getNutriments()?.getSugars(),
                )
            }
            item {
                AllergenAlert(
                    allergens = currentProduct?.getAllergens()
                )
            }
            item {
                IngredientList(
                    ingredients = currentProduct?.getIngredients()
                )
            }
            item {
                Spacer(modifier = Modifier.height(104.dp))
            }
        }

//      Preview Header + Footer - disable when running app
//        VitalScanHeader(
//            modifier = Modifier.align(Alignment.TopCenter)
//        )
//        VitalScanFooter(
//            modifier = Modifier.align(Alignment.BottomCenter),
//            onScanClick = {},
//            onProductClick = {},
//            onHistoryClick = {},
//            onFavoritesClick = {}
//        )
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
                onGoToScanPageClick = {}
            )
        }
    }
}

