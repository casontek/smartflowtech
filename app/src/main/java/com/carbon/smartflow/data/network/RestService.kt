package com.carbon.smartflow.data.network

import com.carbon.smartflow.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface RestService {
    @GET("/api/v1/products.json")
    suspend fun getProducts() :
            Response<List<Product>>
}