package com.groupthree.safeorder.database

import androidx.annotation.WorkerThread


class RestaurantRepository(private val restaurantDAO: RestaurantDAO) {

    val allRestaurants : List<Restaurant> = restaurantDAO.getAllRestaurants()

    @WorkerThread
    suspend fun insertRestaurant(restaurant: Restaurant) = restaurantDAO.insertRestaurant(restaurant)

    @WorkerThread
    suspend fun clearAll() = restaurantDAO.clearAll()


}