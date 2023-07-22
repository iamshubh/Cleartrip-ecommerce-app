package com.cleartrip.ecommerce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleartrip.ecommerce.data.RepositoryImpl
import com.cleartrip.ecommerce.model.ProductApiResponse
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _products = MutableLiveData<ProductApiResponse>()
    val products: LiveData<ProductApiResponse> = _products

    val repo = RepositoryImpl()

    fun loadProds() {
        viewModelScope.launch {
            _products.postValue(repo.getAll())
        }
    }
}