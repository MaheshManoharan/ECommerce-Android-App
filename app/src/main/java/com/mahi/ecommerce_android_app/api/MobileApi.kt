package com.mahi.ecommerce_android_app.api


import com.mahi.ecommerce_android_app.model.HomePageItems
import com.mahi.ecommerce_android_app.model.ProductDetails
import retrofit2.Response
import retrofit2.http.GET


interface MobileApi {

    //https://run.mocky.io/v3/9e0d828f-ec3b-428c-993e-8534a8a36a6c

    @GET("9e0d828f-ec3b-428c-993e-8534a8a36a6c")
    suspend fun getHomePageItems(): Response<HomePageItems>

    @GET("")
    suspend fun getProductDetails(): Response<ProductDetails>


}