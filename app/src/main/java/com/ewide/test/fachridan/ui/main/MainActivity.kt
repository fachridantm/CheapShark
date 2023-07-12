package com.ewide.test.fachridan.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagingData
import com.ewide.test.fachridan.R
import com.ewide.test.fachridan.core.data.source.Resource
import com.ewide.test.fachridan.core.domain.model.Deal
import com.ewide.test.fachridan.core.ui.DealsAdapter
import com.ewide.test.fachridan.core.ui.DealsLoadStateAdapter
import com.ewide.test.fachridan.core.utils.showToast
import com.ewide.test.fachridan.databinding.ActivityMainBinding
import com.ewide.test.fachridan.ui.search.SearchResultActivity
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
            when (it) {
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
            toolbarMain.apply {
                val searchView = menu.findItem(R.id.search_view).actionView as SearchView

                searchView.apply {
                    inputType = InputType.TYPE_NULL
                    isIconifiedByDefault = false
                    queryHint = getString(R.string.search_hint)
                    background = getDrawable(R.drawable.bg_transparent)

                    setOnQueryTextFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            startActivity(Intent(context, SearchResultActivity::class.java))
                        }
                    }

                    setOnClickListener {
                        startActivity(Intent(context, SearchResultActivity::class.java))
                    }

                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.action_sort -> {
                            sortHandler()
                            true
                        }

                        R.id.action_favorite -> {
                            // TODO: Intent to Favorite Activity
                            getString(R.string.favorite).showToast(this@MainActivity)
                            true
                        }

                        else -> false
                    }
                }
            }
        }
    }

    private fun sortHandler() {
        val builder = AlertDialog.Builder(this, R.style.AlertDialog).create()
        val view = View.inflate(this, R.layout.view_sort_dialog, null)
        val radioGroup = view.findViewById<RadioGroup>(R.id.rg_sort)
        val btnSubmit = view.findViewById<Button>(R.id.btn_submit)

        btnSubmit.setOnClickListener {
            when (radioGroup.checkedRadioButtonId) {
                R.id.rb_sort_default -> {
                    // TODO: Sorting data by default
                    getString(R.string.sort_default_label).showToast(this)
                }

                R.id.rb_sort_title -> {
                    // TODO: Sorting data by title
                    getString(R.string.sort_title_label).showToast(this)
                }

                R.id.rb_sort_price -> {
                    // TODO: Sorting data by price
                    getString(R.string.sort_price_label).showToast(this)
                }
            }
            builder.dismiss()
        }

        builder.apply {
            setView(view)
            setCancelable(true)
            show()
        }

    }

    private fun onItemClicked(deal: Deal) {
        // TODO: Intent to Detail Activity
        getString(R.string.item_clicked, deal.title).showToast(this)
    }

    override fun onResume() {
        super.onResume()
        binding.toolbarMain.menu.findItem(R.id.search_view).actionView?.clearFocus()
    }
}
