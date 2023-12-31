package com.technical.test.fachridan.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.technical.test.fachridan.R
import com.technical.test.fachridan.core.data.source.Resource
import com.technical.test.fachridan.core.domain.model.Deal
import com.technical.test.fachridan.core.domain.model.GameDetails
import com.technical.test.fachridan.core.domain.model.Store
import com.technical.test.fachridan.core.ui.StoresAdapter
import com.technical.test.fachridan.core.utils.Constants.EXTRA_DATA
import com.technical.test.fachridan.core.utils.Event
import com.technical.test.fachridan.core.utils.convertToPrice
import com.technical.test.fachridan.core.utils.getParcelableData
import com.technical.test.fachridan.core.utils.loadImage
import com.technical.test.fachridan.core.utils.showToast
import com.technical.test.fachridan.core.utils.toDate
import com.technical.test.fachridan.databinding.ActivityGameDetailsBinding
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
        initFavorite()
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
        binding.rvGameDealsDetails.apply {
            setHasFixedSize(true)
            adapter = storesAdapter
        }
    }

    private fun initFavorite() {
        viewModel.getFavoriteGames().observe(this@GameDetailsActivity) { deals ->
            val data = intent.getParcelableData<Deal>(EXTRA_DATA)
            data?.let {
                val isFavorite = deals.any { game -> game.gameId == data.gameId }
                setFavoriteIcon(isFavorite)
                setFavoriteOnClickListener(isFavorite, it)
            }
        }
    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        binding.toolbarGameDetails.menu.findItem(R.id.action_add_favorite).apply {
            if (isFavorite) {
                icon = getDrawable(R.drawable.ic_favorite)
            } else {
                icon = getDrawable(R.drawable.ic_unfavorite)
            }
        }
    }

    private fun setFavoriteOnClickListener(isFavorite: Boolean, data: Deal) {
        binding.toolbarGameDetails.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_add_favorite -> {
                    viewModel.apply {
                        setFavoriteGames(data, !isFavorite, this@GameDetailsActivity)
                        toast.observe(this@GameDetailsActivity, ::showToast)
                    }
                    true
                }

                else -> false
            }
        }
    }

    private fun showToast(eventMessage: Event<String>) {
        val message = eventMessage.getContentIfNotHandled() ?: return
        message.showToast(this)
    }

    private fun initAction() {
        binding.toolbarGameDetails.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun onItemClicked(store: Store) {
        getString(R.string.item_clicked, "storeId ${store.storeId}").showToast(this)
    }
}