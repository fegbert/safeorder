package com.groupthree.safeorder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var cartID : Int,

    @ColumnInfo
    var productID : Int,

    @ColumnInfo
    var productName : String,

    @ColumnInfo
    var productPrice : Int,

    @ColumnInfo
    var units : Int,

    var restaurantOfProductID : Int
)



