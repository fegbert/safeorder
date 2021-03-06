package com.groupthree.safeorder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RestaurantDAO {

    @Insert
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Query("SELECT * FROM Restaurant ORDER BY restaurantID DESC")
    fun getAllRestaurants() : List<Restaurant>

    @Transaction
    @Query("SELECT * FROM Restaurant")
    fun getRestaurantsWithProducts() : List<RestaurantWithProducts>

    @Query("DELETE FROM Restaurant")
    suspend fun clearAll()

    /*
    @Transaction
    @Query("SELECT * FROM Restaurant WHERE (:restaurantID) = restaurantID")
    fun getProductsOfOneRestaurant(restaurantID : Int): List<RestaurantWithProducts>
    */

}