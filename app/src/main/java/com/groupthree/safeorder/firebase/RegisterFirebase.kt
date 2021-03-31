package com.groupthree.safeorder.firebase

import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.groupthree.safeorder.R
import com.groupthree.safeorder.RegisterActivity
import com.groupthree.safeorder.firebase.firestore.MyFirestore
import com.groupthree.safeorder.firebase.models.User
import kotlinx.android.synthetic.main.registration.*

class RegisterFirebase {

    private val mAuth = FirebaseAuth.getInstance()

    private fun checkRegisterFields(activity : RegisterActivity): Boolean {
        return when {
            TextUtils.isEmpty(activity.registration_firstName2.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    activity,
                    R.string.checkFirstName,
                    Toast.LENGTH_SHORT
                ).show()

                false
            }//check first name

            TextUtils.isEmpty(activity.registration_lastName2.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    activity,
                    R.string.checkLastName,
                    Toast.LENGTH_SHORT
                ).show()
                false
            }//check last name

            TextUtils.isEmpty(activity.registration_email2.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    activity,
                    R.string.checkEmail,
                    Toast.LENGTH_SHORT
                ).show()
                false
            }//check email

            TextUtils.isEmpty(activity.registration_password2.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    activity,
                    R.string.checkPassword,
                    Toast.LENGTH_SHORT
                ).show()
                false
            } //check password

            else -> {
                true
            }
        }
    }

    fun registerUser(activity : RegisterActivity){
        if(checkRegisterFields(activity)){

            val email : String = activity.registration_email2.text.toString().trim { it <= ' ' }
            val password : String = activity.registration_password2.text.toString().trim { it <= ' ' }

            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                    OnCompleteListener<AuthResult> { task ->

                        if (task.isSuccessful) {

                            val firebaseUser : FirebaseUser = task.result!!.user!!

                            //For new entry in Firestore
                            val user = User(
                                firebaseUser.uid,
                                activity.registration_firstName2.text.toString().trim { it <= ' ' },
                                activity.registration_lastName2.text.toString().trim { it <= ' ' },
                                activity.registration_email2.text.toString().trim { it <= ' ' }
                            )

                            MyFirestore().registerUserFS(activity, user)

                        } else {
                            Toast.makeText(
                                activity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

        }

    }
}