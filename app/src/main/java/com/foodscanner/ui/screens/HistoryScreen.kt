package com.foodscanner.ui.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.LaunchedEffect
import com.foodscanner.data.Product
import com.foodscanner.ui.components.historyfavoritesscreen.HistoryClearButton
import com.foodscanner.ui.components.historyfavoritesscreen.HistoryProductCard
import androidx.compose.ui.res.stringResource
import com.foodscanner.R
import com.foodscanner.ui.components.historyfavoritesscreen.DeleteMessage
import com.foodscanner.ui.components.utility.SwipeDeleteAnim
import com.foodscanner.ui.components.utility.animations.SlideInFromBottom

@Composable
fun HistoryScreen(
    currentHistoryState: List<Product?>,
    onHistoryProductClick: (Product?) -> Unit,
    onDeleteHistoryProductClick: (Product?) -> Unit,
    onClearHistoryClick: () -> Unit
) {
    //states
    var searchText by remember { mutableStateOf("") }
    var productToDelete by remember { mutableStateOf<Product?>(null) }
    var showClearHistoryMessage by remember { mutableStateOf(false) }
    var deletingProduct by remember { mutableStateOf<Product?>(null) }
    var deletingAllProducts by remember { mutableStateOf(false) }

    //animation state
    var startAnimation by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startAnimation = true
    }

    //filetered History for Searchbar
    val filteredHistory = currentHistoryState.filter { product ->
        val query = searchText.trim()

        if (query.isBlank()) {
            true
        } else {
            product?.getName()?.contains(query, ignoreCase = true) == true
        }
    }


    //page layout
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
                SlideInFromBottom(
                    visible = startAnimation,
                    delayMillis = 0
                ) {
                    HistoryScreenHeader(
                        pagename = stringResource(R.string.history),
                        downwriting = stringResource(R.string.history_downwriting)
                    )
                }
            }

            item {
                HistorySearchBar(
                    searchText = searchText,
                    onSearchTextChange = { newText ->
                        searchText = newText
                    },
                    searchBarText = stringResource(R.string.history_search),
                    visible = startAnimation,
                    delayMillis = 80
                )
            }


            //history product cards + transition anim + delete anim
            itemsIndexed(
                items = filteredHistory,
                key = { index, product ->
                    product?.getCode() ?: "${product?.getName()}-$index"
                }
            ) { index, product ->
                SwipeDeleteAnim(
                    modifier = Modifier.animateItem(
                        placementSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ),
                    isDeleting = deletingAllProducts || deletingProduct?.getCode() == product?.getCode(),
                    delayMillis = if (deletingAllProducts) index.coerceAtMost(6) * 40 else 0,
                    onDeleteAnimationFinished = {
                        if (deletingProduct?.getCode() == product?.getCode()) {
                            onDeleteHistoryProductClick(product)
                            deletingProduct = null
                        }
                    }
                ) {
                    HistoryProductCard(
                        product = product,
                        visible = startAnimation,
                        delayMillis = 160 + index.coerceAtMost(6) * 60,
                        onCardClick = { clickedProduct ->
                            onHistoryProductClick(clickedProduct)
                        },
                        onCardLongClick = { longClickedProduct ->
                            productToDelete = longClickedProduct
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
            }

            item(
                key = "clear_button"
            ) {
                HistoryClearButton(
                    modifier = Modifier.animateItem(
                        placementSpec = tween(
                            durationMillis = 300,
                            easing = FastOutSlowInEasing
                        )
                    ),
                    visible = startAnimation,
                    delayMillis = 220 + filteredHistory.size.coerceAtMost(6) * 60,
                    onClearHistoryClick = {
                        showClearHistoryMessage = true
                    },
                    clearlist = stringResource(R.string.clear_history)
                )
            }

            item {
                Spacer(modifier = Modifier.height(104.dp))
            }
        }

        //delay delete to give anim time
        LaunchedEffect(deletingAllProducts) {
            if (deletingAllProducts) {
                kotlinx.coroutines.delay(300 + filteredHistory.size.coerceAtMost(6) * 40L)
                onClearHistoryClick()
                deletingAllProducts = false
            }
        }

        //"really delete?" one product from history
        DeleteMessage(
            showMessage = productToDelete != null,
            onDismiss = {
                productToDelete = null
            },
            onConfirmDelete = {
                deletingProduct = productToDelete
                productToDelete = null
            },
            deleteTitle = stringResource(R.string.delete_history_product_title),
            deleteQuestion = stringResource(
                R.string.delete_history_product_message,
                productToDelete?.getName() ?: stringResource(R.string.unknownp)
            )
        )

        //"really delete?" all products from history
        DeleteMessage(
            showMessage = showClearHistoryMessage,
            onDismiss = {
                showClearHistoryMessage = false
            },
            onConfirmDelete = {
                deletingAllProducts = true
                showClearHistoryMessage = false
            },
            deleteTitle = stringResource(R.string.clear_history) + "?",
            deleteQuestion = stringResource(R.string.clear_history_question)
        )
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