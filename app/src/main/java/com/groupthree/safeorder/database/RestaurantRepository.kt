package com.groupthree.safeorder.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.coroutineScope


class RestaurantRepository(private val restaurantDAO: RestaurantDAO) {

    val allRestaurants : List<Restaurant> = restaurantDAO.getAllRestaurants()
    val allRestaurantsWithProducts : List<RestaurantWithProducts> = restaurantDAO.getRestaurantsWithProducts()

    @WorkerThread
    suspend fun insertRestaurant(restaurant: Restaurant) = restaurantDAO.insertRestaurant(restaurant)

    @WorkerThread
    suspend fun clearAll() = restaurantDAO.clearAll()


}