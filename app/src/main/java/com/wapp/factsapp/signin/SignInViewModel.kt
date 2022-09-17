package com.wapp.factsapp.signin

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.utils.*
import kotlinx.coroutines.launch

class SignInViewModel @ViewModelInject constructor(
        private val userDataRepo: UserDataRepo,
        private val firebaseProvider: FirebaseProvider): ViewModel() {


    private val _cred = MutableLiveData<String?>()
    val cred: LiveData<String?>
        get() = _cred

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login

    private val _signUp = MutableLiveData<Boolean>()
    val signUp: LiveData<Boolean>
        get() = _signUp

    private val _forgetPassword = MutableLiveData<Boolean>()
    val forgetPassword: LiveData<Boolean>
        get() = _forgetPassword

    private val _facebookLogin = MutableLiveData<Boolean>()
    val facebookLogin: LiveData<Boolean>
        get() = _facebookLogin

    private val _googleLogin = MutableLiveData<Boolean>()
    val googleLogin: LiveData<Boolean>
        get() = _googleLogin

    private val _anonymously = MutableLiveData<Boolean>()
    val anonymously: LiveData<Boolean> = _anonymously

    private val _userSavedLocally = MutableLiveData<Boolean>()
    val userSavedLocally: LiveData<Boolean>
        get() = _userSavedLocally


    fun checkValidity(email: String, password: String){
        when{
            email.isEmpty() && password.isEmpty() -> _cred.value = EMPTY_EMAIL_PASSWORD
            email.isEmpty() || password.isEmpty() -> _cred.value = EMPTY_EMAIL_PASSWORD
            !isValidEmail(email) -> _cred.value = INVALID_EMAIL
            !isPasswordValid(password) -> _cred.value = INVALID_PASSWORD
            else -> _cred.value = VALID_EMAIL_PASSWORD
        }
    }

    fun saveUserLocally(){
        viewModelScope.launch {
            userDataRepo.getCurrentUserFromFireStore(firebaseProvider.getCurrentUserId())?.let {
                userDataRepo.insertUserIntoRoom(it)
                _userSavedLocally.value = true
            }
        }
    }

    fun onLoginClick(){
        _login.value = true
    }

    fun onFacebookLoginClick(){
        _facebookLogin.value = true
    }

    fun onGoogleLoginClick(){
        _googleLogin.value = true
    }

    fun onAnonymousClick(){
        _anonymously.value = true
    }

    fun onSignUpClick(){
        _signUp.value = true
    }

    fun onSignUpClicked(){
        _signUp.value = false
        _cred.value = ""
    }

    fun onForgotPasswordClick(){
        _forgetPassword.value = true
    }

    fun onForgotPasswordClicked(){
        _forgetPassword.value = false
    }

    fun setCredValueToNull(){
        _cred.value = null
    }

    fun userSaved(){
        _userSavedLocally.value = false
    }
}