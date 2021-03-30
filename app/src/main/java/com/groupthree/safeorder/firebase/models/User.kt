package com.groupthree.safeorder.firebase.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val id : String = "",
    val firstName : String = "",
    val lastName : String = "",
    val email : String = "",
    val mobile : Long = 0,
    val profileCompleted: Int = 0 //
) : Parcelable