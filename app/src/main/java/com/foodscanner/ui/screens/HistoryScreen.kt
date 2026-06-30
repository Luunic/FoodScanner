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
import com.foodscanner.ui.theme.FoodScannerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.foodscanner.ui.components.historyfavoritesscreen.HistoryScreenHeader
import com.foodscanner.ui.components.historyfavoritesscreen.HistorySearchBar
import androidx.compose.foundation.lazy.items
import com.foodscanner.data.Product
import com.foodscanner.ui.components.historyfavoritesscreen.HistoryClearButton
import com.foodscanner.ui.components.historyfavoritesscreen.HistoryProductCard
import androidx.compose.ui.res.stringResource
import com.foodscanner.R
import com.foodscanner.ui.components.historyfavoritesscreen.DeleteMessage

@Composable
fun HistoryScreen(
    currentHistoryState: List<Product?>,
    onHistoryProductClick: (Product?) -> Unit,
    onDeleteHistoryProductClick: (Product?) -> Unit,
    onClearHistoryClick: () -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    var productToDelete by remember { mutableStateOf<Product?>(null) }

    //filetered History for Searchbar
    val filteredHistory = currentHistoryState.filter { product ->
        val query = searchText.trim()

        if (query.isBlank()) {
            true
        } else {
            product?.getName()?.contains(query, ignoreCase = true) == true
        }
    }

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
                HistoryScreenHeader(
                    pagename = stringResource(R.string.history),
                    downwriting = stringResource(R.string.history_downwriting)
                )
            }

            item {
                HistorySearchBar(
                    searchText = searchText,
                    onSearchTextChange = { newText ->
                        searchText = newText
                    },
                    searchBarText = stringResource(com.foodscanner.R.string.history_search)
                )
            }

            items(filteredHistory) { product ->
                HistoryProductCard(
                    product = product,
                    onCardClick = { clickedProduct ->
                        onHistoryProductClick(clickedProduct)
                    },
                    onCardLongClick = { longClickedProduct ->
                        productToDelete = longClickedProduct
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
                    },
                    clearlist = stringResource(R.string.clear_history)
                )
            }

            item {
                Spacer(modifier = Modifier.height(104.dp))
            }
        }

        DeleteMessage(
            productToDelete = productToDelete,
            onDismiss = {
                productToDelete = null
            },
            onConfirmDelete = {
                onDeleteHistoryProductClick(productToDelete)
                productToDelete = null
            },
            deleteTitle = stringResource(R.string.delete_history_product_title),
            deleteQuestion = stringResource(
                R.string.delete_history_product_message,
                productToDelete?.getName() ?: stringResource(R.string.unknownp)
            )
        )

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
                currentHistoryState = emptyList(),
                onHistoryProductClick = {},
                onClearHistoryClick = {},
                onDeleteHistoryProductClick = {},
            )
        }
    }
}