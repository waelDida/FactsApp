package com.wapp.factsapp.flow.ui.quiz.questions

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class QuizFragment : Fragment() {

    val viewModel: QuizViewModel by viewModels()

    private lateinit var binding: FragmentQuizBinding
    private lateinit var correctSound: MediaPlayer
    private lateinit var wrongSound: MediaPlayer
    private lateinit var answersListText: List<TextView>

    companion object{
        const val ANSWER_1 = "answer_1"
        const val ANSWER_2 = "answer_2"
        const val ANSWER_3 = "answer_3"
        const val ANSWER_4 = "answer_4"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        inflater.inflate(R.layout.fragment_quiz, container, false)
        binding = FragmentQuizBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        correctSound = MediaPlayer.create(requireActivity(),R.raw.correct)
        wrongSound = MediaPlayer.create(requireActivity(),R.raw.wrong)

        answersListText = listOf(binding.answer1,binding.answer2,binding.answer3,binding.answer4)

        viewModel.isFinished.observe(viewLifecycleOwner,{
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToQuizCompleted(it))
        })

        viewModel.gameIsOver.observe(viewLifecycleOwner,{
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToGameOver(it.first,it.second,false))
        })

        viewModel.timeIsUp.observe(viewLifecycleOwner,{
            findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToGameOver(it.first,it.second,true))
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        listOf(binding.answer1,binding.answer2,binding.answer3,binding.answer4).forEach {
            it.setOnClickListener {x->
                with(x as TextView){
                    viewModel.onAnswerClick(this.text.toString(),this.tag.toString())
                }
            }
        }

        viewModel.answer.observe(viewLifecycleOwner,{
            it?.let {
                when(it.first){
                    QuizViewModel.CORRECT_ANSWER -> {
                        onCorrectAnswer(it.second)
                    }
                    QuizViewModel.WRONG_ANSWER -> {
                        onWrongAnswer(it.second)
                    }
                }
            }
        })

    }


    private fun onCorrectAnswer(tag: String){
        correctSound.start()
        showGreenColor(tag)
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main){
                viewModel.onCorrectAnswerClicked()
                resetColor(tag)
            }
        }
    }

    private fun onWrongAnswer(tag: String){
        wrongSound.start()
        showRedColor(tag)
        viewModel.decreaseLifeNumber()
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            withContext(Dispatchers.Main){
                viewModel.onWrongAnswerClicked()
                resetColor(tag)
            }
        }

    }

    private fun showGreenColor(tag: String) {
        answersListText.forEach {
            it.isClickable = false
        }
        when(tag){
            ANSWER_1 -> binding.answer1.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_correct)
            ANSWER_2 -> binding.answer2.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_correct)
            ANSWER_3 -> binding.answer3.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_correct)
            ANSWER_4 -> binding.answer4.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_correct)

        }
    }

    private fun showRedColor(tag: String) {
        answersListText.forEach {
            it.isClickable = false
        }
        when(tag){
            ANSWER_1 -> binding.answer1.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_false)
            ANSWER_2 -> binding.answer2.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_false)
            ANSWER_3 -> binding.answer3.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_false)
            ANSWER_4 -> binding.answer4.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_false)
        }
    }

    private fun resetColor(tag: String) {
        answersListText.forEach {
            it.isClickable = true
        }
        when(tag){
            ANSWER_1 -> binding.answer1.background = ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_background)
            ANSWER_2 -> binding.answer2.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_background)
            ANSWER_3 -> binding.answer3.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_background)
            ANSWER_4 -> binding.answer4.background =  ContextCompat.getDrawable(requireContext(),R.drawable.quiz_answer_background)

        }
    }
}