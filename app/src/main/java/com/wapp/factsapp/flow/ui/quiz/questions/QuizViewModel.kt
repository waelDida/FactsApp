package com.wapp.factsapp.flow.ui.quiz.questions

import android.os.CountDownTimer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wapp.factsapp.data.repositories.questionsDataRepository.QuestionDataRepository
import com.wapp.factsapp.models.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizViewModel @ViewModelInject constructor(private val questionDataRepository: QuestionDataRepository): ViewModel() {

    lateinit var countdownTimer: CountDownTimer
    private var timeInMilliSeconds = 60000L

    companion object{
        const val CORRECT_ANSWER = "correct_answer"
        const val WRONG_ANSWER = "wrong_answer"
    }

    private val _minute = MutableLiveData<Int>()
    val minute: LiveData<Int>
        get() = _minute

    private val _second = MutableLiveData<Int>()
    val second: LiveData<Int>
        get() = _second

    private val _questionNumber = MutableLiveData<Int>()
    val questionNumber: LiveData<Int>
        get() = _questionNumber

    private val _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private val _life = MutableLiveData<Int>()
    val life: LiveData<Int>
        get() = _life

    private val _isFinished = MutableLiveData<Int>()
    val isFinished: LiveData<Int>
        get() = _isFinished

    private val _gameIsOver = MutableLiveData<Pair<Int,Int>>()
    val gameIsOver: LiveData<Pair<Int,Int>>
        get() = _gameIsOver

    private val _timeIsUp = MutableLiveData<Pair<Int,Int>>()
    val timeIsUp: LiveData<Pair<Int,Int>>
        get() = _timeIsUp

    private var _answer = MutableLiveData<Pair<String,String>>()
    val answer: LiveData<Pair<String,String>>
        get() = _answer

    private var correctAnswers: Int = 0

   lateinit var questions: List<Question>

    init {
       initQuiz()
    }

    private fun initQuiz(){
        viewModelScope.launch(Dispatchers.IO) {
            questions = questionDataRepository.getAllQuestions().shuffled().subList(0,11)
            withContext(Dispatchers.Main){
                _questionNumber.value = 1
                _life.value = 3
                _currentQuestion.value = questions[1]
                startTimer(timeInMilliSeconds)
            }
        }
    }

    private fun startTimer(timeInSeconds: Long){
        countdownTimer = object : CountDownTimer(timeInSeconds,1000){
            override fun onTick(millisUntilFinished: Long) {
                _minute.value = ((millisUntilFinished/1000)/60).toInt()
                _second.value = ((millisUntilFinished/1000)%60).toInt()
            }

            override fun onFinish() {
                _timeIsUp.value = Pair(correctAnswers,_questionNumber.value!!)
            }
        }
        countdownTimer.start()
    }

    fun onAnswerClick(answer: String, tag: String){
        if(answer != _currentQuestion.value!!.rightAnswer){
            _answer.value = Pair(WRONG_ANSWER,tag)
        }
        else{
            correctAnswers+=1
            _answer.value = Pair(CORRECT_ANSWER,tag)
        }
    }

    fun onCorrectAnswerClicked(){
        displayNextQuestion()
    }

    fun onWrongAnswerClicked(){
        if(_life.value != 0){
            displayNextQuestion()
        }
        else
            _gameIsOver.value = Pair(correctAnswers,_questionNumber.value!!)
    }

    fun decreaseLifeNumber(){
        if(_life.value != 0)
            _life.value = _life.value!!.minus(1)
    }

    private fun displayNextQuestion(){
        if(_questionNumber.value!! < questions.size.minus(1)){
            _questionNumber.value = _questionNumber.value!!.plus(1)
            _currentQuestion.value = questions[_questionNumber.value!!]
        }else{
            _isFinished.value = correctAnswers
        }
    }
}