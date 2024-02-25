package com.demo.learnsignlanguage.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserApi(
    val uid: String,
    val email: String,
    val typeAccount: String = "GMAIL"

) : Parcelable