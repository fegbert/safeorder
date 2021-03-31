package com.groupthree.safeorder.firebase

import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import com.groupthree.safeorder.LoginActivity
import com.groupthree.safeorder.MainActivity
import com.groupthree.safeorder.R
import com.groupthree.safeorder.firebase.firestore.MyFirestore
import kotlinx.android.synthetic.main.login.*

class LoginFirebase {

    private fun checkLoginFields(activity: LoginActivity): Boolean {
        return when {
            TextUtils.isEmpty(activity.login_email_input.text.toString().trim { it <= ' ' }) -> {
                Toast.makeText(
                    activity,
                    R.string.checkEmail,
                    Toast.LENGTH_SHORT
                ).show()
                false
            }//check email

            TextUtils.isEmpty(activity.login_password_input.text.toString().trim { it <= ' ' }) -> {
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

    fun loginUser(activity: LoginActivity) {

        if (checkLoginFields(activity)) {

            val email: String = activity.login_email_input.text.toString().trim { it <= ' ' }
            val password: String = activity.login_password_input.text.toString().trim { it <= ' ' }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        activity.loginSuccess()

                    } else {

                        Toast.makeText(
                            activity,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }


}