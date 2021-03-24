package com.groupthree.safeorder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    var cartID : Int = 0,

    @ColumnInfo
    var productID : Int,

    @ColumnInfo
    var units : Int
){



}

/*

@Entity(tableName = "notes_table")
data class Note (var item: String,
        @ColumnInfo(name = "priority")
        var priority: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    //.....
}

 */