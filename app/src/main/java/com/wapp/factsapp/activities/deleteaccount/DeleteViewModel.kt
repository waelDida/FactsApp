package com.wapp.factsapp.activities.deleteaccount

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteViewModel @ViewModelInject constructor(val userDataRepo: UserDataRepo
                                  ,val firebaseProvider: FirebaseProvider): ViewModel() {

    private val _deleteCompleted = MutableLiveData<Boolean>()
    val deleteCompleted: LiveData<Boolean>
        get() = _deleteCompleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            deleteUserData()
            withContext(Dispatchers.Main){
                _deleteCompleted.value = true
            }
        }
    }

    private suspend fun deleteUserData(){
        val user = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
        firebaseProvider.deleteDetailedStorageReference(user.imgUrl)
        userDataRepo.deleteUserFromFireStore(firebaseProvider.getCurrentUserId())
        userDataRepo.deleteUserFromRoom(firebaseProvider.getCurrentUserId())
        firebaseProvider.deleteUser()
    }
}