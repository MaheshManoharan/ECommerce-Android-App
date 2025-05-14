package com.mahi.ecommerce_android_app.model

data class ProductDetails(
    val image_url: String,
    val name: String,
    val old_price: String,
    val price: String,
    val product_details_note: String,
    val similar_products: List<Product>
)
