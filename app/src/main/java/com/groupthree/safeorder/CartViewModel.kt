package com.groupthree.safeorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.groupthree.safeorder.database.CartItem
import com.groupthree.safeorder.database.CartItemRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class CartViewModel(private val cartItemRepository: CartItemRepository) : ViewModel() {

    // val allCartItems : LiveData<List<CartItem>> = cartItemRepository.allCartItems.asLiveData() //WHY??

    fun insertCartItem(cartItem: CartItem) = viewModelScope.launch {
        cartItemRepository.insertCartItem(cartItem)
    }

    fun clearCart() = cartItemRepository.clearCart()
}

class CartViewModelFactory(private val cartItemRepository: CartItemRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(cartItemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }


}

