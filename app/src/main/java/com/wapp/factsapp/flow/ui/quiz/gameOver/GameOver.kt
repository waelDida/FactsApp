package com.wapp.factsapp.flow.ui.quiz.gameOver

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentGameOverBinding
import com.wapp.factsapp.flow.MainActivity
import com.wapp.factsapp.utils.getPercentageValue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class GameOver : Fragment() {

    val viewModel: GameOverViewModel by viewModels()
    private lateinit var binding: FragmentGameOverBinding
    private lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        inflater.inflate(R.layout.fragment_game_over, container, false)
        binding = FragmentGameOverBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        mainActivity = requireActivity() as MainActivity

        // Load Interstitial Ad
        if(!mainActivity.isInterstitialAdsLoaded())
            mainActivity.loadInterstitialAds()

        val sound = MediaPlayer.create(requireActivity(),R.raw.game_over)
        sound.start()

        viewModel.quizDetails.observe(viewLifecycleOwner,{
            displayPercentage(it.first,it.second)
        })

        viewModel.tryAgain.observe(viewLifecycleOwner,{
            if(it)
                findNavController().navigate(GameOverDirections.actionGameOverToQuizFragment())
        })

        viewModel.quitQuiz.observe(viewLifecycleOwner,{
            if(it)
                findNavController().navigate(GameOverDirections.actionGameOverToNavHome())
        })

        return binding.root
    }

    private fun displayPercentage(x: Int, y:Int){
        CoroutineScope(Dispatchers.IO).launch {
            delay(600)
            withContext(Dispatchers.Main){
                binding.resultProgress.progress = x.getPercentageValue(y)
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