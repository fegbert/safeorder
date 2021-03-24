package com.groupthree.safeorder.database

class UserRepository(private val userDAO: UserDAO) {

    val allUsers : List<User> = userDAO.getAllUsers()

    suspend fun insertUser(user: User) = userDAO.registerUser(user)

}