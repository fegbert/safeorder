package com.groupthree.safeorder.database

import androidx.room.Embedded
import androidx.room.Relation


data class RestaurantWithProducts(
    @Embedded
    var restaurant: Restaurant,

    @Relation(parentColumn = "restaurantID", entityColumn = "restaurantOfProductID")
    var products : List<Product>
)
