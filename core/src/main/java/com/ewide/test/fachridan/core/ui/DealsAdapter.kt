package com.ewide.test.fachridan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.fachridan.core.databinding.ItemListDealBinding
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.utils.convertToPrice
import com.ewide.test.fachridan.core.utils.loadGameImage
import com.ewide.test.fachridan.core.utils.showStrikeThrough

class DealsAdapter(private val onItemClick: (Deal) -> Unit) :
    PagingDataAdapter<Deal, DealsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemListDealBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(deal: Deal) {
            with(binding) {
                tvTitle.text = deal.title
                tvNormalPrice.apply {
                    text = deal.normalPrice.convertToPrice()
                    showStrikeThrough()
                }
                tvSalePrice.text = deal.salePrice.convertToPrice()
                ivGame.loadGameImage(deal.thumb)
                itemView.setOnClickListener { onItemClick(deal) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListDealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deal = getItem(position)
        deal?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Deal>() {
            override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
                return oldItem == newItem
            }
        }
    }
}