package com.mahi.ecommerce_android_app.screens.CartScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBarComposable(
                navHostController = navHostController
            )
        },
        content = {
            LazyColumn(
                Modifier.padding(paddingValues = it)
                    .background(color = Color.White)
            ) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White // This sets the background color to white
                        ),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column {
                            Row(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text(text = "APPLE iPHONE 16 Pro (Pacific Blue, 128GB",
                                    maxLines = 2,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.7f),
                                )
                                AsyncImage(
                                    model = "https://i.ibb.co/0j6QrG4t/Screenshot-2025-05-14-at-12-24-00-PM.png",
                                    // Replace with your image URL
                                    contentDescription = "Remote Image",
                                    modifier = Modifier
                                        .weight(0.3f)
                                        .height(200.dp),
                                    placeholder = coil.compose.rememberImagePainter("https://example.com/placeholder.jpg"), // Optional placeholder
                                    error = coil.compose.rememberImagePainter("https://example.com/error.jpg") // Optional error image
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text(text = "$999.11",
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(0.4f))
                                IconButton(
                                    modifier = Modifier.weight(0.1f),
                                    content = {
                                        Icon(imageVector = Icons.Filled.Delete,
                                            contentDescription = "Delete")
                                    },
                                    onClick = { /*TODO*/ })
                                Spacer(modifier = Modifier.weight(0.1f))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(0.3f)
                                ) {
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(imageVector = Icons.Filled.Add,
                                            contentDescription = "Add item")
                                    }
                                    Text(text = "1")
                                    IconButton(onClick = { /*TODO*/ }) {
                                        Icon(imageVector = Icons.Filled.Close,
                                            contentDescription = "substract item")
                                    }
                                }
                            }
                        }

                    }
                }
            }
        },
        bottomBar = {
            BottomBarWidget()
        })

}

@Composable
fun BottomBarWidget() {
    Surface(
        tonalElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp).background(Color.White)

            ,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("TOTAL", fontSize = 12.sp, color = Color.Black)
                    Text(text = "OMR 100.00",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp)
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(
                        color = Color.Red
                    )
                    .clickable {},
                contentAlignment = Alignment.Center,
            ) {
                Box {
                    Text("CHECKOUT",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComposable(navHostController: NavHostController) {
    TopAppBar(
        title = {
            Text(text = "My Cart (1)")
        },
        navigationIcon = {
            IconButton(onClick = {
                navHostController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
        )
    )
}