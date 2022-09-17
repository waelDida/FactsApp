package com.wapp.factsapp.flow.ui.quiz.quizCompleted


import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.wapp.factsapp.data.providers.firebase.FirebaseProvider
import com.wapp.factsapp.data.repositories.userDataRepositoy.UserDataRepo
import com.wapp.factsapp.utils.REMOTE_COINS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizCompletedViewModel @ViewModelInject constructor(@Assisted val savedStateHandle: SavedStateHandle,
                                                          private val userDataRepo: UserDataRepo,
                                                          private val firebaseProvider: FirebaseProvider): ViewModel() {

    val currentUser = userDataRepo.getLiveUserFromRoom(firebaseProvider.getCurrentUserId())

    val questionNumbers = 10

    private val _correctAnswers = MutableLiveData<Int>()
    val correctAnswers: LiveData<Int>
        get() = _correctAnswers

    private val _playAgain = MutableLiveData<Boolean>()
    val playAgain: LiveData<Boolean>
        get() = _playAgain

    private val _quitQuiz = MutableLiveData<Boolean>()
    val quitQuiz: LiveData<Boolean>
        get() = _quitQuiz

    init {
        _correctAnswers.value = savedStateHandle["correct_answers"]
        updateUserCoins()
    }

    private fun updateUserCoins(){
        viewModelScope.launch(Dispatchers.IO) {
            val user = userDataRepo.getUserFromRoom(firebaseProvider.getCurrentUserId())
            val coins = user.coins
            val y = coins.plus(_correctAnswers.value!!.times(10))
            user.coins = y
            userDataRepo.updateUserIntoRoom(user)
            userDataRepo.updateUserField(firebaseProvider.getCurrentUserId(),
                    REMOTE_COINS, y)
        }
    }

    fun onPlayAgain(){
        _playAgain.value = true
    }

    fun onQuitQuiz(){
        _quitQuiz.value = true
    }
}