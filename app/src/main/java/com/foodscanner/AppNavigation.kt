package com.foodscanner

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodscanner.data.Product
import com.foodscanner.service.startBarcodeScanner
import com.foodscanner.ui.screens.FavoriteScreen
import com.foodscanner.ui.screens.HistoryScreen
import com.foodscanner.ui.screens.ProductScreen
import com.foodscanner.ui.screens.ScanScreen


//@Composable
//fun StartApp(
//    context: Context,
//    viewModel: FoodScannerViewModel
//) {
//
//
//    val productList by viewModel.historyState.collectAsState()
//    val currentProduct by viewModel.currentProduct.collectAsState()
//    val firstName = productList.firstOrNull()?.getName()
//        ?.takeIf { it.isNotBlank() } ?: "Kein Produkt"
//    var productNameList = mutableListOf<String?>()
//    productList.forEach { product ->
//        productNameList.add(0, product.getName())
//    }
//    //ScanScreen(onScanRequested = { startBarcodeScanner(context,viewModel) })
//    ProductScreen()
//}

private sealed class AppRoute(val route: String) {
    data object Scan : AppRoute("scan")
    data object Product : AppRoute("product")
    data object History : AppRoute("history")
    data object Favorit : AppRoute("favorit")
}

@Composable
fun StartApp(
    context: Context,
    viewModel: FoodScannerViewModel,
    controller: Controller
) {
    val navController = rememberNavController()
    val currentProduct by viewModel.currentProduct.collectAsState()

    // mock Product for testing when scanner doesnt work
    var mockProduct: Product? = null

    LaunchedEffect(currentProduct) {

        mockProduct = controller.getProductFromBarcode("4001518117316")!!

        if (currentProduct != null) {
            navController.navigate(AppRoute.Product.route) {
                launchSingleTop = true
            }
        }
    }

    val TAG = "AppNavigation"

    // For testing with mock Json:
//    val inputStream = context.assets.open("MockProduct.json")
//    val mockJsonString: String = inputStream?.bufferedReader().use { it?.readText() as String }
//    val mockJsonElement = Json.parseToJsonElement(mockJsonString)


    //todo: change animation direction

    NavHost(
        navController = navController,
        startDestination = AppRoute.Scan.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        }
    ) {
        composable(AppRoute.Scan.route) {
            ScanScreen(
                onScanRequested = {
                    viewModel.clearCurrentProduct()
                    startBarcodeScanner(context, viewModel)
                },
                onScanClick = {},
                onProductClick = {
                    navController.navigate(AppRoute.Product.route) {
                        launchSingleTop = true
                    }
                },
                onHistoryClick = {
                    navController.navigate(AppRoute.History.route) {
                        launchSingleTop = true
                    }
                },
                onFavoritesClick = {
                    navController.navigate(AppRoute.Favorit.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoute.Product.route) {
            ProductScreen(
                onScanClick = {

                    navController.navigate(AppRoute.Scan.route) {
                        launchSingleTop = true
                    }
                },
                onProductClick = {
                    navController.navigate(AppRoute.Product.route) {
                        launchSingleTop = true
                    }
                },
                onHistoryClick = {
                    navController.navigate(AppRoute.History.route) {
                        launchSingleTop = true
                    }
                },
                onFavoritesClick = {
                    navController.navigate(AppRoute.Favorit.route) {
                        launchSingleTop = true
                    }
                },

                //currentProduct = currentProduct,
                currentProduct = mockProduct
            )
        }

        composable(AppRoute.History.route) {
            HistoryScreen(
                onScanClick = {

                    navController.navigate(AppRoute.Scan.route) {
                        launchSingleTop = true
                    }
                },
                onProductClick = {
                    navController.navigate(AppRoute.Product.route) {
                        launchSingleTop = true
                    }
                },
                onHistoryClick = {
                    navController.navigate(AppRoute.History.route) {
                        launchSingleTop = true
                    }
                },
                onFavoritesClick = {
                    navController.navigate(AppRoute.Favorit.route) {
                        launchSingleTop = true
                    }
                }

            )
        }

        composable(AppRoute.Favorit.route) {
            FavoriteScreen(
                onScanClick = {

                    navController.navigate(AppRoute.Scan.route) {
                        launchSingleTop = true
                    }
                },
                onProductClick = {
                    navController.navigate(AppRoute.Product.route) {
                        launchSingleTop = true
                    }
                },
                onHistoryClick = {
                    navController.navigate(AppRoute.History.route) {
                        launchSingleTop = true
                    }
                },
                onFavoritesClick = {
                    navController.navigate(AppRoute.Favorit.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

