package com.wapp.factsapp.activities.loginwithfacebook

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.User
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.utils.FACEBOOK_SIGN_FAILED
import com.wapp.factsapp.utils.FACEBOOK_SIGN_SUCCEED
import com.wapp.factsapp.utils.REMOTE_MESSAGE_TOKEN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginWithFacebookViewModel @ViewModelInject constructor(private val userDataRepo: UserDataRepo,
                                                              private val firebaseProvider: FirebaseProvider): ViewModel() {

    private val _signInResult = MutableLiveData<Int>()
    val signInResult: LiveData<Int>
        get() = _signInResult


    fun signInWithFacebook(credential: AuthCredential?){
        viewModelScope.launch(Dispatchers.IO) {
            val x = firebaseProvider.signInWithFacebook(credential)
            withContext(Dispatchers.Main){
                when(x){
                    is Resource.SignSuccess -> signInSucceeded()
                    else -> _signInResult.value = FACEBOOK_SIGN_FAILED
                }
            }
        }
    }

    private fun signInSucceeded(){
        viewModelScope.launch(Dispatchers.IO) {
            if(!userDataRepo.userExistsInFireStore(firebaseProvider.getCurrentUserId())){
                when(val x = firebaseProvider.getMessageToken()){
                    is Resource.Success -> {
                        val user = User(
                                uid = firebaseProvider.getCurrentUserId(),
                                name = firebaseProvider.getCurrentName(),
                                imgUrl = firebaseProvider.getCurrentUserPhoto()!!,
                                messageToken = x.data,
                                emailVerified = true
                        )
                        insertUserInRoomAndFireStore(user)
                    }
                    is Resource.Failure -> {
                        val user = User(
                                uid = firebaseProvider.getCurrentUserId(),
                                name = firebaseProvider.getCurrentName(),
                                imgUrl = firebaseProvider.getCurrentUserPhoto()!!,
                                emailVerified = true
                        )
                        insertUserInRoomAndFireStore(user)
                    }
                }
            }
            else{
                when(val x = firebaseProvider.getMessageToken()){
                    is Resource.Success -> {
                        userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_MESSAGE_TOKEN,x.data)
                        //userDataRepo.insertUserIntoRoom(userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId()))
                        userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                            userDataRepo.insertUserIntoRoom(it)
                        }
                    }
                    is Resource.Failure -> {
                        userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                            userDataRepo.insertUserIntoRoom(it)
                        }
                        //userDataRepo.insertUserIntoRoom(userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId()))
                    }
                }
            }
            withContext(Dispatchers.Main){
                _signInResult.value = FACEBOOK_SIGN_SUCCEED
            }
        }
    }

    private suspend fun insertUserInRoomAndFireStore(user:User){
        userDataRepo.createNewUserInFireStore(user)
        userDataRepo.insertUserIntoRoom(user)
    }
}