package com.technical.test.fachridan.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.technical.test.fachridan.core.domain.model.Deal
import com.technical.test.fachridan.core.ui.GamesAdapter
import com.technical.test.fachridan.core.utils.Constants.EXTRA_DATA
import com.technical.test.fachridan.di.FavoriteModuleDependencies
import com.technical.test.fachridan.favorite.databinding.ActivityFavoriteBinding
import com.technical.test.fachridan.favorite.di.DaggerFavoriteComponent
import com.technical.test.fachridan.favorite.di.ViewModelFactory
import com.technical.test.fachridan.ui.details.GameDetailsActivity
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private val gamesAdapter: GamesAdapter by lazy { GamesAdapter(::onItemClicked) }

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initData() {
        viewModel.getFavoriteGames().observe(this) {
            showLoading(true)
            if (!it.isNullOrEmpty()) {
                showLoading(false)
                showIsEmpty(false)
            } else {
                showIsEmpty(true)
                showLoading(false)
            }
            gamesAdapter.submitList(it)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            pbFavorite.visibility = if (state) View.VISIBLE else View.GONE
            rvFavorite.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun showIsEmpty(state: Boolean) {
        binding.apply {
            tvFavoriteDataNotFound.visibility = if (state) View.VISIBLE else View.GONE
            rvFavorite.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun initView() {
        with(binding) {
            rvFavorite.apply {
                adapter = gamesAdapter
                setHasFixedSize(true)
            }
            toolbarFavorite.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }
    }

    private fun onItemClicked(deal: Deal) {
        val intentToDetail = Intent(this, GameDetailsActivity::class.java)
        intentToDetail.putExtra(EXTRA_DATA, deal)
        startActivity(intentToDetail)
    }
}