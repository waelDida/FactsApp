package com.wapp.factsapp.flow.ui.quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentStartBinding


class StartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        inflater.inflate(R.layout.fragment_start, container, false)
        val binding = FragmentStartBinding.inflate(inflater)

        binding.play.setOnClickListener {
            findNavController().navigate(StartFragmentDirections.actionStartFragmentToQuizFragment())
        }
        return binding.root
    }
}