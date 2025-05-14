package com.mahi.ecommerce_android_app.screens.product_detail


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.mahi.ecommerce_android_app.navigation.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductDetailsScreen(navHostController: NavHostController) {

    Scaffold(
        topBar = {
            ProductDetailsTopAppBar(
                navHostController = navHostController
            )
        },
        content = {
            LazyColumn(Modifier.padding(it)){
                item {
                    ProductImageFavorite()
                }
                item {
                    ProductNameRating()
                }
            }
        },
        bottomBar = {
            AddToCartBottomButton(navHostController = navHostController)
        }

    )
}

@Composable
fun AddToCartBottomButton(navHostController: NavHostController) {
    Surface(
        tonalElevation = 4.dp,
        modifier = Modifier.clickable {
            navHostController.navigate(Screen.CartScreen.route)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.Red),
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
                    Text("Add To Cart",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }
}


@Composable
fun ProductNameRating() {
    Column(
        Modifier.padding(10.dp)
    ) {
        Text(text = "APPLE IPHONE 12 PRO (Pacific Blue, 128GB", style = TextStyle(fontWeight = FontWeight.Bold))
        ProductRatingText()
        PriceWithStrikedPriceText()
        Spacer(modifier = Modifier.height(12.dp))
        AboutProduct()
        Spacer(modifier = Modifier.height(12.dp))
        SimilarProductsRow()
    }

}

@Composable
fun SimilarProductsRow() {
    ElevatedCard(
        modifier = Modifier
            .height(350.dp)
            .fillMaxWidth()

    ) {
        val list = listOf(
            "https://i.ibb.co/DPc8cfs8/Screenshot-2025-05-14-at-11-56-55-AM.png",
            "https://i.ibb.co/pjcMBNYN/Screenshot-2025-05-14-at-12-26-11-PM.png",
            "https://i.ibb.co/ymm7chh2/Screenshot-2025-05-12-at-10-02-27-PM.png",
        )
        Row(modifier = Modifier.padding(8.dp)) {
            Column {
                Text("Similar Products", style = TextStyle(fontWeight = FontWeight.Bold,
                    color = Color.Black))
                LazyRow() {
                    items(list.size){
                        SimilarProduct(
                            imageUrl = list[it]
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SimilarProduct(imageUrl: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(150.dp)
            .padding(10.dp)) {
        Box(modifier = Modifier
            .width(100.dp)
            .height(250.dp)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "Remote Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), // Ensuring the image fills the available height
            )
//            Text(text = "150GB", style = TextStyle(color = Color.Red,
//                fontWeight = FontWeight.Bold, fontSize = 18.sp), modifier = Modifier
//                .offset(x = 80.dp, y = 180.dp)
//                .background(Color.White))
        }
        Text(text = "Samsung Galaxy S24", style = TextStyle(fontSize = 10.sp))
        Spacer(modifier = Modifier.height(6.dp))
        Text("$ 1000.0",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Red))
    }
}

@Composable
fun AboutProduct() {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "About Products",
                modifier = Modifier.align(Alignment.Start),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp))
            Text("View Details", style= TextStyle(color = Color.Red, fontWeight = FontWeight.Bold),modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}

@Composable
fun PriceWithStrikedPriceText() {
    Row {
        Text(text = "$ 999.00", style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "$ 1000.0", style = TextStyle(textDecoration = TextDecoration.LineThrough,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray))
    }
}


@Composable
fun ProductRatingText() {
    Box(
        modifier = Modifier
            .padding(6.dp)
            .width(50.dp)
            .height(25.dp)
            .background(
                Color.Magenta,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
            ) // Rounded box
            .padding(6.dp) // Padding inside the box
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically // Center text and icon vertically
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "Star Icon",
                tint = Color.Yellow.copy(alpha = 0.8f), // Star color
                modifier = Modifier.size(18.dp) // Set the size of the star
            )

            Spacer(modifier = Modifier.width(2.dp)) // Space between the icon and text

            Text(
                text = "4.0",
                style = TextStyle(fontSize = 12.sp, color = Color.Black)
            )
        }
    }
}

@Composable
fun ProductImageFavorite() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Ensure Box has a height to accommodate the image and other items
    ) {
// Full-width Image
        AsyncImage(
            model = "https://i.ibb.co/0j6QrG4t/Screenshot-2025-05-14-at-12-24-00-PM.png",
            contentDescription = "Remote Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(), // Ensuring the image fills the available height
        )

// Icon at the top-right corner
        IconButton(
            onClick = { /* Handle icon click */ },
            modifier = Modifier
                .align(Alignment.TopEnd) // Align the icon to the top-right corner
                .padding(16.dp) // Padding for the icon's position
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite, // Icon of your choice
                contentDescription = "Favorite Icon",
                tint = Color.LightGray, // Set the icon color here
                modifier = Modifier.size(32.dp)
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsTopAppBar(navHostController: NavHostController) {
    TopAppBar(
        title = {
            Text(text = "Product Details")
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
        actions = {
            // CartIconWithCount(cartItemCount = 5)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Red,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
        )
    )
}


