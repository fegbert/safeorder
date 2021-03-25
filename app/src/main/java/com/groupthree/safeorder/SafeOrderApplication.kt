package com.groupthree.safeorder

import android.app.Application
import com.groupthree.safeorder.database.CartItemRepository
import com.groupthree.safeorder.database.ProductRepository
import com.groupthree.safeorder.database.RestaurantRepository
import com.groupthree.safeorder.database.SafeOrderDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SafeOrderApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val safeOrderDB by lazy { SafeOrderDB.getDatabase(this, applicationScope) }
    val productRepository by lazy { ProductRepository(safeOrderDB.productDAO()) }
    val restaurantRepository by lazy { RestaurantRepository(safeOrderDB.restaurantDAO()) }
    val cartItemRepository by lazy { CartItemRepository(safeOrderDB.cartItemDAO()) }
}