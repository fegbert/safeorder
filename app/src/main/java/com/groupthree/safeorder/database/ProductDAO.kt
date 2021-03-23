package com.groupthree.safeorder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ProductDAO {

    @Insert
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM Product ORDER BY productID DESC")
    fun getAllProducts(): List<Product>

    @Query("SELECT * FROM Product WHERE :productID = productID")
    fun getProduct(productID : Int) : Product


}