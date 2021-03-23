package com.groupthree.safeorder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

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

        /*
        //Authentifizierung
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        dbRef = db?.reference!!.child("Profil")
        loadProfil()*/



    }


/*
    private fun loadProfil(){

        val user = auth.currentUser
        var userRef = dbRef?.child(user?.uid!!)

        val btn = findViewById<Button>(R.id.topAppBar_userprofile) //CHANGE BUTTON
        btn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }
    }*/
}