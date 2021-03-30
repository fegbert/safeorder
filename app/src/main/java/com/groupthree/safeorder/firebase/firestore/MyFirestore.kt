package com.groupthree.safeorder.firebase.firestore

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.groupthree.safeorder.RegisterActivity
import com.groupthree.safeorder.firebase.models.User

class MyFirestore {

    private val mFirestore = FirebaseFirestore.getInstance()

    //add new entry to Firestore!
    fun registerUser(activity: RegisterActivity, userInfo: User) {

        mFirestore.collection("users")  //table (collections) name
            .document(userInfo.id)                  //document id for user id
            .set(
                userInfo,
                SetOptions.merge()
            )      //store given data, merge fields when adding new values
            .addOnSuccessListener {
                //activity.registerSuccess()
            }
            .addOnFailureListener { e ->
                Log.e(
                    activity.javaClass.simpleName,
                    "Firestore: Unable to register user successfully.",
                    e
                )
            }
    }
}