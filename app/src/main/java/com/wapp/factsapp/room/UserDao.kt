package com.wapp.factsapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wapp.factsapp.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM User WHERE uid = :id")
    suspend fun getLocalUser(id: String): User

    @Query("SELECT * FROM User WHERE uid = :id")
    fun getLiveLocalUser(id: String): LiveData<User?>

    @Update
    suspend fun updateLocalUser(user: User)

    @Query("DELETE FROM User WHERE uid = :id")
    suspend fun deleteLocalUser(id:String)


}