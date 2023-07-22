package com.cleartrip.ecommerce.data

import com.cleartrip.ecommerce.model.ProductApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
    fun loadProds(): ProductApiResponse
}