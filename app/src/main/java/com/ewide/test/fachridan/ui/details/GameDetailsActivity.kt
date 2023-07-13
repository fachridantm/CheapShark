package com.ewide.test.fachridan.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ewide.test.fachridan.R
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.domain.model.GameDetails
import com.ewide.test.fachridan.core.domain.model.Store
import com.ewide.test.fachridan.core.ui.StoresAdapter
import com.ewide.test.fachridan.core.utils.Constants.EXTRA_DATA
import com.ewide.test.fachridan.core.utils.convertToPrice
import com.ewide.test.fachridan.core.utils.getParcelableData
import com.ewide.test.fachridan.core.utils.loadImage
import com.ewide.test.fachridan.core.utils.showToast
import com.ewide.test.fachridan.core.utils.toDate
import com.ewide.test.fachridan.databinding.ActivityGameDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameDetailsBinding
    private val viewModel: GameDetailsViewModel by viewModels()
    private val storesAdapter: StoresAdapter by lazy { StoresAdapter(::onItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        initAction()
    }

    private fun initData() {
        val data = intent.getParcelableData<Deal>(EXTRA_DATA)
        data?.let { deal ->
            viewModel.getGameDetails(deal.gameId).observe(this) {
                when (it) {
                    is Resource.Loading -> {
                        showLoading(true)
                    }

                    is Resource.Success -> {
                        bindData(it.data)
                        showLoading(false)
                    }

                    is Resource.Error -> {
                        showLoading(false)
                        it.message?.showToast(this)
                    }
                }

            }
        }

    }

    private fun bindData(data: GameDetails?) {
        with(binding) {
            data?.let { gameDetails ->
                tvGameTitleDetails.text = gameDetails.gameInfo.title
                tvGameSteamAppIdDetails.text =
                    getString(R.string.steam_app_id, gameDetails.gameInfo.steamAppId)
                tvGameCheapestPriceDetails.text = gameDetails.cheapestPrice.price.convertToPrice()
                tvGameEndsDate.text =
                    getString(R.string.ends_date, gameDetails.cheapestPrice.date.toDate())
                ivGameThumbDetails.loadImage(gameDetails.gameInfo.thumb)

                if (data.deals.isEmpty()) {
                    storesAdapter.submitList(emptyList())
                    showIsEmpty(true)
                } else {
                    storesAdapter.submitList(data.deals)
                    showIsEmpty(false)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            pbGameDetails.visibility = if (state) View.VISIBLE else View.GONE
            rvGameDealsDetails.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun showIsEmpty(state: Boolean) {
        binding.apply {
            tvGameDealsEmpty.visibility = if (state) View.VISIBLE else View.GONE
            rvGameDealsDetails.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun initView() {
        with(binding) {
            rvGameDealsDetails.apply {
                setHasFixedSize(true)
                adapter = storesAdapter
            }
        }
    }

    private fun initAction() {
        with(binding) {
            toolbarGameDetails.apply {
                setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_add_favorite -> {

                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    private fun onItemClicked(store: Store) {
        getString(R.string.item_clicked, "storeId ${store.storeId}").showToast(this)
    }
}