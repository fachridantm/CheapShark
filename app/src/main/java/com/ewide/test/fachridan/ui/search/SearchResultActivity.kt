package com.ewide.test.fachridan.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.ui.SearchAdapter
import com.ewide.test.fachridan.core.utils.Constants
import com.ewide.test.fachridan.core.utils.showToast
import com.ewide.test.fachridan.databinding.ActivitySearchResultBinding
import com.ewide.test.fachridan.ui.details.GameDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchResultBinding
    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter(::onItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initAction()
    }

    private fun initAction() {
        with(binding) {
            toolbarSearch.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
            searchView.setOnQueryTextListener(object :
                SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchHandler(newText)
                    return true
                }
            })
        }
    }

    private fun searchHandler(newText: String?) {
        val query = newText ?: ""
        searchViewModel.getSearchDeals(query).observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                }

                is Resource.Success -> {
                    showLoading(false)
                    if (it.data.isNullOrEmpty()) {
                        searchAdapter.submitList(emptyList())
                        showIsEmpty(true)
                    } else if (query.isEmpty()) {
                        searchAdapter.submitList(emptyList())
                    } else {
                        searchAdapter.submitList(it.data)
                        showIsEmpty(false)
                    }
                }

                is Resource.Error -> {
                    showLoading(false)
                    it.message?.showToast(this)
                }
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            pbSearch.visibility = if (state) View.VISIBLE else View.GONE
            rvSearchResult.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun showIsEmpty(state: Boolean) {
        binding.apply {
            tvSearchDataNotFound.visibility = if (state) View.VISIBLE else View.GONE
            rvSearchResult.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun initView() {
        with(binding) {
            searchView.requestFocus()
            rvSearchResult.apply {
                adapter = searchAdapter
                setHasFixedSize(true)
            }
        }
    }

    private fun onItemClicked(deal: Deal) {
        val intentToDetail = Intent(this, GameDetailsActivity::class.java)
        intentToDetail.putExtra(Constants.EXTRA_DATA, deal)
        startActivity(intentToDetail)
    }

    override fun onResume() {
        super.onResume()
        binding.searchView.requestFocus()
    }

}