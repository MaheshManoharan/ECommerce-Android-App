package com.mahi.ecommerce_android_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mahi.ecommerce_android_app.screens.CartScreen.CartScreen
import com.mahi.ecommerce_android_app.screens.CartScreen.CartScreen
import com.mahi.ecommerce_android_app.screens.HomeScreen.HomeScreen
import com.mahi.ecommerce_android_app.screens.product_detail.ProductDetailsScreen
import com.mahi.ecommerce_android_app.viewmodels.HomeViewModel

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object ProductDetailsScreen: Screen("product_detail_screen")
    object CartScreen : Screen("cart_screen")
}


@Composable
fun MyApp(navHostController: NavHostController, viewModel: HomeViewModel) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {

        composable(Screen.HomeScreen.route) {
            HomeScreen(
                navController = navHostController,
                homeViewModel= viewModel
            )
        }
        composable(Screen.ProductDetailsScreen.route){
            ProductDetailsScreen(
                navHostController = navHostController
            )
        }

        composable(Screen.CartScreen.route){
            CartScreen(
                navHostController = navHostController
            )
        }
    }
}