package com.wapp.factsapp.flow.ui.home

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.adapter.*
import com.wapp.factsapp.databinding.FragmentHomeBinding
import com.wapp.factsapp.flow.MainActivity
import com.wapp.factsapp.utils.CLICK_NUMBER
import com.wapp.factsapp.utils.getFirstName
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         inflater.inflate(R.layout.fragment_home, container, false)
        val binding = FragmentHomeBinding.inflate(inflater)

        val mainActivity = requireActivity() as MainActivity

        // Load Interstitial Ad
        if(!mainActivity.isInterstitialAdsLoaded())
            mainActivity.loadInterstitialAds()

        val adapter = FactsAdapter(FactListener{
            if(viewModel.adsClicks < CLICK_NUMBER){
                viewModel.adsClicks++
                viewModel.setAdsClick(viewModel.adsClicks)
                findNavController().navigate(HomeFragmentDirections.actionNavHomeToFactDetail(it.id))
            }
            else{
                viewModel.adsClicks = 0
                viewModel.setAdsClick(viewModel.adsClicks)
                if(mainActivity.isInterstitialAdsLoaded())
                    mainActivity.showInterstitialAds()
                else
                    findNavController().navigate(HomeFragmentDirections.actionNavHomeToFactDetail(it.id))
            }

        },ShareListener {
            shareTheFact(it.shortDescription)
        })

        viewModel.facts.observe(viewLifecycleOwner,{
            binding.homeSwipe.isRefreshing = false
            if(it.isNotEmpty())
                adapter.submitList(it)
            else{
                binding.factRecycler.visibility = View.GONE
                binding.noDataView.visibility = View.VISIBLE
            }
        })

        viewModel.currentUser.observe(viewLifecycleOwner,{
            it?.let {
                if(it.coins == 0){
                    showWelcomeDialog(it.name)
                    viewModel.attributeTheGiftCoins()
                }
            }
        })

        binding.homeSwipe.setOnRefreshListener {
            viewModel.getFacts()
        }

        binding.factRecycler.adapter = adapter

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

    private fun showWelcomeDialog(userName: String){
        val dialog = Dialog(requireContext())
        val title = getString(R.string.formatted_welcome_text, userName.getFirstName())
        dialog.setContentView(R.layout.welcome_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<TextView>(R.id.title_text).text = title
        val gotItButton = dialog.findViewById<Button>(R.id.got_it_button)
        gotItButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(true)
        dialog.show()
    }
}