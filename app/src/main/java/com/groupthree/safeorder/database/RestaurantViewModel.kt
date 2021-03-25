package com.groupthree.safeorder.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class RestaurantViewModel(private val restaurantRepo : RestaurantRepository) : ViewModel() {
    var allRestaurants : List<Restaurant> = restaurantRepo.allRestaurants
    var allRestaurantsWithProducts : List<RestaurantWithProducts> = restaurantRepo.allRestaurantsWithProducts

    fun getRestaurantWithProductsByID(restaurantID : Int) : RestaurantWithProducts? {
        for (r : RestaurantWithProducts in allRestaurantsWithProducts) {
            if (r.restaurant.restaurantID == restaurantID) {
                return r
            }
        }
        return null
    }

    fun getRestaurantByID(restaurantID : Int) : Restaurant? {
        for (r: Restaurant in allRestaurants) {
            if (r.restaurantID == restaurantID) {
                return r
            }
        }
        return null
    }

    fun insertRestaurant(restaurant : Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepo.insertRestaurant(restaurant)
        }
    }
}

class RestaurantViewModelFactory(private val dataSource : RestaurantRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
            return RestaurantViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}