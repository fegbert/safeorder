package com.groupthree.safeorder.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class UserViewModel(val database : UserDAO, application : Application) : AndroidViewModel(application) {
    private var users = MutableLiveData<List<User>?>()

    init {
        initialize()
    }

    private fun initialize() {
        viewModelScope.launch {
            users.value = getUsersFromDatabase()
        }
    }

    private fun getUsersFromDatabase() : List<User>? {
        var users : List<User>? = null
        viewModelScope.launch(Dispatchers.IO) {
            users = database.getAllUsers()
        }
        return users
    }

    fun getUsers() : List<User>? {
        return users.value
    }

    fun getUserByID(userID : Int) : User? {
        if (users.value != null) {
            for (u : User in users.value!!) {
                if (u.userID == userID) {
                    return u
                }
            }
        }
        return null
    }
}

class UserViewModelFactory(private val dataSource : UserDAO, private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}