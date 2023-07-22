package com.cleartrip.ecommerce.data

import com.cleartrip.ecommerce.model.ProductApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface Repository {
    suspend fun getAll(): ProductApiResponse
}

class RepositoryImpl : Repository {
    override suspend fun getAll(): ProductApiResponse =
        withContext(Dispatchers.IO) {
            NetworkClient.getService().loadProds()
        }
}