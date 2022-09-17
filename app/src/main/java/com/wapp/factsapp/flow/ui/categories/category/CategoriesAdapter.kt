package com.wapp.factsapp.flow.ui.categories.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wapp.factsapp.databinding.CategoryItemBinding
import com.wapp.factsapp.models.Category

class CategoriesAdapter(private val listener: CategoryListener): ListAdapter<Category, CategoriesAdapter.ViewHolder>(
    DiffCallbacks()
) {
    class ViewHolder(private val binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(category:Category,listener: CategoryListener){
            binding.category = category
            binding.listener = listener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category,listener)
    }

    class DiffCallbacks: DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}

class CategoryListener (val clickListener: (item: Category) -> Unit){
    fun onClick(category: Category) = clickListener(category)
}