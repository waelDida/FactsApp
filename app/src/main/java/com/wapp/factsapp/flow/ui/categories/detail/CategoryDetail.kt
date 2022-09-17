package com.wapp.factsapp.flow.ui.categories.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.adapter.*
import com.wapp.factsapp.databinding.FragmentCategoryDetailBinding
import com.wapp.factsapp.flow.MainActivity
import com.wapp.factsapp.utils.CLICK_NUMBER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetail : Fragment() {
    val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_category_detail, container, false)
        val binding = FragmentCategoryDetailBinding.inflate(inflater)

        val mainActivity = requireActivity() as MainActivity

        // Load Interstitial Ad
        if(!mainActivity.isInterstitialAdsLoaded())
            mainActivity.loadInterstitialAds()


        val adapter = FactsAdapter(FactListener {
            if(viewModel.adsClicks < CLICK_NUMBER){
                viewModel.adsClicks++
                viewModel.setAdsClick(viewModel.adsClicks)
                findNavController().navigate(CategoryDetailDirections.actionCategoryDetailToFactDetail(it.id))
            }
            else{
                viewModel.adsClicks = 0
                viewModel.setAdsClick(viewModel.adsClicks)
                if(mainActivity.isInterstitialAdsLoaded())
                    mainActivity.showInterstitialAds()
                else
                    findNavController().navigate(CategoryDetailDirections.actionCategoryDetailToFactDetail(it.id))
            }
        }, ShareListener {
            Toast.makeText(requireActivity(),"onShare clicked",Toast.LENGTH_SHORT).show()
        })
        viewModel.factsByCategory.observe(viewLifecycleOwner,{
            if(it.isNotEmpty())
                adapter.submitList(it)
            else{
                binding.factByCategoryRecycler.visibility = View.GONE
                binding.noDataView.visibility = View.VISIBLE
            }
        })

        binding.factByCategoryRecycler.adapter = adapter

        return binding.root
    }
}