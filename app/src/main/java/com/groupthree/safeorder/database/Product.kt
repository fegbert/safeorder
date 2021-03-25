package com.groupthree.safeorder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(

    @PrimaryKey
    var productID : Int,

    @ColumnInfo
    var productName : String,

    @ColumnInfo
    var productPrice : String,

    @ColumnInfo
    var productDescription : String,

    @ColumnInfo
    var restaurantOfProductID: Int
)