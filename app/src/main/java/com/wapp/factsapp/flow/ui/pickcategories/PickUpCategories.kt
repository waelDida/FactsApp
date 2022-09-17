package com.wapp.factsapp.flow.ui.pickcategories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentPickUpCategoriesBinding
import com.wapp.factsapp.utils.displayMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PickUpCategories : Fragment(),PickUpCategoryAdapter.OnCategoryClickListener {

    val viewModel: PickUpViewModel by viewModels()
    private lateinit var binding: FragmentPickUpCategoriesBinding
    private lateinit var adapter:PickUpCategoryAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inflater.inflate(R.layout.fragment_pick_up_categories, container, false)
        binding = FragmentPickUpCategoriesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        adapter = PickUpCategoryAdapter(this)
        viewModel.pickUpList.observe(viewLifecycleOwner,{
            adapter.submit(it)
        })
        setHasOptionsMenu(true)

        binding.pickCategoryRecycler.adapter = adapter

        return binding.root
    }

    override fun onCategoryClick(position: Int) {
        val category = adapter.getList()[position]
        category.isPicked = !category.isPicked
        viewModel.addOrRemoveFromPickUpList(category)
        adapter.apply {
            updateList(category)
            notifyItemChanged(position)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            if(viewModel.categoriesNameList.isEmpty())
                displayMessage(binding.pickUpCategoriesLayout,getString(R.string.select_category))
            else
                viewModel.updatePickUpList(viewModel.categoriesNameList)
        }
        return super.onOptionsItemSelected(item)
    }
}