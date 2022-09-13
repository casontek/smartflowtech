package com.carbon.smartflow.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val brand: String,
    val name: String,
    val price: String,
    val price_sign: String,
    val currency: String,
    val image_link: String,
    val website_link: String,
    val description: String,
    val category: String,
    val product_type: String,
    val created_at: String,
    val updated_at: String,
    val product_api_url: String,
    val api_featured_image: String,
    val product_colors: List<ProductColor>,
    var brandType: String = ""
) : Parcelable
