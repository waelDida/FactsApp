package com.wapp.factsapp.flow.ui.bookmarks

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.adapter.*
import com.wapp.factsapp.databinding.FragmentBookmarksBinding
import com.wapp.factsapp.resource.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarksFragment : Fragment() {
    val viewModel: BookmarksViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_bookmarks, container, false)
        val binding = FragmentBookmarksBinding.inflate(inflater)

        val adapter = FactsAdapter(FactListener {
            findNavController().navigate(BookmarksFragmentDirections.actionBookmarksFragmentToFactDetail(it.id))
        }, ShareListener {
            shareTheFact(it.shortDescription)
        })

        viewModel.fetchedData.observe(viewLifecycleOwner,{
            when(it){
                is Resource.Success -> adapter.submitList(it.data.toMutableList())
                else -> {
                    binding.bookmarksRecycler.visibility = View.GONE
                    binding.noDataView.visibility = View.VISIBLE
                }
            }
        })

        binding.bookmarksRecycler.adapter = adapter
        return binding.root
    }

    private fun shareTheFact(fact:String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, fact)
        }
        val shareIntent = Intent.createChooser(intent,null)
        startActivity(shareIntent)
    }
}