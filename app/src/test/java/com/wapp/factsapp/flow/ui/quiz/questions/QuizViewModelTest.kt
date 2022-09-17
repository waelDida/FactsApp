package com.wapp.factsapp.flow.ui.quiz.questions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wapp.factsapp.data.sources.quiz.FakeQuestionsDataRepo
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class QuizViewModelTest{

    private lateinit var quizViewModel: QuizViewModel
    private lateinit var fakeQuestionDataRepository: FakeQuestionsDataRepo

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun intiViewModel(){
        fakeQuestionDataRepository = FakeQuestionsDataRepo()
        quizViewModel = QuizViewModel(fakeQuestionDataRepository)
    }

    @Test
    fun `check that the size of the questions list is 10`(){
        assertThat(quizViewModel.questions.size,IsEqual(10))
    }
}