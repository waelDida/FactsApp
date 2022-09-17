package com.wapp.factsapp.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
        @PrimaryKey @NonNull var index:Int,
        var name: String,
        var image: String,
        var isLocked:Boolean = false,
        var isPicked:Boolean = true,
        var value: Int = 0)

//
//var isSelectable:Boolean = true)