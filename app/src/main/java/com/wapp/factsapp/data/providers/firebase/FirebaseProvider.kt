package com.wapp.factsapp.data.providers.firebase


import android.net.Uri
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.wapp.factsapp.models.Fact
import com.wapp.factsapp.resource.Resource

interface FirebaseProvider {

    fun isUserLoggedIn(): Boolean
    fun getCurrentUserId(): String
    fun getCurrentName(): String
    fun getCurrentUserPhoto():String?
    fun signOut(): Boolean
    suspend fun deleteUser()
    fun getSignInMethod():String
    suspend fun reAuthenticate(password:String,googleToken: String):Boolean
    suspend fun deleteDetailedStorageReference(url: String)
    suspend fun createUserWithEmail(email:String,password:String): Resource<Nothing>
    suspend fun signInWithEmail(email:String,password:String):Resource<Int>
    suspend fun signInWithFacebook(credential: AuthCredential?):Resource<Nothing>
    suspend fun signInWithGoogle(idToken: String):Resource<Nothing>
    suspend fun signInAnonymously():Resource<Nothing>
    suspend fun getMessageToken():Resource<String?>
    suspend fun uploadImageToFirebase(uri: Uri?):Resource<String?>
}