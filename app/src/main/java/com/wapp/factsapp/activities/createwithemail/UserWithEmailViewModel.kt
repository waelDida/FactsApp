package com.wapp.factsapp.activities.createwithemail


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.User
import com.wapp.factsapp.resource.Resource
import com.wapp.factsapp.utils.FAILED_TO_SIGN_IN
import com.wapp.factsapp.utils.REGISTERED_SUCCESSFULLY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserWithEmailViewModel @ViewModelInject constructor(private val userDataRepo: UserDataRepo
                                                          ,private val firebaseProvider: FirebaseProvider): ViewModel() {


    private val _creationCompleted = MutableLiveData<Int>()
    val creationCompleted: LiveData<Int>
        get() = _creationCompleted


    fun createNewUser(name:String,email:String,password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val x = firebaseProvider.createUserWithEmail(email,password)
            withContext(Dispatchers.Main){
                when(x){
                    is Resource.SignSuccess -> completeProcess(name)
                    is Resource.Failure -> _creationCompleted.value = FAILED_TO_SIGN_IN
                    else -> _creationCompleted.value = FAILED_TO_SIGN_IN
                }
            }
        }
    }

    private fun completeProcess(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            when(val x = firebaseProvider.getMessageToken()){
                is Resource.Success -> {
                    val user = User(uid = firebaseProvider.getCurrentUserId(), name = name, messageToken = x.data)
                    insertUserInRoomAndFireStore(user)
                }
                else -> {
                    val user = User(uid = firebaseProvider.getCurrentUserId(), name = name)
                    insertUserInRoomAndFireStore(user)
                }
            }
            withContext(Dispatchers.Main){
                _creationCompleted.value = REGISTERED_SUCCESSFULLY
            }
        }
    }

    private suspend fun insertUserInRoomAndFireStore(user:User){
        userDataRepo.createNewUserInFireStore(user)
        userDataRepo.insertUserIntoRoom(user)
    }
}