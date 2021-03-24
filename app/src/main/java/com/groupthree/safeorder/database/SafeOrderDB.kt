package com.groupthree.safeorder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Restaurant::class, User::class, Product::class, CartItem::class), version = 2)
abstract class SafeOrderDB : RoomDatabase() {

    abstract fun restaurantDAO(): RestaurantDAO
    abstract fun userDAO(): UserDAO
    abstract fun productDAO(): ProductDAO
    abstract fun cartItemDAO() : CartItemDAO



    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE: SafeOrderDB? = null

        fun getDatabase(context: Context): SafeOrderDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SafeOrderDB::class.java,
                        "safeorder_database"
                    ).fallbackToDestructiveMigration() // leads to loss of all data in tables,
                        // if DB update required, new MigrationStrategy is needed!!
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

/*
       companion object {

        fun getDatabase(context: Context): SafeOrderDB {

            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SafeOrderDB::class.java,
                        "safeorder_database"
                    ).build()

                INSTANCE = instance

                // return instance
                instance
            }
        }
   }
*/
}

