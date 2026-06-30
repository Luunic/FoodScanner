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
import com.foodscanner.ui.screens.BarcodeScannerScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import com.foodscanner.ui.screens.SettingsScreen
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import com.foodscanner.storage.UserSettingsStorage
import kotlinx.coroutines.launch
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput

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
    data object Scanner : AppRoute("scanner", 4)
    data object Settings : AppRoute("settings", 5)
}

 // main Nav Routes
private fun getRouteIndex(route: String?): Int {
    return when (route) {
        AppRoute.Scan.route -> AppRoute.Scan.index
        AppRoute.Product.route -> AppRoute.Product.index
        AppRoute.History.route -> AppRoute.History.index
        AppRoute.Favorit.route -> AppRoute.Favorit.index
        AppRoute.Scanner.route -> AppRoute.Scanner.index
        AppRoute.Settings.route -> AppRoute.Settings.index
        else -> 0
    }
}

// Swipeable Footer Routes
private fun isSwipeableFooterRoute(route: String?): Boolean {
    return route == AppRoute.Scan.route ||
            route == AppRoute.Product.route ||
            route == AppRoute.History.route ||
            route == AppRoute.Favorit.route
}

// Footer Routes
private fun getFooterRouteByIndex(index: Int): String {
    return when (index) {
        0 -> AppRoute.Scan.route
        1 -> AppRoute.Product.route
        2 -> AppRoute.History.route
        3 -> AppRoute.Favorit.route
        else -> AppRoute.Scan.route
    }
}

@Composable
fun StartApp(
    context: Context,
    viewModel: FoodScannerViewModel,
    controller: Controller
) {

    //initialize Controller/Routes/Product/History/etc
    val TAG = "AppNavigation"
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: AppRoute.Scan.route
    val currentProduct by viewModel.currentProduct.collectAsState()
    val currentHistoryState by viewModel.historyState.collectAsState()
    val lastScannedProduct = currentHistoryState.firstOrNull()

    //Set Username
    val userSettingsStorage = remember { UserSettingsStorage(context) }
    val coroutineScope = rememberCoroutineScope()
    val savedUsername by userSettingsStorage.username.collectAsState(initial = "")
    val savedAllergens by userSettingsStorage.allergens.collectAsState(
        initial = emptyList()
    )
    val displayedUsername = savedUsername.ifBlank {
        stringResource(R.string.default_username)
    }



    // mock Product for testing when scanner doesnt work
    var mockProduct: Product? = null
    var mockProduct2: Product? = null
    var mockhistorylist = emptyList<Product?>()

    LaunchedEffect(Unit) {
        viewModel.clearCurrentProduct()
    }

//    LaunchedEffect(currentProduct) {
//
//        mockProduct = controller.getProductFromBarcode("4001518117316")!!
//        mockProduct2 = controller.getProductFromBarcode("3017624010701")!!
//        mockhistorylist = listOf(mockProduct,mockProduct2)
//
//    }

    //checks if transition to/from settings page
    fun isFadeTransition(
        fromRoute: String?,
        toRoute: String?
    ): Boolean {
        return fromRoute == AppRoute.Settings.route ||
                toRoute == AppRoute.Settings.route ||
                fromRoute == AppRoute.Scanner.route ||
                toRoute == AppRoute.Scanner.route
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(currentRoute) {
                var totalDragX = 0f

                detectHorizontalDragGestures(
                    onDragStart = {
                        totalDragX = 0f
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        totalDragX += dragAmount
                        change.consume()
                    },
                    onDragEnd = {
                        if (!isSwipeableFooterRoute(currentRoute)) {
                            return@detectHorizontalDragGestures
                        }

                        val swipeThreshold = 120f
                        val currentIndex = getRouteIndex(currentRoute)

                        when {
                            //swipe left (1->2)
                            totalDragX < -swipeThreshold -> {
                                val nextIndex = (currentIndex + 1).coerceAtMost(3)
                                val nextRoute = getFooterRouteByIndex(nextIndex)

                                if (nextRoute != currentRoute) {
                                    navController.navigate(nextRoute) {
                                        launchSingleTop = true
                                    }
                                }
                            }

                            //swipe right (2->1)
                            totalDragX > swipeThreshold -> {
                                val previousIndex = (currentIndex - 1).coerceAtLeast(0)
                                val previousRoute = getFooterRouteByIndex(previousIndex)

                                if (previousRoute != currentRoute) {
                                    navController.navigate(previousRoute) {
                                        launchSingleTop = true
                                    }
                                }
                            }
                        }
                    }
                )
            }
    ) {

        NavHost(
            navController = navController,
            startDestination = AppRoute.Scan.route,
            modifier = Modifier.fillMaxSize(),

            enterTransition = {
                val fromRoute = initialState.destination.route
                val toRoute = targetState.destination.route

                if (isFadeTransition(fromRoute, toRoute)) {
                    fadeIn(animationSpec = tween(300))
                } else {
                    val initialIndex = getRouteIndex(fromRoute)
                    val targetIndex = getRouteIndex(toRoute)

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
                }
            },

            exitTransition = {
                val fromRoute = initialState.destination.route
                val toRoute = targetState.destination.route

                if (isFadeTransition(fromRoute, toRoute)) {
                    fadeOut(animationSpec = tween(300))
                } else {
                    val initialIndex = getRouteIndex(fromRoute)
                    val targetIndex = getRouteIndex(toRoute)

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
            },

            popEnterTransition = {
                val fromRoute = initialState.destination.route
                val toRoute = targetState.destination.route

                if (isFadeTransition(fromRoute, toRoute)) {
                    fadeIn(animationSpec = tween(300))
                } else {
                    val initialIndex = getRouteIndex(fromRoute)
                    val targetIndex = getRouteIndex(toRoute)

                    val direction =
                        if (targetIndex > initialIndex) {
                            AnimatedContentTransitionScope.SlideDirection.Left
                        } else {
                            AnimatedContentTransitionScope.SlideDirection.Right
                        }

                    slideIntoContainer(
                        towards = direction,
                        animationSpec = tween(200)
                    )
                }
            },

            popExitTransition = {
                val fromRoute = initialState.destination.route
                val toRoute = targetState.destination.route

                if (isFadeTransition(fromRoute, toRoute)) {
                    fadeOut(animationSpec = tween(300))
                } else {
                    val initialIndex = getRouteIndex(fromRoute)
                    val targetIndex = getRouteIndex(toRoute)

                    val direction =
                        if (targetIndex > initialIndex) {
                            AnimatedContentTransitionScope.SlideDirection.Left
                        } else {
                            AnimatedContentTransitionScope.SlideDirection.Right
                        }

                    slideOutOfContainer(
                        towards = direction,
                        animationSpec = tween(200)
                    )
                }
            }
        )


        {

            composable(AppRoute.Scan.route) {
                ScanScreen(
                    username = displayedUsername,
                    onScanRequested = {

//                        startBarcodeScanner(context, viewModel)

                        navController.navigate(AppRoute.Scanner.route) {
                            launchSingleTop = true
                        }

                    },
                    onLastScannedCLick = { product ->
                        viewModel.setCurrentProduct(product)

                        navController.navigate(AppRoute.Product.route) {
                            launchSingleTop = true
                        }
                    },
                    lastScannedProduct = lastScannedProduct
                )
            }

            composable(AppRoute.Product.route) {
                ProductScreen(
                    currentProduct = currentProduct,
                    selectedAllergens = savedAllergens,
                    onGoToScanPageClick = {
                        navController.navigate(AppRoute.Scan.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(AppRoute.History.route) {
                HistoryScreen(
                    currentHistoryState = currentHistoryState,
                    //currentHistoryState = mockhistorylist,
                    onHistoryProductClick = { product ->
                        viewModel.setCurrentProduct(product)

                        navController.navigate(AppRoute.Product.route) {
                            launchSingleTop = true
                        }
                    },
                    onClearHistoryClick = {
                        viewModel.clearHistory()
                    },
                    onDeleteHistoryProductClick = { product ->
                        viewModel.deleteHistoryProduct(product)
                    },
                )
            }

            composable(AppRoute.Favorit.route) {
                FavoriteScreen(

                )
            }

            composable(AppRoute.Scanner.route) {
                BarcodeScannerScreen(
                    viewModel = viewModel,
                    onBarcodeScanned = {
                        navController.navigate(AppRoute.Product.route) {
                            popUpTo(AppRoute.Scanner.route) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable(AppRoute.Settings.route) {
                SettingsScreen(
                    username = savedUsername,
                    selectedAllergens = savedAllergens,
                    onUsernameChange = { newName ->
                        coroutineScope.launch {
                            userSettingsStorage.saveUsername(newName)
                        }
                    },
                    onAllergensChange = { newAllergens ->
                        coroutineScope.launch {
                            userSettingsStorage.saveAllergens(newAllergens)
                        }
                    }
                )
            }
        }




        VitalScanHeader(
            modifier = Modifier.align(Alignment.TopCenter),
            onSettingsClick = {
                navController.navigate(AppRoute.Settings.route) {
                    launchSingleTop = true
                    restoreState = false
                }
            }
        )

        VitalScanFooter(
            modifier = Modifier.align(Alignment.BottomCenter),
            currentRoute = currentRoute,
            onScanClick = {
                if (currentRoute != AppRoute.Scan.route) {
                    navController.navigate(AppRoute.Scan.route) {
                        popUpTo(AppRoute.Settings.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            },
            onProductClick = {
                if (currentRoute != AppRoute.Product.route) {
                    navController.navigate(AppRoute.Product.route) {
                        popUpTo(AppRoute.Settings.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            },
            onHistoryClick = {
                if (currentRoute != AppRoute.History.route) {
                    navController.navigate(AppRoute.History.route) {
                        popUpTo(AppRoute.Settings.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            },
            onFavoritesClick = {
                if (currentRoute != AppRoute.Favorit.route) {
                    navController.navigate(AppRoute.Favorit.route) {
                        popUpTo(AppRoute.Settings.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            }
        )
    }
}