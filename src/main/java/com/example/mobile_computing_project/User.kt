package com.example.mobile_computing_project

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(val uid: String, val username: String): Parcelable {
    constructor() : this("","")
}