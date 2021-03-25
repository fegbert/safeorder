package com.groupthree.safeorder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.groupthree.safeorder.database.SafeOrderDB
import kotlinx.android.synthetic.main.user_profile.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        /*
        settings_logout?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            //startActivity(Intent(this.context, LoginActivity::class.java))
            //requireActivity().startActivity(Intent(getActivity(), LoginActivity::class.java))
            Toast.makeText(this@MainActivity, "Signed Out", Toast.LENGTH_LONG).show()
            //requireActivity().
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        }*/
        /*
        //Authentifizierung
        auth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance()
        dbRef = db?.reference!!.child("Profil")
        loadProfil()*/

    }

    private fun deleteDatabaseFile(databaseName : String) {
        val databases : File = File(applicationInfo.dataDir + "/databases")
        val db : File = File(databases, databaseName)

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