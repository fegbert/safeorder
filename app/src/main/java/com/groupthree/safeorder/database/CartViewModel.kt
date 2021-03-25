package com.groupthree.safeorder.database

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CartViewModel(private val cartItemRepository: CartItemRepository) : ViewModel() {

    val allCartItems: List<CartItem> = cartItemRepository.allCartItems

    fun insertCartItem(cartItem: CartItem) = viewModelScope.launch {
        cartItemRepository.insertCartItem(cartItem)
    }

    fun clearCart() = viewModelScope.launch {
        cartItemRepository.clearAll()
    }
}

class CartViewModelFactory(private val cartItemRepository: CartItemRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(cartItemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }


}

