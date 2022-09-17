package com.wapp.factsapp.data.repositories.questionsDataRepository

import com.wapp.factsapp.models.Question

interface QuestionDataRepository {
    suspend fun insertAllQuestions(questions: List<Question>)
    suspend fun getAllQuestions(): List<Question>
    suspend fun getRemoteQuestions():List<Question>
}