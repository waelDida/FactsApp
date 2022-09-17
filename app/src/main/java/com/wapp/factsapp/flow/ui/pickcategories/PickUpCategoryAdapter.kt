package com.wapp.factsapp.flow.ui.pickcategories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wapp.factsapp.R
import com.wapp.factsapp.databinding.CategoryPickItemBinding
import com.wapp.factsapp.models.Category

class PickUpCategoryAdapter(private val onCategoryClickListener: OnCategoryClickListener) :RecyclerView.Adapter<PickUpCategoryAdapter.ViewHolder>() {

    private var list = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_pick_item, parent, false)
        val binding = CategoryPickItemBinding.bind(view)
        return ViewHolder(view, binding, onCategoryClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submit(list: List<Category>) {
        this.list = list.toMutableList()
    }

    class ViewHolder(itemView: View, val binding: CategoryPickItemBinding, var onCategoryClickListener: OnCategoryClickListener)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            onCategoryClickListener.onCategoryClick(bindingAdapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getList() = list

    fun updateList(category: Category) {
        list.removeAt(category.index)
        list.add(category.index, category)
    }

    interface OnCategoryClickListener {
        fun onCategoryClick(position: Int)
    }
}