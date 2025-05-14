package com.mahi.ecommerce_android_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mahi.ecommerce_android_app.navigation.MyApp
import com.mahi.ecommerce_android_app.screens.HomeScreen.HomeScreen
import com.mahi.ecommerce_android_app.ui.theme.ECommerceAndroidAppTheme
import com.mahi.ecommerce_android_app.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ECommerceAndroidAppTheme {
                val navController = rememberNavController()

                MyApp(navHostController = navController, viewModel = homeViewModel)
            }
        }
    }
}
