package com.wapp.factsapp.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.wapp.factsapp.utils.CATEGORIES
import com.wapp.factsapp.utils.FACTS
import kotlinx.coroutines.tasks.await

private fun getFactsCollectionReference(): CollectionReference {
    return FirebaseFirestore.getInstance().collection(FACTS)
}

private fun getCategoriesCollectionReference(): CollectionReference{
    return FirebaseFirestore.getInstance().collection(CATEGORIES)
}

suspend fun getOneRemoteFact(id: String): DocumentSnapshot {
    return getFactsCollectionReference().document(id).get().await()
}

suspend fun getAllFactsInCategoriesFromFireStore():List<DocumentSnapshot>{
    return getCategoriesCollectionReference().get().await().documents
}
