package com.groupthree.safeorder

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.groupthree.safeorder.firebase.RegisterFirebase
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)


        register_login_btn.setOnClickListener {
            onBackPressed()
        }

        //Register
        register_r_btn.setOnClickListener {
            RegisterFirebase().registerUser(this@RegisterActivity)
        }

    }

    fun registerSuccess(){
        Toast.makeText(
            this@RegisterActivity,
            R.string.success_register,
            Toast.LENGTH_SHORT
        ).show()

        FirebaseAuth.getInstance().signOut()
        finish()
    }
}
