package com.wapp.factsapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.wapp.factsapp.models.Fact


@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(fact:Fact)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(facts:List<Fact>)

    @Query("SELECT * FROM fact WHERE id= :value")
    fun getFact(value: String): Fact

    @Query("SELECT * FROM fact WHERE id= :value")
    fun getLocalFact(value: String): LiveData<Fact>

    @Update
    suspend fun updateFact(fact: Fact)

    @Query("SELECT * FROM Fact")
    suspend fun getAllFacts(): List<Fact>

    @Query("SELECT * FROM Fact")
    fun getAllLiveFacts(): LiveData<List<Fact>>

    @Query("SELECT * FROM fact WHERE category= :value")
    suspend fun getFactsByCategory(value: String): List<Fact>

    @Query("SELECT * FROM fact WHERE isBookmarked= :value")
    suspend fun getBookmarkedFacts(value: Boolean): List<Fact>

    @Query("SELECT * FROM fact WHERE isLiked = :value")
    suspend fun getFavoriteFacts(value: Boolean): List<Fact>

    @Query("SELECT * FROM fact WHERE dailyFact = :value")
    suspend fun getDailyFacts(value: Boolean): List<Fact>

}