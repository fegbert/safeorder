package com.groupthree.safeorder.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Restaurant::class, User::class, Product::class, CartItem::class], version = 4)
abstract class SafeOrderDB : RoomDatabase() {

    abstract fun restaurantDAO(): RestaurantDAO
    abstract fun userDAO(): UserDAO
    abstract fun productDAO(): ProductDAO
    abstract fun cartItemDAO(): CartItemDAO


    private class SafeOrderDbCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    val restaurantDAO = database.restaurantDAO()
                    val userDAO = database.userDAO()
                    val productDAO = database.productDAO()
                    val cartItemDAO = database.cartItemDAO()


                    // Delete All Content

                    restaurantDAO.clearAll()
                    productDAO.clearAll()
                    cartItemDAO.clearAll()


                    // Insert Restaurants

                    restaurantDAO.insertRestaurant(Restaurant(1,"Pizzeria Ciao Salvatore",
                            Address("Deutz-Kalker Straße", 122, 50679, "Köln", "50.936567057303186;6.989473725262799")))

                    restaurantDAO.insertRestaurant(Restaurant(2,"Deutzer Asia Imbiss",
                            Address("Gotenring", 37, 50679, "Köln", "50.93350071818949;6.977288982934565")))

                    restaurantDAO.insertRestaurant(Restaurant(3,"Restaurant Oasis",
                            Address("Kennedy-Ufer", 1, 50679, "Köln", "50.93761069391004;6.96909359827842")))

                    restaurantDAO.insertRestaurant(Restaurant(4,"Saman Restaurant",
                            Address("Deutz-Kalker Straße", 142, 50679, "Köln", "50.936567273954175;6.992100398278367")))


                    // Insert Products

                    // Pizzeria Ciao Salvatore

                    productDAO.insertProduct(Product(1, "Pizza Margherita", "4.50",
                            "Typisch neapolitanische Pizza.", 1))

                    productDAO.insertProduct(Product(2, "Pizza Tonno", "6.50",
                            "Pizza mit Tomatensauße, Mozarella, Zwiebeln und Thunfisch.", 1))

                    productDAO.insertProduct(Product(3, "Pizza Funghi", "65.0",
                            "Pizza mit Champignons und Mischung anderer Pilze.", 1))

                    productDAO.insertProduct(Product(4, "Fanta", "2.50",
                            "Kohlensäurehaltiges Süßgetränk mit Orangengeschmack.", 1))



                    // Deutzer Asia Imbiss

                    productDAO.insertProduct(Product(5, "Gebratener Reis", "5.90",
                            "Mit verschiedenem Gemüse.", 2))

                    productDAO.insertProduct(Product(6, "Pekingsuppe", "2.50",
                            "Scharf.", 2))

                    productDAO.insertProduct(Product(7, "Hühnerfleisch Chop Suey", "5.90",
                            "Mit verschiedenem Gemüse.", 2))

                    productDAO.insertProduct(Product(8, "Jasmin Tee", "2.50",
                            "Durch Vermischung mit Jasminblüten aromatisierter Tee.", 2))


                    // Restaurant Oasis

                    productDAO.insertProduct(Product(9, "Saganaki", "7.70",
                            "in Backteighülle frittierter Schafskäse.", 3))

                    productDAO.insertProduct(Product(10, "Tomatencremesuppe", "5.70",
                            "Mit Sahnehäubchen.", 3))

                    productDAO.insertProduct(Product(11, "Bifteki Ripieno", "17.50",
                            "Saftiges Hackfleisch gefüllt mit Schafskäse und Tomaten, mit Reis und einem Teller Salat.", 3))

                    productDAO.insertProduct(Product(12, "Filetsteak Piperato", "2950",
                            "Zartes Filetsteak mit einer pikant scharfen Pfefferrahmsauße, Folienkartoffelmit Tzatziki und Salat.", 3))


                    // Saman Restaurant

                    productDAO.insertProduct(Product(13, "Adana Kebab", "14.00",
                            "Zwei Hackfleischspieße aus Lammfleisch.", 4))

                    productDAO.insertProduct(Product(14, "Falafel Teller", "10.00",
                            "Sechs Falafeln, serviert mit Hummus und Salat.", 4))

                    productDAO.insertProduct(Product(15, "Künefe", "6.00",
                            "Mit Pistazien.", 4))

                    productDAO.insertProduct(Product(16, "Saman Teller", "26.00",
                            "Zwei Hackfleischspieße aus Lammfleisch und ein Spieß Hähnchenbrustfilet.", 4))

                }
            }
        }

    }

    companion object {

        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile
        private var INSTANCE: SafeOrderDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SafeOrderDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SafeOrderDB::class.java,
                            "safeorder_database"
                    ).fallbackToDestructiveMigration().addCallback(SafeOrderDbCallback(scope)) // leads to loss of all data in tables,
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

