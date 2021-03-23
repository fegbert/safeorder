package com.groupthree.safeorder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert
    fun registerUser(user: User)

    @Update
    fun updateUserInfo(user: User)


    @Query("SELECT * FROM user WHERE userMail = (:mail ) AND userPassword = (:password)")
    fun loginUser(mail : String, password : String) : User



}