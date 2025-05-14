package com.mahi.ecommerce_android_app.screens.HomeScreen


import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mahi.ecommerce_android_app.R
import com.mahi.ecommerce_android_app.model.HomePageItems
import com.mahi.ecommerce_android_app.model.NetworkResult
import com.mahi.ecommerce_android_app.model.Product
import com.mahi.ecommerce_android_app.navigation.Screen
import com.mahi.ecommerce_android_app.viewmodels.HomeViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {

    val homeState by homeViewModel.homePageState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        homeViewModel.fetchHomePageItems()
    }

    when (homeState) {
        is NetworkResult.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is NetworkResult.Success -> {
            HomeContent(homeState = homeState, navController = navController)
        }

        else -> {
            val errorMsg = (homeState as NetworkResult.Error).message
            // Show an error message
            Text("Error: $errorMsg")
        }

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(homeState: NetworkResult<HomePageItems>, navController: NavHostController) {

    val columns = 2
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehavior,
                navController = navController
            )
        },
        content = {
            LazyColumn(modifier = Modifier.padding(paddingValues = it)
            )
            {
                item {
                    BannerImage(bannerImage = homeState.data!!.banner_image2)
                }
                item {
                    ViewAllMobiles()
                }

                item {
                    VerticalGrid(columns = columns,items = homeState.data!!.products, navController = navController)
                }
                item {
                    BannerImage(bannerImage = homeState.data!!.banner_image1)
                }
            }
        },
        bottomBar = {
            BottomBar()
        }
    )
}



@Composable
fun VerticalGrid(columns: Int, items: List<Product>, navController: NavHostController) {
    LazyVerticalGrid(
        userScrollEnabled = false,
        columns = GridCells.Fixed(columns),
        modifier = Modifier
            .fillMaxWidth()
            .height(550.dp)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        //  horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items) { item ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.ProductDetailsScreen.route)
                }
            ) {
                Box(modifier = Modifier.fillMaxSize()){
                    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                        .data(item.image_url)
                        .crossfade(true)
                        .build(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentDescription = "image")

                    if(item.memory.length>1){
                        Text(text = item.memory, style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold),
                            modifier = Modifier.offset(x = 110.dp, y = 150.dp).background(color = Color.White))

                    }


                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.name,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.price,
                    style = TextStyle(color = Color.Red,
                        fontWeight = FontWeight.Bold))
            }
        }
    }
}

@Composable
fun ViewAllMobiles() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Mobile Phones", style= TextStyle(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.width(30.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "VIEW ALL")
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BannerImage(bannerImage: String) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(bannerImage)
        .crossfade(true)
        .build(),
        contentDescription = "image",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(scrollBehavior: TopAppBarScrollBehavior, navController: NavHostController) {

    val context = LocalContext.current

    CenterAlignedTopAppBar(title = {
        Text(text = context.getString(R.string.app_name), style = TextStyle(Color.White, fontSize = 26.sp))
    },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Red),
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Hamburger Menu Icon", tint = Color.White,)
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.CartScreen.route)
            }) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Mode Icon", tint = Color.White)
            }
        }
    )
}


@Composable
fun BottomBar() {
    val items = listOf(
        BottomBarItem("Home", Icons.Default.Home),
        BottomBarItem("Search", Icons.Default.Search),
        BottomBarItem("Profile", Icons.Default.Person),
        BottomBarItem("Settings", Icons.Default.Settings)
    )

    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)
                ),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = item.label,
                            tint = if (isSelected) Color.Red else MaterialTheme.colorScheme.onSurface)
                        Text(text = item.label, style = MaterialTheme.typography.bodySmall,
                            color = if (isSelected) Color.Red
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = if (isSelected) 14.sp else 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                },
                selected = selectedItem == index,
                onClick = { selectedItem = index },

                )
        }
    }
}


data class BottomBarItem(val label: String, val icon: ImageVector)
