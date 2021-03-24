package com.groupthree.safeorder.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class RestaurantViewModel(val database : RestaurantDAO, application: Application) : AndroidViewModel(application) {
    private var restaurants = MutableLiveData<List<Restaurant>?>()
    private var restaurantsWithProducts = MutableLiveData<List<RestaurantWithProducts>?>()

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch() {
            restaurants.value = getRestaurantsFromDatabase()
            restaurantsWithProducts.value = getRestaurantsWithProductsFromDatabase()
        }
    }

    private fun getRestaurantsFromDatabase() : List<Restaurant>?{
        var restaurants : List<Restaurant>? = null
        viewModelScope.launch(Dispatchers.IO) {
            restaurants = database.getAllRestaurants()
        }
        return restaurants
    }

    private fun getRestaurantsWithProductsFromDatabase() : List<RestaurantWithProducts>? {
        var restaurants : List<RestaurantWithProducts>? = null
        viewModelScope.launch(Dispatchers.IO) {
            restaurants = database.getRestaurantsWithProducts()
        }
        return restaurants
    }

    fun getRestaurants() : List<Restaurant>?{
        return restaurants.value
    }

    fun getRestaurantsWithProducts() : List<RestaurantWithProducts>? {
        return restaurantsWithProducts.value
    }

    fun getRestaurantByID(restaurantID : Int) : Restaurant? {
        val res = restaurants.value
        if (res != null) {
            for (r: Restaurant in res) {
                if (r.restaurantID == restaurantID) {
                    return r
                }
            }
        }
        return null
    }

    fun getRestaurantWithProductsByID(restaurantID : Int) : RestaurantWithProducts? {
        val res = restaurantsWithProducts.value
        if (res != null) {
            for (r : RestaurantWithProducts in res) {
                if (r.restaurant.restaurantID == restaurantID) {
                    return r
                }
            }
        }
        return null
    }

    fun insertRestaurant(restaurant : Restaurant) {
        viewModelScope.launch(Dispatchers.IO) {
            database.insertRestaurant(restaurant)
        }
    }
}

class RestaurantViewModelFactory(private val dataSource : RestaurantDAO, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurantViewModel::class.java)) {
            return RestaurantViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}