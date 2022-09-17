package com.wapp.factsapp.data.sources.questionsDataSource

import com.wapp.factsapp.models.Question

interface QuestionDataSource {
    suspend fun insertAllQuestions(questions: List<Question>)
    suspend fun getAllQuestions(): List<Question>
    suspend fun getRemoteQuestions():List<Question>
}