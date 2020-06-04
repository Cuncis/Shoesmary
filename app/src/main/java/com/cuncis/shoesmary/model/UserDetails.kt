package com.cuncis.shoesmary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetails(
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val city: String,
    val posCode: String,
    val country: String
): Parcelable