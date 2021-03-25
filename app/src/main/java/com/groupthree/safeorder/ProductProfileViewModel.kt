package com.groupthree.safeorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.groupthree.safeorder.database.Product
import com.groupthree.safeorder.database.ProductRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ProductProfileViewModel(private val productRepository: ProductRepository) :ViewModel() {

    val allProducts : List<Product> = productRepository.allProducts

    fun insertProduct(product: Product) = viewModelScope.launch {
        productRepository.insertProduct(product)
    }

    fun getProduct(productID : Int) = productRepository.getProduct(productID)


}

class ProductProfileViewModelFactory(private val productRepository: ProductRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ProductProfileViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ProductProfileViewModel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}