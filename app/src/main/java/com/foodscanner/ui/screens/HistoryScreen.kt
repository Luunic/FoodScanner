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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foodscanner.ui.components.utility.VitalScanFooter
import com.foodscanner.ui.components.utility.VitalScanHeader
import com.foodscanner.ui.theme.FoodScannerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.foodscanner.ui.components.historyscreen.HistoryScreenHeader
import com.foodscanner.ui.components.historyscreen.HistorySearchBar
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.viewmodel.compose.viewModel
import com.foodscanner.data.Product
import com.foodscanner.ui.components.historyscreen.HistoryClearButton
import com.foodscanner.ui.components.historyscreen.HistoryProductCard


@Composable
fun HistoryScreen(
    currentHistoryState: List<Product?>,
    onScanClick: () -> Unit,
    onProductClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onHistoryProductClick: (Product?) -> Unit,
    onClearHistoryClick: () -> Unit,
) {
    var searchText by remember { mutableStateOf("") }

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
                HistoryScreenHeader()
            }

            item {
                HistorySearchBar(
                    searchText = searchText,
                    onSearchTextChange = { newText ->
                        searchText = newText
                    }
                )
            }

            items(currentHistoryState) { product ->
                HistoryProductCard(
                    product = product,
                    onCardClick = { clickedProduct ->
                        onHistoryProductClick(clickedProduct)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item (){
                HistoryClearButton(
                    onClearHistoryClick = {
                        onClearHistoryClick()
                    }
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




@Preview
@Composable
fun HistoryScreenPreview() {
    FoodScannerTheme() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF9F9F9)
        ){
            HistoryScreen(
                onScanClick = {},
                onProductClick = {},
                onHistoryClick = {},
                onFavoritesClick = {},
                currentHistoryState = emptyList(),
                onHistoryProductClick = {},
                onClearHistoryClick = {}
            )
        }
    }
}