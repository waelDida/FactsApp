package com.wapp.factsapp.api

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.wapp.factsapp.models.User
import com.wapp.factsapp.utils.USERS
import kotlinx.coroutines.tasks.await

private fun getCollectionReference(): CollectionReference {
    return FirebaseFirestore.getInstance().collection(USERS)
}

suspend fun createUserInFireStore(user: User){
    getCollectionReference().document(user.uid).set(user).await()
}

suspend fun getUserFromFireStore(id: String): DocumentSnapshot {
    return getCollectionReference().document(id).get().await()
}

suspend fun<T> updateUserFieldInFireStore(id: String,field: String,value: T){
    getCollectionReference().document(id).update(field,value).await()
}

suspend fun deleteRemoteUser(id:String){
    getCollectionReference().document(id).delete().await()
}