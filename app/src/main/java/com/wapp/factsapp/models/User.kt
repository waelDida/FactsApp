package com.wapp.factsapp.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
        @PrimaryKey @NonNull var uid:String = "",
        var name: String = "",
        var factNum: Int = 1,
        var coins:Int = 0,
        var imgUrl: String = "",
        var messageToken: String? = "",

        @Ignore
        var emailVerified: Boolean = false): Parcelable