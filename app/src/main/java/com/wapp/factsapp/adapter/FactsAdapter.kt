package com.wapp.factsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wapp.factsapp.databinding.FactItemBinding
import com.wapp.factsapp.models.Fact

class FactsAdapter(val listener: FactListener,
                   private val shareListener: ShareListener): ListAdapter<Fact, FactsAdapter.ViewHolder>(CallbackUtil()) {

    class ViewHolder(private val binding: FactItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: Fact,listener: FactListener,shareListener: ShareListener){
            binding.fact = fact
            binding.factListener = listener
            binding.shareListener = shareListener
            binding.executePendingBindings()
        }
    }

    class CallbackUtil:DiffUtil.ItemCallback<Fact>() {
        override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FactItemBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fact = getItem(position)
        holder.bind(fact,listener,shareListener)
    }
}

class FactListener (val clickListener: (item: Fact) -> Unit){
    fun onClick(fact: Fact) = clickListener(fact)
}

class ShareListener(val shareListener: (item: Fact) -> Unit){
    fun onClick(fact:Fact) = shareListener(fact)
}