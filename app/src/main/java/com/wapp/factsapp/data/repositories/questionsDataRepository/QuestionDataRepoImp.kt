package com.wapp.factsapp.data.repositories.questionsDataRepository

import com.wapp.factsapp.data.sources.questionsDataSource.QuestionDataSource
import com.wapp.factsapp.models.Question
import javax.inject.Inject

class QuestionDataRepoImp @Inject constructor(private val questionDataSource: QuestionDataSource): QuestionDataRepository {
    override suspend fun insertAllQuestions(questions: List<Question>) {
        questionDataSource.insertAllQuestions(questions)
    }

    override suspend fun getAllQuestions(): List<Question> {
        return questionDataSource.getAllQuestions()
    }

    override suspend fun getRemoteQuestions(): List<Question> {
        return questionDataSource.getRemoteQuestions()
    }
}