package com.ewide.test.fachridan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.fachridan.core.databinding.ItemListSearchBinding
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.utils.loadImage

class SearchAdapter(private val onItemClick: (Deal) -> Unit) :
    ListAdapter<Deal, SearchAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemListSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(deal: Deal) {
            with(binding) {
                itemSearchTvTitle.text = deal.title
                itemSearchIvGame.loadImage(deal.thumb)
                itemView.setOnClickListener { onItemClick(deal) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deal = getItem(position)
        deal?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Deal>() {
            override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
                return oldItem.gameId == newItem.gameId
            }

            override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
                return oldItem.gameId == newItem.gameId
            }
        }
    }
}