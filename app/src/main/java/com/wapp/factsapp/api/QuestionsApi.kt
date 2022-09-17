package com.wapp.factsapp.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.wapp.factsapp.utils.QUESTIONS
import kotlinx.coroutines.tasks.await

private fun getQuestionsCollectionReference(): CollectionReference{
    return FirebaseFirestore.getInstance().collection(QUESTIONS)
}

suspend fun getQuestionsFrmFireStore():List<DocumentSnapshot>{
    return getQuestionsCollectionReference().get().await().documents
}