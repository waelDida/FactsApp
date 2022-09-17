package com.wapp.factsapp.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.Exclude
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Fact(@PrimaryKey @NonNull var id: String = "",
                var category: String = "",
                var title: String = "",
                var shortDescription: String = "",
                var longDescription: String = "",
                var imageUrl: String = "",
                var dailyFact: Boolean = false,
                @Exclude var isBookmarked: Boolean = false,
                @Exclude var isLiked: Boolean = false): Parcelable