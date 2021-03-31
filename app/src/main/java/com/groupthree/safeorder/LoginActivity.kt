package com.groupthree.safeorder

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.groupthree.safeorder.firebase.LoginFirebase
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        login_register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //Login
        login_btn.setOnClickListener {
            LoginFirebase().loginUser(this@LoginActivity)

        }

    }

    fun loginSuccess(){
        Toast.makeText(
            this@LoginActivity,
            R.string.success_login,
            Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}

