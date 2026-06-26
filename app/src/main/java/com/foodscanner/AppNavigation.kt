package com.foodscanner

import android.content.Context
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.foodscanner.data.Product
import com.foodscanner.service.startBarcodeScanner
import com.foodscanner.ui.components.utility.VitalScanFooter
import com.foodscanner.ui.components.utility.VitalScanHeader
import com.foodscanner.ui.screens.FavoriteScreen
import com.foodscanner.ui.screens.HistoryScreen
import com.foodscanner.ui.screens.ProductScreen
import com.foodscanner.ui.screens.ScanScreen
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment


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

private sealed class AppRoute(
    val route: String,
    val index: Int
) {
    data object Scan : AppRoute("scan", 0)
    data object Product : AppRoute("product", 1)
    data object History : AppRoute("history", 2)
    data object Favorit : AppRoute("favorit", 3)
}

private fun getRouteIndex(route: String?): Int {
    return when (route) {
        AppRoute.Scan.route -> AppRoute.Scan.index
        AppRoute.Product.route -> AppRoute.Product.index
        AppRoute.History.route -> AppRoute.History.index
        AppRoute.Favorit.route -> AppRoute.Favorit.index
        else -> 0
    }
}

@Composable
fun StartApp(
    context: Context,
    viewModel: FoodScannerViewModel,
    controller: Controller
) {
    val TAG = "AppNavigation"
    val navController = rememberNavController()
    val currentProduct by viewModel.currentProduct.collectAsState()
    val currentHistoryState by viewModel.historyState.collectAsState()

    // mock Product for testing when scanner doesnt work
    var mockProduct: Product? = null
    var mockProduct2: Product? = null
    var mockhistorylist = emptyList<Product?>()

    LaunchedEffect(currentProduct) {

        mockProduct = controller.getProductFromBarcode("4001518117316")!!
        mockProduct2 = controller.getProductFromBarcode("3017624010701")!!
        mockhistorylist = listOf(mockProduct,mockProduct2)

        if (currentProduct != null) {
            navController.navigate(AppRoute.Product.route) {
                launchSingleTop = true
            }
        }
    }



    // For testing with mock Json:
//    val inputStream = context.assets.open("MockProduct.json")
//    val mockJsonString: String = inputStream?.bufferedReader().use { it?.readText() as String }
//    val mockJsonElement = Json.parseToJsonElement(mockJsonString)



    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = AppRoute.Scan.route,
            modifier = Modifier.fillMaxSize(),
            enterTransition = {
                val initialIndex = getRouteIndex(initialState.destination.route)
                val targetIndex = getRouteIndex(targetState.destination.route)

                val direction =
                    if (targetIndex > initialIndex) {
                        AnimatedContentTransitionScope.SlideDirection.Left
                    } else {
                        AnimatedContentTransitionScope.SlideDirection.Right
                    }

                slideIntoContainer(
                    towards = direction,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                val initialIndex = getRouteIndex(initialState.destination.route)
                val targetIndex = getRouteIndex(targetState.destination.route)

                val direction =
                    if (targetIndex > initialIndex) {
                        AnimatedContentTransitionScope.SlideDirection.Left
                    } else {
                        AnimatedContentTransitionScope.SlideDirection.Right
                    }

                slideOutOfContainer(
                    towards = direction,
                    animationSpec = tween(500)
                )
            },
            popEnterTransition = {
                val initialIndex = getRouteIndex(initialState.destination.route)
                val targetIndex = getRouteIndex(targetState.destination.route)

                val direction =
                    if (targetIndex > initialIndex) {
                        AnimatedContentTransitionScope.SlideDirection.Left
                    } else {
                        AnimatedContentTransitionScope.SlideDirection.Right
                    }

                slideIntoContainer(
                    towards = direction,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                val initialIndex = getRouteIndex(initialState.destination.route)
                val targetIndex = getRouteIndex(targetState.destination.route)

                val direction =
                    if (targetIndex > initialIndex) {
                        AnimatedContentTransitionScope.SlideDirection.Left
                    } else {
                        AnimatedContentTransitionScope.SlideDirection.Right
                    }

                slideOutOfContainer(
                    towards = direction,
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
                    currentProduct = currentProduct,
                    //currentProduct = mockProduct
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
                    },
                    currentHistoryState = currentHistoryState,
                    //currentHistoryState = mockhistorylist,
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

        VitalScanHeader(
            modifier = Modifier.align(Alignment.TopCenter)
        )

        VitalScanFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            onScanClick = {
                viewModel.clearCurrentProduct()

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

