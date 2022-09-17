package com.wapp.factsapp.activities.loginwithemail


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginWithEmailViewModel @ViewModelInject constructor(private val firebaseProvider: FirebaseProvider,
                                                           private val userDataRepo: UserDataRepo): ViewModel() {

    private val _signInResult = MutableLiveData<Int>()
    val signInResult: LiveData<Int>
        get() = _signInResult


    fun signIn(email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val x = firebaseProvider.signInWithEmail(email, password)
            withContext(Dispatchers.Main) {
                when (x) {
                    is Resource.Success -> {
                        if (x.data == EMAIL_VERIFIED)
                            signInSucceeded()
                        else
                            _signInResult.value = EMAIL_NOT_VERIFIED
                    }
                    else -> _signInResult.value = SIGN_IN_FAILS
                }
            }
        }
    }

    private fun signInSucceeded(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val x  = firebaseProvider.getMessageToken()){
                is Resource.Success -> {
                    userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_EMAIL_VERIFIED, true)
                    userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_MESSAGE_TOKEN,x.data)
                }
                is Resource.Failure -> {
                    userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(), REMOTE_EMAIL_VERIFIED, true)
                }
            }
            withContext(Dispatchers.Main){
                _signInResult.value = EMAIL_VERIFIED
            }
        }
    }
}