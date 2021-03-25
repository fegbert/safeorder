package com.groupthree.safeorder

import android.app.Application
import com.groupthree.safeorder.database.CartItemRepository
import com.groupthree.safeorder.database.ProductRepository
import com.groupthree.safeorder.database.RestaurantRepository
import com.groupthree.safeorder.database.SafeOrderDB

class SafeOrderApplication : Application() {
    val safeOrderDB by lazy { SafeOrderDB.getDatabase(this) }
    val productRepository by lazy { ProductRepository(safeOrderDB.productDAO()) }
    val restaurantRepository by lazy { RestaurantRepository(safeOrderDB.restaurantDAO()) }
    val cartItem by lazy { CartItemRepository(safeOrderDB.cartItemDAO()) }
}