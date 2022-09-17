package com.wapp.factsapp.activities.resetpassword

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wapp.factsapp.utils.INVALID_EMAIL
import com.wapp.factsapp.utils.VALID_EMAIL
import com.wapp.factsapp.utils.isValidEmail

class ResetViewModel @ViewModelInject constructor(): ViewModel() {

    private val _checkValidity = MutableLiveData<String>()
    val checkValidity: LiveData<String>
        get() = _checkValidity

    private val _resetClick = MutableLiveData<Boolean>()
    val resetClick: LiveData<Boolean>
        get() = _resetClick

    private val _backClick = MutableLiveData<Boolean>()
    val backClick: LiveData<Boolean>
        get() = _backClick

    fun checkEmailValidity(email: String){
        when{
            email.isNotEmpty() && isValidEmail(email) -> _checkValidity.value = VALID_EMAIL
            else -> _checkValidity.value = INVALID_EMAIL
        }
    }

    fun onResetClick(){
        _resetClick.value = true
    }

    fun onResetClicked(){
        _resetClick.value = false
    }

    fun onBackClick(){
        _backClick.value = true
    }

    fun onBackClicked(){
        _backClick.value = false
    }

}