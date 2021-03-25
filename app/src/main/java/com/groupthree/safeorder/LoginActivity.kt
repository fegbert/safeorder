package com.groupthree.safeorder

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*

class LoginActivity : AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth
    //val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)


        login_register_btn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        //Login
        login_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(login_email_input.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@LoginActivity,
                            "Bitte E-Mail eingeben.",
                            Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(login_password_input.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@LoginActivity,
                            "Bitte Passwort eingeben.",
                            Toast.LENGTH_SHORT
                    ).show()
                } //check password
                else -> {
                    val email : String = login_email_input.text.toString().trim { it <= ' ' }
                    val password : String = login_password_input.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->

                                        if(task.isSuccessful){

                                            Toast.makeText(
                                                    this@LoginActivity,
                                                    "Sie haben sich erfolgreich angemeldet.",
                                                    Toast.LENGTH_SHORT
                                            ).show()

                                            val intent =
                                                    Intent(this@LoginActivity, MainActivity::class.java)

                                            //clear background activities
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                            intent.putExtra("email_id", email)
                                            startActivity(intent)
                                            finish()
                                        } else{
                                            Toast.makeText(
                                                    this@LoginActivity,
                                                    task.exception!!.message.toString(),
                                                    Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                }
            } //when
        } //setOnClickListener



    } //onCreate


}

