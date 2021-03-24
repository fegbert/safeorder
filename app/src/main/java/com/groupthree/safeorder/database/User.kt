package com.groupthree.safeorder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.net.PasswordAuthentication

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var userID : Int = 0,

    @ColumnInfo
    var userFirstName : String,

    @ColumnInfo
    var userLastName : String,

    @ColumnInfo
    var userMail : String,

    @ColumnInfo
    var userPassword : String,

)
