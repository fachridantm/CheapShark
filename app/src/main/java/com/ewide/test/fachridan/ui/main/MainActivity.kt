package com.ewide.test.fachridan.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingData
import com.ewide.test.fachridan.R
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.ui.DealsAdapter
import com.ewide.test.fachridan.core.ui.DealsLoadStateAdapter
import com.ewide.test.fachridan.core.utils.showToast
import com.ewide.test.fachridan.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val dealsAdapter: DealsAdapter by lazy { DealsAdapter(::onItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initData() {
        viewModel.getListOfDeals().observe(this) {
            when(it) {
                is Resource.Loading -> {
                    showLoading(true)
                }

                is Resource.Success -> {
                    showLoading(false)
                    if (it.data != null) {
                        dealsAdapter.submitData(lifecycle, it.data as PagingData<Deal>)
                    } else {
                        dealsAdapter.submitData(lifecycle, PagingData.empty())
                        getString(R.string.data_is_empty).showToast(this)
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
            pbMain.visibility = if (state) View.VISIBLE else View.GONE
            rvDeals.visibility = if (state) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun initView() {
        with(binding) {
            rvDeals.apply {
                setHasFixedSize(true)
                adapter = dealsAdapter.withLoadStateFooter(
                    footer = DealsLoadStateAdapter { dealsAdapter.retry() }
                )
            }
        }
    }

    private fun onItemClicked(deal: Deal) {
        getString(R.string.item_clicked, deal.title).showToast(this)
    }
}
