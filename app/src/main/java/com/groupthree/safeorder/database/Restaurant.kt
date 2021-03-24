package com.groupthree.safeorder.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Restaurant(

    @PrimaryKey(autoGenerate = true)
    var restaurantID : Int = 0,

    @ColumnInfo
    var restaurantName : String,

    @Embedded
    var address: Address
)

