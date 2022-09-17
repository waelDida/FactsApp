package com.wapp.factsapp.data.sources.quiz

import com.wapp.factsapp.data.repositories.questionsDataRepository.QuestionDataRepository
import com.wapp.factsapp.models.Question

class FakeQuestionsDataRepo: QuestionDataRepository {

    private val remoteQuestionsList = listOf(Question(1),Question(2))

    override suspend fun insertAllQuestions(questions: List<Question>) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllQuestions(): List<Question> {
        return remoteQuestionsList
    }

    override suspend fun getRemoteQuestions(): List<Question> {
        return remoteQuestionsList
    }
}