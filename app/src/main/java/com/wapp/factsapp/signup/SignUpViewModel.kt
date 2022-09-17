package com.wapp.factsapp.signup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wapp.factsapp.utils.*

class SignUpViewModel @ViewModelInject constructor():ViewModel() {
    private val _checkValidity = MutableLiveData<String?>()
    val checkValidity: LiveData<String?>
        get() = _checkValidity

    private val _signUp = MutableLiveData<Boolean>()
    val signUp: LiveData<Boolean>
        get() = _signUp

    private val _backClick = MutableLiveData<Boolean>()
    val backClick: LiveData<Boolean>
        get() = _backClick

    fun getRegistrationInfo(name: String, email: String, password: String, retypedPass: String){
        when{
            name.isEmpty() || email.isEmpty() || password.isEmpty() || retypedPass.isEmpty() -> _checkValidity.value = FILL_ALL_FIELDS
            !isPasswordValid(password) -> _checkValidity.value = INVALID_PASSWORD
            password != retypedPass ->  _checkValidity.value = CHECK_PASSWORD
            !isValidEmail(email) -> _checkValidity.value = INVALID_EMAIL
            else -> _checkValidity.value = VALID_INFO
        }
    }

    fun onSignUpClick(){
        _signUp.value = true
    }

    fun onBackClick(){
        _backClick.value = true
    }

    fun onBackClicked(){
        _backClick.value = false
    }
}