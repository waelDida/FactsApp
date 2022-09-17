package com.wapp.factsapp.flow.ui.quiz.quizCompleted

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentQuizCompletedBinding
import com.wapp.factsapp.flow.MainActivity
import com.wapp.factsapp.utils.getPercentageValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class QuizCompleted : Fragment() {

    val viewModel: QuizCompletedViewModel by viewModels()
    private lateinit var binding: FragmentQuizCompletedBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_quiz_completed, container, false)
        binding = FragmentQuizCompletedBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val sound = MediaPlayer.create(requireActivity(),R.raw.completed_game)
        sound.start()

        binding.viewModel = viewModel

        mainActivity = requireActivity() as MainActivity

        // Load Interstitial Ad
        if(!mainActivity.isInterstitialAdsLoaded())
            mainActivity.loadInterstitialAds()

        viewModel.correctAnswers.observe(viewLifecycleOwner,{
            displayPercentage(it)
        })

        viewModel.playAgain.observe(viewLifecycleOwner,{
            if(it)
                findNavController().navigate(QuizCompletedDirections.actionQuizCompletedToQuizFragment())
        })

        viewModel.quitQuiz.observe(viewLifecycleOwner,{
            if(it)
                findNavController().navigate(QuizCompletedDirections.actionQuizCompletedToNavHome())
        })

        return binding.root
    }

    private fun displayPercentage(x: Int){
        CoroutineScope(Dispatchers.IO).launch{
            delay(600)
            withContext(Dispatchers.Main){
                binding.resultProgress.progress = x.getPercentageValue(10)
                withContext(Dispatchers.IO){
                    delay(1000)
                    withContext(Dispatchers.Main){
                        showInterstitialAds()
                    }
                }
            }
        }
    }

    private fun showInterstitialAds(){
        if(mainActivity.isInterstitialAdsLoaded())
            mainActivity.showInterstitialAds()
    }
}