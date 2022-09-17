package com.wapp.factsapp.data.sources.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.models.User
import com.wapp.factsapp.utils.REMOTE_EMAIL_VERIFIED
import com.wapp.factsapp.utils.REMOTE_IMAGE_URL

class FakeUserRepository: UserDataRepo{

    private val remoteList = mutableListOf<User>()
    private val localList = mutableListOf<User>()
    private val localLiveUser  = MutableLiveData<User?>()
    private var userExistInFireStore: Boolean = false

    fun setUserExistInFireStore(value: Boolean){
        userExistInFireStore = value
    }

    override suspend fun createNewUserInFireStore(user: User) {
        remoteList.add(user)
    }

    override suspend fun getCurrentUserFromFireStore(id: String): User {
        return remoteList.filter { it.uid == id }[0]
    }

    override suspend fun <T> updateUserField(id: String, field: String, value: T) {
        val user = remoteList.filter { it.uid == id }[0]
        when(field){
            "coins" ->  user.coins = value as Int
            REMOTE_IMAGE_URL -> user.imgUrl = value as String
            REMOTE_EMAIL_VERIFIED -> user.emailVerified = value as Boolean
        }
        remoteList.add(user.uid.toInt(),user)
    }

    override suspend fun deleteUserFromFireStore(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun userExistsInFireStore(id: String): Boolean {
        return userExistInFireStore
    }

    override suspend fun insertUserIntoRoom(user: User) {
        localList.add(user)
    }

    override suspend fun getUserFromRoom(id: String): User {
        return localList.filter { it.uid == id }[0]
    }

    override fun getLiveUserFromRoom(id: String): LiveData<User?> {
        localLiveUser.value = localList.filter { it.uid == id }[0]
        return localLiveUser
    }

    override suspend fun updateUserIntoRoom(user: User) {
        localList.add(user.uid.toInt(),user)
    }

    override suspend fun deleteUserFromRoom(id: String) {
        TODO("Not yet implemented")
    }

}