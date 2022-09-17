package com.wapp.factsapp.data.providers.firebase

import android.net.Uri
import com.google.firebase.auth.AuthCredential
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.utils.*

class FakeFireBaseProvider: FirebaseProvider {

    private lateinit var id: String
    private lateinit var name:String
    private  var photoUrl: String? = null
    private var messageToken: String? = null
    private var signUpResult: Int = 0
    private var signInResult: Int = 0
    private var facebookSignInResult: Int = 0
    private var googleSignInResult: Int = 0
    private var userLoggedInFlag: Boolean = false
    private lateinit var uploadFlag: String

    fun setId(x:String){
        id = x
    }

    fun setName(_name:String){
        name = _name
    }

    fun setPhotoUrl(url: String?){
        photoUrl = url
    }

    fun setMessageToken(token: String?){
        messageToken = token
    }

    fun setSignUpIndex(index: Int){
        signUpResult = index
    }

    fun setSignInIndex(index: Int){
        signInResult = index
    }

    fun setFacebookSignInIndex(index: Int){
        facebookSignInResult = index
    }

    fun setGoogleSignInIndex(index:Int){
        googleSignInResult = index
    }

    fun setUserLoggedInIndex(flag: Boolean){
        userLoggedInFlag = flag
    }

    fun setUploadFlag(flag:String){
        uploadFlag = flag
    }



    override fun isUserLoggedIn() = userLoggedInFlag

    override fun getCurrentUserId() = id

    override fun getCurrentName() = name

    override fun getCurrentUserPhoto() = photoUrl
    override fun signOut(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override fun getSignInMethod(): String {
        TODO("Not yet implemented")
    }

    override suspend fun reAuthenticate(password: String, googleToken: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteDetailedStorageReference(url: String) {
        TODO("Not yet implemented")
    }


    override suspend fun createUserWithEmail(email: String, password: String): Resource<Nothing> {
        return when(signUpResult){
            1 -> Resource.SignSuccess()
            else -> Resource.Failure()
        }
    }

    override suspend fun signInWithEmail(email: String, password: String): Resource<Int> {
        return when(signInResult){
            1 -> Resource.Success(EMAIL_VERIFIED)
            2 -> Resource.Success(EMAIL_NOT_VERIFIED)
            else -> Resource.Failure()
        }
    }

    override suspend fun signInWithFacebook(credential: AuthCredential?): Resource<Nothing> {
        return when(facebookSignInResult){
            1 -> Resource.SignSuccess()
            else -> Resource.Failure()
        }
    }

    override suspend fun signInWithGoogle(idToken: String): Resource<Nothing> {
        return when(googleSignInResult){
            1 -> Resource.SignSuccess()
            else -> Resource.Failure()
        }
    }

    override suspend fun signInAnonymously(): Resource<Nothing> {
        TODO("Not yet implemented")
    }

    override suspend fun getMessageToken(): Resource<String?>  = Resource.Success(messageToken)
    override suspend fun uploadImageToFirebase(uri: Uri?): Resource<String?> {
        TODO("Not yet implemented")
    }

    //override fun uploadImageToFirebase(uri: Uri?) =  uploadFlag


}