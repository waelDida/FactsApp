package com.wapp.factsapp.data.sources.userDataSource

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.wapp.factsapp.api.createUserInFireStore
import com.wapp.factsapp.api.deleteRemoteUser
import com.wapp.factsapp.api.getUserFromFireStore
import com.wapp.factsapp.api.updateUserFieldInFireStore
import com.wapp.factsapp.models.User
import com.wapp.factsapp.room.AppDatabase
import javax.inject.Inject

class UserDataSourceImp @Inject constructor (private val appDatabase: AppDatabase): UserDataSource {
    override suspend fun createNewUserInFireStore(user: User) {
        createUserInFireStore(user)
    }

    override suspend fun getCurrentUserFromFireStore(id: String): User? {
        val documentSnapshot = getUserFromFireStore(id)
        return documentSnapshot.toObject(User::class.java)
    }


    override suspend fun <T> updateUserField(id: String, field: String, value: T) {
        updateUserFieldInFireStore(id,field,value)
    }

    override suspend fun deleteUserFromFireStore(id: String) {
        deleteRemoteUser(id)
    }

    override suspend fun getUserDocumentSnapshot(id: String): DocumentSnapshot {
        return getUserFromFireStore(id)
    }

    override suspend fun insertUserIntoRoom(user: User) {
        appDatabase.userDao.insert(user)
    }

    override suspend fun getUserFromRoom(id: String): User {
        return appDatabase.userDao.getLocalUser(id)
    }

    override fun getLiveUserFromRoom(id: String): LiveData<User?> {
        return appDatabase.userDao.getLiveLocalUser(id)
    }

    override suspend fun updateUserIntoRoom(user: User) {
        appDatabase.userDao.updateLocalUser(user)
    }

    override suspend fun deleteUserFromRoom(id:String) {
        appDatabase.userDao.deleteLocalUser(id)
    }
}