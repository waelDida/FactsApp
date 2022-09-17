package com.wapp.factsapp


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(private val userDataRepo: UserDataRepo,
                                                   private val firebaseProvider: FirebaseProvider,
                                                   ): ViewModel()  {

    private val _userLoginAccess = MutableLiveData<Boolean>()
    val userLoginAccess: LiveData<Boolean>
        get() = _userLoginAccess

    init {
        if(firebaseProvider.isUserLoggedIn()){
            viewModelScope.launch {
                delay(1000)
                userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                    _userLoginAccess.value = it.emailVerified
                }
            }
        }
        else
            _userLoginAccess.value = false
    }
}