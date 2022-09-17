package com.wapp.factsapp.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude

@Entity
data class Question(
        @PrimaryKey @NonNull var id: Int = 0,
        var question: String = "",
        var answer1: String = "",
        var answer2: String = "",
        var answer3: String = "",
        var answer4: String = "",
        var rightAnswer: String = "",
        @Exclude var image: Int? = null,
        var remote:Boolean = false
)