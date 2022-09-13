package com.carbon.smartflow.data.network

import javax.inject.Inject

class Repository @Inject constructor(private val service: RestService) {

    suspend fun getProducts() = service.getProducts()

}