package com.wapp.factsapp.flow.ui.categories.category

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.FragmentCategoriesBinding
import com.wapp.factsapp.models.Category
import com.wapp.factsapp.models.User
import com.wapp.factsapp.utils.displayMessage
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesFragment : Fragment() {

    val viewModel: CategoriesViewModel by viewModels()
    private lateinit var adapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        inflater.inflate(R.layout.fragment_categories, container, false)
        val binding = FragmentCategoriesBinding.inflate(inflater)
        adapter = CategoriesAdapter(CategoryListener {
            if(it.isLocked)
                showUnlockDialog(viewModel.user,it)
            else
                findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToCategoryDetail(it.name))


        })

        viewModel.categories.observe(viewLifecycleOwner,{
            adapter.submitList(it)
        })

        viewModel.unlockWithSuccess.observe(viewLifecycleOwner,{
            if(it == false)
                displayMessage(binding.categoryLayout,getString(R.string.no_enough_coins))
        })

        binding.categoryRecycler.setHasFixedSize(true)
        binding.categoryRecycler.adapter = adapter

        return binding.root
    }

    private fun showUnlockDialog(user: User, category: Category){
        val dialog = Dialog(requireContext())
        val details = getString(R.string.formatted_unlock_detail, user.coins)
        val unlockButtonText = getString(R.string.formatted_unlock_button,category.value)
        dialog.setContentView(R.layout.unlock_dialog)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.findViewById<TextView>(R.id.unlock_text_details).text = details
        val gotItButton = dialog.findViewById<Button>(R.id.buy_button)
        val closeBtn = dialog.findViewById<ImageView>(R.id.close)
        gotItButton.apply {
            text = unlockButtonText
        }.setOnClickListener {
            viewModel.unlockTopic(user,category)
            dialog.dismiss()
        }
        closeBtn.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()
    }
}