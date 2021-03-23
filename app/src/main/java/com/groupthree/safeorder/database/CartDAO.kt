package com.groupthree.safeorder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {

    @Insert
    fun insert(cartItem: CartItem)

    @Query("SELECT * FROM CartItem")
    fun getAllCartItems() : Flow<List<CartItem>>



}