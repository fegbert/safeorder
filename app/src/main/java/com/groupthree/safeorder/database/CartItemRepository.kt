package com.groupthree.safeorder.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class CartItemRepository(private val cartItemDAO: CartItemDAO) {

    val allCartItems : List<CartItem> = cartItemDAO.getAllCartItems()

    @WorkerThread
    suspend fun insertCartItem(cartItem: CartItem) = cartItemDAO.insertCartItem(cartItem)

    @WorkerThread
    suspend fun clearAll() = cartItemDAO.clearAll()
}