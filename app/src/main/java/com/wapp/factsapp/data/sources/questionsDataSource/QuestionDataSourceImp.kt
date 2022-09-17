package com.wapp.factsapp.data.sources.questionsDataSource

import com.wapp.factsapp.api.getQuestionsFrmFireStore
import com.wapp.factsapp.models.Question
import com.wapp.factsapp.room.AppDatabase
import com.wapp.factsapp.utils.convertToQuestionsList
import javax.inject.Inject

class QuestionDataSourceImp @Inject constructor(private val appDatabase: AppDatabase): QuestionDataSource {
    override suspend fun insertAllQuestions(questions: List<Question>) {
        appDatabase.questionDao.insertAllQuestions(questions)
    }

    override suspend fun getAllQuestions(): List<Question> {
        return appDatabase.questionDao.getAllQuestions()
    }

    override suspend fun getRemoteQuestions(): List<Question> {
        val documentSnapshot = getQuestionsFrmFireStore()
        return documentSnapshot.convertToQuestionsList()
    }
}