package com.wapp.factsapp.data.sources.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.wapp.factsapp.data.sources.userDataSource.UserDataSource
import com.wapp.factsapp.models.User

class FakeUserDataSource:UserDataSource {

    private val remoteUsers = mutableListOf<User>()
    private val localUsers = mutableListOf<User>()
    private val localLiveUser  = MutableLiveData<User?>()


    override suspend fun createNewUserInFireStore(user: User) {
        remoteUsers.add(user)
    }

    override suspend fun getCurrentUserFromFireStore(id: String): User {
        return remoteUsers.filter { it.uid == id }[0]
    }


    override suspend fun <T> updateUserField(id: String, field: String, value: T) {
        val user = remoteUsers.filter { it.uid == id }[0]
        when(field){
            "coins" ->  user.coins = value as Int
        }
        remoteUsers.add(user.uid.toInt(),user)
    }

    override suspend fun deleteUserFromFireStore(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDocumentSnapshot(id: String): DocumentSnapshot {
        TODO("Not yet implemented")
    }

    override suspend fun insertUserIntoRoom(user: User) {
        localUsers.add(user)
    }

    override suspend fun getUserFromRoom(id: String): User {
        return localUsers.filter { it.uid == id }[0]
    }

    override fun getLiveUserFromRoom(id: String): LiveData<User?> {
        localLiveUser.value = localUsers.filter { it.uid == id }[0]
        return localLiveUser
    }

    override suspend fun updateUserIntoRoom(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUserFromRoom(id: String) {
        TODO("Not yet implemented")
    }
}