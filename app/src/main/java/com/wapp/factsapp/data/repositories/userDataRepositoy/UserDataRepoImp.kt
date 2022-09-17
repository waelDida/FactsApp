package com.wapp.factsapp.data.repositories.userDataRepositoy

import androidx.lifecycle.LiveData
import com.wapp.factsapp.data.sources.userDataSource.UserDataSource
import com.wapp.factsapp.models.User
import javax.inject.Inject

class UserDataRepoImp @Inject constructor(private val userDataSource: UserDataSource): UserDataRepo {
    override suspend fun createNewUserInFireStore(user: User) {
        userDataSource.createNewUserInFireStore(user)
    }

    override suspend fun getCurrentUserFromFireStore(id: String): User? {
         return userDataSource.getCurrentUserFromFireStore(id)
    }


    override suspend fun <T> updateUserField(id: String, field: String, value: T) {
        userDataSource.updateUserField(id,field,value)
    }

    override suspend fun deleteUserFromFireStore(id: String) {
        userDataSource.deleteUserFromFireStore(id)
    }

    override suspend fun userExistsInFireStore(id: String): Boolean {
        return userDataSource.getUserDocumentSnapshot(id).exists()
    }

    override suspend fun insertUserIntoRoom(user: User) {
        userDataSource.insertUserIntoRoom(user)
    }

    override suspend fun getUserFromRoom(id: String): User {
        return userDataSource.getUserFromRoom(id)
    }

    override fun getLiveUserFromRoom(id: String): LiveData<User?> {
        return userDataSource.getLiveUserFromRoom(id)
    }

    override suspend fun updateUserIntoRoom(user: User) {
        userDataSource.updateUserIntoRoom(user)
    }

    override suspend fun deleteUserFromRoom(id:String) {
        userDataSource.deleteUserFromRoom(id)
    }
}