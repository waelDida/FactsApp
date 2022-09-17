package com.wapp.factsapp.data.providers.firebase


import android.net.Uri
import com.facebook.AccessToken
import com.google.firebase.auth.*
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.storage.FirebaseStorage
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.uploadhelper.PhotoUploader
import com.wapp.factsapp.utils.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*
import javax.inject.Inject

class FirebaseProviderImp @Inject constructor(private val photoUploader: PhotoUploader): FirebaseProvider {

    private fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

    override fun isUserLoggedIn() = getCurrentUser() != null

    override fun getCurrentUserId() = getCurrentUser()!!.uid

    override fun getCurrentName(): String  = getCurrentUser()!!.displayName!!

    override fun getCurrentUserPhoto() = getCurrentUser()!!.photoUrl.toString()+"?height=500"

    override fun signOut(): Boolean {
        return try{
            FirebaseAuth.getInstance().signOut()
            true
        }catch (e:Exception){
            false
        }
    }

    override suspend fun deleteUser() {
        FirebaseAuth.getInstance().currentUser!!.delete().await()
    }

    override fun getSignInMethod(): String {
        var method = ""
        getCurrentUser()?.let {
            for(profile in it.providerData){
                method =  when(profile.providerId){
                    "facebook.com" -> SIGN_IN_WITH_FACEBOOK
                    "google.com" -> SIGN_IN_WITH_GOOGLE
                    else -> SIGN_IN_WITH_EMAIL
                }
            }
        }
        return method
    }

    override suspend fun reAuthenticate(password: String, googleToken: String):Boolean {
        return try{
            getCurrentUser()?.let {
                for(profile in it.providerData){
                    when(profile.providerId){
                        "facebook.com" -> {
                            val token = AccessToken.getCurrentAccessToken()
                            it.reauthenticate(FacebookAuthProvider.getCredential(token.token)).await()
                        }
                        "google.com" ->{
                            it.reauthenticate(GoogleAuthProvider.getCredential(googleToken,null)).await()
                        }
                        else -> it.reauthenticate(EmailAuthProvider.getCredential(it.email.toString(), password)).await()
                    }
                }
            }
            true
        }
        catch (e:Exception){ false}
    }

    override suspend fun deleteDetailedStorageReference(url: String) {
        if(url.isNotEmpty() && !url.endsWith("?height=500"))
            FirebaseStorage.getInstance().getReferenceFromUrl(url).delete().await()
    }


    override suspend fun createUserWithEmail(email: String, password: String): Resource<Nothing> {
        return try{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).await()
            getCurrentUser()!!.sendEmailVerification().await()
            Resource.SignSuccess()
        }catch (e:Exception){
            Resource.Failure()
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): Resource<Int> {
        return try{
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).await()
            if(getCurrentUser()!!.isEmailVerified)
                Resource.Success(EMAIL_VERIFIED)
            else
                Resource.Success(EMAIL_NOT_VERIFIED)

        }catch (e:Exception){
            Resource.Failure()
        }
    }

    override suspend fun signInWithFacebook(credential: AuthCredential?): Resource<Nothing> {
        return try{
            FirebaseAuth.getInstance().signInWithCredential(credential!!).await()
            Resource.SignSuccess()
        }catch (e:Exception){
            Resource.Failure()
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Resource<Nothing> {
        return try{
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            FirebaseAuth.getInstance().signInWithCredential(credential).await()
            Resource.SignSuccess()
        }
        catch (e:Exception){
            Resource.Failure()
        }
    }

    override suspend fun signInAnonymously(): Resource<Nothing> {
        return try{
            FirebaseAuth.getInstance().signInAnonymously().await()
            Resource.SignSuccess()
        }
        catch (e: Exception){
            Resource.Failure()
        }
    }

    override suspend fun getMessageToken(): Resource<String?> {
        return try {
            val x = FirebaseMessaging.getInstance().token.await()
            Resource.Success(x)
        }
        catch (e:Exception){
            Resource.Failure()
        }
    }

    override suspend fun uploadImageToFirebase(uri: Uri?): Resource<String?> {
        return try{
            val uuid = UUID.randomUUID().toString()
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            val imgRef = FirebaseStorage.getInstance().reference.child("$uid/$uuid")
            imgRef.putBytes(photoUploader.getByteArray(uri)).await()
            val url = imgRef.downloadUrl.await().toString()
            Resource.Success(url)
        }
        catch(e:Exception){
            Resource.Failure()
        }
    }

}