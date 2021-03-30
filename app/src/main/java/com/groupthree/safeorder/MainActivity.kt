package com.groupthree.safeorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var dbRef: DatabaseReference? = null
    var db : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.topFragment)

        bottomNavigationView.setupWithNavController(navController)

    }

    private fun deleteDatabaseFile(databaseName : String) {
        val databases : File = File(applicationInfo.dataDir + "/databases")
        val db : File = File(databases, databaseName)

    }

}