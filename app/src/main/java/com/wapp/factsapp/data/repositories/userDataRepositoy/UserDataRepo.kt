package com.wapp.factsapp.data.repositories.userDataRepositoy

import androidx.lifecycle.LiveData
import com.wapp.factsapp.models.User

interface UserDataRepo {

    //Remote
    suspend fun createNewUserInFireStore(user: User)
    suspend fun getCurrentUserFromFireStore(id: String): User?
    suspend fun<T> updateUserField(id:String,field: String,value:T)
    suspend fun deleteUserFromFireStore(id:String)
    suspend fun userExistsInFireStore(id:String):Boolean

    //Local
    suspend fun insertUserIntoRoom(user: User)
    suspend fun getUserFromRoom(id: String): User
    fun getLiveUserFromRoom(id: String): LiveData<User?>
    suspend fun updateUserIntoRoom(user: User)
    suspend fun deleteUserFromRoom(id:String)
}