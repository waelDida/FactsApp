package com.wapp.factsapp.flow.ui.quiz.gameOver

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo

class GameOverViewModel @ViewModelInject constructor(@Assisted val savedStateHandle: SavedStateHandle,
                                                     userDataRepo: UserDataRepo,
                                                     firebaseProvider: FirebaseProvider): ViewModel() {

    val currentUser = userDataRepo.getLiveUserFromRoom(firebaseProvider.getCurrentUserId())

    private val _correctAnswers = MutableLiveData<Int>()
    val correctAnswers: LiveData<Int>
        get() = _correctAnswers

    private val _questionsNumber = MutableLiveData<Int>()
    val questionsNumber: LiveData<Int>
        get() = _questionsNumber

    private val _timeIsUp = MutableLiveData<Boolean>()
    val timeIsUp: LiveData<Boolean>
        get() = _timeIsUp

    private val _quizDetails = MutableLiveData<Pair<Int,Int>>()
    val quizDetails : LiveData<Pair<Int,Int>>
        get() = _quizDetails

    private val _tryAgain = MutableLiveData<Boolean>()
    val tryAgain: LiveData<Boolean>
        get() = _tryAgain

    private val _quitQuiz = MutableLiveData<Boolean>()
    val quitQuiz: LiveData<Boolean>
        get() = _quitQuiz

    init {
        _correctAnswers.value = savedStateHandle["correct_answers"]
        _questionsNumber.value = savedStateHandle["questions_number"]
        _timeIsUp.value = savedStateHandle["time_is_up"]
        _quizDetails.value = Pair(_correctAnswers.value!!,_questionsNumber.value!!)
    }


    fun onPlayAgain(){
        _tryAgain.value = true
    }

    fun onQuitQuiz(){
        _quitQuiz.value = true
    }
}