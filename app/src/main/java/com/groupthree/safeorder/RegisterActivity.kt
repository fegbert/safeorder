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
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.registration.*
import org.w3c.dom.Text

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)


        register_login_btn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            //onBackPressed()
        }

        //Register
        register_r_btn.setOnClickListener {
            when {
                TextUtils.isEmpty(registration_email2.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@RegisterActivity,
                            "Bitte E-Mail eingeben.",
                            Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(registration_password2.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@RegisterActivity,
                            "Bitte Passwort eingeben.",
                            Toast.LENGTH_SHORT
                    ).show()
                } //check password
                else -> {
                    val email : String = registration_email2.text.toString().trim { it <= ' ' }
                    val password : String = registration_password2.text.toString().trim { it <= ' ' }

                    //Create an instance and register a user with email and password.
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(
                                    OnCompleteListener<AuthResult> { task ->

                                        //when registration is successfully done
                                        if(task.isSuccessful){

                                            //Firebase registered user
                                            val firebaseUser : FirebaseUser = task.result!!.user!!

                                            Toast.makeText(
                                                    this@RegisterActivity,
                                                    "Sie haben sich erfolgreich registriert.",
                                                    Toast.LENGTH_SHORT
                                            ).show()

                                            //send user to Main Activity
                                            val intent =
                                                    Intent(this@RegisterActivity, MainActivity::class.java)

                                            //clear background activities
                                            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                            intent.putExtra("user_id", firebaseUser.uid)
                                            intent.putExtra("email_id", email)
                                            startActivity(intent)
                                            finish()
                                        } else{
                                            Toast.makeText(
                                                    this@RegisterActivity,
                                                    task.exception!!.message.toString(),
                                                    Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            )
                }
            } //when
        } //setOnClickListener


    } //onCreate
}
