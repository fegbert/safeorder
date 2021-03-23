package com.groupthree.safeorder.database

data class Address(
    val street : String,
    val number : Int,
    val zip : Int,
    val city : String,
    val coordinates : String
)
