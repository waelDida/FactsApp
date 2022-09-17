package com.wapp.factsapp.flow.ui.factsdetails

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentFactDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FactDetail : Fragment() {

    val viewModel: FactsDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

       inflater.inflate(R.layout.fragment_fact_detail, container, false)
        val binding = FragmentFactDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.shareFact.observe(viewLifecycleOwner,{
            it?.let {
                shareTheFact(it.shortDescription)
                viewModel.onShareClicked()
            }
        })

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        return binding.root
    }

    private fun shareTheFact(fact:String){
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, fact)
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }


}