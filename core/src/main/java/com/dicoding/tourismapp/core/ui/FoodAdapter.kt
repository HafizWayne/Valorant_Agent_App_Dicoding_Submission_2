package com.dicoding.tourismapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.tourismapp.core.databinding.ItemListTourismBinding
import com.dicoding.tourismapp.core.domain.model.Food

class FoodAdapter : ListAdapter<Food, FoodAdapter.ListViewHolder>(DIFF_CALLBACK) {

    var onItemClick: ((Food) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemListTourismBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class ListViewHolder(private var binding: ItemListTourismBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Food) {
            Glide.with(itemView.context)
                .load(data.fullPortrait)
                .into(binding.ivItemImage)
            binding.tvItemTitle.text = data.displayName
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(getItem(bindingAdapterPosition))
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Food> =
            object : DiffUtil.ItemCallback<Food>() {
                override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                    return oldItem.uuid == newItem.uuid
                }

                override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                    return oldItem == newItem
                }
            }
    }
}