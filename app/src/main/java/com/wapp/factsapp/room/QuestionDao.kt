package com.wapp.factsapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wapp.factsapp.models.Question

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuestions(questions: List<Question>)

    @Query("SELECT * FROM Question")
    suspend fun getAllQuestions(): List<Question>
}