package com.groupthree.safeorder.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemDAO {

    @Insert
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cartItem")
    fun getAllCartItems() : Flow<List<CartItem>>

    @Query("DELETE FROM cartItem")
    suspend fun clearAll()

}