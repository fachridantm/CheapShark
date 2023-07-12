package com.ewide.test.fachridan.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.fachridan.core.databinding.ItemListStoreBinding
import com.ewide.test.fachridan.core.domain.model.Store
import com.ewide.test.fachridan.core.utils.convertToPrice
import com.ewide.test.fachridan.core.utils.loadImage
import com.ewide.test.fachridan.core.utils.showStrikeThrough

class StoresAdapter(private val onItemClick: (Store) -> Unit) :
    PagingDataAdapter<Store, StoresAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemListStoreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(store: Store) {
            with(binding) {
                itemStoreTvTitle.text = store.storeName
                itemStoreTvNormalPrice.apply {
                    text = store.normalPrice.convertToPrice()
                    showStrikeThrough()
                }
                itemStoreTvSalePrice.text = store.salePrice.convertToPrice()
                itemStoreIvLogo.loadImage(store.storeLogo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val deal = getItem(position)
        deal?.let { holder.bind(it) }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Store>() {
            override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem.storeId == newItem.storeId
            }

            override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
                return oldItem == newItem
            }
        }
    }
}